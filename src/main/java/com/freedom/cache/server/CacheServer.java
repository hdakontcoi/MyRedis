package com.freedom.cache.server;

/**
 * 
 * @author zhiqiang.liu
 * @2016年2月24日
 * @email: 837500869@qq.com
 */
import com.freedom.cache.netty.NettyServer;

public class CacheServer {
	public static void main(String[] args) {
		// 主函数入口
		// 1)启动netty
		NettyServer.start();
	}
}
