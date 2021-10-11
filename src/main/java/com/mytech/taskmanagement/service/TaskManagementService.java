package com.mytech.taskmanagement.service;

import com.mytech.taskmanagement.model.TaskRequestDto;
import com.mytech.taskmanagement.model.TaskResponseDto;

import java.io.IOException;
import java.util.List;

public interface TaskManagementService {
	 List<TaskResponseDto> getAllTasks(String zone);
	 void saveTask(TaskRequestDto task) throws IOException;
	 void updateTask(Long taskId, TaskRequestDto task);
	 void deleteTask(Long taskId);
}
