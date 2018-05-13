package railgun.framework.serialize.hessian;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import static railgun.framework.core.RpcSystemConfig.SERIALIZE_POOL_MAX_TOTAL;
import static railgun.framework.core.RpcSystemConfig.SERIALIZE_POOL_MIN_IDLE;
import static railgun.framework.core.RpcSystemConfig.SERIALIZE_POOL_MAX_WAIT_MILLIS;
import static railgun.framework.core.RpcSystemConfig.SERIALIZE_POOL_MIN_EVICTABLE_IDLE_TIME_MILLIS;
import javax.servlet.GenericServlet;

/**
 * Created by GongHuaigu on 2018/5/13.
 * 使用通用对象池
 *
 */
public class HessianSerializePool {
    private GenericObjectPool<HessianSerialize> hessianPool;
    private static volatile HessianSerializePool poolFactory = null;
    //私有化构造方法
    private HessianSerializePool(){
        hessianPool = new GenericObjectPool<HessianSerialize>(new HessianSerializeFactory());
    }

    //带参数构造方法
    public HessianSerializePool(final int maxTotal, final int minIdle, final long maxWaitMillis, final long minEvictableIdleTimeMillis) {
        hessianPool = new GenericObjectPool<HessianSerialize>(new HessianSerializeFactory());
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        hessianPool.setConfig(config);
    }

    //有意思的方法，双重检查锁定与延迟初始化问题
    public static HessianSerializePool getHessianPoolInstance(){
        if (poolFactory == null){
            synchronized (HessianSerializePool.class){
                if (poolFactory == null){
                    poolFactory = new HessianSerializePool(SERIALIZE_POOL_MAX_TOTAL, SERIALIZE_POOL_MIN_IDLE, SERIALIZE_POOL_MAX_WAIT_MILLIS, SERIALIZE_POOL_MIN_EVICTABLE_IDLE_TIME_MILLIS);
                }
            }
        }
        return poolFactory;
    }

    //获取GenericObjectPool


    public GenericObjectPool<HessianSerialize> getHessianPool() {
        return hessianPool;
    }

    //获取一个HessianSerialize对象
    public HessianSerialize borrow(){
        try {
            return getHessianPool().borrowObject();
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    //返回给对象池
    public void restore(final HessianSerialize object){
        getHessianPool().returnObject(object);
    }
}
