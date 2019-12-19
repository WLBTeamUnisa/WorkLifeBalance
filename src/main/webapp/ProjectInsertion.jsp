<!DOCTYPE html>
<html lang="it">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!-- Title -->
<title>WLB - Project Insertion</title>

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
								<h3>Insert project</h3>
							</div>
							<div class="card-body">
								<form action="">

									<div class="form-group row pb-4">
										<label for="name" class="col-sm-2 col-form-label">Name:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control text-center"
												name="name" id="name">
										</div>
									</div>

									<div class="form-group row pb-4">
										<label for="scope" class="col-sm-2 col-form-label">Scope:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control text-center"
												name="scope" id="scope">
										</div>
									</div>

									<div class="form-group row pb-4">
										<label for="startDate" class="col-sm-2 col-form-label">Start
											date:</label>
										<div class="col-sm-10">
											<input type="date" class="form-control text-center"
												name="startDate" id="startDate">
										</div>
									</div>

									<div class="form-group row pb-4">
										<label for="endDate" class="col-sm-2 col-form-label">End
											date:</label>
										<div class="col-sm-10">
											<input type="date" class="form-control text-center"
												name=endDate id="endDate">
										</div>
									</div>

									<div class="form-group row pb-3">
										<label for="descrizione" class="col-sm-2 col-form-label">Descrizione:</label>
										<div class="col-sm-10">
											<textarea name="" id="" cols="30" rows="5"
												class="form-control my-2"></textarea>
										</div>
									</div>

									<div class="form-group row pb-4">
										<label for="manager" class="col-sm-2 col-form-label">Manager:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control text-center"
												name="manager" id="manager">
										</div>
									</div>

									<div class="form-group row pb-4">
										<div class="col-lg-8">
											<div class="card">
												<div class="card-header">
													<h3>Employee list:</h3>
												</div>
												<div class="card-body">
													<div class="form-group text-center mx-auto">
														<ul class="list-group list-group-bordered">
															<li class="list-group-item" id="employee1"><i
																class="fas fa-user my-auto"></i>
																<p class="my-auto ml-3">m.red10@wlb.it</p></li>

															<li class="list-group-item" id="employee2"><i
																class="fas fa-user my-auto"></i>
																<p class="my-auto ml-3">m.green10@wlb.it</p></li>
														</ul>
													</div>
												</div>
											</div>
										</div>

										<div class="col-lg-4 mx-auto my-auto">
											<!-- Button trigger modal -->
											<button type="button" class="btn btn-warning mx-auto"
												data-toggle="modal" data-target="#exampleModal">
												Insert employee <span class="caret"></span>
											</button>
										</div>
									</div>

									<!-- Modal -->
									<div class="modal fade" id="exampleModal" tabindex="-1"
										role="dialog" aria-labelledby="exampleModalLabel"
										aria-hidden="true">
										<div class="modal-dialog" role="document">
											<div class="modal-content">
												<div class="modal-header text-center">
													<h5 class="modal-title" id="exampleModalLabel">Insert
														employee</h5>
													<button type="button" class="close" data-dismiss="modal"
														aria-label="Close">
														<span aria-hidden="true">&times;</span>
													</button>
												</div>

												<div class="modal-body">
													<h1>Search employee</h1>
													<form class="navbar-form" role="search">
														<div class="form-group">
															<div class="input-group mb-3">
																<input type="text" class="form-control"
																	placeholder="m.red1@wlb.it"
																	aria-describedby="basic-addon1">
																<div class="input-group-append">
																	<span class="input-group-text" id="basic-addon1"><i
																		class="fas fa-plus-square"></i></span>
																</div>
															</div>
														</div>
													</form>

												</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-danger"
														data-dismiss="modal">Close</button>
												</div>
											</div>
										</div>
									</div>

									<hr>

									<div class="col-lg-4 mx-auto">
										<button type="submit" class="btn btn-success">Insert</button>
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

</body>
</html>