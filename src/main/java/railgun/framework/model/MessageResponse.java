package railgun.framework.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * Created by GongHuaigu on 2018/5/16.
 */
public class MessageResponse implements Serializable{

    private String messageId;
    private String error;
    private Object result;
    private boolean returnNotNull;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public boolean isReturnNotNull() {
        return returnNotNull;
    }

    public void setReturnNotNull(boolean returnNotNull) {
        this.returnNotNull = returnNotNull;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
