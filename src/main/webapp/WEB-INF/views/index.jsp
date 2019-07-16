<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%@ page import="com.nagarro.restapp.models.Flights"%>
<%@ page import="com.nagarro.restapp.models.User"%>





<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Search Page</title>
</head>
<script type="text/javascript">
var msg='${sessionScope.authorized}';
	console.log(msg);
	if (msg === "false" || msg === "" || msg === null) {console.log(msg) 
		window.location.href="/RestApp";
<%session.removeAttribute("authorized");%>
	}
</script>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">HR Management Portal </a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<li><a href="#">Page 1</a></li>
			<li><a href="#">Page 2</a></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li style="color:white"><a> Welcome ${sessionScope.user}</a>
			</li>
			<li><a style="cursor:pointer" onclick="<%session.removeAttribute("authorized");%>;window.location.href='/RestApp';
			"><span class="glyphicon glyphicon-log-in"></span>
					Logout</a></li>
		</ul>
	</div>
	</nav>

	<div class="container-fluid" style="margin-top: 30px">

		<button type="button" class="btn btn-success"  onclick="$('#myModal').modal('show');$('#editIDdiv').hide();" style="float:right" data-dismiss="modal">Add New Employee</button>

		<div class="col">

			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header" style="padding: 35px 50px;">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4>
								<span class="glyphicon glyphicon-lock"></span> User 
							</h4>
						</div>
						<div class="modal-body" style="padding: 40px 50px;">
							<form role="form">
								<div class="form-group" id="editIDdiv">
									<label for="psw"><span
										class="glyphicon glyphicon-eye-open"></span> ID </label> <input
										type="text" class="form-control" disabled id="editId"
										placeholder="Enter password">
								</div>
								<div class="form-group">
									<label for="usrname"><span
										class="glyphicon glyphicon-user"></span> Name</label> <input
										type="text" class="form-control" id="editName"
										placeholder="Enter NAme">
								</div>
								<div class="form-group">
									<label for="psw"><span
										class="glyphicon glyphicon-eye-open"></span> Email</label> <input
										type="email" class="form-control" id="editEmail"
										placeholder="Enter EMail">
								</div>
								<div class="form-group">
									<label for="psw"><span
										class="glyphicon glyphicon-eye-open"></span> Location</label> <input
										type="text" class="form-control" id="editLocation"
										placeholder="Enter Location">
								</div>
								<div class="form-group">
									<label for="psw"><span
										class="glyphicon glyphicon-eye-open"></span> DOB</label> <input
										type="date" class="form-control" id="editDOB"
										placeholder="Enter password">
								</div>
								<div class="modal-footer container-fluid">
									<div class="row">
										<button type="button" onclick="save()"
											class=" col-6 btn btn-success btn-block">
											<span class="glyphicon glyphicon-off"></span> Save
										</button>
										<button type="button" class=" col-6 btn btn-danger btn-block "
											class="close" data-dismiss="modal">
											<span class="glyphicon glyphicon-off"></span> Cancel
										</button>
									</div>
								</div>


							</form>
						</div>

					</div>

				</div>
			</div>
		</div>
		<table class="table" id="usertable">
			<thead class="thead-dark">
				<tr>
					<th scope="col">ID</th>

					<th scope="col">Name</th>
					<th scope="col">Email</th>
					<th scope="col">Location</th>

					<th scope="col">DOB</th>
					<th scope="col">Options</th>




				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>


		<div class="container-fluid" style="display: none; text-align: Center">
			<h3>No Flights Found</h3>
		</div>

	</div>
	</div>
	<script>
	var baseurl = "http://localhost:3000/RestApp/rest/Users/";
    
		function loadall() {
			console.log("loadcallled");
			$
					.ajax({
						url : baseurl + 'getallusers',
						type : "GET",
						beforeSend : function() {
							//  $('#current_page').append("loading..");
						},
						success : function(data) {
							$('#usertable > tbody')[0].innerHTML ="";

							JSON.parse(data).forEach(
											function(element) {
												$('#usertable > tbody')
														.append(
																'<tr><td>'
																		+ element['Id']
																		+ '</td><td>'
																		+ element['Name']
																		+ '</td><td>'
																		+ element['Email']
																		+ '</td><td>'
																		+ element['Location']
																		+ '</td><td>'
																		+ element['DOB']
																		+ '</td><td><button class="btn btn-success" onclick="edit('
																		+ element['Id']
																		+ ')" >Edit</button </td>&nbsp;<button class="btn btn-danger" onclick="delet('
																		+ element['Id']
																		+ ')" >Delete</button </td> </tr>');
											})
						}
					});

		}

		function edit(id) {
			console.log($('#myModal'));
			$('#myModal').modal('show');
			$.ajax({
				url : baseurl + 'getuser?id='+id,
				type : "GET",
				beforeSend : function() {
					//  $('#current_page').append("loading..");
				},
				success : function(data) {

					var data = JSON.parse(data);
					console.log(data[0]['Id']);
					console.log($('#editId'))
					$('#editId').val(data[0]['Id']);
					$('#editName').val(data[0]['Name']);
					$('#editEmail').val(data[0]['Email']);
					$('#editLocation').val(data[0]['Location']);
					$('#editDOB').val(data[0]['DOB'])
							
				}
			});
			
}

		function delet(id) {
			$.ajax({
				url : baseurl + 'deleteuser?id='+id,
				type : "GET",
				beforeSend : function() {
					//  $('#current_page').append("loading..");
				},
				success : function(data) {
					console.log(data)
					loadall();
				}
			});
			
}
		
		
		function save() {
			let url ="";
			if($('#editId').val()===""){
				url = baseurl + 'createuser?Email='+$('#editEmail').val()+'&Name='+$('#editName').val()+'&Location='+$('#editLocation').val()+'&DOB='+$('#editDOB').val();
			} else {
				url = baseurl + 'edituser?id='+$('#editId').val()+'&Email='+$('#editEmail').val()+'&Name='+$('#editName').val()+'&Location='+$('#editLocation').val()+'&DOB='+$('#editDOB').val();
			}
			$.ajax({
				url:url,
				type : "GET",
				beforeSend : function() {
					//  $('#current_page').append("loading..");
				},
				success : function(data) {
					console.log(data)	;
					$('#myModal').modal('hide');
					loadall();
				}
			});
			
		}
		


		$(document).ready(function() {
			loadall();
		});
	</script>
</body>
</html>