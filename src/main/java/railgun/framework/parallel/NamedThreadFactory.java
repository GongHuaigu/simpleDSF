package railgun.framework.parallel;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by GongHuaigu on 2018/5/17.
 * 定制有名字的线程工厂，ThreadFactory接口了解一下，与Executor的关系
 * 提供的功能：名字，设置线程类型，优先级，等
 * Executors.defaultThreadFactory()
 * 一个问题，涉及到final的内存语义，为何该类的域都用final，final保证多线程环境下一个类可以被安全地初始化。
 * SecurityManager,java提供的应用层的安全管理器，控制一个app对资源的访问，如果没有权限则会抛出异常。
 * ThreadGroup
 * pool-N-thread-M  首先需要理解线程池。
 */
public class NamedThreadFactory implements ThreadFactory{

    private static final AtomicInteger THREAD_NUMBER = new AtomicInteger(1);

    private final AtomicInteger mThreadNum = new AtomicInteger(1);

    //名字
    private final String prefix;

    //线程类型
    private final boolean daemonThread;

    private final ThreadGroup threadGroup;

    public NamedThreadFactory(String prefix, boolean daemonThread) {

        this.prefix = StringUtils.isNotEmpty(prefix) ? prefix+"-thread-" : "";
        this.daemonThread = daemonThread;
        SecurityManager securityManager = System.getSecurityManager();
        //
        threadGroup = (securityManager == null) ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
    }

    /*默认使用用户线程*/
    public NamedThreadFactory(){
        this("rpcserver-threadpool-" + THREAD_NUMBER.getAndIncrement(), false);
    }

    public NamedThreadFactory(String prefix){
        this(prefix,false);
    }

    /*
    * 关键方法，创建并定制新线程
    * */
    @Override
    public Thread newThread(Runnable r) {
        String name = prefix + mThreadNum.getAndIncrement();
        Thread thread = new Thread(threadGroup,r,name,0);
        thread.setDaemon(daemonThread);
        return thread;
    }
}
