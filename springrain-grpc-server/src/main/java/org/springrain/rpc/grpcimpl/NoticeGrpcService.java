package org.springrain.rpc.grpcimpl;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springrain.rpc.grpcauto.GrpcTransactionNoticeServiceGrpc;
import org.springrain.rpc.grpcauto.NoticeResponse;
import org.springrain.rpc.springbind.RpcStaticVariable;

/**
 * 集成自动产生的java类,自定义自己的实现.
 * 
 * 
 * @author caomei
 *
 */
public class NoticeGrpcService extends GrpcTransactionNoticeServiceGrpc.GrpcTransactionNoticeServiceImplBase {
	private static final Logger logger = LoggerFactory.getLogger(NoticeGrpcService.class);

	public NoticeGrpcService() {
	}

	/**
	 * <pre>
	 * 处理请求
	 * </pre>
	 */
	@Override
	public void sendNotice(org.springrain.rpc.grpcauto.NoticeRequest request,
			io.grpc.stub.StreamObserver<org.springrain.rpc.grpcauto.NoticeResponse> responseObserver) {


		String txId = request.getTxId();
		// 0是回滚,1是提交
		Integer operation = request.getOperation();


		Lock lock = null;
		// 封装成grpc传递的对象
		NoticeResponse noticeResponse = null;
		try {
			RpcStaticVariable.grpcTxOperationMap.put(txId, operation);
			lock = RpcStaticVariable.grpcLockMap.get(txId);
			// 设置线程condition
			Condition condition = RpcStaticVariable.grpcConditionMap.get(txId);

			lock.lock();

			condition.signal();
			// 封装成grpc传递的对象
			noticeResponse = NoticeResponse.newBuilder().setMessage("success").setResult(1).build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			String message = e.getClass().getName() + ": " + e.getMessage();
			// 封装成grpc传递的对象
			noticeResponse = NoticeResponse.newBuilder().setMessage(message).setResult(0).build();
		} finally {
			lock.unlock();
			RpcStaticVariable.grpcConditionMap.remove(txId);
			RpcStaticVariable.grpcLockMap.remove(txId);
		}

		// grpc下一步处理
		responseObserver.onNext(noticeResponse);
		// 完成传输
		responseObserver.onCompleted();


	}


}
