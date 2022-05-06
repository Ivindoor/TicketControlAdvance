<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>TICKET CONTROL WEB</title>
<style>
body.loading {
	overflow: hidden;
}
</style>

<!-- JQuery -->
<script src="webjars/jquery/2.2.3/jquery.min.js"></script>
<script src="webjars/datatables/1.10.11/js/jquery.dataTables.js"
	type="text/javascript"></script>
<link href="webjars/datatables/1.10.11/css/jquery.dataTables.css"
	rel="stylesheet">
<script src="webjars/tablesorter/2.25.4/js/jquery.tablesorter.js"
	type="text/javascript"></script>
<script src="webjars/jquery-blockui/2.65/jquery.blockUI.js"
	type="text/javascript"></script>

<!-- file uploader -->
<link href="webjars/bootstrap-fileinput/4.2.0/css/fileinput.min.css"
	media="all" rel="stylesheet" type="text/css" />
<script src="webjars/bootstrap-fileinput/4.2.0/js/fileinput.min.js"
	type="text/javascript"></script>
<script
	src="webjars/bootstrap-fileinput/4.2.0/js/fileinput_locale_es.js"></script>

<!-- Bootstrap -->
<link href="resources/css/bootstrap-sanmina.css" rel="stylesheet">
<script src="resources/js/bootstrap.min.js"></script>

<!-- Select2 autocmplete select box -->
<link href="webjars/select2/4.0.2/dist/css/select2.min.css"
	rel="stylesheet">
<script src="webjars/select2/4.0.2/dist/js/select2.full.min.js"></script>

<!-- Font-Awsome-->
<link rel="stylesheet"
	href="webjars/font-awesome/4.6.2/css/font-awesome.min.css">

<!-- Date Picker  -->
<link
	href="webjars/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css"
	rel="stylesheet">
<script
	src="webjars/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>

<!-- Notifications -->
<script src="webjars/sweetalert/1.1.3/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="webjars/sweetalert/1.1.3/dist/sweetalert.css">
<script src="resources/js/responseManager.js" type="text/javascript"></script>
<!-- Other resources -->
<link href="resources/img/favicon.ico" rel="shortcut icon">
<!-- Load a JSP in a DIV -->
<script>
	$.unblockUI();
	$('#loading').hide();
	$(function() {
		$('.loadDiv').click(function() {
			$('#loading').show();
			$('#contentDiv').load($(this).attr('href'));
			return false;
		});
	});
</script>
</head>
<body>
	<div id='header'>
		<!-- Header -->
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<div id="contentDiv">
		<!-- Page Content -->
		<jsp:include page="home/Principal.jsp"></jsp:include>
	</div>
	<footer class="navbar navbar-static-bottom mtop-0">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xs-12">
					<div class="badge badge-danger uppercase mtop-20">Ticket
						Contro Web</div>
					<img src="resources/img/sanmina-100x56.png" alt="Sanmina"
						style="max-width: 100px; max-height: 100px;"
						class="pull-right mtop-5 mbottom-5">
				</div>
			</div>
		</div>
	</footer>
</body>
</html>
