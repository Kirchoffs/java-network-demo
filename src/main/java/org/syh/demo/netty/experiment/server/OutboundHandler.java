package org.syh.demo.netty.experiment.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class OutboundHandler extends ChannelOutboundHandlerAdapter {
    private int id;
    private String prompt;

    public OutboundHandler(int id) {
        this.id = id;
        this.prompt = String.format("Outbound Handler %d >> ", id);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {        
        printMessage("Message: " + msg);
        ctx.write(msg, promise);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    private void printMessage(String message) {
        System.out.println(prompt + message);
    }
}
