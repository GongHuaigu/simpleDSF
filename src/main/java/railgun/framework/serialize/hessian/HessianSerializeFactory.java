package railgun.framework.serialize.hessian;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by GongHuaigu on 2018/5/13.
 */
public class HessianSerializeFactory extends BasePooledObjectFactory<HessianSerialize> {

    //创建对象方法
    @Override
    public HessianSerialize create() throws Exception {
        return createHessian();
    }

    @Override
    public PooledObject<HessianSerialize> wrap(HessianSerialize hessianSerialize) {
        return new DefaultPooledObject<HessianSerialize>(hessianSerialize);
    }

    private HessianSerialize createHessian(){
        return new HessianSerialize();
    }
}
