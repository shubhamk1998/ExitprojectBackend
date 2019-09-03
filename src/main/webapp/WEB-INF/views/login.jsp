<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
 <script type="text/javascript" src="https://www.google.com/recaptcha/api.js" defer></script>


<style>
body {font-family: Arial, Helvetica, sans-serif;}

</style>
<title>Login Page</title>
<%@ page isELIgnored="false" %>
</head>

	<jsp:include page="header.jsp" />

<body>
  <form class="modal-content animate" id="postform"  action="Login" method="post" style="text-align:center">
    <div class="imgcontainer">
      <h2>Login </h2>
    </div>
	
    <div class="container">
      <input type="text"  class="form-control" placeholder="Enter Username" name="username" id="username" minlength=5 maxlength=50 >
      <span class="alert" id="usernamealert"></span>
		<br>
      <input type="password"  class="form-control" placeholder="Enter Password" name="password" id="password" minlength=5 maxlength=50  >
      <span class="alert" id="passwordalert"></span>
        
        
        <div>
                <div class="g-recaptcha" data-callback="enablelogin" style="margin:auto" data-sitekey="6LekSrUUAAAAAGpyCyNrrhXY6ME-U_SWnwqIz3eg"></div>
        
        </div>
      <br/>
  
      <button type="button" class="btn btn-success"  id="login" disabled onclick="post()">Login</button>
      <br>
      <div id="alert"    >${sessionScope.message} </div>
     
    </div>

  </form>
  <jsp:include page="footer.jsp" />
  
</body>
<script type="text/javascript">


function enablelogin() {
    $('#login').removeAttr('disabled');
}


function  post() {
	var x= false;
	if($('#username').val()==="") {
		x= true;
		$('#usernamealert').text("Username is required");
	}
	if($('#password').val()==="") {
		x=true;
		$('#passwordalert').text("Password is required");
	}
	if(x) {
		return;
	}	else {
		console.log($('#postform'),$('#username').val(),$('#password').val());
	    $('#postform').submit();
	}
	
}

var msg='<%session.getAttribute("authorized");%>' ;
if(msg==='false') {
	console.log("dsdshjfguy")

	$('#alert').text('Wrong Credentials');
    $('#alert').show();

}



<% %>

</script>

</html>