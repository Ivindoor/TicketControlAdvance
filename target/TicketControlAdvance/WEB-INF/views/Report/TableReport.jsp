<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel-body" id="divGeneral">
	<!-- Contenido -->
	<div class="row">
		<br>
	</div>
	<div class="row">
		<div class="col-md-12  mbottom-20"  style="margin-left: 20px;">
			<hr>
			<h4 class="text-info">Reporteo</h4>
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
							<th>Ticket</th>
							<th>Proyecto</th>
							<th>Numero De Parte</th>
							<th>Cantidad</th>
							<th>PO</th>
							<th>Estatus</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="report" items="${report}">
							<tr>
								<td class="partSanmina">${report.ticket}</td>
								<td class="partCustomer">${report.project}</td>
								<td class="customer">${report.partNumber}</td>
								<td class="details-control">${report.qty}</td>
								<td class="details-control">${report.po}</td>
								<td class="editMapped">
									<span class="glyphicon glyphicon-pencil">${report.enabled}</span>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END TABLE -->
	</div>
</div>