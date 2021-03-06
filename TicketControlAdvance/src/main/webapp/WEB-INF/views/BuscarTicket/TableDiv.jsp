<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<title>Buscar Ticket</title>
<!-- navegation -->

<!-- <c:out value="${param.accountID}"></c:out> -->
<div class="panel-body" id="contenidoActualizar">
	<!-- Contenido -->
	
	<div class="row">
		<br>
	</div>
	<div class="row">
		<div class="col-md-12  mbottom-20">
			<hr>
			<h4 class="text-danger">Información del ticket</h4>
		</div>
	</div>
	<div class="row">
		<!-- TABLE -->
		<div class="col-md-10 col-md-offset-1">
			<div class="bs-example" style="margin: 1%;">
				<table class="table table-striped" id="Table">
					<thead>
						<tr>
							<th></th>
							<th>Project</th>
							<th>QtyContainers</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td></td>
							<td >${ticket.project.projectName}</td>
							<td>${ticket.qtyContainers}</td>
							<td >${ticketEnabled}</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END TABLE -->
	</div>
	<div class="row">
		<div class="col-md-12  mbottom-20">
			<hr>
			<h4 class="text-danger">Contenido</h4>
		</div>
	</div>
	<div class="row">
		<!-- TABLE -->
		<div class="col-md-10 col-md-offset-1">
			<div class="bs-example" style="margin: 1%;">
				<p></p>
				<table id='tableFilter' class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Container</th>
							<th>PartNumber</th>
							<th>Qty</th>
							<th>Fecha</th>
							<th>${totalContainers}</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="containerActual" items="${container}">
							<tr>
								<td>${containerActual.getContainer()}</td>
								<td>${containerActual.getPartNumber()}</td>
								<td>${containerActual.getQty()}</td>
								<td>${containerActual.getDte()}</td>
								<td></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END TABLE -->
	</div>
	
</div>
<script>
	$(document).ready(function() {
		$('#tableFilter').DataTable();
		
	})
</script>

<script src="resources/js/sweetalert/sweetalert.min.js"></script>
<script src="resources/js/ScriptBuscarTicket.js"></script>
