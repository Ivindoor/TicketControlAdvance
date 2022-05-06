<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
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
									id="form-name">Administrador de instrucciones</p>
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
<div class="container">
	<h2 class="sub-header">Administración</h2>
</div>
<div class="container">
	<div class="row" style="margin-bottom: 15px;">
		<div class="col-md-12"></div>
		<div class="col-md-5">
			<f:form method="POST" commandName="excelFile" id="uploadForm"
				action="uploadOracleFile" enctype='multipart/form-data'>
				<label class="	">Archivo De Instrucciones</label>
				<input id="uploadedFile" type="file" class="file" name="file">
			</f:form>
		</div>
	</div>
</div>
<br />
<div id='divTableInstructions'>
	<jsp:include page="TableDivInstruction.jsp"></jsp:include>
</div>



<div class="modal fade" id="modalEditInstruction" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
			role="dialog">
			<div class="modal-dialog modal-lg" role="dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Editar Cantidad De La PO</h4>
					</div>
					<div class="modal-body">
					<label for="exampleInputEmail1" id="lblFindPart" style="display: none"></label>
						<div class="row">
							<div class="col-sm-4">
								<div class="form group  clearfix">
									<h4>
										<label for="exampleInputEmail1">Cantidad Requerida</label>
									</h4>
									<input type="text" id="txtEditQtyRequired" class="form-control"
										aria-describedby="basic-addon2">
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form group  clearfix">
									<h4>
										<label for="exampleInputEmail1">Cantidad Almacenada</label>
									</h4>
									<input type="text" id="txtEditQtyStored"
										class="form-control" aria-describedby="basic-addon2">
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form group  clearfix">
									<h4>
										<label for="exampleInputEmail1">Active</label>
									</h4>
									<input type="text" id="txtEditActive" class="form-control"
										aria-describedby="basic-addon2">
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnUpdateInstruction" class="btn btn-primary"
							data-dismiss="modal">Guardar</button>
					</div>
				</div>
			</div>
		</div>


<script>
	$(document).ready(
			function() {
				$("#uploadedFile").fileinput({
					// uploadUrl : 'uploadOracleFile',
					uploadAsync : false,
					language : 'es',
					allowedFileExtensions : [ "xlsx", "XLSX" ],
				});

				$.ajax({
					url : 'loadAllInstructionReport',
					success : function(data) {
						$('#divTableInstructions').html(data);
					},
					error : function(vMsg) {
						$.unblockUI();
						sweetAlertError("Alerta programador algo salio mal: "
								+ vMsg + " !!!");
					}
				});
			});

	$('#uploadForm')
			.submit(
					function(e) {
						e.preventDefault();
						var $form = $("#uploadForm");
						var fd = new FormData($(this)[0]);
						console.info(fd);
						$
								.ajax({
									url : $(this).attr('action'),
									type : $(this).attr('method'),
									data : fd,
									cache : false,
									processData : false,
									contentType : false,
									beforeSend : function() {
										block();
									},
									success : function(data) {
										if (data == "Ok") {
											$
													.ajax({
														url : 'loadAllInstructionReport',
														success : function(data) {
															$(
																	'#divTableInstructions')
																	.html(data);
															$.unblockUI();
															sweetAlertSucces(
																	"Buen trabajo",
																	"El archivo a sido guardado!");
														},
														error : function(vMsg) {
															$.unblockUI();
															sweetAlertError("Alerta programador algo salio mal: "
																	+ vMsg
																	+ " !!!");
														}
													});
										} else {
											$.unblockUI();
											sweetAlertError("Error: " + data);
										}
									},
									error : function(XMLHttpRequest,
											textStatus, errorThrown) {
										$.unblockUI();
										alert(textStatus + "--" + errorThrown);
									}
								});
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

	function block() {
		$
				.blockUI({
					message : '<img src="resources/img/loading.gif" width="150" height="150"/>'
				});
	}
</script>