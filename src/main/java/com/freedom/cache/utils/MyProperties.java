package com.freedom.cache.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
/**
 * 
 * @author zhiqiang.liu
 * @2016年1月1日
 * @QQ: 837500869
 */
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;

public class MyProperties {
	private static final Logger logger = LogManager.getLogger(MyProperties.class);

	// 以下为全局需要

	private static MyProperties myProperties = null;// 全局单例变量，一开始就存在

	static {// 静态块里，只加载一次
		Properties props = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(MyConstants.CONFIG_FILE));
			// Thread.currentThread().getContextClassLoader().getResourceAsStream(MyConstants.CONFIG_FILE);
			props.load(in);
			in.close();
		} catch (Exception e) {
			LoggerUtils.error(logger, "fail to read config file " + MyConstants.CONFIG_FILE);
			System.exit(-1);
		}
		// 读取值
		LoggerUtils.debug(logger, "succeed to read config file " + MyConstants.CONFIG_FILE);
		// netty
		int netty_port = Integer.parseInt(props.getProperty(MyConstants.NETTY_PORT, "10000"));
		int netty_boss = Integer.parseInt(props.getProperty(MyConstants.NETTY_BOSS, "1"));
		netty_boss = 1;
		int netty_worker = Integer.parseInt(props.getProperty(MyConstants.NETTY_WORKER, "1").trim());// the
																										// same
																										// as
																										// redis
		netty_worker = 1;
		// netty_worker = Runtime.getRuntime().availableProcessors() *
		// netty_worker;// 跟cpu核数一致
		// zk
		String zk_servers = props.getProperty(MyConstants.ZK_SERVERS);

		props = null;
		// 构造新的对象
		myProperties = new MyProperties(netty_port, netty_boss, netty_worker, zk_servers);

	}

	public static MyProperties getInstance() {
		return myProperties;
	}

	// 私有属性开始//////////////////////////////////////////////////////////////////
	// netty
	private int netty_port;
	private int netty_boss;
	private int netty_worker;
	// zk
	private String zk_servers;

	private MyProperties() {// 私有方法，保证单例

	}

	private MyProperties(int np, int nboss, int nworker, String zks) {

		// used by netty
		this.netty_port = np;
		this.netty_boss = nboss;
		this.netty_worker = nworker;
		this.zk_servers = zks;

	}

	public String getZk_servers() {
		return zk_servers;
	}

	public int getNetty_port() {
		return netty_port;
	}

	public int getNetty_boss() {
		return netty_boss;
	}

	public int getNetty_worker() {
		return netty_worker;
	}

	public String toString() {
		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append(MyConstants.NETTY_PORT).append(": ").append(netty_port).append(" ");
		strBuilder.append(MyConstants.NETTY_BOSS).append(": ").append(netty_boss).append(" ");
		strBuilder.append(MyConstants.NETTY_WORKER).append(": ").append(netty_worker).append(" ");
		strBuilder.append(MyConstants.ZK_SERVERS).append(": ").append(zk_servers).append(" ");
		return strBuilder.toString();
	}

	// 测试
	public static void main(String[] args) {
		// just for test
		MyProperties property = MyProperties.getInstance();
		logger.debug(property.toString());
	}
}
