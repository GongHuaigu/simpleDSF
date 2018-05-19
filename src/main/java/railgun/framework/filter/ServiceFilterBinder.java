package railgun.framework.filter;

/**
 * Created by GongHuaigu on 2018/5/18.
 * 绑定服务和过滤器
 */
public class ServiceFilterBinder {
    private Object object;
    private Filter filter;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
