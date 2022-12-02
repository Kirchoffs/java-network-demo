package org.syh.demo.netty.simple.server;

import java.util.concurrent.TimeUnit;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("Received from client: " + buf.toString(CharsetUtil.UTF_8));
        System.out.println("Client address: " + ctx.channel().remoteAddress());

        // After 5 seconds
        ctx.channel().eventLoop().execute(() -> {
            process(5, ctx);
        });

        // After 5 + 5 = 10 seconds
        ctx.channel().eventLoop().execute(() -> {
            process(5, ctx);
        });
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hi client", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            ctx.close();
        }
    }

    public void process(int seconds, ChannelHandlerContext ctx) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
            ctx.writeAndFlush(Unpooled.copiedBuffer("A little gift for you", CharsetUtil.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
