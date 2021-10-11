package com.mytech.taskmanagement.service;

import com.mytech.taskmanagement.connector.VoiceRestConnector;
import com.mytech.taskmanagement.dao.entity.Task;
import com.mytech.taskmanagement.dao.repository.TaskRepository;
import com.mytech.taskmanagement.model.TaskRequestDto;
import com.mytech.taskmanagement.model.TaskResponseDto;
import com.mytech.taskmanagement.service.job.JobTaskRunner;
import com.mytech.taskmanagement.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class TaskManagementServiceImpl implements TaskManagementService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    NotificationJobServiceImpl notificationJobServiceImpl;

    @Autowired
    JobTaskRunner jobTaskRunner;

    @Override
    public List<TaskResponseDto> getAllTasks(String zone) {
        List<TaskResponseDto> taskResponseDtoList = new ArrayList<>();
        taskRepository.findAll().forEach(e->{
             taskResponseDtoList.add(new TaskResponseDto(e.getTaskId(),e.getDescription(),
                     e.getCreatedBy(),
                     DateUtil.getZonedTime(e.getDueDate(),zone),
                     e.getReminderTime(),e.getPlace(),
                     e.getTaskStatus(),
                     e.getActionType(),
                     DateUtil.getZonedTime(e.getCreatedTime(),zone),
                     e.getReminderType()));
         });
        return taskResponseDtoList;
    }

    @Override
    public void saveTask(TaskRequestDto dto)  {
        try {
            jobTaskRunner.runConcurrentJob();
            //taskRepository.save(toTaskFromDto(dto,  new Task()));
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateTask(Long taskId, TaskRequestDto taskResponseDto) {
        Task task =  taskRepository.findById(taskId).orElseThrow(RuntimeException::new);
        toTaskFromDto(taskResponseDto, task);
        taskRepository.save(task);
    }

    @Override
     public void deleteTask(Long taskId) {
       taskRepository.deleteById(taskId);
     }

    private Task toTaskFromDto(TaskRequestDto e, Task task) {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        task.setTaskStatus("Created");
        if(task.getTaskId() == null) {
            task.setTaskId(new Random().nextLong());
        }
        task.setCreatedBy("Dheeraj");
        if(e.getDescription() != null) {
            task.setDescription(e.getDescription());
        }
        if(e.getDueDate() != null) {
            task.setDueDate(e.getDueDate());
        }
        if(e.getTaskStatus() != null) {
            task.setTaskStatus(e.getTaskStatus());
        }
        task.setPlace(e.getPlace());
        task.setReminderType(e.getReminderType());
        return task;
    }
}
