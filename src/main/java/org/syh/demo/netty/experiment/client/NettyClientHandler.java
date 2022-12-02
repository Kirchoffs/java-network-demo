package org.syh.demo.netty.experiment.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private Integer id;
    private Boolean returnByChannel;

    public NettyClientHandler(Integer id, Boolean returnByChannel) {
        this.id = id;
        this.returnByChannel = returnByChannel;
    }

    public NettyClientHandler() {}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client channel read");
        String message = (String) msg;
        System.out.println("Received from server: " + message);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Client channel active");
        if (id != null && returnByChannel != null) {
            String message = id + "-" + returnByChannel;
            ctx.writeAndFlush(message);
        } else {
            ctx.writeAndFlush("Hello, server!");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            ctx.close();
        }
    }
}
