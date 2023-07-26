package org.springrain.rpc.util;


import com.google.protobuf.ByteString;
import io.fury.Fury;
import io.fury.Language;
import io.fury.serializer.CompatibleMode;
import org.springrain.rpc.grpcauto.CommonRequest;
import org.springrain.rpc.grpcauto.CommonResponse;
import org.springrain.rpc.grpcimpl.GrpcCommonRequest;
import org.springrain.rpc.grpcimpl.GrpcCommonResponse;


/**
 * Fury 序列化/反序列化工具
 */
public class FurySerializeUtils {

   // static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
   private static Fury fury = Fury.builder().withLanguage(Language.JAVA)
           .withRefTracking(false)
           // Allow to deserialize objects unknown types,
           // more flexible but less secure.
           .withSecureMode(false)
           .withDeserializeUnExistClassEnabled(true)
           .withCompatibleMode(CompatibleMode.COMPATIBLE)
           // .withAsyncCompilationEnabled(true)
           // .buildThreadSafeFury()
           .build();

    public static GrpcCommonResponse deserialize(CommonResponse response) {
        GrpcCommonResponse grpcCommonResponse = (GrpcCommonResponse) fury.deserialize(response.getResponse().toByteArray());
        return grpcCommonResponse;
    }


    public static ByteString serialize(GrpcCommonRequest request) {
        return ByteString.copyFrom(fury.serialize(request));
    }

    public static GrpcCommonRequest deserialize(CommonRequest request) {
        GrpcCommonRequest grpcCommonRequest = (GrpcCommonRequest) fury.deserialize(request.getRequest().toByteArray());
        return grpcCommonRequest;
    }

    public static ByteString serialize(GrpcCommonResponse response) {
        return ByteString.copyFrom(fury.serialize(response));
    }


}
