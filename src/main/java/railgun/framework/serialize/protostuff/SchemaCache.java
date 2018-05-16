package railgun.framework.serialize.protostuff;

import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by GongHuaigu on 2018/5/16.
 * 缓存Schema,使用guava包
 */
public class SchemaCache {
    private static class SchemaCacheHolder{
        private static SchemaCache cache = new SchemaCache();
    }

    public static SchemaCache getInstance(){
        return SchemaCacheHolder.cache;
    }

    private Cache<Class<?>,Schema<?>> cache = CacheBuilder.newBuilder().maximumSize(1024).expireAfterWrite(1, TimeUnit.HOURS).build();

    private Schema<?> get(final Class<?> cls,Cache<Class<?>,Schema<?>> cache){
        try {
            return cache.get(cls, new Callable<Schema<?>>() {
                @Override
                public Schema<?> call() throws Exception {
                    return RuntimeSchema.createFrom(cls);
                }
            });
        }catch (ExecutionException e){
            return null;
        }
    }

    public Schema<?> get(final Class<?> cls){
        return get(cls,cache);
    }
}
