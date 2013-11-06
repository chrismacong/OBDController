package com.fix.obd.tcp;

/**
 * @title ITCPDataReceiver.java
 * @package com.fix.obd.tcp
 * @description 接口：车载终端数据接收器
 * @author youme.ma
 * @date 2013-8-16 下午4:06:10
 * @version V1.0
 */
public interface ITCPDataCommunicator {
	/**
	 * 启动服务
	 */
	public void start();

	/**
	 * 关闭服务
	 */
	public void stop();

	/**
	 * 获取服务端口号
	 */
	public int getServerPort();

	/**
	 * 最大连接数
	 */
	public int getMaxConnNum();

	/**
	 * 超时时长
	 */
	public int getTimeout();

	/**
	 * 服务运行状态
	 */
	public int getStatus();

}
