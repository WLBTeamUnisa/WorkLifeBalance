<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Navbar -->
		<div class="main-header">

			<!-- Logo Header -->
			<div class="logo-header" data-background-color="orange">

				<a href="index.jsp" class="logo"><img src="img/logo.svg" alt="navbar brand" class="navbar-brand"></a>
				
				<button class="navbar-toggler sidenav-toggler ml-auto" type="button" data-toggle="collapse" data-target="collapse" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"><i class="icon-menu"></i></span>
				</button>

				<button class="topbar-toggler more"><i class="icon-options-vertical"></i></button>
				
				<div class="nav-toggle">
					<button class="btn btn-toggle toggle-sidebar">
						<i class="icon-menu"></i>
					</button>
				</div>
			</div>
			<!-- End Logo Header -->

			<!-- Navbar Header -->
			<nav class="navbar navbar-header navbar-expand-lg" data-background-color="orange">

				<div class="container-fluid">
					<ul class="navbar-nav topbar-nav ml-md-auto align-items-center">
						<li class="nav-item"><h4 class="text-right my-auto mr-3" style="color: white;">Nome - Cognome</h4></li>
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
				<li class="nav-item"><a href="#" class="nav-link"><i
						class="fas fa-layer-group"></i>
						<p class="b">Planimetria</p></a></li>

				<li class="nav-item"><a href="#" class="nav-link"><i
						class="fas fa-th-list"></i>
						<p class="b">Dipendente</p></a></li>

				<li class="nav-item"><a href="ProjectList.jsp" class="nav-link"><i
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

				<li class="nav-item"><a href="#sidebarLayouts" class="nav-link"><i
						class="fas fa-th-list"></i>
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
<!-- End Sidebar -->
