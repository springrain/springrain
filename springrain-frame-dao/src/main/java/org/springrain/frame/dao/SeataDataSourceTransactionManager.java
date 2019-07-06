package org.springrain.frame.dao;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.lang.Nullable;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionStatus;
import org.springrain.frame.util.GlobalStatic;

import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransaction;
import io.seata.tm.api.GlobalTransactionContext;

/**
 * Spring 没有提供获取当前县城的事务状态,自定义ThreadLocal实现,在事务开始前记录.
 * 
 * @author caomei
 *
 */

public class SeataDataSourceTransactionManager extends DataSourceTransactionManager {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SeataDataSourceTransactionManager.class);

	@Override
	protected void doCommit(DefaultTransactionStatus status) {

		super.doCommit(status);
		Boolean begin = GlobalStatic.seataTransactionBegin.get();
		if (begin == null) {
			begin = false;
		}
		if (begin && RootContext.inGlobalTransaction()) {
			try {

				GlobalTransaction tx = GlobalTransactionContext.reload(RootContext.getXID());
				tx.commit();

			} catch (TransactionException txe) {
				logger.error(txe.getMessage(), txe);
			}

		}



	}

	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		super.doRollback(status);


		if (RootContext.inGlobalTransaction()) {
			try {
				GlobalTransaction tx = GlobalTransactionContext.reload(RootContext.getXID());
				tx.rollback();
			} catch (TransactionException txe) {
				logger.error(txe.getMessage(), txe);
			}

		}

	}

	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		super.doCleanupAfterCompletion(transaction);
		GlobalStatic.seataTransactionBegin.remove();
	}

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		super.doBegin(transaction, definition);
	}

	@Override
	protected Object doSuspend(Object transaction) {
		return super.doSuspend(transaction);
	}

	@Override
	protected void doResume(@Nullable Object transaction, Object suspendedResources) {
		super.doResume(transaction, suspendedResources);
	}



	@Override
	protected void doSetRollbackOnly(DefaultTransactionStatus status) {
		super.doSetRollbackOnly(status);
	}


	private void unbindtx() {

		if (!GlobalStatic.seataEnable) {
			return;
		}

		// 获取全局的xid
		String txGroupId = RootContext.getXID();
		if (RootContext.inGlobalTransaction() == false || StringUtils.isBlank(txGroupId)) {
			return;
		}
		String unbindXid = RootContext.unbind();
		if (!txGroupId.equalsIgnoreCase(unbindXid)) {
			if (unbindXid != null) {
				RootContext.bind(unbindXid);
			}
		}

	}

}
