<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<title>Mapped Part Number</title>
<div class="row">
	<nav class="navbar  shrink navbar-inverse mtop-0 col-xs-12 padding-0 "
		id="navigation-b">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 padding-0">
					<div class="col-sm-6 col-xs-2 ">
						<div class="row">
							<div class="col-sm-offset-1">
								<p class="pull-left  h4 uppercase navbar-text hidden-xs"
									id="form-name">Mapeo de numero de parte</p>
							</div>
						</div>
					</div>
					<div class="col-sm-4 col-sm-offset-2 col-xs-10 pull-right">
						<div class="row">
							<div class="col-xs-6"></div>
							<div class="col-xs-7 col-xs-offset-4 col-xs-10"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<section class="container-fluid mbottom-10" id="main-content">
		<div class="row">
			<div class="col-lg-10 col-lg-offset-1 clearfix">
				<div class="panel panel-default mbottom-10" id="form-chunk">
					<section class="panel panel-default">
						<header class="panel-heading" style="background-color: #e0e0e0;">
							<h4 class="panel-title" style="color: Black;">
								SECCION DE MAPEO
							</h4>
						</header>
					</section>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-12">
							<h4 class="panel-title" style="color: Black;">
								REALIZAR NUEVO MAPEO 
							</h4>
							</div>
						</div>
						<div class="row">
							<div class="col-md-4">
								<h4>
									<label for="exampleInputEmail1">Part Number Sanmina</label>
								</h4>
								<div>
									<input type="text" class="form-control input-sm "
										id="txtPartSanmina" required>
								</div>
							</div>
							<div class="col-md-4">
								<h4>
									<label for="exampleInputEmail1">Part Number Customer</label>
								</h4>
								<div>
									<input type="text" class="form-control input-sm "
										id="txtPartCustomer" required>
								</div>
							</div>
							<div class="col-md-4">
								<h4>
									<label for="exampleInputEmail1">Customer</label>
								</h4>
								<div>
									<input type="text" class="form-control input-sm "
										id="txtCustomer" required>
								</div>
							</div>
						</div>
						<br />
						<div class="row">
							<div class="col-md-2 col-md-offset-5">
								<button type="button" id="btnsaveMapped" class="btn btn-danger">Guardar</button>
							</div>
						</div>
						<br />
						<div class="row" id="divGeneral">
							<jsp:include page="TableMapped.jsp"></jsp:include>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modalEditMapped" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
			role="dialog">
			<div class="modal-dialog modal-lg" role="dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Editar Mapeo</h4>
					</div>
					<div class="modal-body">
					<label for="exampleInputEmail1" id="lblFindPo" style="display: none"></label>
						<div class="row">
							<div class="col-sm-4">
								<div class="form group  clearfix">
									<h4>
										<label for="exampleInputEmail1">Part Sanmina</label>
									</h4>
									<input type="text" id="txtEditPartSanmina" class="form-control"
										aria-describedby="basic-addon2">
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form group  clearfix">
									<h4>
										<label for="exampleInputEmail1">Part Customer</label>
									</h4>
									<input type="text" id="txtEditPartCustomer"
										class="form-control" aria-describedby="basic-addon2">
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form group  clearfix">
									<h4>
										<label for="exampleInputEmail1">Customer</label>
									</h4>
									<input type="text" id="txtEditCustomer" class="form-control"
										aria-describedby="basic-addon2">
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnUpdateMapped" class="btn btn-primary"
							data-dismiss="modal">Guardar</button>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>
<script>
	$('#btnUpdateMapped')
			.click(
					function() {
						block();
						var partSanmina = $('#txtEditPartSanmina').val();
						var partCustomer = $('#txtEditPartCustomer').val();
						var customer = $('#txtEditCustomer').val();
						var findPartSanmina = $('#lblFindPart').text();
						if (partSanmina && partCustomer && customer) {
							$.ajax({
								url : 'updateMapped',
								type : 'POST',
								data : {
									partSanmina : partSanmina,
									partCustomer : partCustomer,
									customer : customer,
									findPartSanmina : findPartSanmina
								},
								success : function(data) {
									if(data.includes("Ok")){
										$.ajax({
											url : 'showAllMapped',
											success : function(data) {
												$('#divGeneral').html(data);
												$.unblockUI();
												sweetAlertSucces(
														"Success",
														"Mapeo guardado");
											}
										});
									} else if (data.includes("Error: ")) {
										$.unblockUI();
										sweetAlertError(data);
									}
								}, 
								error : function(vMsg){
									$.unblockUI();
									sweetAlertError("Error: " + vMsg);
								}
							});
						} else {
							$.unblockUI();
							sweetAlertError("Error uno de los 3 campos se encuentra vacio, son obligatorios todos los campos");
						}
					});

	$('#btnsaveMapped')
			.click(
					function() {
						var partSanmina = $('#txtPartSanmina').val();
						var partCustomer = $('#txtPartCustomer').val();
						var customer = $('#txtCustomer').val();

						if (partSanmina && partCustomer && customer) {
							block();
							$
									.ajax({
										url : 'saveNewMapped',
										type : 'POST',
										data : {
											partSanmina : partSanmina,
											partCustomer : partCustomer,
											customer : customer
										},
										success : function(data) {
											console.log("data del save "+ data)
											if (data.includes("Ok")) {
												$
														.ajax({
															url : 'showAllMapped',
															success : function(
																	data) {
																$('#divGeneral').html(data);
																$.unblockUI();
																sweetAlertSucces("Success","Mapeo guardado");
																$('#txtPartSanmina').val("");
																$('#txtPartCustomer').val("");
																$('#txtCustomer').val("");
															}
														});
											} else if (data.includes("Este numero de parte de sanmina ya existe")) {
												$.unblockUI();
												sweetAlertError(data);
											} else if (data.includes("Error: ")) {
												$.unblockUI();
												sweetAlertError();
											}
										},
										error : function(vMsg) {
											$.unblockUI();
											sweetAlertError("Error: " + vMsg);
										}
									});
						} else {
							sweetAlertError("Todos los campos son obligatorios, favor de llenarlos");
						}
					});

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

	function sweetAlertConfirm(body, footer, footerbody) {
		swal({
			title : 'Are you sure?',
			text : "body" + "it's the amount you want to save!",
			type : 'warning',
			showCancelButton : true,
			confirmButtonColor : '#3085d6',
			cancelButtonColor : '#d33',
			confirmButtonText : 'Yes!'
		}).then(
				function() {
					swal('footer' + 'Deleted!', 'footerbody'
							+ 'Your file has been deleted.', 'success')
				});
	}

	function block() {
		$
				.blockUI({
					message : '<img src="resources/img/loading.gif" width="150" height="150"/>'
				});
	}
</script>