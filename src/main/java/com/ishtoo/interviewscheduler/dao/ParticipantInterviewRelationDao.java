package com.ishtoo.interviewscheduler.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ParticipantInterviewRelationDao {
	
	@Autowired
	JdbcTemplate template;
	
	public void save(String interviewId, List<String> participants) {
		String sql="insert into ParticipantInterviewRelation(emailId, interviewId) values(?, ?)";
		for (String participant: participants) {
			template.update(sql, participant, interviewId);
		}
	}
}
