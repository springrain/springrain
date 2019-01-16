package org.springrain.rpc.grpcauto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * 定义通用的 Grpc 事务通知 服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.17.1)",
    comments = "Source: grpcTransactionNoticeService.proto")
public final class GrpcTransactionNoticeServiceGrpc {

  private GrpcTransactionNoticeServiceGrpc() {}

  public static final String SERVICE_NAME = "GrpcTransactionNoticeService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.springrain.rpc.grpcauto.NoticeRequest,
      org.springrain.rpc.grpcauto.NoticeResponse> getSendNoticeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendNotice",
      requestType = org.springrain.rpc.grpcauto.NoticeRequest.class,
      responseType = org.springrain.rpc.grpcauto.NoticeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.springrain.rpc.grpcauto.NoticeRequest,
      org.springrain.rpc.grpcauto.NoticeResponse> getSendNoticeMethod() {
    io.grpc.MethodDescriptor<org.springrain.rpc.grpcauto.NoticeRequest, org.springrain.rpc.grpcauto.NoticeResponse> getSendNoticeMethod;
    if ((getSendNoticeMethod = GrpcTransactionNoticeServiceGrpc.getSendNoticeMethod) == null) {
      synchronized (GrpcTransactionNoticeServiceGrpc.class) {
        if ((getSendNoticeMethod = GrpcTransactionNoticeServiceGrpc.getSendNoticeMethod) == null) {
          GrpcTransactionNoticeServiceGrpc.getSendNoticeMethod = getSendNoticeMethod = 
              io.grpc.MethodDescriptor.<org.springrain.rpc.grpcauto.NoticeRequest, org.springrain.rpc.grpcauto.NoticeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "GrpcTransactionNoticeService", "sendNotice"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.springrain.rpc.grpcauto.NoticeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.springrain.rpc.grpcauto.NoticeResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new GrpcTransactionNoticeServiceMethodDescriptorSupplier("sendNotice"))
                  .build();
          }
        }
     }
     return getSendNoticeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcTransactionNoticeServiceStub newStub(io.grpc.Channel channel) {
    return new GrpcTransactionNoticeServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcTransactionNoticeServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GrpcTransactionNoticeServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GrpcTransactionNoticeServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GrpcTransactionNoticeServiceFutureStub(channel);
  }

  /**
   * <pre>
   * 定义通用的 Grpc 事务通知 服务
   * </pre>
   */
  public static abstract class GrpcTransactionNoticeServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 处理请求
     * </pre>
     */
    public void sendNotice(org.springrain.rpc.grpcauto.NoticeRequest request,
        io.grpc.stub.StreamObserver<org.springrain.rpc.grpcauto.NoticeResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendNoticeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendNoticeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.springrain.rpc.grpcauto.NoticeRequest,
                org.springrain.rpc.grpcauto.NoticeResponse>(
                  this, METHODID_SEND_NOTICE)))
          .build();
    }
  }

  /**
   * <pre>
   * 定义通用的 Grpc 事务通知 服务
   * </pre>
   */
  public static final class GrpcTransactionNoticeServiceStub extends io.grpc.stub.AbstractStub<GrpcTransactionNoticeServiceStub> {
    private GrpcTransactionNoticeServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcTransactionNoticeServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcTransactionNoticeServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcTransactionNoticeServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 处理请求
     * </pre>
     */
    public void sendNotice(org.springrain.rpc.grpcauto.NoticeRequest request,
        io.grpc.stub.StreamObserver<org.springrain.rpc.grpcauto.NoticeResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendNoticeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * 定义通用的 Grpc 事务通知 服务
   * </pre>
   */
  public static final class GrpcTransactionNoticeServiceBlockingStub extends io.grpc.stub.AbstractStub<GrpcTransactionNoticeServiceBlockingStub> {
    private GrpcTransactionNoticeServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcTransactionNoticeServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcTransactionNoticeServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcTransactionNoticeServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 处理请求
     * </pre>
     */
    public org.springrain.rpc.grpcauto.NoticeResponse sendNotice(org.springrain.rpc.grpcauto.NoticeRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendNoticeMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * 定义通用的 Grpc 事务通知 服务
   * </pre>
   */
  public static final class GrpcTransactionNoticeServiceFutureStub extends io.grpc.stub.AbstractStub<GrpcTransactionNoticeServiceFutureStub> {
    private GrpcTransactionNoticeServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GrpcTransactionNoticeServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcTransactionNoticeServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GrpcTransactionNoticeServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 处理请求
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.springrain.rpc.grpcauto.NoticeResponse> sendNotice(
        org.springrain.rpc.grpcauto.NoticeRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendNoticeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_NOTICE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcTransactionNoticeServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GrpcTransactionNoticeServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_NOTICE:
          serviceImpl.sendNotice((org.springrain.rpc.grpcauto.NoticeRequest) request,
              (io.grpc.stub.StreamObserver<org.springrain.rpc.grpcauto.NoticeResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GrpcTransactionNoticeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GrpcTransactionNoticeServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.springrain.rpc.grpcauto.GrpcAutoCreateTransactionNoticeService.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GrpcTransactionNoticeService");
    }
  }

  private static final class GrpcTransactionNoticeServiceFileDescriptorSupplier
      extends GrpcTransactionNoticeServiceBaseDescriptorSupplier {
    GrpcTransactionNoticeServiceFileDescriptorSupplier() {}
  }

  private static final class GrpcTransactionNoticeServiceMethodDescriptorSupplier
      extends GrpcTransactionNoticeServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GrpcTransactionNoticeServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GrpcTransactionNoticeServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GrpcTransactionNoticeServiceFileDescriptorSupplier())
              .addMethod(getSendNoticeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
