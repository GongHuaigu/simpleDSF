package railgun.netty;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import railgun.framework.filter.Filter;
import railgun.framework.filter.ServiceFilterBinder;
import railgun.framework.model.MessageRequest;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by GongHuaigu on 2018/5/18.
 * MethodInvocation   祖宗是Advice，也相当于一个拦截器,这里的handler是拦截器处理器
 */
public class MethodProxyAdvisor implements MethodInterceptor {

    private Map<String,Object> handlerMap;
    private boolean returnNotNull = true;

    public boolean isReturnNotNull() {
        return returnNotNull;
    }

    public void setReturnNotNull(boolean returnNotNull) {
        this.returnNotNull = returnNotNull;
    }

    public MethodProxyAdvisor(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object [] params = methodInvocation.getArguments();
        if (params.length <= 0){
            return null;
        }
        MessageRequest request = (MessageRequest)params[0];
        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);
        String methodName = request.getMethodName();
        Object[] parameters = request.getParameterVal();

        boolean existFilter = ServiceFilterBinder.class.isAssignableFrom(serviceBean.getClass());
        //创建代理
        ((MethodInvoker)methodInvocation.getThis()).setServiceBean(existFilter ?
                ((ServiceFilterBinder) serviceBean).getObject() : serviceBean);
        if (existFilter){
            //服务程序
            ServiceFilterBinder processors = (ServiceFilterBinder)serviceBean;
            if (processors.getFilter()!=null){
                Filter filter = processors.getFilter();
                //如果是null,则返回一个空数组，防错
                Object[] args = ArrayUtils.nullToEmpty(parameters);
                //获取参数的类型
                Class<?> [] parameterTypes = ClassUtils.toClass(args);
                //实例化方法
                Method method = MethodUtils.getMatchingAccessibleMethod(processors.getObject().getClass(),methodName,parameterTypes);
                //一个拦截器起作用的地方，自然需要传给拦截器方法切点信息
                if (filter.before(method,processors.getObject(),parameters)){
                    //执行方法
                    Object result = methodInvocation.proceed();
                    filter.after(method,processors.getObject(),parameters);
                    setReturnNotNull(result != null);
                    return result;
                }else{
                    return null;
                }
            }
        }
        Object result = methodInvocation.proceed();
        setReturnNotNull(result != null);
        return result;
    }
}
