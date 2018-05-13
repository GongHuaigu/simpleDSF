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

}
