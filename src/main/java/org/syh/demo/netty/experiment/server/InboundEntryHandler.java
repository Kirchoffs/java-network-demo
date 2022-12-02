package org.syh.demo.netty.experiment.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class InboundEntryHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        printMessage("======== START ========");
        printMessage("Start processing message");
        ctx.fireChannelRead(msg);
    }

    private void printMessage(String message) {
        System.out.println("Inbound Entry Handler >> " + message);
    }
}
