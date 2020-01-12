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
<title>WLB - Lista dipendenti</title>

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
				<div class="container mt-4 text-center">

					<div class="col-lg-9 mx-auto">

						<div class="card">

							<div class="card-header">
								<h3 class="my-auto">Lista dipendenti</h3>
							</div>

							<div class="card-body">

								<div class="collapse mx-auto mb-3" id="search-nav">
									<form class="navbar-left navbar-form nav-search mb-4">
										<div class="input-group">
											<div class="input-group-prepend">
												<button type="submit" class="btn btn-search pr-1">
													<i class="fa fa-search search-icon"></i>
												</button>
											</div>
											<input type="text" placeholder="Search ..."
												class="form-control" onkeyup="Suggestions(this.value)">
										</div>
									</form>
								</div>

								<div style="overflow-y: scroll; height: 230px;">
									<ul class="list-group list-group-bordered" id="suggestionsList">
										<c:forEach items="${employeeList}" var="employee">
											<li class="list-group-item"><a
												href="ShowEmployeePage?email=${employee.email}"
												class="mx-auto nav-link" style="color: #2f3640">${employee.name}
													${employee.surname} - ${employee.email}</a></li>
										</c:forEach>

										<c:if test="${empty employeeList}">
											<div class="my-auto text-center p-5">
												<h3>Non esistono dipendenti.</h3>
											</div>
										</c:if>
									</ul>
								</div>

								<a class="btn btn-success mt-3" href="EmployeeInsertPage"
									role="button">Inserisci nuovo dipendente</a>

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

			<!-- END MAIN-PANEL -->
		</div>

		<!-- END WRAPPER -->
	</div>


	<c:if test="${not empty result}">
		<script>
				//SWEETALERT
				if("${result}"=="success"){
					Swal.fire({
						icon: 'success',
						title: 'Ottimo!',
						text: "Operazione effettuata con successo!"
					})
				}
				
		</script>
	</c:if>

	<c:if test="${not empty statusResult}">
		<script>
			function sweetalertclick() {
				swal("Ottimo!", "Status modificato con successo!","success")
			}
			window.onload = sweetalertclick;
		</script>
	</c:if>
	<script>
		function Suggestions(email) {

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {

					var lista = JSON.parse(this.responseText);

					var suggestionsList = "";

					for (i = 0; i < lista.length; i++) {
						suggestionsList += "<li class='list-group-item'><a  href='ShowEmployeePage?email="+lista[i].email+"' class='mx-auto nav-link' style='color: #2f3640'>"
								+ lista[i].name
								+ " "
								+ lista[i].surname
								+ " - " + lista[i].email + "</a></li>";
					}

					document.getElementById("suggestionsList").innerHTML = suggestionsList;
				}
			}
			xhttp.open("GET", "SearchEmployeeServlet?email=" + email, true);
			xhttp.send();
		}
	</script>


</body>
</html>