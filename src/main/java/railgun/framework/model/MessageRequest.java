package railgun.framework.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Created by GongHuaigu on 2018/5/16.
 * 抽象一个调用？？
 */
public class MessageRequest {
    private String messageId;
    private String className;
    private String methodName;
    private Class<?>[] typeParameters;
    private Object[] parameterVal;
    private boolean invokeMetrics = true;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(Class<?>[] typeParameters) {
        this.typeParameters = typeParameters;
    }

    public Object[] getParameterVal() {
        return parameterVal;
    }

    public void setParameterVal(Object[] parameterVal) {
        this.parameterVal = parameterVal;
    }

    public boolean isInvokeMetrics() {
        return invokeMetrics;
    }

    public void setInvokeMetrics(boolean invokeMetrics) {
        this.invokeMetrics = invokeMetrics;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toStringExclude(this,new String[]{"typeParameters","parameterVal"});
    }
}
