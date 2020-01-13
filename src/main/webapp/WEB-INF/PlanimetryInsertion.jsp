<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!-- Title -->
<title>WLB - Inserimento planimetria</title>

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

				<!-- CONTAINER -->
				<div class="container mt-4 text-center">

					<!-- COLONNA -->
					<div class="col-lg-9 mx-auto">

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
                                    <div class="container col-lg-10 mx-auto" id="containerPlanimetry">

                                        <!-- FLOOR -->
                                        <div class="row">
                                            <div class="col-sm-9">
                                                <div class="form-group input-group mx-auto">
                                                    <div class="input-group-prepend col-sm-4 px-0">
                                                        <span class="input-group-text w-100">
                                                            <h5 class="b my-auto mx-auto">Piano</h5>
                                                        </span>
                                                    </div>
                                                    <input name="floor" id="Floor" oninput="validFloor()"
                                                        class="form-control text-center" value='1' placeholder="Piano*"
                                                        type="text" disabled>
                                                </div>
                                            </div>

                                            <div class="col-sm-3 my-auto text-right pr-4" id="buttonColumn">
                                                <button type="button" id="nextbtn" onclick="updateFloor()"
                                                    class="btn btn-dark" disabled><i class="fas fa-plus"></i></button>
                                            </div>
                                        </div>


                                        <!-- ROOM -->
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group input-group mx-auto">
                                                    <div class="input-group-prepend col-sm-3 px-0">
                                                        <span class="input-group-text w-100">
                                                            <h5 class="b my-auto mx-auto">Stanza</h5>
                                                        </span>
                                                    </div>
                                                    <input name="room" id="Room" oninput="validRoom()"
                                                        class="form-control text-center" value='1' placeholder="Stanza*"
                                                        type="text" disabled>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- WORKSTATION  -->
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="form-group input-group mx-auto">
                                                    <div class="input-group-prepend col-sm-3 px-0">
                                                        <span class="input-group-text w-100">
                                                            <h5 class="b my-auto mx-auto">Postazioni</h5></i>
                                                        </span>
                                                    </div>
                                                    <input name="workstation" id="Workstation"
                                                        oninput="validWorkstation()"
                                                        class="form-control text-center col-sm-9 px-0"
                                                        placeholder="Postazioni*" type="text">
                                                </div>
                                                <span id="errorWorkstation"></span>
                                            </div>
                                        </div>


                                        <!-- "INSERISCI" BUTTON-->
                                        <div class="form-group col-sm-5 mx-auto">
                                            <button type="button" id="insertButtton" class="btn btn-success mx-auto "
                                                onclick="updatePlanimetryArray()" disabled>
                                                Inserisci</button>
                                        </div>


                                        <!-- LIST PLANIMETRY-->
                                        <div class="form-group row justify-content-md-center"
                                            id="PlanimetryActuallyInserted">
                                            <div class="col-lg-12">
                                                <div class="card my-auto">
                                                    <div class="card-header p-2 " id="TitlePlanimetryActuallyInserted">
                                                        <h3 class="my-auto">Planimetria attualmente inserita</h3>
                                                    </div>

                                                    <div class="card-body">
                                                        <div class="form-group text-center mx-auto"
                                                            style="overflow-y: scroll; height: 200px;">
                                                            <ul class="list-group list-group-bordered "
                                                                id="PlanimetryList">
                                                            </ul>
                                                        </div>
                                                    </div>


                                                </div>

                                                <!-- INPUT HIDDEN FOR JSON-->
                                                <input type="hidden" id="hiddenParameter" name="jsonObject" />

                                                <!-- "conferma" and "reset" BUTTON-->
                                                <div class="row mt-4">
                                                    <div class="col-sm-8 row mx-auto">
                                                        <div class="col-sm-5 mx-auto">
                                                            <button type="submit" id="confirmForm"
                                                                class="btn btn-success col-sm-12 mx-auto"
                                                                disabled>Conferma</button>
                                                        </div>

                                                        <div class="col-sm-5 mx-auto">
                                                            <button type="button" id="resetForm"
                                                                onclick="clearPlanimetry()"
                                                                class="btn btn-danger col-sm-12 mx-auto">Reset</button>
                                                        </div>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>



                                        <!-- END CONTAINER -->
                                    </div>

                                    <!-- END FORM -->
                                </form>

                                <!-- END CARD-BODY -->
                            </div>

                            <!-- END CARD -->
                        </div>

						<!-- END COLONNA -->
					</div>

					<!-- END CONTAINER -->
				</div>

				<!-- END CONTENT -->
			</div>
			<jsp:include page="Footer.jsp" />

			<!-- END MAIN PANEL -->
		</div>

		<!-- END WRAPPER -->
	</div>

	<script>
		var insertedPlanimetry = '${insertedPlanimetry}';
		console.log(insertedPlanimetry.length);
		
		if(insertedPlanimetry.length>0){
			console.log(insertedPlanimetry);
			var jsonArray = JSON.parse(insertedPlanimetry);
			
			console.log(jsonArray);
			
			var result = "<table class='table table-striped'><tr><th>Piano</th><th>Stanza</th><th>Postazioni</th></tr>";
			
			for(var i=0; i<jsonArray.length; i++){
				console.log(jsonArray[i]);				
				result += "<tr><td>" + jsonArray[i].floor + "</td><td>" + jsonArray[i].room + "</td><td>" + jsonArray[i].workstation + "</td></tr>";
			}
			result += "</table>";
			document.getElementById("PlanimetryList").innerHTML = result;
			
			$(".card-header").hide();			
			$("#containerPlanimetry").children().hide(); 
			$("#PlanimetryActuallyInserted").show();
			$("#TitlePlanimetryActuallyInserted").show();			
			$("#confirmForm").hide();
			$("#resetForm").hide();			
		}
		
	</script>

</body>


<script>

function checkSizeWidth(){
	if ($(window).width() < 576) {
		if($("#buttonColumn").hasClass("text-right")){
			$("#buttonColumn").removeClass("text-right")
		}
		if($("#buttonColumn").hasClass("pr-4")){
			$("#buttonColumn").removeClass("pr-4")
		}
	} else {
		if(!($("#buttonColumn").hasClass("text-right"))){
			$("#buttonColumn").addClass("text-right")
		}
		if(!($("#buttonColumn").hasClass("pr-4"))){
			$("#buttonColumn").addClass("pr-4")
		}
	}
}

checkSizeWidth();

$(window).resize(function(){

	checkSizeWidth();

});

</script>

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
        
		if (checkForm() == true) {//IF THE INPUT PARAMETERS ARE CORRECT
			planimetry = { "floor": parseInt(NumFloor.value), "room": parseInt(NumRoom.value), "workstation": parseInt(NumWorkstation.value) };
			jsondata.push(planimetry);

            //TABLE CREATION
			result = "<table class='table table-striped'><tr><th>Piano</th><th>Stanza</th><th>Postazioni</th></tr>";
			for (i = 0; i < jsondata.length; i++) {
				result += "<tr><td>" + jsondata[i].floor + "</td><td>" + jsondata[i].room + "</td><td>" + jsondata[i].workstation + "</td></tr>";
			}
			result += "</table>";

            //Button able/disable update
			document.getElementById("Workstation").value = "";
			document.getElementById("insertButtton").disabled = true;
			document.getElementById("nextbtn").disabled = false;

			//room value update
			var nextNum = parseInt(document.getElementById("Room").value) + 1;
			document.getElementById("Room").value = nextNum;

            //Set result in the planimetry table
			document.getElementById("PlanimetryList").innerHTML = result;

			//jsondata=JSON.stringify('planimetry');
			//document.getElementById("errorWorkstation").innerHTML = JSON.stringify(jsondata);

            //UPDATE JSON 
			document.getElementById("hiddenParameter").value=JSON.stringify(jsondata);
            
			document.getElementById("confirmForm").disabled = false;

		}

	}
	
	//ADD NEW FLOOR FUNCTION
	function updateFloor() {
		var nextNum = parseInt(document.getElementById("Floor").value) + 1;
		document.getElementById("Floor").value = nextNum;
		document.getElementById("Room").value = '1';
		document.getElementById("nextbtn").disabled = true;
	}

	//RESET FORM FUNCTION
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
            document.getElementById("insertButtton").disabled=true;
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
	
//function that control if the input parmeters are correct
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