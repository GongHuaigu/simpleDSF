package railgun.demo.serialize;

/**
 * Created by GongHuaigu on 2018/5/13.
 */
public interface ISerializer {

    //序列化
    public <T> byte[] serialize(T t);
    //反序列化
    public <T> T deserialize(byte[] data,Class<T> tClass);
}
