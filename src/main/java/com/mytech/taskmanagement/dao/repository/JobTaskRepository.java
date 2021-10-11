package com.mytech.taskmanagement.dao.repository;

import com.mytech.taskmanagement.dao.entity.JobTask;
import com.mytech.taskmanagement.dao.entity.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface JobTaskRepository extends CrudRepository<JobTask, String> {


	List<Task> findByJobStatus(String taskStatus);


}
