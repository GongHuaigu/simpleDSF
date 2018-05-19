package railgun.framework.core;

/**
 * Created by GongHuaigu on 2018/5/13.
 * 系统配置
 */
public class RpcSystemConfig {

    /*commons-pool 对象池的配置*/
    public static final int SERIALIZE_POOL_MAX_TOTAL = 500;
    public static final int SERIALIZE_POOL_MIN_IDLE = 10;
    public static final int SERIALIZE_POOL_MAX_WAIT_MILLIS = 5000;
    public static final int SERIALIZE_POOL_MIN_EVICTABLE_IDLE_TIME_MILLIS = 600000;


    /*线程池相关*/
    //系统并行数
    public static final int SYSTEM_PROPERTY_PARALLEL = Math.max(2, Runtime.getRuntime().availableProcessors());

    //nettyrpc.default.thread.nums是系统参数
    public static final int SYSTEM_PROPERTY_THREADPOOL_THREAD_NUMS = Integer.getInteger("nettyrpc.default.thread.nums", 16);
    public static final int SYSTEM_PROPERTY_THREADPOOL_QUEUE_NUMS = Integer.getInteger("nettyrpc.default.queue.nums", -1);

    public static final String DELIMITER = ":";
}
