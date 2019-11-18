package com.dml.hw.im.runner;

import com.dml.hw.im.client.NettyClient1;

/**
 * @author Louis
 * @version 1.0
 * @since 2019/11/18
 */

public class ClientRunner {

    public static void main(String[] args) {

        NettyClient1 nettyClient = new NettyClient1();
        nettyClient.connectServer();
    }
}
