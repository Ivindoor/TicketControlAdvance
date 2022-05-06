<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<title>Buscar Ticket</title>
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
									id="form-name">Buscar Ticket</p>
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
<section class="container-fluid mbottom-10" id="main-content">
	<div class="row">
		<div class="col-lg-8 col-lg-offset-1 clearfix"
			style="left: 10%; right: 10%;">
			<div class="panel panel-default mbottom-10" id="form-chunk">
				<section class="panel panel-default">
					<header class="panel-heading" style="background-color: #5B5B5B;">
						<h3 class="panel-title" style="color: white;">
							General &nbsp;&nbsp;<span id="labelSearchTicket"
								class="label label-primary pull-right "
								style="font-size: medium;"></span>
						</h3>
					</header>
					<br />
					<div class="row">
						<div id="modalDelete" class="modal fade" role="dialog">
							<div class="modal-dialog modal-lg">
								<!-- Modal content-->
								<div class="modal-content">
									<!-- style=" width: 1000px;-->
									<div class="modal-header">
										<button type="button" class="close" id="close"
											data-dismiss="modal">&times;</button>
										<h4 class="modal-title" style="text-align: center;">Eliminar
											Ticket</h4>
									</div>
									<div class="modal-body">
										<div class="row">
											<div class="col-md-8 col-md-offset-2  mbottom-20">
												<div class="panel panel-gray-dark">
													<div class="panel-heading">
														<h3 class="panel-title">Comentario</h3>
													</div>
													<div class="panel-body">
														<textarea id="txtComment" name="txtComment"
															class="form-control" rows="5" required></textarea>
													</div>
													<div class="panel-footer"></div>
												</div>
											</div>
										</div>
										<!-- end row -->
									</div>
									<!--end modal body  -->
									<div class="modal-footer">
										<div class="col-sm-1 pull-left">
											<button id="btnDelete" name="btnDelete"
												class="btn btn-primary btn-lg" data-dismiss="modal">Eliminar</button>
										</div>
										<div class="col-sm-offset-8 pull-right">
											<button id="cancel" name="cancel" type="button"
												class="btn btn-danger btn-lg" data-dismiss="modal">Cancelar</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-4 col-md-offset-2 ">
							<select name="selectProject" id="selectProject"
								class="form-control">
								<option disabled selected value="">SELECCIONE UN FILTRO</option>
								<option value="4">Ticket</option>
								<option value="6">Serial</option>
							</select>
						</div>
						<div class="col-sm-4 col-md-offset-0 ">
							<div class="input-group mandatory clearfix">
								<input type="text" id="txtSearchTicket" class="form-control "
									placeholder="Ingrese un Contenedor"
									aria-describedby="basic-addon2" disabled="disabled"> <span
									class="input-group-btn">
									<button class="btn btn-secondary btn-primary  " type="button"
										id="btnSearchTicket" onclick="searchButton();"
										disabled="disabled">
										<span class="glyphicon glyphicon-search"></span> Buscar
									</button>
								</span>

							</div>
						</div>
					</div>
					<div id='contenidoActualizar'>
						<jsp:include page="TableDiv.jsp"></jsp:include>
					</div>
					<footer class="panel-footer" style="background-color: #5B5B5B;">
						<button type="button" class="btn btn-danger btn-sm"
							id="btnDeleteTicket" name="btnDeleteTicket" disabled>
							<span class="glyphicon glyphicon-trash"></span> Eliminar Ticket
						</button>
					</footer>
				</section>
			</div>
		</div>
	</div>
</section>
<script src="resources/js/sweetalert/sweetalert.min.js"></script>
<script src="resources/js/ScriptBuscarTicket.js"></script>
</body>
</html>