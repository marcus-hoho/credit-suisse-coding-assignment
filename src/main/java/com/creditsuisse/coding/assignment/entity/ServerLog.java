package com.creditsuisse.coding.assignment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ServerLog")
public class ServerLog {

	public static final String STARTED_STATE = "STARTED";
	
	@Id
	@Column(name = "Id")
	private String id;

	@Column(name = "StartTime")
	private Long startTime;

	@Column(name = "EndTime")
	private Long endTime;

	@Column(name = "Duration")
	private Long duration;

	@Column(name = "Host")
	private String host;
	
	
	@Column(name = "Type")
	private String type;
	
	@Column(name = "Alert")
	private boolean alert;
	
	private String state;
	private Long timestamp;
	

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public Long getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	public Long getStartTime() {
		return startTime;
	}


	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}


	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	
	public Long getDuration() {
		return duration;
	}


	public void setDuration(Long duration) {
		this.duration = duration;
	}


	public String getHost() {
		return host;
	}


	public void setHost(String host) {
		this.host = host;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public boolean getAlert() {
		return alert;
	}


	public void setAlert(boolean alert) {
		this.alert = alert;
	}

	@Override
	public String toString() {
		return "ServerLog [Id=" + id + ", state=" + state + ", timestamp=" + timestamp+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", duration=" + duration+ ", host=" + host + ", type="+ type + ", alert=" + alert+"]";
	}
	
	
	
}
