<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="it">

<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>WLB - Profilo Dipendente</title>
<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />
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

				<!-- CONTAINER -->
				<div class="container mt-4 text-center">

					<!-- COLONNA -->
					<div class="col-lg-7 mx-auto">

						<!-- CARD -->
						<div class="card">
							<div class="card-header">
								<h3>Profilo dipendente</h3>
							</div>

							<!-- CARD-BODY -->
							<div class="card-body registration-fields">

								<!-- FORM -->
								<form name="Profile" method="post"
									action="ShowEmployeeProfileServlet">

									<!-- CONTAINER -->
									<div class="container">


										<!-- NOME -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fa fa-user"></i></span>
											</div>
											<input name="name" id="Name"
												class="form-control text-center" value="${employee.name}"
												type="text" readonly required>
										</div>
										


										<!-- COGNOME -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fa fa-user"></i></span>
											</div>
											<input name="surname" id="Surname" value="${employee.surname}"
												class="form-control text-center" 
												type="text" readonly required>
										</div>
										


										<!-- EMAIL -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
													class="fa fa-envelope"></i></span>
											</div>
											<input name="email" id="Email" value="${employee.email}"
												class="form-control text-center" 
												type="email" readonly required>
										</div>
									


										<!-- SELECT RUOLO -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
													class="fas fa-user-tie"></i></span>
											</div>
											<select name="status" class="custom-select text-center"
												id="Status" onchange="changeStatusFunction()" required>
												<option value="Employee">Dipendente</option>
												<option value="Manager">Manager</option>
											</select>
										</div>
										
										<!-- form-group// Status choose -->

										<span id="errorName"> </span>

										<div class="form-group col-sm-6 mx-auto">
											<button type="submit" id="changeStatusButton"
												class="btn btn btn-success btn-block mx-auto" disabled>
												Modifica</button>
										</div>
										<!-- form-group//  Register button-->

										<!-- FINE CONTAINER -->
									</div>

									<!-- FINE FORM -->
								</form>

								<!-- FINE CARD-BODY -->
							</div>

							<!-- FINE CARD -->
						</div>

						<!-- FINE COLONNA -->
					</div>

					<!-- FINE CONTAINER -->
				</div>

				<!-- FINE CONTENT -->
			</div>
			<jsp:include page="footer.jsp" />

			<!-- FINE MAIN PANEL -->
		</div>

		<!-- FINE WRAPPER -->
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
<script>

window.onload = function() {
	if("${employee.status}"==0)
	 	document.getElementById("Status").selectedIndex = "0";
	else
		document.getElementById("Status").selectedIndex = "1";
}


function changeStatusFunction()
{
	var statusEmployee =document.querySelector("#Status");
	var statusInt;
	if(statusEmployee.value=="Manager")
		statusInt=1;
	else
		statusInt=0;

	//document.getElementById("errorName").innerHTML = "${employee.status}";
	if("${employee.status}"!=statusInt)
		{
			document.getElementById("changeStatusButton").disabled=false;
			
		}
	else
		{
			document.getElementById("changeStatusButton").disabled=true;
			
		}
	
}

</script>


</html>