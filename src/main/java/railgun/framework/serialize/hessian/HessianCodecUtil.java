package railgun.framework.serialize.hessian;

import com.google.common.io.Closer;
import io.netty.buffer.ByteBuf;
import railgun.framework.serialize.MessageCodecUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by GongHuaigu on 2018/5/13.
 */
public class HessianCodecUtil implements MessageCodecUtil {

    private HessianSerializePool pool = HessianSerializePool.getHessianPoolInstance();
    private static Closer closer = Closer.create();

    /*
    * 编码：message ---->  byte[]  ---->  ByteBuf
    * */
    @Override
    public void encode(ByteBuf out, Object message) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            closer.register(byteArrayOutputStream);
            HessianSerialize serialize = pool.borrow();
            serialize.serialize(byteArrayOutputStream,message);
            byte[] body = byteArrayOutputStream.toByteArray();
            int length = body.length;
            out.writeInt(length);
            out.writeBytes(body);
            pool.restore(serialize);
        }finally {
            closer.close();
        }
    }

    /*
    * 解码
    *
    * */
    @Override
    public Object decode(byte[] data) throws IOException {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
            closer.register(byteArrayInputStream);
            HessianSerialize serialize = pool.borrow();
            Object object = serialize.deserialize(byteArrayInputStream);
            pool.restore(serialize);
            return object;
        }finally {
            closer.close();
        }
    }
}
