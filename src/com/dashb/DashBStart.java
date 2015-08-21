package com.dashb;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.dashb.framework.database.PersistenceManager;

public class DashBStart {

        static final boolean SSL = System.getProperty("ssl") != null;
        static final int PORT = Integer.parseInt(System.getProperty("port", SSL? "8443" : "8080"));

        public Logger logger = Logger.getLogger(DashBStart.class);

        public static void main(String[] args) throws Exception {
	        Logger logger = Logger.getLogger(DashBStart.class);
	        //String logFilePath = System.getProperty("prodlibPath")+ "\\conf\\properties\\log4j.properties";
	        ///prodlib/DashB/conf/properties
	        String logFilePath = "conf/properties/log4j.properties";
	        PropertyConfigurator.configure(logFilePath);
	        PersistenceManager pesistenceManager = new PersistenceManager();
	 
	        logger.info("Started Main.class");
	
	        // Configure SSL.
	        final SslContext sslCtx;
	        if (SSL) {
	            SelfSignedCertificate ssc = new SelfSignedCertificate();
	            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
	        } else {
	            sslCtx = null;
	        }
	
	        // Configure the server.
	        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap b = new ServerBootstrap();
	            b.option(ChannelOption.SO_BACKLOG, 1024);
	            b.group(bossGroup, workerGroup)
	                    .channel(NioServerSocketChannel.class)
	                    .handler(new LoggingHandler(LogLevel.INFO))
	                    .childHandler(new HttpServerInitializer(sslCtx,pesistenceManager));
	
	            Channel ch = b.bind(new InetSocketAddress(PORT)).sync().channel();
	
	            System.err.println("Open your web browser and navigate to " +(SSL? "https" : "http") + "://127.0.0.1:" + PORT + '/');
	
	            ch.closeFuture().sync();
	        } finally {
	            bossGroup.shutdownGracefully();
	            workerGroup.shutdownGracefully();
	        }
        }

}
