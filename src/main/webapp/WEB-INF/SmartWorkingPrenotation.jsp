<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="it">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!-- Title -->
<title>WLB - Smart Working</title>

<!-- Icon -->
<link rel="icon" href="img/icon.ico" type="image/x-icon" />

<!-- CSS Files -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/atlantis.css">
<link rel="stylesheet" href="css/calendar.css">

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

							<c:choose>
								<c:when test="${booking=='no'}">
									<div class="card-header">
										<h3 class="my-auto">Avviso:</h3>
									</div>
									<div class="card-body">
										<h2 class="my-auto mx-auto">Hai già effettuato una
											prenotazione questa settimana.</h2>
									</div>
								</c:when>

								<c:otherwise>
									<c:if test="${booking=='yes'}">
										<div class="calendar-section">

											<div class="calendar calendar-first" id="calendar_first">
												<div class="calendar_header">
													<button class="switch-month switch-left">
														<i class="fas fa-chevron-left"></i>
													</button>
													<h2></h2>
													<button class="switch-month switch-right">
														<i class="fas fa-chevron-right"></i>
													</button>
												</div>
												<div class="calendar_weekdays"></div>
												<div class="calendar_content"></div>
											</div>
										</div>
										<form action="SmartWorkingDaysPrenotationServlet"
											id="smartWorkingDays" method="post">
											<button type="submit" class=" btn btn-success mb-3"
												id="sendButton">Prenota</button>
										</form>
									</c:if>
								</c:otherwise>

							</c:choose>

							<!-- FINE CARD -->
						</div>

						<!-- FINE CARD -->
					</div>

					<!-- FINE CONTAINER -->
				</div>

				<!-- FINE CONTENT -->
			</div>

			<jsp:include page="footer.jsp" />

			<!-- FINE MAIN-PANEL -->
		</div>

		<!-- FINE WRAPPER -->
	</div>

	<!-- Calendar -->
	<script src="js/calendar.js"></script>

	<c:if test="${not empty error}">
		<script>
				//SWEETALERT
				Swal.fire({
				  icon: 'warning',
				  title: 'Oops...',
				  text: "${error}"})
		</script>
	</c:if>

</body>
</html>