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
									id="form-name">Reporte de tickets</p>
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
	<div class="col-md-12" style="margin-left: 20px;">
		<hr>
		<h4 class="text-info">Filtros</h4>
	</div>
	<div class="col-md-12">

		<div class="col-md-2" style="margin-top: 20px;">
			<div>
				<span>Numero De Ticket</span> <input type="text"
					class="form-control input-sm " id="txtTicketReport"
					placeholder="Ingrese un numero de ticket">
			</div>
		</div>
		<div class="col-md-2" style="margin-top: 20px;">
			<div>
				<span>Numero De Parte</span> <input type="text"
					class="form-control input-sm " id="txtPartReport"
					placeholder="Ingrese un numero de parte">
			</div>
		</div>
		<div class="col-md-2" style="margin-top: 20px;">
			<div>
				<span>PO</span> <input type="text" class="form-control input-sm "
					id="txtPoReport" placeholder="Ingrese una PO">
			</div>
		</div>
		<div class="col-md-2" style="margin-top: 20px;">
			<div class="form-group">
				<div id="filterDate2">
					<span>Fecha Inicial</span>
					<!-- Datepicker as text field -->
					<div class="input-group date" data-date-format="dd-mm-yyyy">
						<input type="text" class="form-control"
							placeholder="Ingrese un rago de fechas">
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-th"></span>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div class="col-md-2" style="margin-top: 20px;">
			<div class="form-group">
				<div id="filterDate2">
					<span>Fecha Final</span>
					<!-- Datepicker as text field -->
					<div class="input-group date" data-date-format="dd-mm-yyyy">
						<input type="text" class="form-control"
							placeholder="Ingrese un rago de fechas">
						<div class="input-group-addon">
							<span class="glyphicon glyphicon-th"></span>
						</div>
					</div>

				</div>
			</div>
		</div>
		<div class="col-md-2" style="margin-top: 40px;">
			<div>
				<div class="btn-group">
					<button id="btnValue" type="button" style="height: 28px"
						class="btn btn-danger" aria-haspopup="true" aria-expanded="false">Seleccione
						un proyecto</button>
					<button type="button" style="height: 28px"
						class="btn btn-danger dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul id="dropProjects" class="dropdown-menu pre-scrollable">
						<li class="disabled"><a href="#">Seleccione un proyecto</a></li>
						<li role="separator" class="divider"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-12">
		<div class="row" id="divReport">
			<jsp:include page="TableReport.jsp"></jsp:include>
		</div>
	</div>

</div>
<script>
	$(document)
			.ready(
					function() {
						var allProjects = [];
						var currentHeading = "";

						$
								.ajax({
									url : 'getProjectsReport',
									success : function(data) {
										for (var i = 0; i < data.length; i++)
											allProjects[i] = data[i].projectName;

										$
												.each(
														allProjects,
														function(key, value) {
															if (value != 'NA'
																	&& value != 'EMBARQUES'
																	&& value != 'NOKIA') {
																var $dropdown = $("#dropProjects");
																var menuItemNo = value;
																$dropdown
																		.append("<li id='" + key + "'><a class='dropdown-item' href='#'>"
																				+ menuItemNo
																				+ "</a></li>");
															}
														});
									}
								});
					});

	// $('#dropProjects').on("click", function() { 
	// 	var project = this.innerHTML;
	// 	$('#btnValue').text(project);            
	// });      

	$('.input-group.date').datepicker({
		format : "dd-mm-yyyy"
	});

	$(document).on('click', '#dropProjects li a', function() {
		console.log("Selected Option:" + $(this).text());
		$("#btnValue").text($(this).text());
	});
</script>