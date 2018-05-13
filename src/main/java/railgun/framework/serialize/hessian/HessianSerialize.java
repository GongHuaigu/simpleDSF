package railgun.framework.serialize.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import railgun.framework.serialize.RpcSerialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by GongHuaigu on 2018/5/13.
 */
public class HessianSerialize implements RpcSerialize{

    @Override
    public void serialize(OutputStream output, Object object) throws IOException {
        Hessian2Output ho = new Hessian2Output(output);
        try {
            ho.startMessage();
            ho.writeObject(object);
            ho.completeMessage();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            ho.close();
            output.close();
        }
    }

    @Override
    public Object deserialize(InputStream input) throws IOException {
        Object obj = null;
        Hessian2Input hi = new Hessian2Input(input);
        try {
            hi.startMessage();
            obj = hi.readObject();
            hi.completeMessage();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally {
            hi.close();
            input.close();
        }
        return obj;
    }
}
