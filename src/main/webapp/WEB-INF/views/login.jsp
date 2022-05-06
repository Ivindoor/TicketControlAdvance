<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sanmina secure access</title>
<!-- Fonts -->
<link rel="shortcut icon"
	href="${contextPath}/resources/img/favicon.ico">
<link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${contextPath}/resources/js/jquery.min.js"
	type="text/javascript"></script>

<!-- Bootstrap JS -->
<script src="${contextPath}/resources/js/bootstrap.min.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="${contextPath}/resources/js/responseManager.js"></script>

<!-- Fonts -->
<link
	href='http://fonts.googleapis.com/css?family=Maven+Pro:400,500,700,900'
	rel='stylesheet' type='text/css'>
<!-- Bootstrap -->
<link href="${contextPath}/resources/css/bootstrap.css" rel="stylesheet">

<link href="https://fonts.googleapis.com/css?family=Roboto"
	rel="stylesheet" type="text/css">
</head>

<body class='body'>
	<div class='container'>
		<div class='col-sm-8 col-sm-offset-2  mtop-10'>
			<div class="row">
				<div class="col-xs-6">
					<h3 class="mtop-40 mbottom-5">Welcome</h3>
				</div>
				<div class="col-xs-6">
					<img src="${contextPath}/resources/img/sanmina-100x56.png"
						class="pull-right">
				</div>
			</div>
			<div class="row panel panel-default">
				<div class="col-xs-12 bg-gray-darker" id="main">
					<img src="${contextPath}/resources/img/secure-access-header.png"
						class="mtop-20 img-responsive center-block">
				</div>
				<form id="loginForm" role="form col-xs-12">
					<div class="clearfix">
						<div class="col-lg-6 col-md-5 mtop-15 clearfix" id="feedback">
							<!-- Remove ".hide" class to show the alert, show this alert for errors-->
							<div class="alert alert-danger" id="failLogin">
								<b>Incorrect user or password</b>
							</div>
							<!-- Remove ".hide" class to show the alert, show this alert whenever the-->
							<div class="alert alert-success alert-dismissable hide">
								<button type="button" class="close" data-dismiss="alert"
									aria-hidden="true"></button>
								<b>You was successfully logged out</b>
							</div>
							<!-- Add the ".has-error" in case the value entered was incorrect, add the ".mandatory" class to required fields-->
							<div class="form-group">
								<label for="username">Username</label> <input type="text"
									class="form-control" name="userName" id="userName"
									placeholder="name_lastname or employeenumber" id="username"
									required>
							</div>
							<div class="form-group mtop-10">
								<label for="userpassword">Password</label> <input
									type="password" name="userPassword" id="userPassword"
									class="form-control" id="userpassword"
									placeholder="Password/2FA Code" required>
							</div>
							<div class="form-group mtop-10">
								<button type="submit" id="btn-submit"
									class="btn btn-danger btn-lg btn-loading">
									<span class="content">Sign in<span></span></span>
								</button>
							</div>
						</div>

						<div
							class="col-lg-6 hidden-xs hidden-sm col-md-7 mtop-10 mbottom-10">
							<div class="clearfix well well-info legal-notice">
								<div class="col-xs-12">
									<h5 class="text-center ng-binding"
										ng-bind-html="'userIDView.legalNoticeTitle' | i18n">SANMINA
										CORPORATION ELECTRONIC WORKSPACE LEGAL NOTICE</h5>
									<p class="text-smaller ng-binding"
										ng-bind-html="'userIDView.legalNotice' | i18n">
										This is a Sanmina Corporation electronic workspace. This
										system is provided for the business use of Sanmina and its
										Partners, including customers and suppliers. All information,
										including personal information, placed on or sent over this
										system may be monitored, examined, recorded, and/or copied,
										subject to the terms of any Non-Disclosure Agreement that may
										exist between Sanmina and its Partners. This system is also
										governed by all applicable federal, state and local laws,
										including U.S. export laws. Accordingly, you may not place or
										send any information over this system that violates any of
										those laws or infringes on the rights of any third party. For
										example, information known to fall under the requirements of
										the International Traffic in Arms Regulations (ITAR) or Export
										Administration Regulations (EAR) should not be placed on or
										sent over this system. Unauthorized use or access to this
										system is strictly prohibited and may be subject to legal
										action. Complete terms and conditions of use are available <a
											href="http://www.Sanmina.com/company-profile/legal-information/index.php"
											target="_blank">here</a>. By use of this system, the user
										consents to the terms of this Notice.
									</p>
								</div>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="${contextPath}/resources/js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${contextPath}/resources/js/responseManager.js"></script>

	<script>
		$(document).ready(function() {
			$("#failLogin").hide();
			startApp();
		});

		$("#loginForm").on(
				'submit',
				function(e) {
					if ($("#userName").val().length != 0
							&& $("#userPassword").val().length != 0) {
						e.preventDefault();
						$.ajax({
							type : "GET",
							url : 'loginUser/userName=' + $("#userName").val()
									+ '/userPassword='
									+ $("#userPassword").val() + '',
							async : true,
							contentType : "application/json",
							success : function(data) {
								console.log(data)
								if (data == "index") {
									$("#failLogin").hide();
									$(location).attr('href', data);
								} else {
									$("#failLogin").show();
								}

							}
						});

					} else {
						getNotification("error",
								"Los campos no pueden quedar vacios");
					}

				});
	</script>
</body>
</html>