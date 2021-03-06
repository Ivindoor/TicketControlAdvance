<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel-body" id="divValid">
	<!-- Contenido -->
	<div class="row">
		<br>
	</div>
	<div class="row">
		<div class="col-md-12  mbottom-20">
			<hr>
			<h4 class="text-info">Validaciones</h4>
		</div>
	</div>
	<div class="row">
		<!-- TABLE -->
		<div class="col-md-10 col-md-offset-1">
			<div class="bs-example" style="margin: 1%;">
				<p></p>
				<table id='tblValid' class="table table-striped table-hover">
					<thead>
						<tr>
							<th>Container</th>
							<th>Numero De Parte</th>
							<th>Cantidad</th>
							<th>Validado</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="LstContainers" items="${LstContainers}">
							<tr>
								<td>${LstContainers.serialNumber}</td>
								<td>${LstContainers.partNumber}</td>
								<td>${LstContainers.quantity}</td>
								<td id="changeHTML">No</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<!-- END TABLE -->
	</div>
</div>