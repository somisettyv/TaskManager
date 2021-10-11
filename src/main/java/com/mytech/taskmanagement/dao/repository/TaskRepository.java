package com.mytech.taskmanagement.dao.repository;

import com.mytech.taskmanagement.dao.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {


	List<Task> findByTaskStatus(String taskStatus);
    List<Task> findByDueDateAfter(Timestamp timestamp);

	/**
	 * To get all the Tasks which are over due
	 * @param timestamp
	 * @return
	 */
	List<Task> findByDueDateBefore(Timestamp timestamp);


}
