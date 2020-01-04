<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Work Life Balance - Login</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/ico" href="img/logo.ico" />

<!-- CSS Files -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/atlantis.css">

<!-- Font -->
<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css">

<!-- Template -->
<link rel="stylesheet" type="text/css" href="css/util.css">
<link rel="stylesheet" type="text/css" href="css/login.css">

<script src="js/plugin/webfont/webfont.min.js"></script>
<script>
	WebFont.load({
		google : {
			"families" : [ "Lato:300,400,700,900" ]
		},
		custom : {
			"families" : [ "Flaticon", "Font Awesome 5 Solid",
					"Font Awesome 5 Regular", "Font Awesome 5 Brands",
					"simple-line-icons" ],
			urls : [ 'css/fonts.min.css' ]
		},
		active : function() {
			sessionStorage.fonts = true;
		}
	});
</script>
</head>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-t-85 p-b-20">
				<form class="login100-form validate-form" action="LoginServlet"
					method="POST">
					<span class="login100-form-title p-b-70"> Work Life Balance
					</span> <span class="login100-form-avatar"> <img src="img/logo.png"
						alt="AVATAR">
					</span>

					<div class="wrap-input100 validate-input m-t-85 m-b-35"
						data-validate="m.rossi1@wlb.it or 
					m.rossi1@wlbadmin.it">
						<input class="input100" type="text" id="email" name="email" /> <span
							class="focus-input100" data-placeholder="Email"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-50"
						data-validate="Inserire una password 8-20 caratteri con almeno una maiuscola,minuscola e carattere speciale.
						Caratteri speciali consentiti: .!@#$%^&">
						<input class="input100" type="password" id="password"
							name="password"> <span class="focus-input100"
							data-placeholder="Password"></span>
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

	<!--   Core JS Files   -->
	<script src="js/core/jquery.3.2.1.min.js"></script>
	<script src="js/core/popper.min.js"></script>
	<script src="js/core/bootstrap.min.js"></script>

	<!-- jQuery UI -->
	<script src="js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script src="js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>
		
	<!-- jQuery Scrollbar -->
	<script src="js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>
	
	<!-- Sweet Alert -->
	<script src="js/sweetalert2.all.js"></script>

	<!-- Atlantis JS -->
	<script src="js/atlantis.min.js"></script>
	
	<script src="js/login.js"></script>


</body>
</html>