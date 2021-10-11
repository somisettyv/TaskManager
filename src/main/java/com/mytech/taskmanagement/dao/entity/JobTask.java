package com.mytech.taskmanagement.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Job_Id char(50),
 * Site_Name char(50),
 * Created_By char(50),
 * Start_Time datetime,
 * Completed_Time datetime,
 * Job_Status char(50);
 */

@Entity(name = "Job_Task")
@AllArgsConstructor
@Setter
@Getter
public class JobTask {

	public JobTask() {
	}

	@Id
	@Column(name="Job_Id")
	private String jobId;
	@Column(name="Site_Name")
	private String siteName;
	@Column(name="Created_By")
	private String createdBy;
	@Column(name="Start_Time")
	private Timestamp startTime;
	@Column(name="Completed_Time")
	private Timestamp completedTime;
	@Column(name="Job_Status")
	private String jobStatus;


}
