package railgun.framework.filter;

/**
 * Created by GongHuaigu on 2018/5/18.
 */
public interface ChainFilter {
    Object invoke() throws Throwable;
}
