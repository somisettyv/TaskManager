package com.mytech.taskmanagement.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;


@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskRequestDto {

    private String description;
    private Timestamp dueDate;
    private int reminderTime;
    private String place;
    private String reminderType;
    private String taskStatus;

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReminderType() {
        return reminderType;
    }

    public void setReminderType(String reminderType) {
        this.reminderType = reminderType;
    }

    public Timestamp getDueDate() {
        return dueDate;
    }

    public void setDueDate(Timestamp dueDate) {
        this.dueDate = dueDate;
    }

    public int getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(int reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
