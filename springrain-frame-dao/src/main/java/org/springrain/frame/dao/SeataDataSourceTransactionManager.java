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
 * seata和spring事务混合使用,spring事务开启-->seata事务开启-->spring事务提交-->seata事务提交.
 * 虽然存在提交或者回滚时状态不一致的风险,但是无注解,可以动态开启seata事务.敏感操作建议使用@GlobalTransaction注解
 * 
 * @author caomei
 *
 */

public class SeataDataSourceTransactionManager extends DataSourceTransactionManager {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(SeataDataSourceTransactionManager.class);

	@Override
	protected void doCommit(DefaultTransactionStatus status) {

		// 先提交spring事务.
		super.doCommit(status);

		// 当前线程是否是seata的创建者,和spring事务存在同步风险,需要记录好日志.敏感操作建议使用@GlobalTransaction注解
		Boolean begin = GlobalStatic.seataTransactionBegin.get();
		if (begin == null) {
			begin = false;
		}
		if (begin && RootContext.inGlobalTransaction()) {
			try {
				// 分支事务执行把,把事务角色修改成了GlobalTransactionRole.Participant,reload重新设置成GlobalTransactionRole.Launcher
				GlobalTransaction tx = GlobalTransactionContext.reload(RootContext.getXID());
				tx.commit();

			} catch (TransactionException txe) {
				logger.error(txe.getMessage(), txe);
			}

		}



	}

	@Override
	protected void doRollback(DefaultTransactionStatus status) {
		// 先回滚spring事务
		super.doRollback(status);

		// 回滚seata事务.
		if (RootContext.inGlobalTransaction()) {
			try {
				// 分支事务执行把,把事务角色修改成了GlobalTransactionRole.Participant,reload重新设置成GlobalTransactionRole.Launcher
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
