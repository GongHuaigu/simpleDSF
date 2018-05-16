package railgun.demo.serialize;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import railgun.demo.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class ProtoStuffSerializer implements ISerializer {


    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();
    /*
    * 用来生成实例，可允许一个类没有无参构造器
    * */
    private static Objenesis objenesis = new ObjenesisStd(true);


    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            cachedSchema.put(cls, schema);
        }
        return schema;
    }

    public <T> byte[] serialize(T obj) {
        Class<T> cls = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(cls);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            buffer.clear();
        }
    }

    public <T> T deserialize(byte[] data, Class<T> cls) {
        try {
            T message = (T) objenesis.newInstance(cls);
            Schema<T> schema = getSchema(cls);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        User u = new User();
        u.setEmail("liyebing@163.com");
        u.setName("kongxuan");

        User u1 = new User();
        u1.setEmail("liyebing@162.com");
        u1.setName("kongxuan11");

        List<User> userList = new ArrayList<User>();
        Map<String, User> userMap = new HashMap<String, User>();
        userList.add(u1);
        userMap.put("a", u1);

        u.setUserList(userList);
        u.setUserMap(userMap);


        byte[] userByte = new ProtoStuffSerializer().serialize(u);
        User user = new ProtoStuffSerializer().deserialize(userByte, User.class);
        //System.out.println(user.getEmail() + " : " + user.getName() + " : " + new String(new JSONSerializer().serialize(u.getUserList())) + " : " + new String(new JSONSerializer().serialize(u.getUserMap())));
    }

}
