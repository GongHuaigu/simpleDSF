package railgun.spring;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import railgun.framework.event.ServerStartEvent;
import railgun.framework.filter.Filter;
import railgun.framework.filter.ServiceFilterBinder;
import railgun.netty.MessageRecvExecutor;

/**
 * Created by GongHuaigu on 2018/5/19.
 * 框架的服务类，对应配置文件中的服务配置
 */
public class NettyRpcService implements ApplicationContextAware,ApplicationListener{

    private String interfaceName;
    private String ref;
    private String filter;
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        ServiceFilterBinder binder = new ServiceFilterBinder();
        binder.setObject(applicationContext.getBean(ref));
        if (StringUtils.isBlank(filter) || !(applicationContext.getBean(filter) instanceof Filter)){

        }else{
            binder.setFilter((Filter) applicationContext.getBean(filter));
        }
        MessageRecvExecutor.getInstance().getHandlerMap().put(interfaceName,binder);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        applicationContext.publishEvent(new ServerStartEvent(new Object()));
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
