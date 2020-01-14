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
<title>WLB - Storico Prenotazioni</title>

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

				<!-- CONTAINER -->
				<div class="container mt-4 text-center">

					<!-- COLUMN -->
					<div class="col-lg-9 mx-auto">

						<!-- CARD -->
						<div class="card">
							<div class="card-header">
								<h3 class="my-auto" id="title">Storico prenotazioni</h3>

								<div class="row container mt-2">
									<div class="col-sm-4 mx-auto">
										<select name="" class="custom-select text-center"
											id="monthSelect" style="height: 40px; weight: 150px" required>
											<option value="1">Gennaio</option>
											<option value="2">Febbraio</option>
											<option value="3">Marzo</option>
											<option value="4">Aprile</option>
											<option value="5">Maggio</option>
											<option value="6">Giugno</option>
											<option value="7">Luglio</option>
											<option value="8">Agosto</option>
											<option value="9">Settembre</option>
											<option value="10">Ottobre</option>
											<option value="11">Novembre</option>
											<option value="12">Dicembre</option>
										</select>
									</div>
									<div class="col-sm-4 mx-auto">
										<select class="custom-select text-center"
											style="height: 40px; weight: 150px" id="yearSelect" required>
										</select>
									</div>
								</div>
							</div>
							<div class="card-body" id="myCard">

								<!-- END CARD BODY -->
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


	<script>
		function checkSize(){
			if ($(window).width() < 992) {
				if(!($("#monthSelect").hasClass("mb-3"))){
					$("#monthSelect").addClass("mb-3")
				}
			} else {
				if($("#monthSelect").hasClass("mb-3")){
					$("#monthSelect").removeClass("mb-3")
				}
			}
		}
		
		checkSize();
		
		$(window).resize(function(){
			checkSize();
		});
	</script>
	<script>
$(document).ready(function () {

	var container = $("#myCard");
    var monthSelect = $("#monthSelect");
    var yearSelect = $("#yearSelect");
    var emailEmployee = '${employeeSupervised}';
    var employeeJson = '${employeeJson}';
    loadYears();

    function loadYears()
    {
    	
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				 
				var lista = JSON.parse(this.responseText);

				var options = "";

				for (i = 0; i < lista.length; i++) {
					options += "<option>" + lista[i].year + "</option>";
				}
				document.getElementById("yearSelect").innerHTML = options;
				yearSelect.val(new Date().getFullYear());
				loadCalendarHistory();
			}
		}
		xhttp.open("GET", "SuggestionsYear", true);
		xhttp.send();
    }
    
	    function loadCalendarHistory() {
	        var month = monthSelect.val();
	        var year = yearSelect.val();
	        if (((monthSelect.val() != null) && (yearSelect.val() != null))) {
	            var xhttp = new XMLHttpRequest();
	            xhttp.onreadystatechange = function () {
	                if (this.readyState == 4 && this.status == 200) {
	                    var lista = JSON.parse(this.responseText);
	                    
	                    if(lista==null || lista.length==0){
	                    	if(emailEmployee==null || emailEmployee==""){
	                    		$("#title").html("");
	                    		$("#title").append("Storico prenotazioni");
	                    		$("#calendarHistoryTable").remove();
	                    		$("#myCard").html("");
	                    		$("#myCard").append("<div class='card-body my-auto mx-auto'><h2>Non hai effettuato nessuna prenotazione per questo mese.</h2></div>");
	                    	} else {
	                    		var jsonObj = "";
	                    		if(employeeJson!=null){
	                    			jsonObj = JSON.parse(employeeJson);
	                    		}
	                    		if(jsonObj!=""){
	                    			$("#title").html("");
		                    		$("#title").append("Storico prenotazioni di " + jsonObj.name + " " + jsonObj.surname);
	                    			$("#calendarHistoryTable").remove();
	                    			$("#myCard").html("");
	                    			$("#myCard").append("<div class='card-body my-auto mx-auto'><h2>" + jsonObj.name + " non ha effettuato nessuna prenotazione per questo mese.</h2></div>");
	                    		}
	                    	}
	                    } else {
		                    	lista.sort(function(a,b){
		                            return a.date.localeCompare(b.date);
		                        });
		                    	
		                    	if(emailEmployee==null || emailEmployee==""){
		                    		$("#title").html("");
		                    		$("#title").append("Storico prenotazioni");
		                    	} else {
		                    		var jsonObj = "";
		                    		if(employeeJson!=null){
		                    			jsonObj = JSON.parse(employeeJson);
		                    		}
		                    		if(jsonObj!=""){
		                    		$("#title").html("");
		                    		$("#title").append("Storico prenotazioni di " + jsonObj.name + " " + jsonObj.surname);
		                    		}
		                    	}
		                    	
		                    	$("#myCard").html("");
		                    	$("#myCard").append("<div style='overflow-y: scroll; height: 300px;' id='calendarHistoryTable'><table class='table table-bordered table-striped'><thead><tr><th scope='col'>DATA</th><th scope='col'>MODALITA' DI LAVORO</th><th scope='col'>PIANO</th><th scope='col'>STANZA</th><th scope='col'>POSTAZIONE</th></tr></thead><tbody id='myTbody'>");
		                    	
		                    	for (var i = 0; i < lista.length; i++) {
		                    		if(lista[i].type=="Smartworking"){
		                    			$("#myTbody").append("<tr><td>" + lista[i].date +  "</td><td>" + lista[i].type +  "</td><td></td><td></td><td></td>");
		                    		} else if(lista[i].type=="Workstation"){
		                    			$("#myTbody").append("<tr><td>" + lista[i].date + "</td><td>" + lista[i].type + "</td><td>" + lista[i].floor + "</td><td>" + lista[i].room + "</td><td>" + lista[i].workstation + "</td>");
		                    		}
		                    	}
		                    	$("#myCard").append("</tbody></table></div>");
		                    }
	                }
	            }
	            xhttp.open("GET", "ShowCalendarHistory?employeeEmail=" + emailEmployee + "&month=" + month + "&year=" + year, true);
	            xhttp.send();
	        } else {
	        	container.html("");
	        	container.append("<h2 class='mx-auto my-auto'>Seleziona un mese.</h2>");
	        }
	    }
	    
	    monthSelect.on("change", function () {
	    	loadCalendarHistory();
	    });
	    
	    yearSelect.on("change", function () {
	    	loadCalendarHistory();
	    });
});
</script>

</body>

</html>
