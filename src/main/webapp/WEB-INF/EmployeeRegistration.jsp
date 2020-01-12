<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="it">

<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>WLB - Registrazione Dipendente</title>
<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!-- Icon -->
<link rel="apple-touch-icon" sizes="57x57"
	href="img/favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60"
	href="img/favicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="img/favicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="img/favicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="img/favicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="img/favicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144"
	href="img/favicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="img/favicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180"
	href="img/favicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"
	href="img/favicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="img/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="img/favicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="img/favicon/favicon-16x16.png">
<link rel="manifest" href="img/favicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage"
	content="img/favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">

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
		<jsp:include page="Header.jsp" />
		<div class="main-panel">

			<!-- BODY PAGE-->
			<div class="content" style="display: flex; align-items: center;">

				<!-- CONTAINER -->
				<div class="container mt-4 text-center">

					<!-- COLUMN -->
					<div class="col-lg-9 mx-auto">

						<!-- CARD -->
						<div class="card">
							<div class="card-header">
								<h3>Registrazione dipendente</h3>
							</div>

							<!-- CARD-BODY -->
							<div class="card-body registration-fields">

								<!-- FORM -->
								<form name="registration" method="post"
									action="EmployeeRegistrationServlet">

									<!-- CONTAINER -->
									<div class="container">


										<!-- NAME -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fa fa-user"></i></span>
											</div>
											<input name="name" id="Name" oninput="validName()"
												class="form-control text-center" placeholder="Nome*"
												type="text" required>
										</div>
										<!-- form-group// Name -->
										<span id="errorName"> </span>


										<!-- SURNAME -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fa fa-user"></i></span>
											</div>
											<input name="surname" id="Surname" oninput="validSurname()"
												class="form-control text-center" placeholder="Cognome*"
												type="text" required>
										</div>
										<!-- form-group// Last name -->
										<span id="errorSurname"></span>


										<!-- EMAIL -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
													class="fa fa-envelope"></i></span>
											</div>
											<input name="email" id="Email" oninput="validEmail()"
												class="form-control text-center" placeholder="Email*"
												type="email" required>
										</div>
										<!-- form-group// Email -->
										<span id="errorEmail"></span>



										<!-- PASSWORD -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fas fa-key"></i></span>
											</div>
											<input name="password" id="Password"
												oninput="validPassword()" class="form-control text-center"
												placeholder="Password*" type="password" required>
										</div>
										<!-- form-group// Password -->


										<!-- PASSWORD CONFIRM -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fas fa-key"></i></span>
											</div>
											<input name="verifyPassword" id="VerifyPassword"
												oninput="validPassword()" class="form-control text-center"
												placeholder="Conferma password*" type="password" required>
										</div>
										<!-- form-group// Verify Password  -->
										<span id="errorPassword"></span>


										<!-- SELECT ROLE -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
													class="fas fa-user-tie"></i></span>
											</div>
											<select name="status" class="custom-select text-center"
												id="inputGroupSelect03" required>
												<option value="Employee">Dipendente</option>
												<option value="Manager">Manager</option>
											</select>
										</div>
										<!-- form-group// Status choose -->

										<span id="errorRegistrationForm"></span>
										<div class="form-group col-sm-6 mx-auto">
											<button type="submit" id="registrationButton"
												class="btn btn btn-success btn-block mx-auto" disabled>
												Registra</button>
										</div>
										<!-- form-group//  Register button-->

										<!-- END CONTAINER -->
									</div>

									<!-- END FORM -->
								</form>

								<!-- END CARD-BODY -->
							</div>

							<!-- END CARD -->
						</div>

						<!-- END COLUMN -->
					</div>

					<!-- END CONTAINER -->
				</div>

				<!-- END CONTENT -->
			</div>
			<jsp:include page="Footer.jsp" />

			<!-- END MAIN PANEL -->
		</div>

		<!-- END WRAPPER -->
	</div>

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
		if (nameValue.length >= 2 && nameValue.length <= 20
				&& input.value.match(/^[A-Za-z]+$/)) {
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
		if (surnameValue.length >= 2 && surnameValue.length <= 20
				&& input.value.match(/^[A-Za-z\s]+$/)) {
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
		if ((emailValue.length - 7) >= 5 && (emailValue.length - 7) <= 30
				&& input.value.match(/^[a-z]{1}\.[a-z]+[0-9]+\@wlb.it$/)) {
			var xmlHttpReq = new XMLHttpRequest();
			xmlHttpReq.onreadystatechange = function() {
				if (xmlHttpReq.readyState == 4 && xmlHttpReq.status == 200
						&& xmlHttpReq.responseText == ok) {
					if (input.classList.contains("is-invalid"))
						input.classList.remove("is-invalid");
					input.classList.add("is-valid");
					document.getElementById("errorEmail").innerHTML = "";
					emailOk = true;
				} else if (xmlHttpReq.readyState == 4
						&& xmlHttpReq.status == 200
						&& xmlHttpReq.responseText != ok) {
					if (input.classList.contains("is-valid"))
						input.classList.remove("is-valid");
					input.classList.add("is-invalid");
					document.getElementById("errorEmail").innerHTML = "Attenzione! Questa email è già associata ad un altro utente.";
					emailOk = false;
				}
				checkForm();
			}
			xmlHttpReq.open("GET",
					"/WorkLifeBalance/CheckEmployeeServlet?email="
							+ encodeURIComponent(input.value), true);
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
		if (password.length >= 8
				&& password.toUpperCase() != password
				&& password.toLowerCase() != password
				&& password
						.match(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[\.!@#\$%\^&\*]).{8,20}$/)) {
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