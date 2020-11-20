package com.ishtoo.interviewscheduler.model;

public class ResumeFile {
	private int fileId;
	private String emailId;
	private String filePath;
	
	public ResumeFile() {
		super();
	}
	public ResumeFile(int fileId, String emailId, String filePath) {
		super();
		this.fileId = fileId;
		this.emailId = emailId;
		this.filePath = filePath;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	@Override
	public String toString() {
		return "ResumeFile [fileId=" + fileId + ", emailId=" + emailId + ", filePath=" + filePath + "]";
	}
}
