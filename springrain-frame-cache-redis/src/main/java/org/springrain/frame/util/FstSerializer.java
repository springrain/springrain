package org.springrain.frame.util;

import org.nustaq.serialization.FSTConfiguration;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Description: Fst 序列化.<br>
 *
 * @author m-xy
 *     Created By 2019/8/20 下午3:36
 */
public class FstSerializer implements RedisSerializer<Object> {

    private static FSTConfiguration fstConfiguration = FSTConfiguration.createStructConfiguration();

    @Override
    public byte[] serialize(Object obj) {
        if (obj==null) {
            return null;
        }
        return fstConfiguration.asByteArray(obj);
    }

    @Override
    public Object deserialize(byte[] bytes) {
        if (bytes==null) {
            return null;
        }
        return fstConfiguration.asObject(bytes);
    }
}
