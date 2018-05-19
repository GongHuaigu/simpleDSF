package railgun.netty;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.commons.lang3.time.StopWatch;
import railgun.framework.model.MessageRequest;

/**
 * Created by GongHuaigu on 2018/5/18.
 * 作用，执行MessageRequest中封装的调用
 */
public class MethodInvoker {
    private Object serviceBean;

    public Object getServiceBean() {
        return serviceBean;
    }

    public void setServiceBean(Object serviceBean) {
        this.serviceBean = serviceBean;
    }

    private StopWatch sw = new StopWatch();

    //
    public Object invoke(MessageRequest request) throws Throwable{
        String methodName = request.getMethodName();
        Object[] params = request.getParameterVal();
        sw.reset();
        sw.start();
        Object result = MethodUtils.invokeMethod(serviceBean,methodName,params);
        sw.stop();
        return result;
    }

    //获取执行时间
    public long getInvokeTimespan(){
        return sw.getTime();
    }

}
