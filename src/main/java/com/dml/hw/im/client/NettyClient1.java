package com.dml.hw.im.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author Louis
 * @version 1.0
 * @since 2019/11/18
 */
@Slf4j
public class NettyClient1 {

    private final int MAX_RETRY = 5;

    public void connectServer() {

        NioEventLoopGroup worker = new NioEventLoopGroup();

        Bootstrap client = new Bootstrap();
        client
                // 1.指定线程模型
                .group(worker)
                // 2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                // 3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        log.info("处理请求。。。");
                    }
                });

        // 4.建立连接
        this.connect(client, MAX_RETRY);
    }


    private void connect(Bootstrap client, int retry) {

        client
                .connect("localhost", 17070)
                .addListener(future -> {

                    if (future.isSuccess()) {
                        log.info("建立连接成功。。。");
                    } else if (retry == 0){
                        log.error("connect fail, retry limit...");
                    } else {
                        // 第几次重连
                        int order = MAX_RETRY - retry + 1;
                        int delay = 1 << order;
                        log.warn("connect fail, time->" + order + " reconnect delay " + delay);
                        client.config().group().schedule(() -> connect(client, retry -1), delay, TimeUnit.SECONDS);
                    }
                });

    }


}
