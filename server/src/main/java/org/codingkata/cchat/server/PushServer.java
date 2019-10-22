package org.codingkata.cchat.server;

import org.codingkata.cchat.server.NettyServerBootstrap;

public class PushServer {

    public static void main(String[] args) {
        NettyServerBootstrap serverBootstrap = new NettyServerBootstrap(8300);
        serverBootstrap.bind();
    }
}
