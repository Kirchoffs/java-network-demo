package org.syh.demo.netty.experiment.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class InboundHandler extends ChannelInboundHandlerAdapter {
    private int id;
    private String prompt;

    public InboundHandler(int id) {
        this.id = id;
        this.prompt = String.format("Inbound Handler %d >> ", id);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String message = (String) msg;
        printMessage(message);
        String[] parts = message.split("-");
        
        if (parts.length == 2) {
            String handlerId = parts[0];
            boolean returnByChannel = Boolean.parseBoolean(parts[1]);
            
            if (handlerId.equals(String.valueOf(id))) {
                printMessage("Before returning response");
                if (returnByChannel) {
                    ctx.channel().writeAndFlush("Response from Inbound Handler " + id + " by channel");
                } else {
                    ctx.writeAndFlush("Response from Inbound Handler " + id);
                }
                printMessage("After returning response");
                return;
            }
        }
        
        ctx.fireChannelRead(msg);
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
