<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Create Participant</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link href="/css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/css/select2.min.css" rel="stylesheet" />
	<script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-beta.1/dist/js/select2.min.js"></script>
</head>

<body>
	<h3>Create Participant</h3>
	<script>
		function validateemail() {
			var x = document.myform.emailId.value;
			var atposition = x.indexOf("@");
			var dotposition = x.lastIndexOf(".");
			if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= x.length) {
				alert("Please enter a valid e-mail address");
				return false;
			}
		}  
	</script>
	<div class="content-container">
		<div class="form-container">
			<form name="myform" action="" method="post" enctype="multipart/form-data"
				onsubmit="return validateemail();">
				<div class="form-item">
					<label for="emailId">Email Id: </label>
					<input class="text-input" type="text" id="emailId" name="emailId" maxlength="50"
						required="required"><br>
				</div>
				<div class="form-item">
					<label for="participantName">Participant Name: </label>
					<input class="text-input" type="text" id="participantName" name="participantName" maxlength="50"
						required="required"><br>
				</div>
				<div class="form-item">
					<label for="fileName">Upload Resume: </label>
					<input class="text-input" type="file" id="filename" name="filename" required="required">
				</div>
				<br>
				<center><input class="submit-button" type="submit"></center>
			</form>
		</div>

		<p class="alert-text" style="color: red;">${error}</p>
	</div>
</body>

</html>