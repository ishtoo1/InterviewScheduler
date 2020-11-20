package com.ishtoo.interviewscheduler.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ishtoo.interviewscheduler.dao.ResumeFileDao;

@Service
public class FileUploadService {
	
	@Autowired
	ResumeFileDao resumeFileDao;
	
	public void uploadFile(MultipartFile file, String emailId) throws IllegalStateException, IOException {
		String targetPath="C:\\Users\\ishtm\\OneDrive\\Documents\\UploadFolder\\" + file.getOriginalFilename();
		resumeFileDao.save(targetPath, emailId);
		file.transferTo(new File(targetPath));
	}
}
