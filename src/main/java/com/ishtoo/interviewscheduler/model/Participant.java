package com.ishtoo.interviewscheduler.model;

public class Participant {
	private String emailId;
	private String participantName;
	
	public Participant() {
		super();
	}
	public Participant(String emailId, String participantName) {
		super();
		this.emailId = emailId;
		this.participantName = participantName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}
	@Override
	public String toString() {
		return "Participant [emailId=" + emailId + ", participantName=" + participantName + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (obj==this) {
			return true;
		}
		if (obj == null || obj.getClass() != this.getClass()) { 
			return false;
		}
		else {
	        return ((Participant) obj).getEmailId().equals(this.emailId) && ((Participant) obj).getParticipantName().equals(this.participantName);
	    }
	}
}
