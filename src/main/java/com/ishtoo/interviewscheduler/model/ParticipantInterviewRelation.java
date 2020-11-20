package com.ishtoo.interviewscheduler.model;

public class ParticipantInterviewRelation {
	private String emailId;
	private String interviewId;
	
	public ParticipantInterviewRelation() {
		super();
	}
	public ParticipantInterviewRelation(String emailId, String interviewId) {
		super();
		this.emailId = emailId;
		this.interviewId = interviewId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(String interviewId) {
		this.interviewId = interviewId;
	}
	@Override
	public String toString() {
		return "ParticipantInterviewRelation [emailId=" + emailId + ", interviewId=" + interviewId + "]";
	}
}
