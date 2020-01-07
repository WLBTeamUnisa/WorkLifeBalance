<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="it">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />

<meta content='width=device-width, initial-scale=1.0, shrink-to-fit=no'
	name='viewport' />

<!-- Title -->
<title>WLB - Progetto</title>

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
								<h3 class="my-auto">${project.name}</h3>
							</div>

							<div class="card-body">
							
							<!-- CONTAINER -->
									<div class="container">

										<!-- SCOPE -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fas fa-search"></i></span>
											</div>
											<input type="text" class="form-control text-center b" name="scope"
												id="scope" value="${project.scope}" disabled>
										</div>


										<!-- DATA INIZIO -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
														class="fas fa-calendar-plus"></i></span>
											</div>
											<input type="date" class="form-control text-center b" name="startDate"
												id="startDate" value="${project.startDate}" disabled>
										</div>


										<!-- DATA FINE -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i
														class="fas fa-calendar-minus"></i></span>
											</div>
											<input type="date" class="form-control text-center b" name=endDate
												id="endDate" value="${project.endDate}" disabled>
										</div>


										<!-- DESCRIZIONE -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fas fa-comment-alt"></i></span>
											</div>
											<textarea name="description" id="description"
												class="form-control text-center b"
												disabled>${project.description}</textarea>
										</div>


										<!-- MANAGER -->
										<div class="form-group input-group mx-auto">
											<div class="input-group-prepend">
												<span class="input-group-text"><i class="fa fa-user"></i></span>
											</div>
											<input type="text" class="form-control text-center b" name="managerEmail"
												id="managerEmail" value="${project.employee.name} ${project.employee.surname} - ${project.employee.email}" disabled>
										</div>

										<!-- LISTA DIPENDENTI -->
										<div class="form-group">
											<div class="container">
												<div class="card col-lg-10 mx-auto">
													<div class="card-header p-2">
														<h3 class="my-auto">Dipendenti</h3>
													</div>
													<div class="card-body">
														<div class="form-group text-center mx-auto">
															<div style="overflow-y: scroll; height: 100px;">
																<ul class="list-group list-group-bordered"
																	id="employeeList">
																	<c:forEach items="${project.employees}" var="employee">
																		<li class='list-group-item'><a href="ShowCalendarHistoryPage?employeeEmail=${employee.email}">
																			<i class='fas fa-user my-auto mr-2'></i>${employee.name} ${employee.surname} - ${employee.email}
																			</a>
																		</li>
																	</c:forEach>
																</ul>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>

										<!-- FINE CONTAINER -->
									</div>

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
</body>
</html>