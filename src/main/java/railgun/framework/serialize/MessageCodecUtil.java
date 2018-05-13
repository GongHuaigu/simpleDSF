package railgun.framework.serialize;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * Created by GongHuaigu on 2018/5/13.
 * 编码工具方法接口
 */
public interface MessageCodecUtil {
    //final static int MESSAGE_LENGTH = 4;

    void encode(final ByteBuf out,final Object message) throws IOException;

    Object decode(byte[] data) throws IOException;
}

