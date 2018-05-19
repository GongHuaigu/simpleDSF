package railgun.netty;

import com.google.common.util.concurrent.ListeningExecutorService;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import railgun.framework.core.RpcSystemConfig;
import railgun.framework.parallel.NamedThreadFactory;
import railgun.framework.serialize.RpcSerializeProtocol;

import java.nio.channels.spi.SelectorProvider;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;

/**
 * Created by GongHuaigu on 2018/5/17.
 * 接收消息的线程池
 * 接收消息需要实现的功能：
 * 用到的基础类：线程工厂NamedThreadFactory
 *
 * 其实现基本和JUC包中的线程池一直，可参考ThreadPoolExecutor
 */
public class MessageRecvExecutor implements ApplicationContextAware{

    private String serverAddress;
    private int echoApiPort;
    private RpcSerializeProtocol serializeProtocol = RpcSerializeProtocol.JDKSERIALIZE;
    private static final String DELIMITER = RpcSystemConfig.DELIMITER;

    /*设定线程数量参考*/
    private static final int PARALLEL = RpcSystemConfig.SYSTEM_PROPERTY_PARALLEL * 2;

    private static int threadNums = RpcSystemConfig.SYSTEM_PROPERTY_THREADPOOL_THREAD_NUMS;
    private static int queueNums = RpcSystemConfig.SYSTEM_PROPERTY_THREADPOOL_QUEUE_NUMS;

    /*
    *
    * */
    private static volatile ListeningExecutorService threadPoolExecutor;

    /*
    * NettyRpcService  MessageRecvExecutor.getInstance().getHandlerMap().put(interfaceName, binder);
    * 作用：存放接口
    * 任何一个NettyRpcService被创建，Executor都会保存这个服务接口
    * */
    private Map<String, Object> handlerMap = new ConcurrentHashMap<String, Object>();
    private int numberOfEchoThreadsPool = 1;

    ThreadFactory threadRpcFactory = new NamedThreadFactory("NettyRPC ThreadFactory");

    /*
    * netty 线程池
    * */
    EventLoopGroup boss = new NioEventLoopGroup();
    EventLoopGroup worker = new NioEventLoopGroup(PARALLEL, threadRpcFactory, SelectorProvider.provider());

    private MessageRecvExecutor(){


    }

    //单例？？
    private static class MessageRecvExecutorHolder{
        static final MessageRecvExecutor INSTANCE = new MessageRecvExecutor();
    }

    public static MessageRecvExecutor getInstance(){
        return MessageRecvExecutorHolder.INSTANCE;
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //加载handle的map
    }

    public Map<String, Object> getHandlerMap() {
        return handlerMap;
    }

    public void setHandlerMap(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }
}
