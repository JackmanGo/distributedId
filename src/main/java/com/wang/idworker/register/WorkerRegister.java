package com.wang.idworker.register;

/**
 * Worker注册
 * @author wangxi
 * @since 1.1.0
 */
public interface WorkerRegister {

	/**
	 * 注册workerId
	 * @return 注册成功的worker id
	 */
	long register();
	
	/**
	 * 退出注册
	 */
	void logout();
}
