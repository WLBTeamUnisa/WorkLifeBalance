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
								<h3 class="my-auto">Storico prenotazioni</h3>

								<div class="row container">
									<div class="col-sm-3 mx-auto">
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
									<div class="col-sm-3 mx-auto">
										<select class="custom-select text-center"
											style="height: 40px; weight: 150px" id="yearSelect" required>
											<option value="2010">2010</option>
											<option value="2011">2011</option>
											<option value="2012">2012</option>
											<option value="2013">2013</option>
											<option value="2014">2014</option>
											<option value="2015">2015</option>
											<option value="2016">2016</option>
											<option value="2017">2017</option>
											<option value="2018">2018</option>
											<option value="2019">2019</option>
											<option value="2020">2020</option>
										</select>
									</div>
								</div>
							</div>
							<div class="card-body" id="myCard">

								<!-- FINE CARD BODY -->
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


	<script>
$(window).resize(function(){

	if ($(window).width() <= 992) {
		if(!($("#monthSelect").hasClass("mb-3"))){
			$("#monthSelect").addClass("mb-3")
		}
	} else {
		if($("#monthSelect").hasClass("mb-3")){
			$("#monthSelect").removeClass("mb-3")
		}
	}

});
</script>
	<script>
$(document).ready(function () {

	//INIZIALIZZO LE VARIE SELECT
    var monthSelect = $("#monthSelect");
    var yearSelect = $("#yearSelect");
    yearSelect.val(new Date().getFullYear());
    
    var emailEmployee = '${employeeSupervised}';
    console.log(emailEmployee);
    
    loadCalendarHistory();

	//LOAD CalendarHistory
	    function loadCalendarHistory() {

	        var month = monthSelect.val();
	        var year = yearSelect.val();

	        if (((monthSelect.val() != null) && (yearSelect.val() != null))) {
	            var xhttp = new XMLHttpRequest();
	            xhttp.onreadystatechange = function () {
	                if (this.readyState == 4 && this.status == 200) {
	                    var lista = JSON.parse(this.responseText);
	                    console.log(lista);
	                    
	                    if(lista==null || lista.length==0){
	                    		$("#calendarHistoryTable").remove();
	                    		$("#myCard").html("");
	                    		$("#myCard").append("<div class='card-body my-auto mx-auto'><h2>Non hai effettuato nessuna prenotazione per questo mese.</h2></div>");
		                    } else {
		                    	lista.sort(function(a,b){
		                            return a.date.localeCompare(b.date);
		                        });
		                    	
		                    	$("#myCard").html("");
		                    	$("#myCard").append("<div style='overflow-y: scroll; height: 300px;' id='calendarHistoryTable'><table class='table table-bordered table-striped'><thead><tr><th scope='col'>DATA</th><th scope='col'>MODALITA' DI LAVORO</th><th scope='col'>POSTO</th><th scope='col'>STANZA</th><th scope='col'>PIANO</th></tr></thead><tbody id='myTbody'>");
		                    	
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
		        //Da cambiare
	        	container.html("");
	        	container.append("<h2 class='mx-auto my-auto'>Seleziona un mese.</h2>");
	        }
	    }
	    
	    
	  //SETTO GLI EVENTI ONCHANGE ALLE SELECT
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
