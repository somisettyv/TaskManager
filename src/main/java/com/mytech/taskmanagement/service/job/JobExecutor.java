package com.mytech.taskmanagement.service.job;

import java.util.List;





public interface JobExecutor {

	void execute(List<String> cppRequests, final String fileName);
	
	void shutdownAndAwaitTermination();
	
	void wait(int millinsec);

}
