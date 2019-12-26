<%@ page import="java.util.*,it.unisa.wlb.model.bean.Employee"%>
<!DOCTYPE html>
<html lang="it">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!-- Title -->
<title>WLB - Inserimento progetto</title>

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
								<h3>Inserisci progetto</h3>
							</div>
							<div class="card-body">
								<form action="AddProjectServlet" method="post">

									<div class="form-group row pb-4">
										<label for="" class="col-sm-2 col-form-label">Nome:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control text-center"
												name="name" id="name" onkeyup="verificaNome()">
										</div>
									</div>

									<div class="form-group row pb-4">
										<label for="scope" class="col-sm-2 col-form-label">Scope:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control text-center"
												name="scope" id="scope" onkeyup="verificaScope()">
										</div>
									</div>

									<div class="form-group row pb-4">
										<label for="startDate" class="col-sm-2 col-form-label">Data
											inizio:</label>
										<div class="col-sm-10">
											<input type="date" class="form-control text-center"
												name="startDate" id="startDate"
												onblur="verificaDataInizio()">
										</div>
									</div>

									<div class="form-group row pb-4">
										<label for="endDate" class="col-sm-2 col-form-label">Data
											fine:</label>
										<div class="col-sm-10">
											<input type="date" class="form-control text-center"
												name=endDate id="endDate" onblur="verificaDataFine()">
										</div>
									</div>

									<div class="form-group row pb-3">
										<label for="descrizione" class="col-sm-2 col-form-label">Descrizione:</label>
										<div class="col-sm-10">
											<textarea name="description" id="description" cols="30"
												rows="5" class="form-control my-2"
												onblur="verificaDescrizione()"></textarea>
										</div>
									</div>

									<div class="form-group row pb-4">
										<label for="managerEmail" class="col-sm-2 col-form-label">Manager:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control text-center"
												name="managerEmail" id="managerEmail"
												onkeyup="verificaManager()">
										</div>
									</div>

									<div class="form-group row pb-1">
										<div class="col-lg-8">
											<div class="card">
												<div class="card-header p-2">
													<h3 class="my-auto">Lista dipendenti:</h3>
												</div>
												<div class="card-body">
													<div class="form-group text-center mx-auto">
														<ul class="list-group list-group-bordered"
															id="employeeList">

														</ul>
													</div>
												</div>
											</div>
										</div>

										<div class="col-lg-4 mx-auto mb-auto">
											<!-- Button trigger modal -->
											<button type="button" class="btn btn-warning mx-auto"
												data-toggle="modal" data-target="#exampleModal">
												Inserisci dipendente <span class="caret"></span>
											</button>
										</div>
									</div>

									<hr>

									<div class="col-lg-4 mx-auto">
										<button type="submit" class="btn btn-success"
											id="insertButton" disabled>Inserisci</button>
									</div>

									<!-- FINE FORM DI INSERIMENTO PROGETTO -->
								</form>

								<!-- Modal -->
								<div class="modal fade" id="exampleModal" tabindex="-1"
									role="dialog" aria-labelledby="exampleModalLabel"
									aria-hidden="true">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header text-center">
												<h5 class="modal-title" id="exampleModalLabel">Inserisci
													dipendente</h5>
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>

											<div class="modal-body">
												<h3>Ricerca dipendente</h3>
												<input type="email" onkeyup="Suggestions(this.value)"
													class="form-control" placeholder="m.red1@wlb.it"
													aria-describedby="basic-addon1" name="q" id="lista"
													list="suggestions">
												<datalist id="suggestions"></datalist>
												<button class="input-group-text" id="basic-addon1"
													onclick="insertEmployee(lista.value)">
													<i class="fas fa-plus-square" data-dismiss="modal"></i>
												</button>
											</div>
										</div>
									</div>
								</div>

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
		var nomeOK = false;
		var scopeOK = false;
		var dataInizioOK = false;
		var dataFineOK = false;
		var descrizioneOK = false;
		var managerOK = false;
		//TEMPLATE
		var borderOK = '1px solid #080';
		var borderNO = '1px solid #f00';
		function verificaNome(){
			var input = $("#name").val();
			if(input.trim().length>=4 && input.trim().length<=15 && input.match(/^[A-Za-z0-9]+$/)){
				$("#name").css("border", borderOK);
				nomeOK = true;
			} else {
				$("#name").css("border", borderNO);
				nomeOK = false;
			}
			changeInsertButtonState();
		}
		function verificaScope(){
			var input = $("#scope").val();
			if(input.trim().length>=3 && input.trim().length<=25 && input.match(/^[A-Za-z\s]+$/)){
				$("#scope").css("border", borderOK);
				scopeOK = true;
			} else {
				$("#scope").css("border", borderNO);
				scopeOK = false;
			}
			changeInsertButtonState();
		}
		
		function verificaDataInizio(){
			var input = $("#startDate").val();
			
			if(input.match(/^(19|20)\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/)){
				$("#startDate").css("border", borderOK);
				dataInizioOK = true;
			} else {
				$("#startDate").css("border", borderNO);
				dataInizioOK = false;
			}
			changeInsertButtonState();
		}
		
		function verificaDataFine(){
			var input = $("#endDate").val();
			
			if(input.match(/^(19|20)\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/)){
				$("#endDate").css("border", borderOK);
				dataFineOK = true;
			} else {
				$("#endDate").css("border", borderNO);
				dataFineOK = false;
			}
			changeInsertButtonState();
		}
		
		function verificaDescrizione(){
			var input = $("#description").val();
			
			if(input.trim().length>=20 && input.trim().length<=250 && input.match(/^[\s\S]+$/)){
				$("#description").css("border", borderOK);
				descrizioneOK = true;
			} else {
				$("#description").css("border", borderNO);
				descrizioneOK = false;
			}
			changeInsertButtonState();
		}
		
		function verificaManager(){
			var input = $("#managerEmail").val();
			if(input.match(/^[a-z]{1}\.[a-z]+[1-9]*\@wlb.it$/)){
				$("#managerEmail").css("border", borderOK);
				managerOK = true;
			} else {
				$("#managerEmail").css("border", borderNO);
				managerOK = false;
			}
			changeInsertButtonState();
		}
		
		function changeInsertButtonState(){
			var btn = $("#insertButton");
			if(nomeOK && scopeOK && dataInizioOK && dataFineOK && descrizioneOK && managerOK){
				document.getElementById('insertButton').disabled = false;
				btn.css("background-color", "#31CE36");
				btn.css("color", "#ffffff");
			} else {
				document.getElementById('insertButton').disabled = true;
				btn.css("background-color", "#d6d6d6");
				btn.css("color", "#ffffff");
			}
		}
		
		function Suggestions(email){
			
			var xhttp = new  XMLHttpRequest();
			xhttp.onreadystatechange = function(){
				if(this.readyState == 4 && this.status == 200){
					
					var lista = JSON.parse(this.responseText);
					
					 var options="";
					  
					  for (i = 0; i < lista.length; i++) { 
					    options += "<option>" + lista[i].email + "</option>";
					  }
					  console.log(options);
					  document.getElementById("suggestions").innerHTML = options;
				}
			}
			xhttp.open("GET", "SuggestionEmployees?email="+ email , true);
			xhttp.send();
		}
		
		function insertEmployee(email)
		{
			console.log(email);
			var xhttp = new  XMLHttpRequest();
			xhttp.onreadystatechange = function(){
				if(this.readyState == 4 && this.status == 200){
					var lista = JSON.parse(this.responseText);
					var li="";
					
					li += "<li>" + lista.emailEmployee + "</li>";
					console.log(li);
					document.getElementById("employeeList").innerHTML += li;

				}
			}
			xhttp.open("GET", "AddEmployeeToList?email="+ email , true);
			xhttp.send();
		}
	</script>

</body>
</html>