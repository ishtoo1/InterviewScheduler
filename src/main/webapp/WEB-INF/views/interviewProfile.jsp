<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Interview Profile</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
</head>

<body>
	<h3>Interview Profile</h3>
	<div class="content-container">
		<b>Interview Id:</b> ${interview.getInterviewId()} <br>
		<b>Interview Name:</b> ${interview.getInterviewName()} <br>
		<b>Interview Start Time:</b> ${interview.getStartTime()} <br>
		<b>Interview End Time:</b> ${interview.getEndTime()}<br>

		<b>List of Participants in this Interview:</b> <br><br>
		<table border="1">
			<tr>
				<th>Email Id</th>
				<th>Participant Name</th>
			</tr>
			<c:forEach items="${allParticipantsGivingThisInterview}" var="participant">
				<tr>
					<td>${participant.getEmailId()}</td>
					<td>${participant.getParticipantName()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>