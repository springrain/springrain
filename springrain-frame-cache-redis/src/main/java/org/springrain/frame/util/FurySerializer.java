package org.springrain.frame.util;

import io.fury.Fury;
import io.fury.Language;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Description: Fury 序列化.<br>
 *
 * @author springrain
 */
public class FurySerializer implements RedisSerializer<Object> {

    // static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
   private static Fury fury = Fury.builder().withLanguage(Language.JAVA).withRefTracking(true)
            // Allow to deserialize objects unknown types,
            // more flexible but less secure.
            .withSecureMode(false)
            .build();

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
