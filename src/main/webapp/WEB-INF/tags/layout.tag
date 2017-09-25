<%@tag description="Simple layout Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>RealStateManagement</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="public/assets/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="public/assets/style.css" />
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script src="public/assets/bootstrap/js/bootstrap.js"></script>
<script src="public/assets/script.js"></script>



<!-- Owl stylesheet -->
<link rel="stylesheet" href="public/assets/owl-carousel/owl.carousel.css">
<link rel="stylesheet" href="public/assets/owl-carousel/owl.theme.css">
<script src="public/assets/owl-carousel/owl.carousel.js"></script>
<!-- Owl stylesheet -->


<!-- slitslider -->
<link rel="stylesheet" type="text/css" href="public/assets/slitslider/css/style.css" />
<link rel="stylesheet" type="text/css" href="public/assets/slitslider/css/custom.css" />
<script type="text/javascript" src="public/assets/slitslider/js/modernizr.custom.79639.js"></script>
<script type="text/javascript" src="public/assets/slitslider/js/jquery.ba-cond.min.js"></script>
<script type="text/javascript" src="public/assets/slitslider/js/jquery.slitslider.js"></script>
<!-- slitslider -->

<script type="text/javascript">
	function submitForm(itemValue) {
		console.log(itemValue);

		var form = document.createElement("form");
		form.method = "POST";
		form.action = "/property/list";

		var element1 = document.createElement("input");
		element1.name = "searchBy";
		element1.value = '';
		form.appendChild(element1);

		var element2 = document.createElement("input");
		element2.name = "searchPurposeTypeId";
		element2.value = itemValue;
		form.appendChild(element2);

		var element3 = document.createElement("input");
		element3.name = "searchPropertyTypeId";
		element3.value = 0;
		form.appendChild(element3);

		var element4 = document.createElement("input");
		element4.name = "order";
		element4.value = 0;
		form.appendChild(element4);

		var element5 = document.createElement("input");
		element5.name = "currentPage";
		element5.value = 1;
		form.appendChild(element5);

		document.body.appendChild(form);

		form.submit();
	}
</script>
</head>

<body>


	<!-- Header Starts -->
	<div class="navbar-wrapper">

		<div class="navbar-inverse" role="navigation">
			<div class="container">
				<div class="navbar-header">


					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>

				</div>


				<!-- Nav Starts -->
				<div class="navbar-collapse  collapse">
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="index">Home</a></li>
						<!--<li><a href="about.php">About</a></li>-->
						<li><a href="agents">Agents</a></li>
						<!--<li><a href="blog.php">Blog</a></li>-->
						<c:choose>
							<c:when test="${currentUser != null}">
								<li><a> <c:choose>
											<c:when test="${currentUser.role == 1}">
												BUYER
											</c:when>
											<c:when test="${currentUser.role ==2}">
												SELLER
											</c:when>
											<c:when test="${currentUser.role ==3}">
												AGENT
											</c:when>
											<c:otherwise>
												ADMIN
											</c:otherwise>
										</c:choose></a></li>
								<li><a><span>${currentUser.firstName}</span> <span>${currentUser.lastName}</span></a></li>
								<li>
									<form method="get" action="logout">
										<button id="logout">Log Out</button>
									</form>
								</li>
							</c:when>
							<c:otherwise>
								<li><a href="login">Log In</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<!-- #Nav Ends -->

			</div>
		</div>

	</div>
	<!-- #Header Starts -->

	<div class="container">
		<!-- Header Starts -->
		<div class="header">
			<a href="index"><img src="public/images/logo.png" alt="Realestate"></a>
			<ul class="pull-right">
				<c:if test="${not empty currentUser}">
					<c:choose>
						<c:when test="${currentUser.role == 4}">
							<li><a href="property/requests">Pending requests</a></li>
						</c:when>
						<c:when test="${currentUser.role == 2}">
							<li><a href="property/upload">Upload property</a></li>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
					<li><a href="property/bookAppt/requests">Appointments</a></li>
					<li><a href="order/list">Orders</a></li>
				</c:if>
				<li><a href="#" onclick="submitForm(2)">Sell</a></li>
				<li><a href="#" onclick="submitForm(1)">Rent</a></li>
			</ul>
		</div>
	</div>

	<!-- 	<div class="">$bodyContent</div> -->
	<jsp:doBody />

	<div class="footer">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-sm4">
					<h4>Information</h4>
					<ul class="row">
						<c:if test="${not empty currentUser}">
							<c:choose>
								<c:when test="${currentUser.role == 4}">
									<li class="col-lg-12 col-sm-12 col-xs-3"><a href="property/requests">Pending requests</a></li>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							<li class="col-lg-12 col-sm-12 col-xs-3"><a href="property/bookAppt/requests">Appointments</a></li>
							<li class="col-lg-12 col-sm-12 col-xs-3"><a href="order/list">Orders</a></li>
						</c:if>
						<li class="col-lg-12 col-sm-12 col-xs-3"><a href="agents">Agents</a></li>
					</ul>
				</div>

				<div class="col-lg-4 col-sm-4">
					<h4>Newsletter</h4>
					<p>Get notified about the latest properties in our marketplace.</p>
					<form class="form-inline" role="form">
						<input type="text" placeholder="Enter Your email address" class="form-control">
						<button class="btn btn-success" type="button">Notify Me!</button>
					</form>
				</div>

				<div class="col-lg-4 col-sm-4">
					<h4>Contact us</h4>
					<p>
						<b>Group #3.</b><br> <span class="glyphicon glyphicon-map-marker"></span> 1000 N 4th St, IA, USA, 52557 <br>
						<span class="glyphicon glyphicon-envelope"></span> bbadarch@mum.edu<br> <span
							class="glyphicon glyphicon-earphone"></span> (111) 999-9999
					</p>
				</div>
			</div>
			<p class="copyright">Copyright 2017. All rights reserved.</p>
		</div>
	</div>
	<!-- Modal -->
	<div id="loginpop" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="row">
					<div class="col-sm-6 login">
						<h4>Login</h4>
						<form class="" role="form" action="login" method="POST">
							<div class="form-group">
								<label class="sr-only" for="exampleInputEmail2">Email address</label> <input type="email" class="form-control"
									id="email" name="email" placeholder="Enter email" required="required">
							</div>
							<div class="form-group">
								<label class="sr-only" for="exampleInputPassword2">Password</label> <input type="password" class="form-control"
									id="password" type="password" name="password" placeholder="Password" required="required">
							</div>
							<div class="checkbox">
								<label> <input type="checkbox"> Remember me
								</label>
							</div>
							<button type="submit" class="btn btn-success">Sign in</button>
						</form>
					</div>
					<div class="col-sm-6">
						<h4>New User Sign Up</h4>
						<p>Join today and get updated with all the properties deal happening around.</p>
						<a class="btn btn-info" href="/register">Join Now</a>
						<!-- <button type="submit" class="btn btn-info"  onclick="window.location.href='register.php'">Join Now</button> -->
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>