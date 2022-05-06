<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Instrucciones</title>
</head>
<body>
	<div class="panel-body" id="dataShippingInstruction">
		<!-- Contenido -->
		<div class="row">
			<!-- TABLE -->
			<div class="col-md-10 col-md-offset-1">
				<div class="bs-example" style="margin: 1%;">
					<table id='tblInstruction' class="table table-striped table-hover">
						<!--class="table table-striped"  -->
						<thead>
							<tr>
								<th style="display: none">Id</th>
								<th>Instruccion</th>
								<th>Prioridad</th>
								<th>Po</th>
								<th>PoLine</th>
								<th>Numero De Parte</th>
								<th>Cantidad Requerida</th>
								<th>Cantidad Almacenada</th>
								<th>Fecha De Embarque</th>
								<th>Seleccionar</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="instructions" items="${instructions}">
								<tr>
									<td id="tdIdPoLine" style="display: none">${instructions.idPoLine}</td>
									<td id="tdShippingInstruction">${instructions.shippingInstruction}</td>
									<td id="tdPriority">${instructions.priority}</td>
									<td id="tdPo" class="PO">${instructions.po}</td>
									<td id="tdPoLine" class="PoLine">${instructions.poLine}</td>
									<td id="tdPartNumber">${instructions.partNumber}</td>
									<td id="tdQtyRequired">${instructions.partQtyRequired}</td>
									<td id="tdQtyStored">${instructions.partQtyStored}</td>
									<td id="tdExpiredDate">${instructions.expiredDate}</td>
									<td id="tdSelect" class="SelectClass">
										<button id="btnSelect" name="btnSelect" type="button"
											class="btn btn-info btn-md">Escoger</button>
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
	<script>
		$(document).ready(function() {
			var table = $('#tblInstruction').DataTable({
				"fnRowCallback" : function(nRow, aData, iDisplayIndex) {
					var expirationDate = aData[8];
					var now = new Date();
					var twoDigitMonth = ((now.getMonth().length + 1) === 1) ? (now.getMonth() + 1) : '0' + (now.getMonth() + 1);
					var nowFormat = now.getFullYear() + "-" + twoDigitMonth + "-" + now.getDate();
					if(expirationDate >= nowFormat){
					    $('td:eq(8)', nRow).html(
							'<span class="glyphicon glyphicon-time"><br> Procesando</span>');
					} else{
						$('td:eq(8)', nRow).html(
							'<span class="glyphicon glyphicon-fire"><br> Alerta</span>');
					}
				}
			});
		});

		$('#tblInstruction').on('click', 'tbody td.SelectClass', function() {
			var tr = $(this).closest('tr');
			var poLine = tr.find('.PoLine').text();
			console.log("PoLine" + poLine)
			$('#dataShippingInstruction').hide();
			$.ajax({
				url : 'startEngine',
				type : 'POST',
				data : {
					poLine : poLine
				},
				success : function(data) {
					console.log(data);
					if(data > 0){
						$('#btnPoLine').text(poLine);
						$('#btnRequired').text(data);
						$.ajax({
							url : 'qtyStored',
							type : 'POST',
							data : {
								poLine : poLine
							},
							success : function(data) {
								$('#btnStored').text(data);
								$('#dataInfortamtion').show();
								$("#btnNewTicket").prop("disabled", false);
							},
							error : function(vMsg) {
								sweetAlertError("Alerta programador algo salio mal: "
										+ vMsg + "!!!");
							}
						});
						
					}else{
						sweetAlertError("Esta PoLine ya esta completa!!!");
					}
				},
				error : function(vMsg) {
					sweetAlertError("Alerta programador algo salio mal: "
							+ vMsg + "!!!");
				}
			});
		});
	</script>
</body>
</html>