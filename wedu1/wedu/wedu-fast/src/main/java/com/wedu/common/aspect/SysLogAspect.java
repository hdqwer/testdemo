package com.wedu.common.aspect;

import com.wedu.common.annotation.SysLog;
import com.google.gson.Gson;
import com.wedu.common.utils.HttpContextUtils;
import com.wedu.common.utils.IPUtils;
import com.wedu.modules.sys.entity.SysLogEntity;
import com.wedu.modules.sys.entity.SysUserEntity;
import com.wedu.modules.sys.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * 系统日志，切面处理类
 *
 * @author wedu
 */
@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	//定义了一个切点logPointCut这个切点匹配所有带@SysLog注解的方法
	@Pointcut("@annotation(com.wedu.common.annotation.SysLog)")
	public void logPointCut() {

	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		//获取被拦截方法的签名
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		//然后进一步获取到method方法
		Method method = signature.getMethod();
		//这个对象是用来储存日志信息的
		SysLogEntity sysLog = new SysLogEntity();
		//从被拦截的方法上获取SysLog注解
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//提取value的值，将value值存储到SysLogEntity类的Operation属性上
			sysLog.setOperation(syslog.value());
		}

		//请求的方法名
		//获取被拦截对象的全类名
		String className = joinPoint.getTarget().getClass().getName();
		//获取被拦截方法的名称
		String methodName = signature.getName();
		//拼接
		sysLog.setMethod(className + "." + methodName + "()");

		//获取被拦截方法的参数列表
		Object[] args = joinPoint.getArgs();
		try{
			//使用Gson库将参数列表转换为json格式
			String params = new Gson().toJson(args);
			//将params设置到SysLogEntity类的Params属性中
			sysLog.setParams(params);
		}catch (Exception e){

		}

		//获取request，代表HTTP请求
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//从请求对象中获取ip地址并设置到SysLogEntity对象中
		sysLog.setIp(IPUtils.getIpAddr(request));

		//首先获取用户身份信息并把它强制转换为SysUserEntity实体类，并从实体类中获取用户名。将用户名存储到SysUserEntity的
		//Username属性中
		String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
		sysLog.setUsername(username);
		//拦截方法的执行时间
		sysLog.setTime(time);
		//设置日志的创建时间为当前时间
		sysLog.setCreateDate(new Date());
		//保存系统日志
		sysLogService.save(sysLog);
	}
}
