<%@page import="java.util.Base64"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sanmina secure access</title>
<!-- Fonts -->
<link
	href='http://fonts.googleapis.com/css?family=Maven+Pro:400,500,700,900'
	rel='stylesheet' type='text/css'>
<!-- Bootstrap -->
<link href="resources/css/bootstrap.css" rel="stylesheet">
</head>

<body class='body'>
	<div class='container'>
		<div class='col-sm-8 col-sm-offset-2  mtop-10'>
			<div class="row">
				<div class="col-xs-6">
					<h3 class="mtop-40 mbottom-5">Welcome</h3>
				</div>
				<div class="col-xs-6">
					<img src="resources/img/sanmina-100x56.png" class="pull-right">
				</div>
			</div>
			<div class="row panel panel-default">
				<div class="col-xs-12 bg-gray-darker" id="main">
					<img src="resources/img/secure-access-header.png"
						class="mtop-20 img-responsive center-block">
				</div>
				<c:url var="loginUrl" value="/login" />
				<form:form id="loginForm" modelAttribute="userCredential">
					<div class="clearfix">
						<div class="col-lg-6 col-md-5 mtop-15 clearfix" id="feedback">
							<c:if test="${param.error != null}">
								<div class="alert alert-danger">
									<p>Credenciales incorrectas.</p>
								</div>
							</c:if>
							<form:label path="universalLogin">User:</form:label>
							<form:input path="universalLogin" class="form-control"
								placeholder="user Login" id="usName" />
							<form:label path="password">Password:</form:label>
							<form:password path="password" class="form-control"
								placeholder="Password/2FA Code" id="usPassword" />
							<input type="submit" value="Sign in"
								class="btn btn-danger btn-lg btn-loading col-sm-6 col-sm-offset-6 col-xs-12 mtop-5"
								id="btnSubmit">
						</div>
						<div class="col-lg-6 col-md-7 mtop-10 mbottom-10">
							<div class="clearfix well well-info">
								<div class="col-xs-12">
									<h5 class="text-center">
										SANIMNA CORPORATION ELECTRONIC<br /> WORKSPACE LEGAL NOTICE</h5>
										<p class="text-smaller">
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
											the International Traffic in Arms Regulations (ITAR) or
											Export Administration Regulations (EAR) should not be placed
											on or sent over this system. Unauthorized use or access to
											this system is strictly prohibited and may be subject to
											legal action. Complete terms and conditions of use are
											available <a
												href="http://www.Sanmina.com/company-profile/legal-information/index.php"
												target="_blank">here</a>. By use of this system, the user
											consents to the terms of this Notice.											
										</p>
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="resources/js/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="resources/js/bootstrap.min.js"></script>

</body>
</html>