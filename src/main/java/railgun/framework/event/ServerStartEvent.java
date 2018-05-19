package railgun.framework.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by GongHuaigu on 2018/5/19.
 * 服务器开启事件
 */
public class ServerStartEvent extends ApplicationEvent{
    public ServerStartEvent(Object source) {
        super(source);
    }
}
