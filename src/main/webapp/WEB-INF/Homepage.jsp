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

			<!-- CORPO PAGINA-->
			<div class="content" style="display: flex; align-items: center;">
				<div class="container mt-4 text-center">

					<div class="col-lg-9 mx-auto">

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

									<div class="card-body p-0" id="myCard">

										<div class="card-header">
											<h3 class="my-auto">Pianificazione settimana corrente</h3>
										</div>

										<table class="table table-striped" id="myTable">
											<tbody id="tbody">
											</tbody>
										</table>

										<!-- FINE CARD-BODY MANAGER -->
									</div>

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

			<jsp:include page="Footer.jsp" />

			<!-- FINE MAIN-PANEL -->
		</div>

		<!-- FINE WRAPPER -->
	</div>

	<script>
		var tbody = $("#tbody");
		
		tbody.html("");
		
		var lista = '${jsonList}';
		
		var listaJson = JSON.parse(lista);
		
		listaJson.sort(function(a,b){
            return a.date.localeCompare(b.date);
        });

		if(listaJson.length==0){
			$("#myTable").remove();
			$("#myCard").append("<div class='card-body my-auto mx-auto'><h2>Per la settimana corrente non hai prenotato nessun giorno di Smart Working e nessuna postazione di lavoro.</h2></div>");
		}
		
		for(var i = 0; i<listaJson.length; i++){
			var obj = listaJson[i];
			console.log[obj];

			if(obj.type=="smartWorking"){
				tbody.append("<tr><td>" + obj.date + "</td> <td><span align='center'><i class='fas fa-home' style='font-size: 1.73em;'></i></span></td> <td>Smart Working</td></tr>");
			} else {
				tbody.append("<tr><td>" + obj.date + "</td> <td><span align='center'><i class='fas fa-building' style='font-size: 1.73em;'></i></span></td> <td>P" + obj.floor + " - S" + obj.room + " - W" + obj.workstation + "</td></tr>");
			}
		}
	
	</script>

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

</body>
</html>