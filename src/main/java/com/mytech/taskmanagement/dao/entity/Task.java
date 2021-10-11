package com.mytech.taskmanagement.dao.entity;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@Entity(name = "Task")
@AllArgsConstructor
public class Task {

	public Task() {
	}

	@Id
	@Column(name="Task_Id")
	private Long taskId;
	
	@Column(name="Description")
	private String description;
	@Column(name="Created_By")
	private String createdBy;
	@Column(name="Created_Time")
	private Timestamp createdTime;
	@Column(name="Due_Date")
	private Timestamp dueDate;
	@Column(name="Reminder_Time")
	private int reminderTime;
	@Column(name="Reminder_Type")
	private String reminderType;
	@Column(name="Place")
	private String place;
	@Column(name="Task_Status")
	private String taskStatus;
	@Column(name="Action_Type")
	private String actionType;

	public String getReminderType() {
		return reminderType;
	}

	public void setReminderType(String reminderType) {
		this.reminderType = reminderType;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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

	/*
	public String getRecurrenceType() {
		return recurrenceType;
	}

	public void setRecurrenceType(String recurrenceType) {
		this.recurrenceType = recurrenceType;
	}*/

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
}
