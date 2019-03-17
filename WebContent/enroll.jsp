<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">MyCourses</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav mr-auto"></div>
			<div class="navbar-nav ml-auto">
				<a class="nav-item nav-link"
					href="http://localhost:8080/mycourses/Student">My Courses</a> 
				<a class="nav-item nav-link active"
					href="http://localhost:8080/mycourses/Enroll">Enroll for
					courses</a>
			</div>
		</div>
	</nav>
	<br>
	<div class="container">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<th scope="col">Course ID</th>
					<th scope="col">Course Name</th>
					<th scope="col">Course Teacher</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.courses}" var="i">
					<tr>
						<td>${i[0]}</td>
						<td>${i[1]}</td>
						<td>${i[2]}</td>
						<td>
							<form class="form-inline" method="post" action="EnrollCourse">
								<input type="hidden" name="courseId" value="${i[0]}">
								<input type="hidden" name="courseName" value="${i[1]}">
								<input type="hidden" name="courseTeacher" value="${i[2]}">
								<button type="submit" class="btn btn-outline-primary btn-sm">Enroll for this course</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>