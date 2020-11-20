package com.ishtoo.interviewscheduler.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.interviewscheduler.model.Interview;

@Repository
public class InterviewDao {
	
	@Autowired
	JdbcTemplate template;
	
	public void save(Interview interview) {
		String sql="insert into Interview(interviewId, interviewName, startTime, endTime) values(?, ?, ?, ?)";
		DateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		template.update(sql, interview.getInterviewId(), interview.getInterviewName(), timestampFormat.format(interview.getStartTime()), timestampFormat.format(interview.getEndTime()));
	}

	@SuppressWarnings("deprecation")
	public List<Interview> getUpcomingInterviews() {
		String sql="select * from Interview where startTime>=?";
		DateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return template.query(sql, new Object[] { timestampFormat.format(new Date()) }, new BeanPropertyRowMapper<>(Interview.class));
	}

	@SuppressWarnings("deprecation")
	public Interview findById(String interviewId) {
		String sql="select * from Interview where interviewId=?";
		return (Interview) template.queryForObject(sql, new Object[] { interviewId }, new BeanPropertyRowMapper<>(Interview.class));
	}

	public boolean checkIfInterviewAlreadyExists(String interviewId) {
		String sql = "select exists (select * from Interview where interviewId=?)";
		return template.queryForObject(sql, Boolean.class, new Object[] { interviewId });
	}
	
	public void delete(String interviewId) {
		String sql="delete from Interview where interviewId=?";
		template.update(sql, interviewId);
	}
	
	public List<Interview> getInterviews() {
		String sql="select * from interview";
		return template.query(sql, new BeanPropertyRowMapper<>(Interview.class));
	}
}
