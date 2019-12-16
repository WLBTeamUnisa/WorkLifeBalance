<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">

<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>Atlantis Lite - Bootstrap 4 Admin Dashboard</title>
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
			urls : [ '../assets/css/fonts.min.css' ]
		},
		active : function() {
			sessionStorage.fonts = true;
		}
	});
</script>
</head>


<body>
	<div class="wrapper">
		<!-- Navbar -->
		<div class="main-header">
			<!-- Logo Header -->
			<div class="logo-header" data-background-color="orange">

				<a href="." class="logo"><img src="assets/img/logo.svg"
					alt="navbar brand" class="navbar-brand"></a>
				<button class="navbar-toggler sidenav-toggler ml-auto" type="button"
					data-toggle="collapse" data-target="collapse" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"><i class="icon-menu"></i></span>
				</button>

				<button class="topbar-toggler more">
					<i class="icon-options-vertical"></i>
				</button>
				<div class="nav-toggle">
					<button class="btn btn-toggle toggle-sidebar">
						<i class="icon-menu"></i>
					</button>
				</div>
			</div>
			<!-- End Logo Header -->

			<!-- Navbar Header -->
			<nav class="navbar navbar-header navbar-expand-lg"
				data-background-color="orange">

				<div class="container-fluid">

					<ul class="navbar-nav topbar-nav ml-md-auto align-items-center">
						<li class="nav-item"><h5 class="text-right my-auto mr-3"
								style="color: white;">Login</h5></li>
						<li class="nav-item dropdown hidden-caret"><a
							class="dropdown-toggle profile-pic" data-toggle="dropdown"
							href="#" aria-expanded="false">
								<div class="avatar-sm">
									<img src="../assets/img/profile.jpg" alt="..."
										class="avatar-img rounded-circle">
								</div>
						</a>
							<ul class="dropdown-menu dropdown-user animated fadeIn">
								<div class="dropdown-user-scroll scrollbar-outer">
									<li>
										<div class="user-box">
											<div class="avatar-lg">
												<img src="../assets/img/profile.jpg" alt="image profile"
													class="avatar-img rounded">
											</div>
											<div class="u-text">
												<h4>Hizrian</h4>
												<p class="text-muted">hello@example.com</p>
												<a href="profile.html"
													class="btn btn-xs btn-secondary btn-sm">View Profile</a>
											</div>
										</div>
									</li>
									<li>
										<div class="dropdown-divider"></div> <a class="dropdown-item"
										href="#">My Profile</a> <a class="dropdown-item" href="#">My
											Balance</a> <a class="dropdown-item" href="#">Inbox</a>
										<div class="dropdown-divider"></div> <a class="dropdown-item"
										href="#">Account Setting</a>
										<div class="dropdown-divider"></div> <a class="dropdown-item"
										href="#">Logout</a>
									</li>
								</div>
							</ul></li>
					</ul>
				</div>
			</nav>
			<!-- End Navbar -->
		</div>

		<!-- Sidebar -->
		<div class="sidebar sidebar-style-2" style="background-color: #2f3640">
			<div class="sidebar-wrapper scrollbar scrollbar-inner">
				<div class="sidebar-content">

					<!-- ADMIN LIST-->
					<ul class="nav nav-primary my-auto">
						<li class="nav-section"><span class="sidebar-mini-icon">
								<i class="fa fa-ellipsis-h"></i>
						</span>
							<h4 class="text-section">Admin</h4></li>
						<li class="nav-item"><a href="#base" class="nav-link"><i
								class="fas fa-layer-group"></i>
							<p class="b">Planimetria</p></a></li>

						<li class="nav-item"><a href="#sidebarLayouts"
							class="nav-link"><i class="fas fa-th-list"></i>
							<p class="b">Dipendente</p></a></li>

						<li class="nav-item"><a href="#forms" class="nav-link"><i
								class="fas fa-pen-square"></i>
							<p class="b">Progetto</p></a></li>
					</ul>

					<!-- DIPENDENTE LIST-->
					<ul class="nav nav-primary my-auto">
						<li class="nav-section"><span class="sidebar-mini-icon">
								<i class="fa fa-ellipsis-h"></i>
						</span>
							<h4 class="text-section">Dipendente</h4></li>
						<li class="nav-item"><a href="#base" class="nav-link"><i
								class="fas fa-layer-group"></i>
							<p class="b">Visualizza planimetria</p></a></li>

						<li class="nav-item"><a href="#sidebarLayouts"
							class="nav-link"><i class="fas fa-th-list"></i>
							<p class="b">Storico</p></a></li>

						<li class="nav-item"><a href="#forms" class="nav-link"><i
								class="fas fa-pen-square"></i>
							<p class="b">Prenota postazione</p></a></li>

						<li class="nav-item"><a href="#forms" class="nav-link"><i
								class="fas fa-pen-square"></i>
							<p class="b">Smart Working</p></a></li>
					</ul>
				</div>
			</div>
		</div>

		<div class="main-panel">
			<!-- CORPO PAGINA-->
			<div class="content" style="display: flex; align-items: center;">
				<div class="container mt-4 text-center">