<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="panel-body" id="contenidoPartial">
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="bs-example" style="margin: 1%;">
				<table id='tblTicket' class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Ticket</th>
							<th>Contenedor</th>
							<th>NumeroParte</th>
							<th>Cantidad</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="partialList" items="${partialList}">
							<tr>
								<td id="tbTicket">${partialList.ticket}</td>
								<td id="tbContainer">${partialList.container}</td>
								<td id="tbPartNumber">${partialList.partNumber}</td>
								<td id="tbQty">${partialList.qty}</td>
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
		$('#tblTicket').DataTable();
	})
</script>
<script src="resources/js/sweetalert/sweetalert.min.js"></script>