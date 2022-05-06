<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<title>Home Page</title>
<!-- navegation -->
<div class="row">
	<nav class="navbar  shrink navbar-inverse mtop-0 col-xs-12 padding-0 "
		id="navigation-b">
		<!--navbar-static-top  -->
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12 padding-0">
					<div class="col-sm-6 col-xs-2 ">
						<div class="row">
							<!-- hidden on smartphones -->
							<div class="col-sm-offset-1">
								<p class="pull-left  h4 uppercase navbar-text hidden-xs"
									id="form-name">Nuevo Ticket</p>
							</div>
						</div>
					</div>
					<div class="col-sm-4 col-sm-offset-2 col-xs-10 pull-right">
						<div class="row">
							<div class="col-xs-6">
							</div>
							<div class="col-xs-7 col-xs-offset-4 col-xs-10">
								<button type="button" id="btnSelectProject"
									class="btn btn-primary btn-lg  btn-block navbar-btn uppercase text-bold btn-loading"
									onclick="showPopup()">
									<span class="content visible-lg">Selecciona proyecto</span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</nav>
</div>
<section class="container-fluid mbottom-10" id="main-content">
	<div class="row">
		<div class="col-lg-8 col-lg-offset-2 clearfix">
			<div class="panel panel-default mbottom-10" id="form-chunk">
				<section class="panel panel-default">
					<header class="panel-heading" style="background-color: #5B5B5B;">
						<h4 class="panel-title" style="color: white;">
							General &nbsp;&nbsp;<span id="lblProjectName"
								class="label label-primary  pull-right"></span>
						</h4>
					</header>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-4">
								<h4>
									<label for="exampleInputEmail1">Ticket</label>
								</h4>
								<div class="form group mandatory clearfix">
									<input type="text" class="form-control input-sm "
										id="txtTicket" name="txtTicket"
										disabled="disabled">
								</div>
							</div>
							<div id="divPartNumber" class="col-md-4" style="display: none;">
								<h4>
									<label id="lblPartNumber" for="exampleInputEmail1">Numero
										De Parte</label>
								</h4>
								<div class="form group mandatory clearfix">
									<input type="text" name="txtPartNumber" id="txtPartNumber"
										class="form-control input-sm"
										placeholder="Ingrese un numero de parte" disabled="disabled">
								</div>
							</div>
							<div class="col-md-4">
								<h4>
									<label for="exampleInputEmail1">Serial</label>
								</h4>
								<div class="form group mandatory clearfix">
									<input type="text" name="NoContainer" id="NoContainer"
										class="form-control input-sm" placeholder="Ingrese un serial"
										disabled="disabled">
								</div>
							</div>
						</div>
						<div id="divPO" class="row" style="display: none;">
							<div class="col-sm-2 col-md-offset-0">
							
								<div class="form group  clearfix">
									<h4>
										<label for="exampleInputEmail1" id="LabelPO">PO</label>
									</h4>
									<input type="tel" class="form-control input-sm " id="PO"
										disabled="disabled" placeholder="Ingrese un PO">
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col-md-12  mbottom-20">
								<hr>
								<h4>Información del ticket</h4>
							</div>
						</div>
						<div id='contenidoActualizar'>
							<jsp:include page="TableDivTicket.jsp"></jsp:include>
						</div>
					</div>
					<div id="myModal" class="modal fade" role="dialog">
						<div class="modal-dialog modal-lg">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" id="close"
										data-dismiss="modal">&times;</button>
									<h4 class="modal-title" style="text-align: center;">Selecciona
										un proyecto</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-md-8 col-md-offset-2  mbottom-20">
											<div class="panel panel-gray-dark">
												<div class="panel-heading">
													<h3 class="panel-title">Project</h3>
												</div>
												<div class="panel-body">
													<select name="selectProject" id="selectProject"
														class="form-control">
														<option disabled selected value="">SELECT PROJECT</option>
													</select>
												</div>
												<div class="panel-footer"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<div class="col-sm-1 pull-left">
										<button id="btnSaveProject" name="btnSaveProject"
											class="btn btn-primary btn-lg" data-dismiss="modal" disabled>Guardar</button>
									</div>
									<div class="col-sm-offset-8 pull-right">
										<button id="cancel" name="cancel" type="button"
											class="btn btn-danger btn-lg" data-dismiss="modal">Cancelar</button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<footer class="panel-footer" style="background-color: #5B5B5B;">
						<button type="button" class="btn btn-danger btn-sm"
							id="btnNewTicket" disabled>
							<span class="glyphicon glyphicon-plus"></span>Nuevo Ticket
						</button>
						<button type="button" id="btnSaveTicket"
							class="btn btn-danger btn-sm" disabled>
							<span class="glyphicon glyphicon-floppy-disk"></span> Guardar
						</button>
						<button type="button" id="btnOpen" data-toggle="modal"
							data-target="#myModalOpen" class="btn btn-danger btn-sm">
							<span class="glyphicon glyphicon-folder-open"></span> Abrir
							Ticket
						</button>
						<button type="button" id="btnReprint" name="btnReprint"
							class="btn btn-danger btn-sm" disabled>
							<span class="glyphicon glyphicon-print"></span> Reimprimir ticket
						</button>
						<button type="button" id="btnClean" class="btn btn-danger btn-sm"
							disabled>
							<span class="glyphicon glyphicon-remove"></span> Limpiar Campos
						</button>
					</footer>
					<div class="modal fade" id="myModalOpen" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<h4 class="modal-title" id="myModalLabel">Abrir Ticket</h4>
								</div>
								<div class="modal-body">
									<div class="row">
										<div class="col-sm-6 col-md-offset-3">
											<div class="form group  clearfix">
												<h4>
													<label for="exampleInputEmail1">Ticket</label>
												</h4>
												<input type="text" id="txtOpenTicket" name="txtOpenTicket"
													class="form-control" placeholder="Ingrese un Ticket"
													aria-describedby="basic-addon2">
											</div>
										</div>
									</div>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal">Cerrar</button>
									<button type="button" id="btnOpenTicket"
										class="btn btn-primary" data-dismiss="modal">Abrir
										Ticket</button>
								</div>
							</div>
						</div>
					</div>
				</section>
			</div>
		</div>
	</div>
</section>
<script src="resources/js/sweetalert/sweetalert.min.js"></script>
<script src="resources/js/ScriptHome.js"></script>