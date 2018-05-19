package railgun.netty.handler;

import io.netty.channel.ChannelPipeline;

import java.util.Map;

/**
 * Created by GongHuaigu on 2018/5/18.
 * 作用：制造一个ChannelHandler
 */
public interface NettyRpcRecvHandler {
    void handle(Map<String, Object> handlerMap, ChannelPipeline pipeline);
}
