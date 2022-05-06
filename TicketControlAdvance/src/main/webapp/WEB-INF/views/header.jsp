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
				Ticket Control Web</a>
		</div>
		<!-- User -->
		<div class="pull-right  hidden-xs mtop-5">
			<div class="pull-left mleft-15 hidden-md hidden-sm hidden-xs">
				<img src="resources/img/user-avatar.png" alt="User profile picture"
					class="img-circle" style="height: 40px; width: 40px">
			</div>
			<a style="text-decoration: none;"
				class="text-bold text-gray pull-left mleft-20 uppercase 
							      mtop-5 mleft-10 hidden-sm hidden-xs">
				Bienvenido ${credential.name} </a> <a
				class="btn btn-default btn-circle pull-right mleft-20 mtop-5"
				id="btnLogout" href="logout"> <span
				class="glyphicon glyphicon-off"></span>
			</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="navbar-collapse  collapse pull-right" id="menunavbar"
			style="height: 1px;">

			<div id="divTicket"  class="pull-right  hidden-xs mtop-5">
				<ul class="nav navbar-nav">
					<li id="lstTicketControl" class="dropdown "><a
						href="#" class="dropdown-toggle " data-toggle="dropdown">Ticket
							Control<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a class="loadDiv" href="home" onclick="" id="home">Generar
									Ticket</a></li>
							<li><a class="loadDiv" href="BuscarTicket" id="BuscarTicket"
								onclick="">Buscar Ticket</a></li>
						</ul>
					</li>
				</ul>
			</div>
			
			<div  class="pull-right  hidden-xs mtop-5">
				<ul class="nav navbar-nav">
					<li><a
						href="http://143.116.204.102:8080/ShippingTruck/BoxSplit"
						target="_blank" onclick="" id="ShippingTruck">Shipping Truck </a>
					</li>
				</ul>
			</div>
			
			
			<div id="divSkid" class="pull-right  hidden-xs mtop-5">
				<ul class="nav navbar-nav">
					<li id="lstSkitControl" class="dropdown"><a href="#"
						class="dropdown-toggle " data-toggle="dropdown">Skid Control<span
							class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a class="loadDiv" href="HojaSkid" 
								onclick="">Generar Skid</a></li>
							<li><a>Administrador</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
</nav>
<script>
	$.unblockUI();
	$('#loading').hide();

	$(document).ready(function() {

		$.ajax({
			url : 'levelTicket',
			success : function(data) {
				console.log("Ticket " + data);
				if (data == "True") {
					$("#divTicket").show();
					console.log("Muestra");
				} else {
					// desactivalo
					console.log("Desactivado");
					$("#divTicket").hide();
				}
			},
			error : function(message) {
				console.log(message);
			}
		});
		$.ajax({
			url : 'levelSkid',
			success : function(data) {
				console.log("Skid " + data);
				if (data == "True") {
					console.log("Muestra");
					$("#divSkid").show();
				} else {
					console.log("Desactivado");
					$("#divSkid").hide();
				}
			},
			error : function(message) {
				console.log(message);
			}
		});
	});
</script>



