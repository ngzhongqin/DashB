package com.dashb;

import com.dashb.framework.database.PersistenceManager;
import com.dashb.router.RouteInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    private final SslContext sslCtx;
    private PersistenceManager pesistenceManager;

    public HttpServerInitializer(SslContext sslCtx, PersistenceManager pesistenceManager) {
        this.sslCtx = sslCtx;
        this.pesistenceManager=pesistenceManager;
    }

    @Override
    public void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }

        p.addLast("codec",new HttpServerCodec());
        p.addLast("aggregator", new HttpObjectAggregator(512 * 1024));

        p.addLast(new RouteInboundHandler(pesistenceManager));
    }
}
