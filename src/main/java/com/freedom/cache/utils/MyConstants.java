package com.freedom.cache.utils;

/**
 * 
 * @author zhiqiang.liu
 * @2016年1月1日
 * @QQ: 837500869
 */
public class MyConstants {
	//// https://dev.mysql.com/doc/internals/en/sending-more-than-16mbyte.html
	// public static final int MAX_PACKET_LENGTH = 16777215;

	// 解析配置文件使用
	public static String CONFIG_FILE = System.getProperty("myredisProperties", "src/main/resources/myredis.properties");

	// netty使用
	public static String NETTY_PORT = "netty_server_port";
	public static String NETTY_BOSS = "netty_boss_number";
	public static String NETTY_WORKER = "netty_worker_number";

	// zookeeper使用
	public static String ZK_SERVERS = "zk_servers";
	public static String ZK_NAMESPACE = "my_redis_cluster";
	public static String ZK_NAMESPACE_TASKS = "/" + ZK_NAMESPACE + "/tasks";

	// handler使用

}
