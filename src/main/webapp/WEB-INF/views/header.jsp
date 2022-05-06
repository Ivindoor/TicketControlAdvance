<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<style type="text/css">
.icon-applogo {
	background-image: url("resources/img/apLogo.png");
	background-position: center center;
}
</style>
<nav
	class="navbar navbar-default navbar-static-top  margin-0 col-xs-12 padding-0"
	role="navigation">
	<div class="container-fluid">
		<!-- APP tittle -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#menunavbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<img src="resources/img/logo.png" alt="appIcon" class="pull-left"
				style="margin-top: 5px; height: 40px; width: 40px"> <a
				class="navbar-brand" style="padding-left: 25px;" href="#">
				Ticket Control Advance</a>
		</div>
		<!-- User -->
		<div class="pull-right  hidden-xs mtop-5">
			<div class="pull-left mleft-15 hidden-md hidden-sm hidden-xs">
				<img src="resources/img/user-avatar.png" alt="User profile picture"
					class="img-circle" style="height: 40px; width: 40px">
			</div>
			<a style="text-decoration: none;" class="navbar-text hidden-xs">
				Bienvenido <!-- 				<div class="row"> -->
				${currentUser.universalLogin} <!-- 				</div> -->
			</a> <a class="btn btn-default btn-circle pull-right mleft-20 mtop-5"
				id="btnLogout" href="logout"> <span
				class="glyphicon glyphicon-off"></span>
			</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="navbar-collapse  collapse pull-right" id="menunavbar"
			style="height: 1px;">
			<div id="divTicket" class="pull-right  hidden-xs mtop-5">
				<ul class="nav navbar-nav">
					<li id="lstTicketControl" class="dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown">Ticket Control
							Advance<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a class="loadDiv" href="home" id="home">Generar
									Nuevo Ticket</a></li>
							<li><a class="loadDiv" href="BuscarTicket" id="BuscarTicket">Eliminar
									Ticket</a></li>
<!-- 							<li><a class="loadDiv" href="FileManager" id="LoadFile">Administrador -->
<!-- 									De Instrucciones</a></li> -->
							<li><a class="loadDiv" href="MappedPartNumber" id="Mapped"
								onclick="">Mapeo De Numero De Parte</a></li>
							<li style="display: none"><a class="loadDiv" href="TicketReport" id="Mapped"
								onclick="">Reporte De Tickets</a></li>
						</ul></li>
				</ul>
			</div>
			<div id="divUsers" class="pull-right  hidden-xs mtop-5" style="display: none">
				<ul class="nav navbar-nav">
					<li id="lstUsers" class="dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown">Logins<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a class="loadDiv" href="creatorUser" id="creatorUser">Nuevo Login</a></li>
						</ul></li>
				</ul>
			</div>
			<div class="pull-right  hidden-xs mtop-5">
				<ul class="nav navbar-nav">
					<li><a
						href="http://GDL1AMIISW01:8080/ShippingTruck/BoxSplit"
						target="_blank" onclick="" id="ShippingTruck">Shipping Truck </a></li>
				</ul>
			</div>
		</div>
	</div>
</nav>
<script>
	//$.unblockUI();
	$('#loading').hide();
	$(document).ready(function() {
		$.ajax({
			url : 'levelAdmin',
			success : function(data) {
				console.log("Admin " + data);
				if (data.includes("1")) {
					$("#BuscarTicket").hide();
					$("#LoadFile").hide();
					$("#Mapped").hide();
				} else if (data.includes("2")) {
					$("#BuscarTicket").show();
					$("#LoadFile").hide();
					$("#Mapped").hide();
				} else if (data.includes("3") || data.includes("4")) {
					$("#BuscarTicket").show();
					$("#LoadFile").show();
					$("#Mapped").show();
				}
			},
			error : function(message) {
				console.log(message);
			}
		});
	});
</script>