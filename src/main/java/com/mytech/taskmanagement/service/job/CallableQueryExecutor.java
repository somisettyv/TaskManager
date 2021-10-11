package com.mytech.taskmanagement.service.job;

import java.util.concurrent.Callable;





/**
 * 
 * This is callable wrapper class that invokes query executor 
 * 
 * @author vsomis956
 *
 */
public class CallableQueryExecutor implements Callable<JobStatus> {
	
	
	private String cppRequest;
	
	
	private QueryExecutor sequQueryExecutor;
 
	public CallableQueryExecutor(String cppRequest, QueryExecutor sequQueryExecutor) {
		this.cppRequest = cppRequest;
		this.sequQueryExecutor = sequQueryExecutor;
	}
 
	@Override
	public JobStatus call() {
		return sequQueryExecutor.executeQueries(cppRequest);
    }


}
