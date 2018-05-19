package railgun.framework.serialize;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by GongHuaigu on 2018/5/17.
 * 定义各种序列化协议
 */
public enum RpcSerializeProtocol {

    JDKSERIALIZE("jdknative"), KRYOSERIALIZE("kryo"), HESSIANSERIALIZE("hessian"), PROTOSTUFFSERIALIZE("protostuff");

    private String serializeProtocol;

    private RpcSerializeProtocol(String serializeProtocol) {
        this.serializeProtocol = serializeProtocol;
    }

    @Override
    public String toString() {
        ReflectionToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
        return ReflectionToStringBuilder.toString(this);
    }

    public String getProtocol() {
        return serializeProtocol;
    }
}
