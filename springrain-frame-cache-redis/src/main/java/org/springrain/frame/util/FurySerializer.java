package org.springrain.frame.util;

import org.apache.fury.Fury;
import org.apache.fury.config.CompatibleMode;
import org.apache.fury.config.Language;
import org.apache.fury.ThreadSafeFury;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Description: Fury 序列化.<br>
 *
 * @author springrain
 */
public class FurySerializer implements RedisSerializer<Object> {

    // static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
    private static ThreadSafeFury fury = Fury.builder().withLanguage(Language.JAVA)
            // Allow to deserialize objects unknown types,more flexible but less secure.
            .requireClassRegistration(false)
            .withDeserializeNonexistentClass(true)
            .withCompatibleMode(CompatibleMode.COMPATIBLE)
            .withRefTracking(true)
            // .withAsyncCompilation(true)

            // 全局变量使用线程安全的模式
            .buildThreadSafeFury();
    //.build();

    @Override
    public byte[] serialize(Object obj) {
        if (obj == null) {
            return null;
        }
        return fury.serialize(obj);
    }

    @Override
    public Object deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return fury.deserialize(bytes);
    }
}
