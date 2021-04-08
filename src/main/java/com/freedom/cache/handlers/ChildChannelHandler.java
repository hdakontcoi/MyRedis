package com.freedom.cache.handlers;

/**
 * 
 * @author zhiqiang.liu
 * @2016年1月1日
 *
 */
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.freedom.cache.utils.LoggerUtils;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
	private static final Logger logger = LogManager.getLogger(ChildChannelHandler.class);

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		// 要在这里加上所有的处理句柄
		LoggerUtils.debug(logger, "initChannel invoked...");
		LoggerUtils.debug(logger, "channel:" + channel.getClass().toGenericString());

		ChannelPipeline cp = channel.pipeline();// 下面的次序不能变
		//cp.addLast(MyConstants.FIXED_LENGTH_HANDLER, new FixedLengthHandler());
		//cp.addLast(MyConstants.GREETING_PACKET_HANDLER, new GreetingPacketResultHandler());
		//cp.addLast(MyConstants.AUTHEN_RESULT_HANDLER, new AuthenticateResultHandler());
		//cp.addLast(MyConstants.FETCH_BINLOG_NAMEPOSITION_RESULT_HANDLER, new FetchBinlogNamePositionResultHandler());
		//cp.addLast(MyConstants.FETCH_BINLOG_CHECKSUM_RESULT_HANDLER, new FetchBinlogChecksumResultHandler());
		//cp.addLast(MyConstants.LOG_EVENT_PARSE_HANDLER, new BinlogEventParseHandler());
		//cp.addLast(MyConstants.CHANNEL_TRIGGER_DE_REGISTERED, new ChannelDeRegisterHandler());
		// 到这里就可以存起来
		
	}

}
