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
<title>WLB - Homepage</title>

<!-- Icon -->
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
							<c:choose>
								<c:when test="${userRole=='Admin'}">
									<div class="card-header">
										<h3 class="my-auto">Admin Dashboard</h3>
									</div>

									<div class="card-body">
										<h4 class="my-auto">Admin</h4>
										<!-- FINE CARD-BODY -->
									</div>
								</c:when>

								<c:otherwise>

									<c:if test="${user.status == 0}">
										<div class="card">

											<table class="table borderless">


												<tbody>

													<tr>
														<td>Giorno 1 </td>
														<td><span align="center"><i
																class="fas fa-home" style="font-size: 1.73em;"></i></span></td>
														<td></td>

													</tr>

													<tr>
														<td>Giorno 2</td>
														<td><span align="center"><i
																class="fas fa-laptop" style="font-size: 1.73em;"></i></span></td>
														<td>P1- S3- P16</td>
													</tr>

													<tr>
														<td>Giorno 3</td>
														<td><span align="center"><i
																class="fas fa-home" style="font-size: 1.73em;"></i></span></td>
														<td></td>
													</tr>
													<tr>
														<td>Giorno 4</td>
														<td><span align="center"><i
																class="fas fa-laptop" style="font-size: 1.73em;"></i></span></td>
														<td>P1- S3- P16</td>
													</tr>
													<tr>
														<td>Giorno 5</td>
														<td><span align="center"><i
																class="fas fa-home" style="font-size: 1.73em;"></i></span></td>
														<td></td>
													</tr>
													<div class="card-footer" style="font-size: 1.35em;">
														<B>3° SETTIMANA</B>
													</div>
												</tbody>
											</table>
										<!-- FINE CARD-BODY EMPLOYEE-->
										</div>

									</c:if>


									<c:if test="${user.status == 1}">
										<div class="card">
											<table class="table borderless">


												<tbody>

													<tr>
														<td>Giorno 1</td>
														<td><span align="center"><i
																class="fas fa-home" style="font-size: 1.73em;"></i></span></td>
														<td></td>

													</tr>

													<tr>
														<td>Giorno 2</td>
														<td><span align="center"><i
																class="fas fa-laptop" style="font-size: 1.73em;"></i></span></td>
														<td>P1- S3- P16</td>
													</tr>

													<tr>
														<td>Giorno 3</td>
														<td><span align="center"><i
																class="fas fa-home" style="font-size: 1.73em;"></i></span></td>
														<td></td>
													</tr>
													<tr>
														<td>Giorno 4</td>
														<td><span align="center"><i
																class="fas fa-laptop" style="font-size: 1.73em;"></i></span></td>
														<td>P1- S3- P16</td>
													</tr>
													<tr>
														<td>Giorno 5</td>
														<td><span align="center"><i
																class="fas fa-home" style="font-size: 1.73em;"></i></span></td>
														<td></td>
													</tr>
													<div class="card-footer" style="font-size: 1.35em;">
														<B>3° SETTIMANA</B>
													</div>
												</tbody>
											</table>
										<!-- FINE CARD-BODY MANAGER -->
										</div>
									</c:if>
								</c:otherwise>
							</c:choose>
							<!-- FINE CARD -->
						</div>

						<!-- FINE COLONNA -->
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
</html>