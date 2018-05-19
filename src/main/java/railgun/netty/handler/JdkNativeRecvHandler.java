package railgun.netty.handler;

import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import railgun.framework.serialize.MessageCodecUtil;

import java.util.Map;

/**
 * Created by GongHuaigu on 2018/5/18.
 */
public class JdkNativeRecvHandler implements NettyRpcRecvHandler {

    @Override
    public void handle(Map<String, Object> handlerMap, ChannelPipeline pipeline) {
        pipeline.addLast(new LengthFieldBasedFrameDecoder
                (Integer.MAX_VALUE, 0, MessageCodecUtil.MESSAGE_LENGTH, 0, MessageCodecUtil.MESSAGE_LENGTH));
        pipeline.addLast(new LengthFieldPrepender(MessageCodecUtil.MESSAGE_LENGTH));
        pipeline.addLast(new ObjectEncoder());
        pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE,
                ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
        //pipeline.addLast()
    }
}
