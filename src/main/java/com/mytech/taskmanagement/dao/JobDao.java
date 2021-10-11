package com.mytech.taskmanagement.dao;

import com.mytech.taskmanagement.dao.entity.JobTask;
import com.mytech.taskmanagement.dao.entity.Task;
import com.mytech.taskmanagement.dao.repository.JobTaskRepository;
import com.mytech.taskmanagement.model.TaskRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Scanner;

@Component
public class JobDao {

    private static final String delimiter = "-";

    @Autowired
    private JobTaskRepository jobTaskRepository;

    public static String toString(InputStream inputStream) {
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(Calendar.getInstance().getTime());
    }

    /**
     * To generate SnapshotId
     *
     * @return
     */
    public static String generateSnapshotId(String siteName) {
        return siteName + delimiter + getCurrentTime();
    }

    public String createJob(String siteName) {
        JobTask jobTask = null;
        try {
            jobTask = toTaskFromDto(siteName);
            jobTaskRepository.save(jobTask);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return jobTask.getJobId();
    }


    public void updateTask(String taskId) {
        JobTask task =  jobTaskRepository.findById(taskId).orElseThrow(null);
        if(task != null){
            task.setJobStatus("Completed");
            jobTaskRepository.save(task);
        }

    }

    private JobTask toTaskFromDto(String siteName) {
        JobTask jobTask = new JobTask();
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        jobTask.setJobId(generateSnapshotId(siteName));
        jobTask.setSiteName(siteName);
        jobTask.setCreatedBy("Venku");
        jobTask.setCompletedTime(timestamp);
        jobTask.setStartTime(timestamp);
        jobTask.setJobStatus("Created");

        System.out.println("jobTask ===== id " + jobTask.getJobId());
        return jobTask;
    }
}
