package com.ishtoo.interviewscheduler.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ResumeFileDao {

	@Autowired
	JdbcTemplate template;
	
	public void save(String filePath, String emailId) {
		String sql="insert into ResumeFile(emailId, filePath) values(?, ?)";
		template.update(sql, emailId, filePath);
	}
}
