package railgun.framework.serialize.protostuff;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import railgun.framework.serialize.MessageCodecUtil;

/**
 * Created by GongHuaigu on 2018/5/16.
 * netty使用的编码器
 */
public class MessageEncoder extends MessageToByteEncoder<Object>{

    private MessageCodecUtil util = null;

    public MessageEncoder(final MessageCodecUtil util){
        this.util = util;
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object msg, ByteBuf out) throws Exception {
        util.encode(out,msg);
    }
}
