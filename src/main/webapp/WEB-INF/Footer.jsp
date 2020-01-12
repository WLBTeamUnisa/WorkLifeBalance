<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<footer class="footer">
	<div class="container-fluid">
		<nav class="pull-left">
			<ul class="nav">
				<li class="nav-item"><a class="nav-link" href="#"> WLB Team
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#"> Help </a></li>
				<li class="nav-item"><a class="nav-link" href="#"> Licenses
				</a></li>
			</ul>
		</nav>
		<div class="copyright ml-auto">2020, made by WLB Team</div>
	</div>
</footer>

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

<!-- Sweet Alert -->
<script src="js/sweetalert2.all.js"></script>

<!-- Atlantis JS -->
<script src="js/atlantis.min.js"></script>

<script>

function checkWidth(){
	if ($(window).width() < 992) {
		if($("#logoNavbar").hasClass("ml-3")){
			$("#logoNavbar").removeClass("ml-3")
		}
	} else {
		if(!($("#logoNavbar").hasClass("ml-3"))){
			$("#logoNavbar").addClass("ml-3")
		}
	}
}

checkWidth();

$(window).resize(function(){

	checkWidth();

});
	
</script>