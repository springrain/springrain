package org.springrain.frame.util;

import org.apache.fory.Fory;
import org.apache.fory.ThreadSafeFory;
import org.apache.fory.config.CompatibleMode;
import org.apache.fory.config.Language;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Description: Fory 序列化.<br>
 *
 * @author springrain
 */
public class ForySerializer implements RedisSerializer<Object> {

    // static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
    private static ThreadSafeFory fory = Fory.builder().withLanguage(Language.JAVA)
            // Allow to deserialize objects unknown types,more flexible but less secure.
            .requireClassRegistration(false)
            .withDeserializeNonexistentClass(true)
            .withCompatibleMode(CompatibleMode.COMPATIBLE)
            .withRefTracking(true)
            // .withAsyncCompilation(true)

            // 全局变量使用线程安全的模式
            .buildThreadSafeFory();
    //.build();

    @Override
    public byte[] serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        return fory.serialize(obj);
    }

    @Override
    public Object deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return fory.deserialize(bytes);
    }
}
