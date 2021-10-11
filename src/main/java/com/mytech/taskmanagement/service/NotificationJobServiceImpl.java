package com.mytech.taskmanagement.service;

import com.mytech.taskmanagement.connector.VoiceRestConnector;
import com.mytech.taskmanagement.dao.entity.Task;
import com.mytech.taskmanagement.dao.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class NotificationJobServiceImpl implements NotificationJobService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    private VoiceRestConnector voiceRestConnector;

    @Value("${executor.waittime.ms: 30000}")
    int executorWaitTime;



    /**
     * loop forever unless getNextUnprocessedFileName() throws exception - in this case we cannot
     * continue since it may get into an endless loop trying to process the same file.
     */
    @Override
    public void processRequest() {
        boolean isCatastrophicFailure = false;
        int sleepTimeInSeconds = executorWaitTime / 1000;
        while (!isCatastrophicFailure) {
            try {
                log.info("Start sending Notification loop");
                sendRemainders();
                log.info("Ended sending Notification loop");

                log.info("Sleep for " + sleepTimeInSeconds + " seconds then check for more files.");
                try {
                    log.info("Sleep for " + sleepTimeInSeconds + " seconds then check for more files.");
                    Thread.sleep(executorWaitTime);
                } catch (InterruptedException e) {
                    log.info("Thread interrupted ... continue", e.getMessage());
                }
            } catch (Exception e) {
                log.error("failed to process files", e);
                isCatastrophicFailure = true;
            }
        }
        System.exit(2);
    }

    @Override
    public void sendRemainders() {
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        List<Task> taskList = taskRepository.findByDueDateBefore(timestamp);
        taskList.stream()
                .filter(e -> e.getTaskStatus().equalsIgnoreCase("Created"))
                .forEach(
                        e -> {
                            try {
                                log.info("Send Notification to " + e.getTaskId());
                                voiceRestConnector.postVoiceRemainderWithOkhttp(e);
                                e.setTaskStatus("ReminderSent");
                                taskRepository.save(e);
                            } catch (IOException ioException) {
                                throw new RuntimeException(ioException.getMessage());
                            }
                        });
    }
}
