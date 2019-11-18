package com.dml.hw.im.runner;

import com.dml.hw.im.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Louis
 * @version 1.0
 * @since 2019/11/18
 */

@Component
public class ServerRunner implements ApplicationRunner {

    @Autowired
    @Qualifier("nettyServer1")
    private NettyServer nettyServer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        nettyServer.run();
    }
}
