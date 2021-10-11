package com.mytech.taskmanagement.service.job;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.util.CollectionUtils;


/**
 * 
 * @author vsomis956
 *
 */
@Service
public class CallableJobExecutorImpl implements JobExecutor {

	private int threadCt = Runtime.getRuntime().availableProcessors() + 1;
	private ExecutorService executorService = Executors.newFixedThreadPool(threadCt);

	@Autowired
	private QueryExecutor sequQueryExecutor;

	private static final Logger log = LoggerFactory.getLogger(CallableJobExecutorImpl.class);

	
	public void execute(List<String> cppRequests, String fileName) {
		if(executorService.isTerminated()) {
			executorService = Executors.newFixedThreadPool(threadCt);
		}
		if(CollectionUtils.isEmpty(cppRequests)) {
			log.info("cppRequests List empty");
			return;
		}
		List<Future<JobStatus>> callableList = new ArrayList<Future<JobStatus>>();
		List<JobStatus> customerResponses = new ArrayList<JobStatus>();
		cppRequests.stream().forEach(e -> {
			//Validate Request
			// cppRequest.getCustomerIdentifiersMap().get(0)
			Callable<JobStatus> callable = new CallableQueryExecutor(e, sequQueryExecutor);
			Future<JobStatus> future = executorService.submit(callable);
			callableList.add(future);
		});

		for (Future<JobStatus> fut : callableList) {
			JobStatus customerResponse = null;
			try {
				customerResponse = fut.get();
			} catch (InterruptedException | ExecutionException e) {
				log.error("An Error Occured while executing the queries", e);
			}
			//JobStatus.add(CustomerResponses);
		}
		
		/*
		 * //Let us process the next file if (StringUtils.isNotBlank(fileName) &&
		 * requestManager.renameFile(fileName)) { requestProcessor.processRequest(); }
		 */		
		log.info("CustomerResponsess ==== ");
		//executorService.shutdown();
	}

	public void shutdownAndAwaitTermination() {
		executorService.shutdownNow();
		try {
			if (!executorService.awaitTermination(100, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
				if (!executorService.awaitTermination(100, TimeUnit.SECONDS))
					System.err.println("Pool did not terminate");
			}
		} catch (InterruptedException ie) {
			executorService.shutdownNow();
			Thread.currentThread().interrupt();
		}
	}
	

	@Override
	public void wait(int millinsec) {
		try {
			executorService.wait(millinsec);
		} catch (InterruptedException e) {
			log.error("Error in wait",e);
		}
		
	}

}
