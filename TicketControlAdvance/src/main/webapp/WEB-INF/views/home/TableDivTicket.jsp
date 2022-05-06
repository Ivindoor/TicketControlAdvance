<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<title>Buscar Ticket</title>
<!-- navegation -->
<!-- <c:out value="${param.accountID}"></c:out> -->
<div class="panel-body" id="contenidoActualizar">
	<!-- Contenido -->
	<div class="row">
		<!-- TABLE -->
		<div class="col-md-10 col-md-offset-1">
			<div class="bs-example" style="margin: 1%;">
				<table id='tableFilter' class="table table-striped table-hover">
					<!--class="table table-striped"  -->
					<thead>
						<tr>
							<th>Contenedor</th>
							<th>NumeroParte</th>
							<th>Cantidad</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="containerList" items="${containerList}">
							<tr>
								<td id="tbContainer">${containerList.container}</td>
								<td id="tbPartNumber">${containerList.partNumber}</td>
								<td id="tbQty">${containerList.qty}</td>
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
<!-- <script src="resources/js/ScriptBuscarTicket.js"></script> -->