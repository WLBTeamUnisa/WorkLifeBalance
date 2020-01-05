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
							&nbsp;
							<div class="card-header">Storico Prenotazioni</div>
							&nbsp;
							<div class="container">
								<div class="row">
									<div class="col-sm">
										<select name="" class="custom-select text-center" id=""
											style="height: 40px; weight: 150px" required>
											<option value="Gennaio">Gennaio</option>
											<option value="Febbraio">Febbraio</option>
											<option value="Marzo">Marzo</option>
											<option value="Aprile">Aprile</option>
											<option value="Maggio">Maggio</option>
											<option value="Giugno">Giugno</option>
											<option value="Luglio">Luglio</option>
											<option value="Agosto">Agosto</option>
											<option value="Settembre">Settembre</option>
											<option value="Ottobre">Ottobre</option>
											<option value="Novembre">Novembre</option>
											<option value="Dicembre">Dicembre</option>
										</select>
									</div>
									<div class="col-sm">

										<select class="custom-select text-center"
											style="height: 40px; weight: 150px" required>
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
									<div class="col-sm">
										<button style="height: 40px; width: 150px" type="submit" id=""
											class="btn btn btn-success btn-block mx-auto" disabled>
											Cerca</button>
									</div>
								</div>
							</div>
							&nbsp; &nbsp;
							<div style="overflow-y: scroll; height: 230px;">

								<table class="table table-bordered table-striped mb-0">
									<thead>
										<tr>
											<th scope="col">DATA</th>
											<th scope="col">MODALITA' DI LAVORO</th>
											<th scope="col">POSTO</th>
											<th scope="col">STANZA</th>
											<th scope="col">PIANO</th>
										</tr>
									</thead>
									<tbody>
										<tr>

											<td>2019-10-31</td>
											<td>Smart Working</td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>

											<td>2019-10-30</td>
											<td>In Azienda</td>
											<td>25</td>
											<td>10</td>
											<td>1</td>
										</tr>
										<tr>

											<td>2019-10-29</td>
											<td>Smart Working</td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>

											<td>2019-10-28</td>
											<td>In Azienda</td>
											<td>25</td>
											<td>10</td>
											<td>1</td>
										</tr>
										<tr>

											<td>2019-10-27</td>
											<td>Smart Working</td>
											<td></td>
											<td></td>
											<td></td>
										</tr>
										<tr>

											<td>2019-10-26</td>
											<td>In Azienda</td>
											<td>25</td>
											<td>10</td>
											<td>1</td>
										</tr>
										<!-- FINE BODY TABLE-->
									</tbody>
									<!-- FINE TABLE -->
								</table>

								<!-- FINE CARD-BODY -->
							</div>
							<!-- FINE CARD -->
						</div>
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

</body>

</html>
