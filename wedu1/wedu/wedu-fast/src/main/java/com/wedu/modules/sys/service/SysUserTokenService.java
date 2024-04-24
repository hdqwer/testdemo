

package com.wedu.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wedu.common.utils.R;
import com.wedu.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 *
 * @author wedu
 */
public interface SysUserTokenService extends IService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 为微信小程序端生成token
	 * @param userId 用户ID
	 * @return R
	 */
	R createTokenForMiniProgram(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);

}
