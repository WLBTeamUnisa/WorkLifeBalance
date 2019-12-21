<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>WLB - Registrazione Dipendente</title>
<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no' name='viewport' />
<link rel="icon" href="img/icon.ico" type="image/x-icon" />

<!-- CSS Files -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/atlantis.css">

<!-- Fonts and icons -->
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
	<div class="wrapper">
		<jsp:include page="header.jsp" />
		<div class="main-panel">
			<!-- CORPO PAGINA-->
			<div class="content" style="display: flex; align-items: center;">
				<div class="container mt-4 text-center">
					<div class="col-lg-7 mx-auto">
						<div class="card">
							<div class="card-header">
								<h3>
									<bold>Registrazione</bold>
								</h3>
							</div>


							<div class="registration-fields">
								<form name="registration" method="post" action="/WorkLifeBalance/EmployeeRegistrationServlet">
									<div class="form-group input-group">
										<div class="input-group-prepend">
											<span class="input-group-text">
												<i class="fa fa-user">
												</i>
											</span>
										</div>
										<input name="Name" id="Name" oninput="validName()" class="form-control" placeholder="Nome*" type="text" required>
									</div>
									<!-- form-group// Name -->
									<span id="errorName"> 
									</span>

									<div class="form-group input-group">
										<div class="input-group-prepend"> 
											<span class="input-group-text"> 
												<i class="fa fa-user">
												</i>
											</span>
										</div>
										<input name="Surname" id="Surname" oninput="validSurname()" class="form-control" placeholder="Cognome*" type="text" required>
									</div>
									<!-- form-group// Last name -->
									<span id="errorSurname"></span>

									<div class="form-group input-group">
										<div class="input-group-prepend"> 
											<span class="input-group-text"> 
												<i class="fa fa-envelope">
												</i>
											</span>
										</div>
										<input name="email" id="Email" oninput="validEmail()" class="form-control" placeholder="Email*" type="email" required>
									</div>
									<!-- form-group//  Email -->
									<span id="errorEmail"></span>

									<div class="form-group input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"> 
												<i class="fas fa-key">
												</i>
											</span>
										</div>
										<input name="password" id="Password" oninput="validPassword()"  class="form-control" placeholder="Password*" type="password" required>
									</div>
									<!-- form-group//   Password -->


									<div class="form-group input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"> 
												<i class="fas fa-key">
												</i>
											</span>
										</div>
										<input name="verifyPassword" id="VerifyPassword" oninput="validPassword()" class="form-control" placeholder="Conferma password*" type="password" required>
									</div>
									<!-- form-group// Verify Password  -->
									<span id="errorPassword"></span>


									<div class="form-group input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"> 
												<i class="fas fa-user-tie">
												</i>
											</span>
										</div>
										<select name="status" class="custom-select" id="inputGroupSelect03" required>
											<option value="employee">Dipendente</option>
											<option value="manager">Manager</option>
										</select>
									</div>
									<!-- form-group// Status choose -->

									<span id="errorRegistrationForm"></span>
									<div class="form-group">
										<span class="rounded-icon">
											<button type="submit" id="registrationButton" class="btn btn btn-success btn-block" disabled>
												Registra
											</button>
										</span>
									</div>
									<!-- form-group//  Register button-->
									
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="footer.jsp" />
	</div>
	</div>
	<!--   Core JS Files   -->
	<script src="js/core/jquery.3.2.1.min.js"></script>
	<script src="js/core/popper.min.js"></script>
	<script src="js/core/bootstrap.min.js"></script>

	<!-- jQuery UI -->
	<script src="js/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js"></script>
	<script
		src="js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>

	<!-- jQuery Scrollbar -->
	<script src="js/plugin/jquery-scrollbar/jquery.scrollbar.min.js"></script>


	<!-- jQuery Sparkline -->
	<script src="js/plugin/jquery.sparkline/jquery.sparkline.min.js"></script>

	<!-- Datatables -->
	<script src="js/plugin/datatables/datatables.min.js"></script>

	<!-- Bootstrap Notify -->
	<script src="js/plugin/bootstrap-notify/bootstrap-notify.min.js"></script>

	<!-- Sweet Alert -->
	<script src="js/plugin/sweetalert/sweetalert.min.js"></script>

	<!-- Atlantis JS -->
	<script src="js/atlantis.min.js"></script>

</body>

<script type="text/javascript">
	var nameOk = false;
	var surnameOk = false;
	var emailOk = false;
	var passwordOk = false;

	function validName() {

		var input = document.querySelector("#Name");

		var msgError = "La sintassi del nome non è corretta";
		var nameValue = input.value;
		if (nameValue.length >= 2 && nameValue.length <= 20 && input.value.match(/^[A-Za-z]+$/)) {
			if (input.classList.contains("is-invalid"))
				input.classList.remove("is-invalid");
			input.classList.add("is-valid");
			document.getElementById("errorName").innerHTML = "";
			nameOk = true;
		} else {
			if (input.classList.contains("is-valid"))
				input.classList.remove("is-valid");
			input.classList.add("is-invalid");
			document.getElementById("errorName").innerHTML = msgError;
			nameOk = false;
		}
		checkForm();

	}
	function validSurname(form) {

		var input = document.querySelector("#Surname");

		var msgError = "La sintassi del cognome non è corretta";
		var surnameValue = input.value;
		if (surnameValue.length >= 2 && surnameValue.length <= 20 && input.value.match(/^[A-Za-z\s]+$/)) {
			if (input.classList.contains("is-invalid"))
				input.classList.remove("is-invalid");
			input.classList.add("is-valid");
			document.getElementById("errorSurname").innerHTML = "";
			surnameOk = true;
		} else {
			if (input.classList.contains("is-valid"))
				input.classList.remove("is-valid");
			input.classList.add("is-invalid");
			document.getElementById("errorSurname").innerHTML = msgError;
			surnameOk = false;
		}
		checkForm();

	}
	function validEmail() {
	
		var ok = "<ok/>";
		var input = document.querySelector("#Email");
		var msgError = "La sintassi dell'email non è corretta";
		var emailValue = input.value;
		if (emailValue.length >= 5 && emailValue.length <= 30 && input.value.match(/^[a-z]{1}\.[a-z]+[1-9]*\@wlb.it$/)) {
			var xmlHttpReq = new XMLHttpRequest();
			xmlHttpReq.onreadystatechange = function() {
				window.alert(xmlHttpReq.responseText);
				if (xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText == ok) {
					if (input.classList.contains("is-invalid"))
						input.classList.remove("is-invalid");
					input.classList.add("is-valid");
					document.getElementById("errorEmail").innerHTML = "";
					emailOk = true;
				} else if (xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200 && xmlHttpReq.responseText != ok) {
					if (input.classList.contains("is-valid"))
						input.classList.remove("is-valid");
					input.classList.add("is-invalid");
					document.getElementById("errorEmail").innerHTML = "Attenzione! Questa email esiste già.";
					emailOk = false;
				}
				checkForm();
			}
			xmlHttpReq.open("GET", "/WorkLifeBalance/CheckEmployeeServlet?email=" + encodeURIComponent(input.value), true);
			xmlHttpReq.send();
		} else {
			if (input.classList.contains("is-valid"))
				input.classList.remove("is-valid");
			input.classList.add("is-invalid");
			document.getElementById("errorEmail").innerHTML = msgError;
			emailOk = false;
		}
		checkForm();
		
	}
	function validPassword() {
		var inputpw = document.querySelector("#Password");
		var inputpwconf = document.querySelector("#VerifyPassword");
		var password = inputpw.value;
		if (password.length >= 8 && password.toUpperCase() != password && password.toLowerCase() != password && password.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\.!@#\$%\^&\*]).{8,20}$/)) {
			if (inputpw.classList.contains("is-invalid"))
				inputpw.classList.remove("is-invalid");
			inputpw.classList.add("is-valid");

			if (password == inputpwconf.value) {
				if (inputpwconf.classList.contains("is-invalid"))
					inputpwconf.classList.remove("is-invalid");
				inputpwconf.classList.add("is-valid");
				document.getElementById("errorPassword").innerHTML = "";
				passwordOk = true;
			} else {
				if (inputpwconf.classList.contains("is-valid"))
					inputpwconf.classList.remove("is-valid");
				inputpwconf.classList.add("is-invalid");
				document.getElementById("errorPassword").innerHTML = "Le due password devono coincidere";
				passwordOk = false;
			}
		} else {
			if (inputpw.classList.contains("is-valid"))
				inputpw.classList.remove("is-valid");
			inputpw.classList.add("is-invalid");
			
			if (inputpwconf.classList.contains("is-valid"))
				inputpwconf.classList.remove("is-valid");
			inputpwconf.classList.add("is-invalid");
			
			document.getElementById("errorPassword").innerHTML = "La password deve contenere almeno una maiuscola, un numero, un carattere speciale valido ed almeno 8 caratteri";
			passwordOk = false;
		}
		checkForm();
	}
	
	function checkForm() {
		if (nameOk && surnameOk && emailOk && passwordOk) {
			document.getElementById("registrationButton").disabled = false;
			document.getElementById("errorRegistrationForm").innerHTML = "";
		} else {
			document.getElementById("registrationButton").disabled = true;
			document.getElementById("errorRegistrationForm").innerHTML = "Compila tutti i campi obbligatori";
		}

	}
</script>

</html>