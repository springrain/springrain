package org.springrain.frame.dao;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class FrameDataSourceTransactionManager extends DataSourceTransactionManager {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {

		
		Integer transactionIsolationLevel=definition.getIsolationLevel() != TransactionDefinition.ISOLATION_DEFAULT ? definition.getIsolationLevel() : null;
		
		if(transactionIsolationLevel!=null) {
			TransactionSynchronizationManager.setCurrentTransactionIsolationLevel(transactionIsolationLevel);
		}
		

		super.doBegin(transaction, definition);
	}

}
