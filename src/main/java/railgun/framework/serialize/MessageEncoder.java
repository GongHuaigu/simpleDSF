package railgun.framework.serialize;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by GongHuaigu on 2018/5/13.
 * Netty的编码器
 */
public class MessageEncoder extends MessageToByteEncoder<Object>{


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {

    }
}
