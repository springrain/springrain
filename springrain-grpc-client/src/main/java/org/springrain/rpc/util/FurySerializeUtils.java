package org.springrain.rpc.util;


import com.google.protobuf.ByteString;
import io.fury.Fury;
import io.fury.Language;
import org.springrain.rpc.grpcauto.CommonResponse;
import org.springrain.rpc.grpcimpl.GrpcCommonRequest;
import org.springrain.rpc.grpcimpl.GrpcCommonResponse;


/**
 * Fury 序列化/反序列化工具
 */
public class FurySerializeUtils {

   // static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
   static Fury fury = Fury.builder().withLanguage(Language.JAVA).withRefTracking(true)
           // Allow to deserialize objects unknown types,
           // more flexible but less secure.
           .withSecureMode(false)
           .build();

    public static GrpcCommonResponse deserialize(CommonResponse response) {

        GrpcCommonResponse grpcCommonResponse = (GrpcCommonResponse) fury.deserialize(response.getResponse().toByteArray());

        return grpcCommonResponse;
    }


    public static ByteString serialize(GrpcCommonRequest request) {
        return ByteString.copyFrom(fury.serialize(request));
    }

}
