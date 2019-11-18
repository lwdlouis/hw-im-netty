package com.dml.hw.im.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Louis
 * @version 1.0
 * @since 2019/11/18
 */

@Slf4j
@Component
public class NettyServer1 implements NettyServer{

    @Override
    public void run() {

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();
        server
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) throws Exception {
                        log.info("处理请求。。。");
                    }
                });

        server.attr(AttributeKey.newInstance("boss"), "boss");

        server.handler(new ChannelInitializer<NioServerSocketChannel>() {
            @Override
            protected void initChannel(NioServerSocketChannel channel) throws Exception {
                log.info("服务启动中");
            }
        });


        server.childOption(ChannelOption.SO_KEEPALIVE, true);
        server.childOption(ChannelOption.TCP_NODELAY, true);

        server.bind(17070).addListener(new GenericFutureListener<Future<? super Void>>() {

            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    log.info("启动成功");
                }
            }
        });
    }





}
