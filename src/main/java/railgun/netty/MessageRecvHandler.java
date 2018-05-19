package railgun.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import railgun.framework.model.MessageRequest;
import railgun.framework.model.MessageResponse;

import java.util.Map;

/**
 * Created by GongHuaigu on 2018/5/18.
 * 自定义ChannelHandler
 *
 */
public class MessageRecvHandler extends ChannelInboundHandlerAdapter{

    private final Map<String,Object> handlerMap;

    public MessageRecvHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageRequest request = (MessageRequest) msg;
        MessageResponse response = new MessageResponse();
        //包装成一个task，交给线程池执行

    }
}
