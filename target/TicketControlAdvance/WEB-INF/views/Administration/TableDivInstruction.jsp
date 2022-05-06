<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel-body" id="divTableInstructions">
	<!-- Contenido -->
	<div class="row">
		<!-- TABLE -->
		<div class="col-md-12">
			<div class="bs-example" style="margin: 1%;">
				<table id='tblReport' class="table table-striped table-hover">
					<!--class="table table-striped"  -->
					<thead>
						<tr>
							<th>Prioridad</th>
							<th>Instruccion</th>
							<th>Po</th>
							<th>Po Line</th>
							<th>Numero De Parte</th>
							<th>Cantidad Requerida</th>
							<th>Cantidad Almacenada</th>
							<th>Activo</th>
							<th>Fecha De Embarque</th>
							<th>Project</th>
<!-- 							<th>Editar</th> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reportList" items="${reportList}">
							<tr class="RowPaint">
								<td id="tbPriority">${reportList.priority}</td>
								<td id="tbshippingInstruction">${reportList.shippingInstruction}</td>
								<td id="tbPo">${reportList.po}</td>
								<td id="tbPoLine">${reportList.poLine}</td>
								<td id="tbPartNumber">${reportList.partNumber}</td>
								<td id="tbPartQtyRequired">${reportList.partQtyRequired}</td>
								<td id="tbPartQtyStored">${reportList.partQtyStored}</td>
								<td id="tbActive">${reportList.active}</td>
								<td id="tbExpiredDate">${reportList.expiredDate}</td>
								<td id="tbProject">${reportList.project}</td>
<!-- 								<td class="editInstruction"><button type="button" -->
<!-- 										id="btnEditInstruction" class="btn btn-primary btn-sm"> -->
<!-- 										<span class="glyphicon glyphicon-pencil"></span> -->
<!-- 									</button></td> -->
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
		var table = $('#tblReport').DataTable({"fnRowCallback" : function(nRow,aData, iDisplayIndex) {
						var expirationDate = aData[8];
						var now = new Date();
						var twoDigitMonth = ((now.getMonth().length + 1) === 1) ? (now.getMonth() + 1) : '0' + (now.getMonth() + 1);
						var nowFormat = now.getFullYear() + "-" + twoDigitMonth + "-" + now.getDate();
						if (expirationDate >= nowFormat) { 
							$('td:eq(8)', nRow).html('<span class="glyphicon glyphicon-time"><br> Procesando</span>');
						} else {
							$('td:eq(8)', nRow).html('<span class="glyphicon glyphicon-fire"><br> Alerta</span>');
						}

						if (aData[7].includes("false") && aData[5].includes("0")) {
							$('td:eq(7)', nRow).html('<span class="glyphicon glyphicon-ok-circle"><br> Completa</span>');
							$('td:eq(8)', nRow).html('<span class="glyphicon glyphicon-leaf"><br> Listo</span>');
						} else if (aData[7].includes("true") && aData[6] == "0") {
							$('td:eq(7)', nRow).html('<span class="glyphicon glyphicon-remove-circle"><br> Vacía</span>');
						} else {
							$('td:eq(7)', nRow).html('<span class="glyphicon glyphicon-warning-sign"><br> Empezada</span>');
						}
					}
			});
	});
	
	$('#tblReport tbody').on('click','td.editInstruction',function() {
		$('#modalEditInstruction').modal('toggle');
		$('#modalEditInstruction').modal('show');
		var tr = $(this).closest('tr');
		var qtyRequired = tr.find('.partQtyRequired').text();
		var qtyStored = tr.find('.partQtyStored').text();
		var active = tr.find('.active').text();
		var poLine = tr.find('.poLine').text();
		$('#txtEditQtyRequired').val('');
		$('#txtEditQtyStored').val('');
		$('#txtEditActive').val('');
		$('#lblFindPo').text(poLine);
		$('#txtEditQtyRequired').val(qtyRequired);
		$('#txtEditQtyStored').val(qtyStored);
		$('#txtEditActive').val(active);
	});
</script>