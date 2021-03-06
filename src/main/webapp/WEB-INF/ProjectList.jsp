<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!-- Title -->
<title>WLB - Lista progetti</title>

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
								<h3 class="my-auto">Lista progetti</h3>
							</div>

							<div class="card-body">

								<div class="collapse mx-auto" id="search-nav">
									<form class="navbar-left navbar-form nav-search mb-4">
										<div class="input-group">
											<div class="input-group-prepend">
												<i class="fa fa-search search-icon my-auto ml-2"></i>
											</div>
											<input type="text"
												placeholder="Inserisci il nome del progetto... "
												class="form-control" onkeyup="Suggestions(this.value)">
										</div>
										<!--  		<datalist id="suggestions"></datalist>  -->
									</form>
								</div>

								<div style="overflow-y: scroll; height: 230px;">
									<ul class="list-group list-group-bordered" id="suggestionsList">
										<c:forEach items="${projectList}" var="project">
											<li class="list-group-item"><a
												href="ShowProjectServlet?name=${project.name}"
												class="mx-auto nav-link" style="color: #2f3640">${project.name}</a></li>
										</c:forEach>

										<c:if test="${empty projectList}">
											<div class="my-auto text-center p-5">
												<h3>Non esistono progetti.</h3>
											</div>
										</c:if>
									</ul>
								</div>

								<a class="btn btn-success mt-3" href="ProjectInsertPage"
									role="button">Inserisci nuovo progetto</a>

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
				} else if("${result}"=="error"){
					Swal.fire({
						icon: 'error',
						title: 'Ops!',
						text: "Si � verificato un errore!"
						})
				}
				
		</script>
	</c:if>

	<script>
		function Suggestions(name) {

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {

					var lista = JSON.parse(this.responseText);

					var options = "";
					var suggestionsList = "";

					for (i = 0; i < lista.length; i++) {
						options += "<option>" + lista[i].name + "</option>";
						suggestionsList += "<li class='list-group-item'><a href='ShowProjectServlet?name="
								+ lista[i].name
								+ "' class='mx-auto nav-link' style='color: #2f3640'>"
								+ lista[i].name + "</a></li>";
					}
					console.log(options);

					document.getElementById("suggestionsList").innerHTML = suggestionsList;
				}
			}
			xhttp.open("GET", "SearchProjectServlet?name=" + name, true);
			xhttp.send();
		}
	</script>

</body>
</html>