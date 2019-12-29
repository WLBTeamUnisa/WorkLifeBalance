<%@ page import="java.util.*,it.unisa.wlb.model.bean.Employee"%>
<!DOCTYPE html>
<html lang="it">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!-- Title -->
<title>WLB - Modifica progetto</title>

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
								<h3>Modifica progetto</h3>
							</div>

							<!-- CARD-BODY -->
							<div class="card-body">

								<!-- FORM -->
								<form action="ModifyProjectServlet" method="post">

									<!-- CONTAINER -->
									<div class="container">

										<!-- NOME -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fa fa-user"></i></span>
											</div>
											<input type="text" class="form-control text-center"
												name="name" id="name" onkeyup="verificaNome()"
												value="${oldProject.name}" required>
										</div>


										<!-- SCOPE -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
													class="fas fa-search"></i></span>
											</div>
											<input type="text" class="form-control text-center"
												name="scope" id="scope" onkeyup="verificaScope()"
												value="${oldProject.scope}" required>
										</div>


										<!-- DATA INIZIO -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
													class="fas fa-calendar-plus"></i></span>
											</div>
											<input type="date" class="form-control text-center"
												name="startDate" id="startDate" value="${startDate}"
												onkeyup="verificaDataInizio()" required>
										</div>


										<!-- DATA FINE -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
													class="fas fa-calendar-minus"></i></span>
											</div>
											<input type="date" class="form-control text-center"
												name=endDate id="endDate" onkeyup="verificaDataFine()"
												value="${endDate}" required>
										</div>


										<!-- DESCRIZIONE -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
													class="fas fa-comment-alt"></i></span>
											</div>
											<textarea name="description" id="description"
												class="form-control text-center"
												onkeyup="verificaDescrizione()" required>${oldProject.description}</textarea>
										</div>


										<!-- MANAGER -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fa fa-user"></i></span>
											</div>
											<input type="text" class="form-control text-center"
												name="managerEmail" id="managerEmail"
												onkeyup="verificaManager()"
												value="${oldProject.employee.email}" required>
										</div>

										<hr>

										<div class="col-lg-4 mx-auto">
											<button type="submit" class="btn btn-success"
												id="insertButton">Modifica</button>
										</div>

										<!-- FINE CONTAINER -->
									</div>
									<!-- FINE FORM DI INSERIMENTO PROGETTO -->
								</form>
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
		var nomeOK = true;
		var scopeOK = true;
		var dataInizioOK = true;
		var dataFineOK = true;
		var descrizioneOK = true;
		var managerOK = true;
		//TEMPLATE
		var borderOK = '1px solid #080';
		var borderNO = '1px solid #f00';
		function verificaNome() {
			var input = $("#name").val();
			if (input.trim().length >= 4 && input.trim().length <= 15
					&& input.match(/^[A-Za-z0-9]+$/)) {
				$("#name").css("border", borderOK);
				nomeOK = true;
			} else {
				$("#name").css("border", borderNO);
				nomeOK = false;
			}
			changeInsertButtonState();
		}
		function verificaScope() {
			var input = $("#scope").val();
			if (input.trim().length >= 3 && input.trim().length <= 25
					&& input.match(/^[A-Za-z\s]+$/)) {
				$("#scope").css("border", borderOK);
				scopeOK = true;
			} else {
				$("#scope").css("border", borderNO);
				scopeOK = false;
			}
			changeInsertButtonState();
		}

		function verificaDataInizio() {
			var input = $("#startDate").val();

			if (input
					.match(/^(19|20)\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/)) {
				$("#startDate").css("border", borderOK);
				dataInizioOK = true;
			} else {
				$("#startDate").css("border", borderNO);
				dataInizioOK = false;
			}
			changeInsertButtonState();
		}

		function verificaDataFine() {
			var input = $("#endDate").val();

			if (input
					.match(/^(19|20)\d{2}[-](0[1-9]|1[012])[-](0[1-9]|[12][0-9]|3[01])$/)) {
				$("#endDate").css("border", borderOK);
				dataFineOK = true;
			} else {
				$("#endDate").css("border", borderNO);
				dataFineOK = false;
			}
			changeInsertButtonState();
		}

		function verificaDescrizione() {
			var input = $("#description").val();

			if (input.trim().length >= 20 && input.trim().length <= 250
					&& input.match(/^[\s\S]+$/)) {
				$("#description").css("border", borderOK);
				descrizioneOK = true;
			} else {
				$("#description").css("border", borderNO);
				descrizioneOK = false;
			}
			changeInsertButtonState();
		}

		function verificaManager() {
			var input = $("#managerEmail").val();
			if (input.match(/^[a-z]{1}\.[a-z]+[0-9]*\@wlb.it$/)) {
				$("#managerEmail").css("border", borderOK);
				managerOK = true;
			} else {
				$("#managerEmail").css("border", borderNO);
				managerOK = false;
			}
			changeInsertButtonState();
		}
		
		function changeInsertButtonState() {
			var btn = $("#insertButton");
			if (nomeOK && scopeOK && dataInizioOK && dataFineOK
					&& descrizioneOK && managerOK) {
				document.getElementById('insertButton').disabled = false;
				btn.css("background-color", "#31CE36");
				btn.css("color", "#ffffff");
			} else {
				document.getElementById('insertButton').disabled = true;
				btn.css("background-color", "#d6d6d6");
				btn.css("color", "#ffffff");
			}
		}
		
	</script>

</body>
</html>