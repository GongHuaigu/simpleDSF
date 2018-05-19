package railgun.framework.serialize.protostuff;

import com.sun.scenario.effect.impl.prism.PrImage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import railgun.framework.serialize.MessageCodecUtil;
import sun.util.logging.PlatformLogger;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by GongHuaigu on 2018/5/16.
 * netty使用的解码器
 */
public class MessageDecoder extends ByteToMessageDecoder {

    final public static int MESSAGE_LENGTH = MessageCodecUtil.MESSAGE_LENGTH;
    private MessageCodecUtil util;
    public MessageDecoder (final MessageCodecUtil util){
        this.util = util;
    }
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes()<MessageDecoder.MESSAGE_LENGTH)
            return;
        in.markReaderIndex();
        int messageLength = in.readInt();
        if (messageLength<0){
            ctx.close();
        }

        if (in.readableBytes() < messageLength){
            in.resetReaderIndex();
            return;
        }else{
            byte[] messageBody = new byte[messageLength];
            in.readBytes(messageBody);

            try {
                Object obj = util.decode(messageBody);
                out.add(obj);
            }catch (IOException ioe){
                Logger.getLogger(MessageDecoder.class.getName()).log(Level.SEVERE,null,ioe);
            }
        }

    }
}
