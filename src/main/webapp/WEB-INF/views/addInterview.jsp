<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Add Interview</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
</head>

<body>
	<h3>Interview Scheduler</h3>
	<div class="content-container">
		<div class="form-container">
			<form action="" method="post">
				<div class="form-item">
					<label for="interviewId">Interview Id: </label>
					<input class="text-input" type="text" id="interviewId" name="interviewId" maxlength="10"
						required="required"><br>
				</div>
				<div class="form-item">
					<label for="interviewName">Interview Name: </label>
					<input class="text-input" type="text" id="interviewName" name="interviewName" maxlength="50"
						required="required"><br>
				</div>
				<div id="startAndEndTime">
					<div class="form-item">
						<label for="startTime">Start Time: </label>
						<input class="text-input" type="datetime-local" id="startTime" name="startTime"
							required="required"><br>
					</div>
					<div class="form-item">
						<label for="endTime">End Time: </label>
						<input class="text-input" type="datetime-local" id="endTime" name="endTime"
							required="required"><br>
					</div>
				</div>
				<div class="form-item">
					<label for="participants">Participants: </label>
					<select data-placeholder="Choose Participants" multiple="multiple" class="js-example-basic-multiple"
						id="participants" name="participants" required="required">
						<option value="">Choose Participants</option>
						<c:forEach var="participant" items="${allParticipants}">
							<option value="${participant.getEmailId()}">${participant.getParticipantName()}
								(${participant.getEmailId()})</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<center><input class="submit-button" type="submit"></center>
			</form>
		</div>
		<p class="alert-text" style="color: red;">${error}</p>
	</div>
	<script type="text/javascript">
		$(document).ready(function () {
			$('.js-example-basic-multiple').select2();
		});
	</script>

	<script type="text/javascript">
		$(document).ready(function () {
			function timeSelectOnChange() {
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				var startTimeModified = Date.parse(startTime);
				var today = new Date();
				if (startTime.length > 0 && today > startTimeModified) {
					alert("Start Time should be greater than the Current Time");
					$('#participants').find('option').remove();
					return;
				}
				if (startTime.length == 0 || endTime.length == 0) {
					return;
				}
				if (endTime < startTime) {
					alert("End Time should be greater than Start Time");
					$('#participants').find('option').remove();
					return;
				}
				var data = {};
				var url = "/getParticipantsOnBasisOfStartAndEndTime/" + startTime + "/" + endTime;
				console.log(url);
				$.ajax({
					url: url,
					type: "post",
					data: data,
					success: function (data, status, xhr) {
						$('#participants').find('option').remove();
						$('#participants').append('<option value="">Choose Participants</option>');
						console.log(data);
						for (var i = 0; i < data.length; i++) {
							$('#participants').append('<option value = "' + data[i].emailId + '">' + data[i].participantName + ' (' + data[i].emailId + ')' + '</option>');
						}
					},
					error: function (xhr, status, err) {
						console.log(xhr);
						console.log(status);
						console.log(err);
					}
				});
			}
			$(function () {
				$("#startAndEndTime").change(timeSelectOnChange);
			});
		})
	</script>
</body>

</html>