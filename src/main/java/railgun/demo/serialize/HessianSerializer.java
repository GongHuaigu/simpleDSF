package railgun.demo.serialize;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.thoughtworks.xstream.mapper.Mapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by GongHuaigu on 2018/5/13.
 * Hessian序列化框架的使用
 */
public class HessianSerializer implements ISerializer {

    public byte[] serialize(Object obj) {
        if (obj == null)
            throw new NullPointerException();

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianOutput ho = new HessianOutput(os);
            ho.writeObject(obj);
            return os.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T deserialize(byte[] data, Class<T> tClass) {
        if (data == null)
            throw new NullPointerException();
        try {
            ByteArrayInputStream in = new ByteArrayInputStream(data);
            HessianInput hi = new HessianInput(in);
            return (T)hi.readObject();
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    //测试
    public static void main(String[] args) {

    }
}
