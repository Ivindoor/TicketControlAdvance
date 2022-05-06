<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="panel-body" id="divGeneral">
	<!-- Contenido -->
	<div class="row">
		<br>
	</div>
	<div class="row">
		<div class="col-md-12  mbottom-20">
			<hr>
			<h4 class="text-info">Mapeos Activos</h4>
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
							<th>Part Sanmina</th>
							<th>Part Customer</th>
							<th>Customer</th>
							<th>Activo</th>
							<th>Editar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="mapped" items="${mapped}">
							<tr>
								<td class="partSanmina">${mapped.partNumberSanmina}</td>
								<td class="partCustomer">${mapped.partNumberCustomer}</td>
								<td class="customer">${mapped.customer}</td>
								<td class="details-control"><a>${mapped.active}</a>
								</td>
								<td class="editMapped"><button type="button" id="btnEditMapped"
										class="btn btn-primary btn-sm">
										<span class="glyphicon glyphicon-pencil"></span>
									</button></td>
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
	$(document)
			.ready(
					function() {
						var table = $('#tableFilter')
								.DataTable(
										{
											"fnRowCallback" : function(nRow,
													aData, iDisplayIndex) {
												if (aData[3].includes("false")) {
													console.log("false");
													$('td:eq(3)', nRow)
															.html(
																	'<button type="button" class="btn btn-danger btn-sm"> <span class="glyphicon glyphicon-off"> </span> </button>');
												} else if (aData[3]
														.includes("true")) {
													console.log("true");
													$('td:eq(3)', nRow)
															.html(
																	'<button type="button" class="btn btn-primary btn-sm"> <span class="glyphicon glyphicon-off"> </span> </button>');
												}
											}
										});
					});

	$('#tableFilter tbody')
			.on('click','td.details-control',
					function() {
						block();
						var tr = $(this).closest('tr');
						var partSanmina = tr.find('.partSanmina').text();
						console.log(partSanmina)
						if (partSanmina) {
							$.ajax({
								url : 'changeActive',
								type : 'POST',
								data : {
									partSanmina : partSanmina
								},
								success : function(data) {
									if (data.includes("Ok")) {
										$.ajax({
											url : 'showAllMapped',
											success : function(data) {
												$('#divGeneral').html(data);
												$.unblockUI();
											}
										});
									} else if (data.includes("This mapped hasn't a part number sanmina")) {
										sweetAlertError("Este mappeo no tiene un numero de parte de sanmina, favor de revisar la base de datos")
									} else if (data.includes("Error: ")) {
										sweetAlertError(data)
									}
								}
							});
						} else {
							sweetAlertError("no existe el numero de parte");
						}
					});

	$('#tableFilter tbody').on('click','td.editMapped',function() {
		$('#modalEditMapped').modal('toggle');
		$('#modalEditMapped').modal('show');
		var tr = $(this).closest('tr');
		var partSanmina = tr.find('.partSanmina').text();
		var partCustomer = tr.find('.partCustomer').text();
		var customer = tr.find('.customer').text();
		$('#txtEditPartSanmina').val('');
		$('#txtEditPartCustomer').val('');
		$('#txtEditCustomer').val('');
		$('#lblFindPart').text(partSanmina);
		$('#txtEditPartSanmina').val(partSanmina);
		$('#txtEditPartCustomer').val(partCustomer);
		$('#txtEditCustomer').val(customer);
	});
	
	function sweetAlertError(data) {
		swal({
			title : "Advertencia !!!",
			text : data,
			type : "error",
			confirmButtonText : "Aceptar"
		});
	}

	function block() {
		$.blockUI({
			message : '<img src="resources/img/loading.gif" width="150" height="150"/>'
		});
	}
</script>