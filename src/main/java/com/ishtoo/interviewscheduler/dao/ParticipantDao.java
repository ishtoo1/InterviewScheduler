package com.ishtoo.interviewscheduler.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ishtoo.interviewscheduler.model.Participant;

@Repository
public class ParticipantDao {
	
	@Autowired
	JdbcTemplate template;
	
	public void save(Participant participant) {
		String sql="insert into Participant(emailId, participantName) values(?, ?)";
		template.update(sql, participant.getEmailId(), participant.getParticipantName());
	}
	
	public boolean checkIfParticipantAlreadyExists(String emailId) {
		String sql = "select exists (select * from Participant where emailId=?)";
		return template.queryForObject(sql, Boolean.class, new Object[] { emailId });
	}
	
	@SuppressWarnings("deprecation")
	public List<Participant> getAllParticipantsNotHavingInterviewInThisDuration(@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endTime) {
		String sql="select D.emailId, participantName "
				+ "from (select distinct B.emailId "
				+ "from (select * from Interview where endTime>? and startTime<?) as A "
				+ "inner join ParticipantInterviewRelation as B "
				+ "on A.interviewId=B.interviewId) as C "
				+ "right join Participant as D "
				+ "on D.emailId=C.emailId "
				+ "where C.emailId is null";
		DateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return template.query(sql, new Object[] { timestampFormat.format(startTime), timestampFormat.format(endTime) }, new BeanPropertyRowMapper<>(Participant.class));
	}
	
	public List<Participant> getAllParticipants() {
		String sql="select * from Participant";
		return template.query(sql, new BeanPropertyRowMapper<>(Participant.class));
	}

	@SuppressWarnings("deprecation")
	public List<Participant> findParticipantsGivingThisInterview(String interviewId) {
		String sql="select Participant.emailId, participantName from "
				+ "(select * from ParticipantInterviewRelation where interviewId=?) as A "
				+ "inner join Participant on A.emailId=Participant.emailId";
		return template.query(sql, new Object[] { interviewId }, new BeanPropertyRowMapper<>(Participant.class));
	}
}
