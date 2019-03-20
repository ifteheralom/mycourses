<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">

<title>Administration</title>

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
		<a class="navbar-brand" href="http://localhost:8080/mycourses/Login">MyCourses</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav mr-auto"></div>
			<div class="navbar-nav ml-auto"></div>
		</div>
	</nav>
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">Welcome <c:out value="${fullname}"></c:out></h1>
			<p class="lead">MyCourses Administration Desk</p>
		</div>
	</div>

	<div class="container">
		<div class="row">
			<div class="col-md-4">

				<div class="card">
					<div class="card-header">Add new Courses</div>
					<div class="card-body">
						<form method="post" action="AddCourse">
							<div class="form-group">
								<label for="formGroupExampleInput">Course ID</label> <input
									type="text" class="form-control" name="courseId"
									placeholder="Like CSE 101">
							</div>
							<div class="form-group">
								<label for="formGroupExampleInput2">Course Name</label> <input
									type="text" class="form-control" name="courseName"
									placeholder="Like Web Development">
							</div>
							<div class="form-group">
								<label for="formGroupExampleInput2">Course Teacher</label> <input
									type="text" class="form-control" name="courseTeacher"
									placeholder="Like Mr. Johnson">
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
						</form>
					</div>
				</div>
			</div>

			<div class="col-md-8">
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
									<form class="form-inline" method="post" action="RemoveCourse">
										<input type="hidden" name="id" value="${i[0]}">
										<button type="submit" class="btn btn-outline-danger btn-sm">Remove</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>

</body>
</html>