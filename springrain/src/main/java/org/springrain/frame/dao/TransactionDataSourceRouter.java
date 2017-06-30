package org.springrain.frame.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.NoTransactionException;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 配合spirng的事务拦截器,如果有事务就是写库,没有事务就是读库
 * @author caomei
 *
 */
public class TransactionDataSourceRouter extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		
		
		try{
            TransactionInterceptor.currentTransactionStatus();
		}catch(NoTransactionException e){//没有事务,从库
			return "dataSourceRead";
		}
		return "dataSourceWrite";
	}
	

	


	

}
