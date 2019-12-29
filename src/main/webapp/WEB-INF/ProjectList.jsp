<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!-- Title -->
<title>WLB - Lista progetti</title>

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

							<div class="card-header">
								<h3 class="my-auto">Lista progetti</h3>
							</div>

							<div class="card-body">

								<a class="nav-link nav-item toggle-nav-search hidden-caret mb-3"
									data-toggle="collapse" href="#search-nav" role="button"
									aria-expanded="false" aria-controls="search-nav">
									<button class="btn">
										<i class="fa fa-search"></i>
									</button>
								</a>

								<div class="collapse mx-auto mb-3" id="search-nav">
									<form class="navbar-left navbar-form nav-search">
										<div class="input-group">
											<div class="input-group-prepend">
													<i class="fa fa-search search-icon my-auto ml-2"></i>
											</div>
											<input type="text" placeholder="Search ... "
												class="form-control" onkeyup="Suggestions(this.value)">
										</div>
								<!--  		<datalist id="suggestions"></datalist>  -->
									</form>
								</div>

								<ul class="list-group list-group-bordered" id="suggestionsList" style="overflow-y:scroll; height: 230px;">
									<c:forEach items="${projectList}" var="project">
									<li class="list-group-item"><a href="ShowProjectServlet?name=${project.name}"
										class="mx-auto nav-link" style="color: #2f3640">${project.name}</a></li>							
									</c:forEach>
								</ul>

								<a class="btn btn-success mt-3" href="ProjectInsertPage"
									role="button">Inserisci nuovo progetto</a>

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
						suggestionsList += "<li class='list-group-item'><a href='ShowProjectServlet?name="+lista[i].name+"' class='mx-auto nav-link' style='color: #2f3640'>" + lista[i].name + "</a></li>";
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