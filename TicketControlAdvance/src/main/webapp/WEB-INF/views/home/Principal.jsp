<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<style>
.carousel .item>img {
	position: relative;
	top: 0;
	left: 0;
	max-width: 100%;
	height: 100%;
}

.carousel-inner>.item>img {
	position: relative;
	top: 0;
	left: 0;
	min-width: 100%;
	height: 500px;
}
</style>
<div class="jumbotron bg-transparent padding-0">
	<div class="container-fluid" style='margin-bottom: 10%'>
		<div id="icons-list"
			class="col-lg-5 col-lg-offset-1 col-md-5 col-xs-14 mtop-20">
			<h1 class="text-gray-darker visible-lg">Sanmina-SCI</h1>
			<h2 class="text-gray-darker visible-md">Sanmina-SCI</h2>
			<h3 class="text-gray-darker visible-sm text-center">Bienvenidos
				a AP tool</h3>
			<p class="text-gray-darker mtop-20">
				<i>Esta es una aplicación para el control y seguimiento de todos los componentes manejados en
				Sanmina, esto para llevar mejor un control al momento de enviar el producto terminado.
	                 
				</i>
			</p>
			<p class="visible-xs visible-sm text-center">
				<a href="#" class="btn btn-lg btn-primary mbottom-20">
					</a>
			</p>
<!-- 			<a href="#" -->
<!-- 				class="btn btn-lg btn-primary mbottom-20 hidden-xs hidden-sm"> -->
<!-- 				Seccion de ayuda</a> -->
		</div>
		<div class="col-lg-6 col-md-6 ">
			<div id="carousel-example-generic" class="carousel slide"
				data-ride="carousel" data-interval="5000">
				<ol class="carousel-indicators">
					<li data-target="#carousel-example-generic" data-slide-to="0"></li>
					<li data-target="#carousel-example-generic" data-slide-to="1"></li>
					<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					<li data-target="#carousel-example-generic" data-slide-to="3"></li>
				</ol>
				<br> <br>
				<div class="carousel-inner">
					<!-- SUBSTITUTE THESE ITEMS WITH YOUR FEATURED IMAGES -->
					<div class="item active bg-gray-light">
						<img src="resources/img/slide1.jpg">
					</div>
					<div class="item bg-gray">
						<img src="resources/img/slide2.jpg">
					</div>
					<div class="item bg-gray-dark">
						<img src="resources/img/slide3.jpg">
					</div>
					<div class="item bg-gray">
						<img src="resources/img/slide4.jpg">
					</div>
				</div>
			</div>
		</div>
	</div>
	<br>
	
</div>
<script>
	$.unblockUI();
	$('#loading').hide();

	$(function() {
		//Internal smooth scroll
		function smoothNav(btn) {
			btn.click(function(e) {
				e.preventDefault();
				$('html, body').stop().animate({
					'scrollTop' : $($(this).attr('href')).offset().top - 3
				}, 250, 'swing');
			})
		}
		smoothNav($('#btn-form'));
	});
</script>
