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
							<th>Ticket</th>
							<th>Project</th>
							<th>QtyContainers</th>
							<th>Status</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${ticket.ticket}</td>
							<td>${ticket.project.projectName}</td>
							<td>${ticket.qtyContainers}</td>
							<td>${ticketEnabled}</td>
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
							<th align="center">Container</th>
							<th align="center">PartNumber</th>
							<th align="center">Qty</th>
							<th align="center">Fecha</th>
							<%-- 							<th>${totalContainers}</th> --%>
							<th align="center" id="headRemove">Eliminar Serial</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="containerList" items="${containerList}">
							<tr>
								<td align="center" class="serial">${containerList.getContainer()}</td>
								<td align="center" class="part">${containerList.getPartNumber()}</td>
								<td align="center" class="qty">${containerList.getQty()}</td>
								<td align="center">${containerList.getDte()}</td>
								<td class="remove" align="center" id="rowRemove">
									<button type="button" id="btnRemoved"
										class="btn btn-danger btn-sm removeSerial">
										<span class="glyphicon glyphicon-remove-sign"></span>
									</button>
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

<div class="row">
	<div id="modalRemove" class="modal fade" role="dialog">
		<div class="modal-dialog modal-lg">
			<!-- Modal content-->
			<div class="modal-content">
				<!-- style=" width: 1000px;-->
				<div class="modal-header">
					<button type="button" class="close" id="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title" style="text-align: left;">Remover
						Unidades Del Ticket</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-md-3">
							<h4>
								<label for="exampleInputEmail1">Validacion De Serial</label>
							</h4>
							<input type="text" id="txtSerial" class="form-control"
								aria-describedby="basic-addon2" placeholder="Escanea el serial">
						</div>
					</div>
					<br />
					<div class="row">
						<table id='tblRemoved' class="table table-striped table-hover">
							<thead>
								<tr>
									<th>Serial A Remover Del Ticket</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<div class="panel-body">
											<div class="row">
												<div class="col-md-4">
													<h4>
														<label for="exampleInputEmail1">Serial </label>
													</h4>
													<input type="text" id="txtValSerial" class="form-control"
														aria-describedby="basic-addon2" disabled="disabled">
												</div>
												<div class="col-md-4">
													<h4>
														<label for="exampleInputEmail1">Numero De Parte</label>
													</h4>
													<input type="text" id="txtValPartNumber"
														class="form-control" aria-describedby="basic-addon2"
														disabled="disabled">
												</div>
												<div class="col-md-4">
													<h4>
														<label for="exampleInputEmail1">Cantidad</label>
													</h4>
													<input type="text" id="txtValQty" class="form-control"
														aria-describedby="basic-addon2" disabled="disabled">
												</div>
											</div>
											<br>
											<div class="row">
												<div class="col-md-12">
													<div class="panel panel-gray-dark">
														<div class="panel-heading">
															<h3 class="panel-title">Justificacion</h3>
														</div>
														<div class="panel-body">
															<textarea id="txtCommentSerial" class="form-control"
																rows="5"
																placeholder="El comentario debe tener una justificacion del motivo por el cual se remueve la unidad del ticket (ya sea por purga o retiro del material por medio de un SOAP)"
																required></textarea>
														</div>
														<div class="panel-footer"></div>
													</div>
												</div>
											</div>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- end row -->
				</div>
				<!--end modal body  -->
				<div class="modal-footer">
					<div class="col-md-1 pull-left">
						<button id="btnRemoveSerial" class="btn btn-primary btn-lg"
							disabled>Remover Unidad</button>
					</div>
					<!-- 					<div class="col-sm-offset-8 pull-right"> -->
					<!-- 						<button id="cancel" name="cancel" type="button" -->
					<!-- 							class="btn btn-danger btn-lg" data-dismiss="modal" disabled>Cancelar</button> -->
					<!-- 					</div> -->
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
// 		$('#tableFilter').DataTable();
	});

	$('#modalRemove').on('shown.bs.modal', function() {
		$('#txtSerial').prop('disabled', false);
		$('#btnRemoveSerial').prop('disabled', true);
		$('#txtSerial').val("");
		$('#txtSerial').focus();
		$('#txtCommentSerial').val("");
	})

	$('#tableFilter tbody').on('click', 'td.remove button', function() {
		$('#modalRemove').modal('toggle');
		$('#modalRemove').modal('show');
		var tr = $(this).closest('tr');
		var serial = tr.find('.serial').text();
		var part = tr.find('.part').text();
		var qty = tr.find('.qty').text();
		$('#txtValSerial').val('');
		$('#txtValPartNumber').val('');
		$('#txtValQty').val('');

		$('#txtValSerial').val(serial);
		$('#txtValPartNumber').val(part);
		$('#txtValQty').val(qty);
	});

	$('#txtSerial').keypress(function(e) {
		var valSerial = $('#txtSerial').val();
		var serialToRemove = $('#txtValSerial').val();
		if (e.keyCode == 13) {
			if (valSerial == serialToRemove) {
				$('#txtSerial').prop('disabled', true);
// 				$('#txtCommentSerial').prop('disabled', false);
// 				$('#txtCommentSerial').focus();
				$('#btnRemoveSerial').prop('disabled', false);
			}
		}
	});
	

	$("#btnRemoveSerial").click(
			function() {
				block();
				var serial = $('#txtSerial').val();
				var comment = $('#txtCommentSerial').val();
				console.log("Total de caracteres en el length" + comment.length);
				
				if (comment.length > 30){
					console.log("Podemos eliminar el serial cumpliendo con la justificacion");
					$.ajax({
						url : 'removeSerial',
						type : 'POST',
						data : {
							serial : serial,
							comment : comment
						},
						success : function(data) {
							$.unblockUI();
							if (data == "") {
								sweetAlertSucces("El serial","a sido removido del ticket");
								$('#modalRemove').modal('toggle');
							}
						},
						error : function(vMsg) {
							$.unblockUI();
							sweetAlertError(vMsg);
						}
					});
				} else{ 
					sweetAlertError("Favor de completar una justificacion validad para poder remover este serial.");
					$.unblockUI();
				}
				
			});

	function block() {
		$.blockUI({
			message : '<img src="resources/img/loading.gif" width="150" height="150"/>'
		});
	}

	function sweetAlertError(data) {
		swal({
			title : "Advertencia !!!",
			text : data,
			type : "error",
			confirmButtonText : "Aceptar"
		});
	}

	function sweetAlertSucces(title, data) {
		swal(title, data, "success")
	}
</script>
<script src="resources/js/sweetalert/sweetalert.min.js"></script>
<script src="resources/js/ScriptBuscarTicket.js"></script>