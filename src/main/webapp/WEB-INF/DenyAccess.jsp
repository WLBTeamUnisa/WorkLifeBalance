<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="it">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!-- Title -->
<title>WLB - Access Deny</title>

<!-- Icon -->
<link rel="icon" href="img/icon.ico" type="image/x-icon" />

<!-- CSS Files -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/atlantis.css">
<link rel="stylesheet" href="css/calendar.css">

<style>
.card-header {
	border-radius: 5px;
}

.main-panel {
	width: calc(100%);
}

.main-panel>.content {
	margin-top: 0px;
	min-height: calc(100%);
}
</style>
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

		<div class="main-panel">

			<!-- CORPO PAGINA-->
			<div class="content" style="display: flex; align-items: center;">
				<div class="container text-center">

					<div class="col-lg-5 mx-auto">

						<div class="card">
							<div class="card-header" style="background-color: red">
								<i class="fa fa-times-circle fa-3x" aria-hidden="true"
									style="color: white"></i>
							</div>
							<div class="card-body">
								<h2 class="my-auto mx-auto">Accesso negato per questa pagina.</h2>
								<c:if test="${not empty user}">
								<br>
								<h4 class="my-auto mx-auto"><a class="my-auto mx-auto" href=".">Clicca qui per ritornare alla homepage</a></h4>
								</c:if>
								<c:if test="${empty user}">
								<br>
								<h4 class="my-auto mx-auto"><a class="my-auto mx-auto" href=".">Clicca qui per ritornare alla pagina di login</a></h4>
								</c:if>
							</div>

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

	<!--   Core JS Files   -->
	<script src="js/core/jquery.3.2.1.min.js"></script>
	<script src="js/core/popper.min.js"></script>
	<script src="js/core/bootstrap.min.js"></script>

</body>
</html>