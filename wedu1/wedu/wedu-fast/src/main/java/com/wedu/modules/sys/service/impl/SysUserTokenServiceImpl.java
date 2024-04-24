

package com.wedu.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wedu.common.utils.R;
import com.wedu.modules.sys.dao.SysUserTokenDao;
import com.wedu.modules.sys.entity.SysUserTokenEntity;
import com.wedu.modules.sys.entity.dto.TokenDto;
import com.wedu.modules.sys.oauth2.TokenGenerator;
import com.wedu.modules.sys.service.SysUserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;


	//生成令牌的方法
	@Override
	public R createToken(long userId) {
		//生成令牌
		String token = this.generateToken(userId);
		//将令牌以及过期时间放入响应对象R中
		R r = R.ok().put("token", token).put("expire", EXPIRE);
		return r;
	}

	//使用了TokenDto（数据传输对象）来封装令牌及其过期时间，而不是直接在响应对象中放入键值对
	@Override
	public R createTokenForMiniProgram(long userId) {
		String token = this.generateToken(userId);
		TokenDto tokenDto = new TokenDto();
		tokenDto.setToken(token);
		tokenDto.setExpire(EXPIRE);
		R r = R.ok().put("data", tokenDto);
		return r;
	}

	private String generateToken(long userId){
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserTokenEntity tokenEntity = this.getById(userId);
		if(tokenEntity == null){
			//如果没有生成，创建一个新的SysUserTokenEntity实体类
			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			this.save(tokenEntity);
		}else{
			//如果已经有令牌，更新令牌
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//更新token
			this.updateById(tokenEntity);
		}
		return token;
	}

	@Override
	public void logout(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//修改token
		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		this.updateById(tokenEntity);
	}
}
