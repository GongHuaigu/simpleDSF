package railgun.framework.serialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by GongHuaigu on 2018/5/13.
 */
public interface RpcSerialize {
    void serialize(OutputStream output,Object object) throws IOException;

    Object deserialize(InputStream input) throws IOException;
}
