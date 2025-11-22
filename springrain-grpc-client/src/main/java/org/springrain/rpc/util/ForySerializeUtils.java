package org.springrain.rpc.util;


import com.google.protobuf.ByteString;
import org.apache.fory.Fory;
import org.apache.fory.ThreadSafeFory;
import org.apache.fory.config.CompatibleMode;
import org.apache.fory.config.Language;
import org.springrain.rpc.grpcauto.CommonRequest;
import org.springrain.rpc.grpcauto.CommonResponse;
import org.springrain.rpc.grpcimpl.GrpcCommonRequest;
import org.springrain.rpc.grpcimpl.GrpcCommonResponse;


/**
 * Fory 序列化/反序列化工具
 */
public class ForySerializeUtils {

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

    public static GrpcCommonResponse deserialize(CommonResponse response) {
        GrpcCommonResponse grpcCommonResponse = (GrpcCommonResponse) fory.deserialize(response.getResponse().toByteArray());
        return grpcCommonResponse;
    }


    public static ByteString serialize(GrpcCommonRequest request) {
        return ByteString.copyFrom(fory.serialize(request));
    }

    public static GrpcCommonRequest deserialize(CommonRequest request) {
        GrpcCommonRequest grpcCommonRequest = (GrpcCommonRequest) fory.deserialize(request.getRequest().toByteArray());
        return grpcCommonRequest;
    }

    public static ByteString serialize(GrpcCommonResponse response) {
        return ByteString.copyFrom(fory.serialize(response));
    }


}
