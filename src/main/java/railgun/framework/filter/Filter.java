package railgun.framework.filter;

import java.lang.reflect.Method;

/**
 * Created by GongHuaigu on 2018/5/18.
 */
public interface Filter {

    boolean before(Method method,Object processor,Object [] requestObjects);

    boolean after(Method method,Object processor, Object[] requestObjects);
}
