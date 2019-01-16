package org.springrain.rpc.util;


import org.springrain.rpc.grpcauto.CommonRequest;
import org.springrain.rpc.grpcauto.CommonResponse;
import org.springrain.rpc.grpcimpl.GrpcCommonRequest;
import org.springrain.rpc.grpcimpl.GrpcCommonResponse;

import com.google.protobuf.ByteString;



/**
 * ProtoStuff 序列化/反序列化工具
 */
public class GRPCSerializeUtils {

	public static GrpcCommonRequest deserialize(CommonRequest request) {
		return ProtobufUtils.deserialize(request.getRequest().toByteArray(), GrpcCommonRequest.class);
    }

	public static GrpcCommonResponse deserialize(CommonResponse response) {
		return ProtobufUtils.deserialize(response.getResponse().toByteArray(), GrpcCommonResponse.class);
    }

	public static ByteString serialize(GrpcCommonResponse response) {
        return ByteString.copyFrom(ProtobufUtils.serialize(response));
    }

	public static ByteString serialize(GrpcCommonRequest request) {
		return ByteString.copyFrom(ProtobufUtils.serialize(request));
    }

}
