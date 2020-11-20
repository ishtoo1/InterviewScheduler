package com.ishtoo.interviewscheduler.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Interview {
	private String interviewId;
	private String interviewName;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date startTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date endTime;

	public Interview() {
		super();
	}
	public Interview(String interviewId, String interviewName, Date startTime, Date endTime) {
		super();
		this.interviewId = interviewId;
		this.interviewName = interviewName;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	public String getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(String interviewId) {
		this.interviewId = interviewId;
	}
	public String getInterviewName() {
		return interviewName;
	}
	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Override
	public String toString() {
		return "Interview [interviewId=" + interviewId + ", interviewName=" + interviewName + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
}
