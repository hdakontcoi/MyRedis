package com.freedom.cache.server;

/**
 * 
 * @author zhiqiang.liu
 * @2016年2月24日
 * @email: 837500869@qq.com
 */
import com.freedom.cache.netty.NettyServer;

public class CacheServer {//基于redis-2.6.14
	public static void main(String[] args) {
		// 主函数入口
		// 1)启动netty
		NettyServer.start();
	}
}
