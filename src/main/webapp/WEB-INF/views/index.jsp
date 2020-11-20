<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
	<meta charset="ISO-8859-1">
	<title>Home Page</title>
</head>

<body>
	<h3>Home Page</h3>
	<div class="content-container">
		<p>Search to view Interview profile:</p>
		<form action="/viewInterviewProfile" method="post">
			<select data-placeholder="Search" class="js-example-basic-single" id="interviewId" name="interviewId"
				required="required">
				<option value="">Search Interview</option>
				<c:forEach var="interview" items="${allInterviews}">
					<option value="${interview.getInterviewId()}">${interview.getInterviewName()}
						(${interview.getInterviewId()})</option>
				</c:forEach>
			</select> <input type="submit">
		</form>
		<script type="text/javascript">
			$(document).ready(function () {
				$('.js-example-basic-single').select2();
			});
		</script>
		<br>
		<br>
		<script>
			$(document).ready(function () {
				$('#successMessage').delay(1000).fadeOut();
			});
		</script>
		<div class="alert-text" id="successMessage" style="color: blue;">${success}</div>
		<br>
		<div class="list-container">
			<div class="list-item">
				<a href="/addInterview">Add an Interview</a>
			</div>
			<br>
			<div class="list-item">
				<a href="/getUpcomingInterviews">View all Upcoming Interviews</a>
			</div>
			<br>
			<div class="list-item">
				<a href="/editInterview">Edit an Interview</a>
			</div>
			<br>
			<div class="list-item">
				<a href="/createParticipant">Create a Participant</a>
			</div>
			<br>
		</div>
	</div>
</body>

</html>