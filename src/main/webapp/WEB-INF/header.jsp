<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<!-- Navbar -->
<div class="main-header">

	<!-- Logo Header -->
	<div class="logo-header" data-background-color="orange">

		<a href="." class="logo ml-3" id="logoNavbar"><img
			src="img/logoWLB.png" alt="navbar brand" class="navbar-brand"
			height="40"></a>

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
				<li class="nav-item dropdown hidden-caret"><a
					class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
						<div class="avatar-sm">
							<svg height="100%" width="100%">
								<circle cx="50%" cy="50%" r="19" fill="white" />
								<text x="50%" y="50%" text-anchor="middle" fill="black"
									font-family="Lato" dy=".3em" font-weight="bold">
									<c:set var="name" value="${user.name}" />
									<c:set var="surname" value="${user.surname}" />
									${fn:substring(name, 0, 1)}
									${fn:substring(surname, 0, 1)}
								</text>
							</svg>
						</div>
				</a>

					<div class="dropdown-menu animated fadeIn text-center">
						<div class="dropdown-item">
							<form action="LogoutServlet">
								<button class="btn btn-link" style="color: black;" role="link">Logout</button>
							</form>
						</div>
					</div></li>
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
			<c:choose>
				<c:when test="${userRole=='Admin'}">
					<ul class="nav nav-primary my-auto">
						<li class="nav-section"><span class="sidebar-mini-icon">
								<i class="fa fa-ellipsis-h"></i>
						</span>
							<h4 class="text-section">Admin</h4></li>

						<li class="nav-item"><a href="." class="nav-link"><i
								class="fas fa-home"></i>
								<p class="b">Homepage</p></a></li>

						<li class="nav-item"><a href="PlanimetryInsertionPage"
							class="nav-link"><i class="fas fa-layer-group"></i>
								<p class="b">Planimetria</p></a></li>

						<li class="nav-item"><a href="EmployeesListPage"
							class="nav-link"><i class="fas fa-th-list"></i>
								<p class="b">Dipendente</p></a></li>

						<li class="nav-item"><a href="ProjectsListPage"
							class="nav-link"><i class="fas fa-pen-square"></i>
								<p class="b">Progetto</p></a></li>
					</ul>
				</c:when>

				<c:otherwise>

					<!-- DIPENDENTE LIST-->
					<c:if test="${user.status == 0}">
						<ul class="nav nav-primary my-auto">
							<li class="nav-section"><span class="sidebar-mini-icon">
									<i class="fa fa-ellipsis-h"></i>
							</span>
								<h4 class="text-section">Dipendente</h4></li>

							<li class="nav-item"><a href="." class="nav-link"><i
									class="fas fa-home"></i>
									<p class="b">Homepage</p></a></li>

							<li class="nav-item"><a href="ShowPlanimetryPage"
								class="nav-link"><i class="fas fa-layer-group"></i>
									<p class="b">Visualizza planimetria</p></a></li>

							<li class="nav-item"><a href="#sidebarLayouts"
								class="nav-link"><i class="fas fa-th-list"></i>
									<p class="b">Storico</p></a></li>

							<li class="nav-item"><a
								href="ShowWorkstationPrenotationPage" class="nav-link"><i
									class="fas fa-pen-square"></i>
									<p class="b">Prenota postazione</p></a></li>

							<li class="nav-item"><a href="ShowSmartWorkingPrenotation"
								class="nav-link"><i class="fas fa-pen-square"></i>
									<p class="b">Smart Working</p></a></li>
						</ul>
					</c:if>

					<!-- MANAGER LIST -->
					<c:if test="${user.status == 1}">
						<ul class="nav nav-primary my-auto">
							<li class="nav-section"><span class="sidebar-mini-icon">
									<i class="fa fa-ellipsis-h"></i>
							</span>
								<h4 class="text-section">Manager</h4></li>

							<li class="nav-item"><a href="." class="nav-link"><i
									class="fas fa-home"></i>
									<p class="b">Homepage</p></a></li>

							<li class="nav-item"><a href="ShowPlanimetryPage"
								class="nav-link"><i class="fas fa-layer-group"></i>
									<p class="b">Visualizza planimetria</p></a></li>

							<li class="nav-item"><a href="#sidebarLayouts"
								class="nav-link"><i class="fas fa-th-list"></i>
									<p class="b">Storico</p></a></li>

							<li class="nav-item"><a
								href="ShowWorkstationPrenotationPage" class="nav-link"><i
									class="fas fa-pen-square"></i>
									<p class="b">Prenota postazione</p></a></li>

							<li class="nav-item"><a href="ShowSmartWorkingPrenotation"
								class="nav-link"><i class="fas fa-pen-square"></i>
									<p class="b">Smart Working</p></a></li>

							<li class="nav-item"><a href="#base" class="nav-link"><i
									class="fas fa-th-list"></i>
									<p class="b">
										Storico giornate<br> lavorative
									</p></a></li>

							<li class="nav-item" id="Projects" onclick="showProject()"><a
								data-toggle="collapse" class="nav-link" href=""> <i
									class="fas fa-layer-group"></i>
									<p class="b">Progetti supervisionati</p> <span class="caret"></span>
							</a>
								<div class="collapse">
									<ul class="nav nav-collapse" id="projectList"
										style="display: none;">
									</ul>
								</div></li>
						</ul>
					</c:if>
				</c:otherwise>

			</c:choose>

		</div>
	</div>
</div>

<script>

$(document).ready(function(){
	  $("#Projects").click(function(){
	  if($("#projectList").is(":visible")){
	    $("#projectList").hide(500);}
	  else
	  {
	  	$("#projectList").show(500);
	  }
	  });
	});

function showProject() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					var lista = JSON.parse(this.responseText);
					var li = "";
					
					for (i = 0; i < lista.length; i++){
						li += "<li><a href='.'><span class='sub-item'>"+lista[i].projectName+"</span></a></li>"
						console.log(li);
						document.getElementById("projectList").innerHTML = li;
					}
				}
			}
			xhttp.open("GET", "ShowAllSupervisedProjectsServlet", true);
			xhttp.send();
		}

</script>
<!-- End Sidebar -->