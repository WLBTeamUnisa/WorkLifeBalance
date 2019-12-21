<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Work Life Balance-Login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/ico" href="img/logo.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="css/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="css/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="css/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<!--===============================================================================================-->

</head>
<body>

<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-85 p-b-20">
				<form class="login100-form validate-form" action="LoginServlet">
					<span class="login100-form-title p-b-70"> Work Life Balance </span> <span class="login100-form-avatar"> <img
						src="img/logo.png" alt="AVATAR">
					</span>

					<div class="wrap-input100 validate-input m-t-85 m-b-35" data-validate="m.rossi1@wlb.it or 
					m.rossi1@wlbadmin.it">
						<input class="input100" type="text" id="email" name="email"/> 
						<span class="focus-input100" data-placeholder="Email"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-50" data-validate="Inserire una password 8-20 caratteri con almeno una maiuscola,minuscola e carattere speciale.
						Caratteri speciali consentiti: .!@#$%^&">
						<input class="input100" type="password" id="password" name="password"> 
						<span class="focus-input100" data-placeholder="Password" ></span>
					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn" type="submit">Accedi</button>
					</div>
				</form>

				<ul class="login-more mt-4">
					<li class="m-b-8"><span class="txt1"> Password </span> <a
						href="" class="txt2"> dimenticata? </a></li>
				</ul>
			</div>
		</div>
	</div>


	<div id="dropDownSelect1"></div>

	<!--===============================================================================================-->
	<script src="js/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="js/popper.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="js/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="js/moment.min.js"></script>
	<script src="js/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="js/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="js/login.js"></script>


</body>
</html>