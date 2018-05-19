package railgun.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import railgun.framework.serialize.RpcSerializeProtocol;

/**
 * Created by GongHuaigu on 2018/5/18.
 * 消息接收初始化器
 */
public class MessageRecvChannelInitializer extends ChannelInitializer<SocketChannel>{

    private RpcSerializeProtocol protocol;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

    }
}
