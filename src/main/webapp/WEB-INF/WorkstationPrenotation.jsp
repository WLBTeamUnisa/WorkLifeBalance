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

<style>
.flex-container {
	display: flex;
	flex-wrap: wrap;
}

.flex-container>svg {
	margin: 10px;
	margin-right: 0px;
}
</style>

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
								<h3 class="my-auto">Seleziona il giorno e la postazione da
									prenotare:</h3>
								<div class="row">
									<div class="col-sm-4">
										<div class="form-group">
											<label for="dateSelect">Data:</label> <select
												class="form-control form-control-sm" id="dateSelect">
												<c:forEach items="${availableDates}" var="data">
													<option>${data}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<label for="floorSelect">Piano:</label> <select
												class="form-control form-control-sm" id="floorSelect">

											</select>
										</div>
									</div>
									<div class="col-sm-4">
										<div class="form-group">
											<label for="roomSelect">Stanza:</label> <select
												class="form-control form-control-sm" id="roomSelect">
												<option>1</option>
												<option>2</option>
												<option>3</option>
												<option>4</option>
												<option>5</option>
											</select>
										</div>
									</div>
								</div>
							</div>

							<div class="card-body">
								<div style="overflow-y: scroll; height: 230px;">
									<div class="flex-container"></div>
									<form action="" id="finalForm"></form>
								</div>
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
	<script src="js/sweetalert2.all.js"></script>

	<!-- Atlantis JS -->
	<script src="js/atlantis.min.js"></script>

	<script>
		$(document).ready(function () {
			
			//INIZIALIZZO LE VARIE SELECT
			var dateSelect = $("#dateSelect");
			var floorSelect = $("#floorSelect");
			var roomSelect = $("#roomSelect");
			
			//OGGETTO DELLA REQUEST
			var insertedPlanimetry = '${insertedPlanimetry}';
			
			//CONTAINER DEGLI OGGETTI SVG
			var container = $(".flex-container");
			
			//INIZIALIZZO ALLA PRIMA STANZA
			loadRoom(1);

			
			//LOAD PIANI
				if(insertedPlanimetry.length > 0){
					var arrayJson = JSON.parse(insertedPlanimetry);
					var arrayFloor = [];
					for(var i = 0; i<arrayJson.length; i++){
						if(!(arrayFloor.includes(arrayJson[i].floor))){
							arrayFloor.push(arrayJson[i].floor);
						}
					}
				}
				floorSelect.html("");
				for(var j=0; j<arrayFloor.length; j++){
					floorSelect.append("<option value=" + arrayFloor[j] + ">" + arrayFloor[j] + "</option>");
				}
				
				loadPlanimetry();
			
			
			//LOAD STANZE
			function loadRoom(piano){
				if(insertedPlanimetry.length > 0){
					var arrayJson = JSON.parse(insertedPlanimetry);
					var arrayRoom = [];
					for(var i = 0; i<arrayJson.length; i++){
						if(arrayJson[i].floor==piano){
							arrayRoom.push(arrayJson[i].room);
						}
					}
				}
				roomSelect.html("");
				for(var j=0; j<arrayRoom.length; j++){
					roomSelect.append("<option value=" + arrayRoom[j] + ">" + arrayRoom[j] + "</option>");
				}
			}
			
			
			//LOAD PLANIMETRY
			function loadPlanimetry(){
				
				var date = dateSelect.val();
				var floor = floorSelect.val();
				var room = roomSelect.val();
								
				var xhttp = new XMLHttpRequest();
				xhttp.onreadystatechange = function(){
					if(this.readyState == 4 && this.status == 200){
						var lista = JSON.parse(this.responseText);
						container.html("");
						for(var i = 0; i<lista.length; i++){
							if(lista[i].status == 0){
								container.append("<svg width='50' height='50'> <rect width='50' height='50' style='fill:green;stroke:black;stroke-width:5;opacity:0.5' /> <text x='50%' y='50%' text-anchor='middle' fill='white' font-family='Lato' dy='.4em' font-weight='bold' style='opacity: 0.8;'>" + lista[i].workstation + "</text> </svg>");
							} else if (lista[i].status == 1){
								console.log(lista[i]);
								container.append("<svg width='50' height='50'> <rect width='50' height='50' style='fill:red;stroke:black;stroke-width:5;opacity:0.5' class='unavailable' /> <text x='50%' y='50%' text-anchor='middle' fill='black' font-family='Lato' dy='.4em' font-weight='bold' style='opacity: 0.6;'>" + lista[i].workstation + "</text> </svg>");
							}
						}
					}
				}
				xhttp.open("GET", "WorkstationsAvailability?date=" + date + "&floor=" + floor + "&room=" + room, true);
				xhttp.send();
			}
			
			
			//SETTO GLI EVENTI ONCHANGE ALLE SELECT
			roomSelect.on("change", function () {
				loadPlanimetry();
			});
			floorSelect.on("change", function () {
				loadRoom(floorSelect.val());
				loadPlanimetry();
			});
			dateSelect.on("change", function () {
				loadPlanimetry();
			});
			
			//-------FIN QUI VA BENE--------
			
			
			//ONCLICK DA CONTINUARE E RIVEDERE
			clickedElement = $("svg");
			var clicked = "";
			
			clickedElement.on("click", function () {
				clicked = $(this);

				if (clicked.children("rect").hasClass("unavailable")) { return; }

				//SWEETALERT
				Swal.fire({
					title: 'Sei sicuro?',
					text: "Stai prenotando una postazione per questa data: " + clicked.children("text").html() + "/01/2020",
					icon: 'warning',
					showCancelButton: true,
					confirmButtonColor: '#3085d6',
					cancelButtonColor: '#d33',
					cancelButtonText: 'Cancella',
					confirmButtonText: 'Si, voglio prenotare'
				}).then((result) => {
					//SE PREMO "PRENOTA"
					if (result.value) {
						Swal.fire(
							'Successo!',
							'La prenotazione e\' stata effettuata.',
							'success'
						)

						$("#finalForm").html("<input type='hidden' name='prenotation' value=\"{'data':'" + dateSelect.find(":selected").text() + "', 'workstation': " + clicked.children('text').text() + ", 'room':'" + roomSelect.find(":selected").text() + "', 'floor':" + floorSelect.find(":selected").text() + "}\">");

						//clicked.children("rect").fadeOut(function(){
						clicked.children("rect").attr("style", "fill:red;stroke:black;stroke-width:5;opacity:0.5");
						clicked.children("rect").addClass("unavailable");
						clicked.children("text").attr("fill", "black");
						clicked.children("text").prop("style", "opacity:0.6");
					}	//Fine if
					else if (result.dismiss === Swal.DismissReason.cancel) {
						//PREMO SU "ELIMINA" PER NON SCEGLIERE IL GIORNO CHE HO CLICCATO
						Swal.fire(
							'Cancellata!',
							'La prenotazione e\' stata eliminata',
							'error'
						)
					}	//Fine else
				});
			});
		});
	</script>

</body>
</html>