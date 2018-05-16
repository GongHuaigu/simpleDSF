package railgun.framework.serialize.protostuff;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import railgun.framework.model.MessageRequest;
import railgun.framework.model.MessageResponse;
import railgun.framework.serialize.RpcSerialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by GongHuaigu on 2018/5/13.
 * 使用protostuff序列化工具
 *
 */
public class ProtostuffSerialize implements RpcSerialize {

    private static SchemaCache cachedSchema = SchemaCache.getInstance();
    private static Objenesis objenesis = new ObjenesisStd(true);

    private boolean rpcDirect = false;

    public boolean isRpcDirect() {
        return rpcDirect;
    }

    public void setRpcDirect(boolean rpcDirect) {
        this.rpcDirect = rpcDirect;
    }

    private static <T> Schema<T> getSchema(Class<T> cls){
        return (Schema<T>) cachedSchema.get(cls);
    }


    /**
     * ??这种写法的作用？？
     *
     *
     *
     *
    public <T> T deserialize(byte[] data, Class<T> cls) {
        try {
            T message = (T) objenesis.newInstance(cls);
            Schema<T> schema = getSchema(cls);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
    @Override
    public void serialize(OutputStream output, Object object) throws IOException {
        Class cls = object.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema schema = getSchema(cls);
            ProtostuffIOUtil.writeTo(output,object,schema,buffer);
        }catch (Exception e){
            throw new IllegalStateException(e.getMessage(),e);
        }finally {
            buffer.clear();
        }
    }

    @Override
    public Object deserialize(InputStream input) throws IOException {
       try {
           Class  cls = isRpcDirect() ? MessageRequest.class : MessageResponse.class;
           Object message = (Object) objenesis.newInstance(cls);
           Schema<Object> schema = getSchema(cls);
           ProtostuffIOUtil.mergeFrom(input,message,schema);
           return message;
       }catch (Exception  e){
           throw new IllegalStateException(e.getMessage(),e);
       }
    }
}
