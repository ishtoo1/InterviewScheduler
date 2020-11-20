<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Upcoming Interviews</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
</head>

<body>
	<h3>Upcoming Interviews</h3>
	<div class="content-container">
		<p><b>List of Upcoming Interviews: </b></p><br><br>
		<table border="1">
			<tr>
				<th>Interview Id</th>
				<th>Interview Name</th>
				<th>Start Time</th>
				<th>End Time</th>
			</tr>
			<c:forEach items="${allUpcomingInterviews}" var="interview">
				<tr>
					<td><a href="/interview/${interview.getInterviewId()}">${interview.getInterviewId()}</a></td>
					<td>${interview.getInterviewName()}</td>
					<td>${interview.getStartTime()}</td>
					<td>${interview.getEndTime()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>