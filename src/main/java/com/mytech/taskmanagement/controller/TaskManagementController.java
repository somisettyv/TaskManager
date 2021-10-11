package com.mytech.taskmanagement.controller;

import com.mytech.taskmanagement.model.TaskRequestDto;
import com.mytech.taskmanagement.model.TaskResponseDto;
import com.mytech.taskmanagement.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Controller
public class TaskManagementController {

	@Autowired
	TaskManagementService taskManagementService;

	@GetMapping(value = "/mytech/task")
	public ResponseEntity<List<TaskResponseDto>> getAllTasks(@RequestParam(value = "zone", required = false) String zone) {
		List<TaskResponseDto> taskResponseDtoList = taskManagementService.getAllTasks(zone);
		return new ResponseEntity<>(taskResponseDtoList, HttpStatus.OK);
	}

	@PostMapping("/mytech/task")
	public ResponseEntity<Void> addTask(@RequestBody @NotNull TaskRequestDto taskResponseDto) throws IOException {
		taskManagementService.saveTask(taskResponseDto);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/mytech/task/{taskId}")
	public ResponseEntity<Void> updateTask(@PathVariable Long taskId, @RequestBody @NotNull TaskRequestDto taskResponseDto) {
		taskManagementService.updateTask(taskId, taskResponseDto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/mytech/task/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
		taskManagementService.deleteTask(taskId);
		return ResponseEntity.noContent().build();
	}
}
