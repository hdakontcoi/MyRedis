package com.freedom.cache.netty;

/**
 * 
 * @author zhiqiang.liu
 * @2016年1月1日
 * @qq:837500869
 */
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.freedom.cache.handlers.ChildChannelHandler;
import com.freedom.cache.utils.LoggerUtils;
import com.freedom.cache.utils.MyProperties;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	private static final Logger logger = LogManager.getLogger(NettyServer.class);
	private static int CPU = Runtime.getRuntime().availableProcessors();

	static {
		new Thread(new Runnable() {
			@Override
			public void run() {
				MyProperties p = MyProperties.getInstance();
				int port = p.getNetty_port();
				int boss = p.getNetty_boss();
				int worker = p.getNetty_worker();
				LoggerUtils.info(logger, "Netty -cpu:" + CPU + " port:" + port + " boss:" + boss + " worker:" + worker);
				EventLoopGroup bossGroup = new NioEventLoopGroup(boss);
				// 这里要做一些变动，使用自己的类，方便干预一些行为
				EventLoopGroup workerGroup = new NioEventLoopGroup(worker);
				try {
					ServerBootstrap b = new ServerBootstrap();
					b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
							.option(ChannelOption.SO_BACKLOG, 4096).childHandler(new ChildChannelHandler());
					// 绑定端口，同步等待成功
					ChannelFuture f = b.bind(port).sync();
					// 等待服务端监听端口关闭
					LoggerUtils.info(logger, "netty server start ok.");
					f.channel().closeFuture().sync();
				} catch (Exception e) {
					LoggerUtils.error(logger, e.toString());
				} finally {// 优雅退出，释放线程资源
					bossGroup.shutdownGracefully();
					workerGroup.shutdownGracefully();
					System.exit(-1);// netty都不在了，退出吧
					LoggerUtils.info(logger, "Netty Server exit...");
				}
			}
		}).start();
	}

	public synchronized static void start() {
		// 仅仅为了启动netty 线程,保证只会启动1次
	}
}
