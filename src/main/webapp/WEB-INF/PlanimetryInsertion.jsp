<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">

<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>WLB - Inserimento planimetria</title>
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
								<h3>Inserimento planimetria</h3>
							</div>

							<!-- CARD-BODY -->
							<div class="card-body">

								<!-- FORM -->
								<form method="post" action="AddPlanimetryServlet">

									<!-- CONTAINER -->
									<div class="container col-lg-8 mx-auto">

										<!-- FLOOR -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fas fa-layer-group"></i></span>
											</div>
											<input name="floor" id="Floor" oninput="validFloor()"
												class="form-control text-center col-sm-7" value='1'
												placeholder="Piano*" type="text" disabled>
												<div class="input-group-append">
													<button class="input-group-text btn btn-warning" type="button"
													id="nextbtn" onclick="updateFloor()" disabled>Nuovo</button>
												</div>
										</div>

										<!-- FLOOR -->
										<!--<div class="form-group input-group offset-md-3">
											<div class="input-group-prepend">
												<span class="input-group-text "><i
														class="fas fa-layer-group"></i></span>
											</div>
											<input name="floor" id="Floor" oninput="validFloor()"
												class="form-control text-center col-4 float-left" value='1'
												placeholder="Piano*" type="text" disabled>
											&nbsp &nbsp &nbsp&nbsp
											 "NEXT" BUTTON-->

											<!--<button type="button" id="nextbtn" onclick="updateFloor()"
												class="btn btn-dark" disabled>Next</button>

										</div>-->



										<!-- ROOM -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fas fa-door-closed"></i></span>
											</div>
												<input name="room" id="Room" oninput="validRoom()"
												class="form-control text-center col-sm-7" value='1'
												placeholder="Stanza*" type="text" disabled>
											</div>
										

										<!-- ROOM 
										<div class="form-group input-group offset-md-3">
											<div class="input-group-prepend">
												<span class="input-group-text "><i
														class="fas fa-door-closed"></i></span>
											</div>
											<input name="room" id="Room" oninput="validRoom()"
												class="form-control text-center col-4 float-left" value='1'
												placeholder="Stanza*" type="text" disabled>
										</div> -->


										<!-- WORKSTATION  -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fas fa-box"></i></span>
											</div>
											<input name="workstation" id="Workstation" oninput="validWorkstation()"
											class="form-control text-center col-sm-7 " placeholder="Postazioni*"
											type="text">
											</div>
											<span id="errorWorkstation"></span>

										<!-- WORKSTATION 
										<div class="form-group input-group offset-md-3">
											<div class="input-group-prepend">
												<span class="input-group-text "><i class="fas fa-box"></i></span>
											</div>
											<input name="workstation" id="Workstation" oninput="validWorkstation()"
												class="form-control text-center col-4 " placeholder="Postazioni*"
												type="text">
										</div>
										<span id="errorWorkstation"></span>
										-->

										
										<!-- "INSERISCI" BUTTON-->
										<div class="form-group  ">
											<button style="background-color:#f59e42" type="button" id="insertButtton"
												class="btn mx-auto col-4 " onclick="updatePlanimetryArray()" disabled>
												Inserisci</button>
										</div>


										<!-- lista planimetry-->
										<div class="form-group row justify-content-md-center  ">
											<div class="col-lg-12">
												<div class="card ">
													<div class="card-header p-2 ">
														<h3 class="my-auto">Planimetria</h3>
													</div>

													<div class="card-body ">
														<div class="form-group text-center mx-auto  ">
															<ul class="list-group list-group-bordered "
																id="PlanimetryList">
															</ul>
														</div>

													</div>

												</div>

												<input type="hidden" id="hiddenParameter" name="jsonObject"/>

												<!-- conferma e reset BUTTON-->
												<div class="form-group ">
													<button type="submit" id="confirmForm"
														class="btn btn btn-dark  col-5">
														Conferma</button>
													<button type="button" id="resetForm" onclick="clearPlanimetry()"
														class="btn btn-danger  col-5">Reset</button>
												</div>
											</div>
										</div>



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
	<script src="js/plugin/jquery-ui-touch-punch/jquery.ui.touch-punch.min.js"></script>

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

	var floorOk = false;
	var roomOK = false;
	var workstationOk = false;

	var jsondata = []; //file JSON 

	function updatePlanimetryArray() {
		//get parameters from form
		var NumFloor = document.getElementById("Floor");
		var NumRoom = document.getElementById("Room");
		var NumWorkstation = document.getElementById("Workstation");

		var planimetry, i, result = "";



		if (checkForm() == true) {
			planimetry = { "floor": parseInt(NumFloor.value), "room": parseInt(NumRoom.value), "workstation": parseInt(NumWorkstation.value) };
			jsondata.push(planimetry);

			result = "<table><tr><th>Piano</th><th>Stanza</th><th>Postazioni</th></tr>";
			for (i = 0; i < jsondata.length; i++) {

				result += "<tr><td>" + jsondata[i].floor + "</td><td>" + jsondata[i].room + "</td><td>" + jsondata[i].workstation + "</td></tr>";

			}
			result += "</table>";

			document.getElementById("Workstation").value = "";
			document.getElementById("insertButtton").disabled = true;
			document.getElementById("nextbtn").disabled = false;

			//room value update
			var nextNum = parseInt(document.getElementById("Room").value) + 1;
			document.getElementById("Room").value = nextNum;

			document.getElementById("PlanimetryList").innerHTML = result;

			//jsondata=JSON.stringify('planimetry');
			document.getElementById("errorWorkstation").innerHTML = JSON.stringify(jsondata);

			document.getElementById("hiddenParameter").value=JSON.stringify(jsondata);

		}

	}



	//FUNZIONE CHE INSERISCE IL NUOVO PIANO ()
	function updateFloor() {

		var nextNum = parseInt(document.getElementById("Floor").value) + 1;
		document.getElementById("Floor").value = nextNum;
		document.getElementById("Room").value = '1';
		document.getElementById("nextbtn").disabled = true;
	}

	

	//FUNZIONE CHE RESETTA IL FORM
	function clearPlanimetry() {
		jsondata = [];
		document.getElementById("PlanimetryList").innerHTML = "";
		document.getElementById("Room").value = '1';
		document.getElementById("Floor").value = '1';
		document.getElementById("Workstation").value = "";


	}


	///Input Controls
	function validWorkstation() {
		if (document.getElementById("Workstation").value >= 1 && document.getElementById("Workstation").value <= 100) {
			document.getElementById("insertButtton").disabled = false;
			workstationOk = true;
			document.getElementById("errorWorkstation").innerHTML = "";

		}
		else {
			workstationOk = false;
			document.getElementById("errorWorkstation").innerHTML = "Errore! inserire un numero di postazioni corretto";
			document.getElementById("Workstation").onfocus;
		}




	}

	function validRoom() {
		if (document.getElementById("Room").value >= 1 && document.getElementById("Room").value <= 20) {
			roomOK = true;
		}
		else
			roomOK = false;


	}

	function validFloor() {
		if (document.getElementById("Floor").value >= 1 && document.getElementById("Floor").value <= 200) {
			floorOk = true;
		}
		else
			floorOk = false;

	}

	function checkForm() {
		validFloor();
		validRoom();
		validWorkstation();
		if (floorOk && roomOK && workstationOk) {
			return true;
		} else {
			document.getElementById("errorWorkstation").innerHTML = "Errore durante l'inserimento!"
			clearPlanimetry();
			return false;
		}

	}
</script>
</html>