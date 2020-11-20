package com.ishtoo.interviewscheduler.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ishtoo.interviewscheduler.dao.InterviewDao;
import com.ishtoo.interviewscheduler.dao.ParticipantDao;
import com.ishtoo.interviewscheduler.dao.ParticipantInterviewRelationDao;
import com.ishtoo.interviewscheduler.model.Interview;
import com.ishtoo.interviewscheduler.model.Participant;
import com.ishtoo.interviewscheduler.service.FileUploadService;
import com.ishtoo.interviewscheduler.service.SendEmailService;

@Controller
public class AdminController {
	
	@Autowired
	ParticipantDao participantDao;
	
	@Autowired
	InterviewDao interviewDao;
	
	@Autowired
	ParticipantInterviewRelationDao participantInterviewRelationDao;
	
	@Autowired
	SendEmailService sendEmailService;
	
	@Autowired
	FileUploadService fileUploadService;
	
	@GetMapping("/")
	public String home(Model m) {
		m.addAttribute("allInterviews", interviewDao.getInterviews());
		return "index";
	}
	
	@PostMapping(path = "/getParticipantsOnBasisOfStartAndEndTime/{startTime}/{endTime}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Participant> findParticipantsFreeInThisDuration(@PathVariable("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startTime, @PathVariable("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endTime) {
		List<Participant> allParticipants = participantDao.getAllParticipantsNotHavingInterviewInThisDuration(startTime, endTime);
		return allParticipants;
	}
	
	@GetMapping("/addInterview")
	public String addInterview() {
		return "addInterview";
	}
	
	@PostMapping("/addInterview")
	public String checkAddInterview(@ModelAttribute Interview interview, @RequestParam("participants") List<String> participants, RedirectAttributes redirectAttributes) {
		if (interviewDao.checkIfInterviewAlreadyExists(interview.getInterviewId())) {
			redirectAttributes.addFlashAttribute("error", "Interview with this Id already exists");
			return "redirect:/addInterview";
		}
		if (participants.size() < 2) {
			redirectAttributes.addFlashAttribute("error", "Participants in an interview cannot be less than 2!");
			return "redirect:/addInterview";
		}
		interviewDao.save(interview);
		participantInterviewRelationDao.save(interview.getInterviewId(), participants);
		sendEmailService.sendEmail(participants, "An Interview has been scheduled for you from " + interview.getStartTime() + " to " + interview.getEndTime() + ". Kindly note the same.", "Interview Schedule");
		redirectAttributes.addFlashAttribute("success", "Operation Successful!");
		return "redirect:/";
	}
	
	@GetMapping("/getUpcomingInterviews")
	public String allUpcomingInterviews(Model m) {
		m.addAttribute("allUpcomingInterviews", interviewDao.getUpcomingInterviews());
		return "upcomingInterviews";
	}
	
	@GetMapping("/interview/{interviewId}")
	public String viewInterview(@PathVariable("interviewId") String interviewId, Model m) {
		m.addAttribute("interview", (Interview) interviewDao.findById(interviewId));
		m.addAttribute("allParticipantsGivingThisInterview", participantDao.findParticipantsGivingThisInterview(interviewId));
		return "interviewProfile";
	}
	
	@PostMapping(path = "/getInterviewById/{interviewId}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> findStartAndEndTimeForThisInterview(@PathVariable("interviewId") String interviewId) {
		Interview interview=interviewDao.findById(interviewId);
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("interview", interview);
		map.put("participantsAlready", participantDao.findParticipantsGivingThisInterview(interviewId));
		map.put("freeParticipants", participantDao.getAllParticipantsNotHavingInterviewInThisDuration(interview.getStartTime(), interview.getEndTime()));
		return map;
	}
	
	@PostMapping(path = "/getParticipantsOnBasisOfStartAndEndTime/{interviewId}/{startTime}/{endTime}", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Map<String, Object> findParticipantsFreeInThisDuration(@PathVariable("interviewId") String interviewId, @PathVariable("startTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date startTime, @PathVariable("endTime") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") Date endTime) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<Participant> participantsAlready=participantDao.findParticipantsGivingThisInterview(interviewId);
		List<Participant> freeParticipants=participantDao.getAllParticipantsNotHavingInterviewInThisDuration(startTime, endTime);
		map.put("participantsAlready", participantsAlready);
		freeParticipants.removeAll(participantsAlready);
		map.put("freeParticipants", freeParticipants);
		return map;
	}
	
	@GetMapping("/editInterview")
	public String editInterview(Model m) {
		m.addAttribute("allUpcomingInterviews", interviewDao.getUpcomingInterviews());
		return "editInterview";
	}
	
	@PostMapping("/editInterview")
	public String checkEditInterview(@ModelAttribute Interview interview, @RequestParam("participants") List<String> participants, RedirectAttributes redirectAttributes) {
		if (participants.size() < 2) {
			redirectAttributes.addFlashAttribute("error", "Participants in an interview cannot be less than 2!");
			return "redirect:/addInterview";
		}
		interviewDao.delete(interview.getInterviewId());
		interviewDao.save(interview);
		participantInterviewRelationDao.save(interview.getInterviewId(), participants);
		sendEmailService.sendEmail(participants, "An Interview has been scheduled for you from " + interview.getStartTime() + " to " + interview.getEndTime() + ". Kindly note the same.", "Interview Schedule");
		redirectAttributes.addFlashAttribute("success", "Operation Successful!");
		return "redirect:/";
	}
	
	@PostMapping("/viewInterviewProfile")
	public String directToInterviewProfile(@RequestParam("interviewId") String interviewId) {
		return "redirect:/interview/" + interviewId;
	}
	
	@GetMapping("/createParticipant")
	public String createParticipant() {
		return "createParticipant";
	}
	
	@PostMapping("/createParticipant")
	public String checkCreateParticipant(@ModelAttribute Participant participant, @RequestParam("filename") MultipartFile file, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
		if (participantDao.checkIfParticipantAlreadyExists(participant.getEmailId())) {
			redirectAttributes.addFlashAttribute("error", "Participant with this EmailId already exists");
			return "redirect:/createParticipant";
		}
		participantDao.save(participant);
		fileUploadService.uploadFile(file, participant.getEmailId());
		redirectAttributes.addFlashAttribute("success", "Operation Successful!");
		return "redirect:/";
	}
	
}
