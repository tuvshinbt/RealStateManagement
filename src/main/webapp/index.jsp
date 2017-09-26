<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:layout>
	<div class="">
		<div id="slider" class="sl-slider-wrapper">
			<div class="sl-slider">

				<div class="sl-slide" data-orientation="horizontal" data-slice1-rotation="-25" data-slice2-rotation="-25"
					data-slice1-scale="2" data-slice2-scale="2">
					<div class="sl-slide-inner">
						<div class="bg-img bg-img-1"></div>
						<h2>
							<a href="#">2 Bed Rooms and 1 Dinning Room Aparment on Sale</a>
						</h2>
						<blockquote>
							<p class="location">
								<span class="glyphicon glyphicon-map-marker"></span> 1890 Syndey, Australia
							</p>
							<p>Until he extends the circle of his compassion to all living things, man will not himself find peace.</p>
							<cite>$ 20,000,000</cite>
						</blockquote>
					</div>
				</div>

				<div class="sl-slide" data-orientation="vertical" data-slice1-rotation="10" data-slice2-rotation="-15"
					data-slice1-scale="1.5" data-slice2-scale="1.5">
					<div class="sl-slide-inner">
						<div class="bg-img bg-img-2"></div>
						<h2>
							<a href="#">2 Bed Rooms and 1 Dinning Room Aparment on Sale</a>
						</h2>
						<blockquote>
							<p class="location">
								<span class="glyphicon glyphicon-map-marker"></span> 1890 Syndey, Australia
							</p>
							<p>Until he extends the circle of his compassion to all living things, man will not himself find peace.</p>
							<cite>$ 20,000,000</cite>
						</blockquote>
					</div>
				</div>

				<div class="sl-slide" data-orientation="horizontal" data-slice1-rotation="3" data-slice2-rotation="3"
					data-slice1-scale="2" data-slice2-scale="1">
					<div class="sl-slide-inner">
						<div class="bg-img bg-img-3"></div>
						<h2>
							<a href="#">2 Bed Rooms and 1 Dinning Room Aparment on Sale</a>
						</h2>
						<blockquote>
							<p class="location">
								<span class="glyphicon glyphicon-map-marker"></span> 1890 Syndey, Australia
							</p>
							<p>Until he extends the circle of his compassion to all living things, man will not himself find peace.</p>
							<cite>$ 20,000,000</cite>
						</blockquote>
					</div>
				</div>

				<div class="sl-slide" data-orientation="vertical" data-slice1-rotation="-5" data-slice2-rotation="25"
					data-slice1-scale="2" data-slice2-scale="1">
					<div class="sl-slide-inner">
						<div class="bg-img bg-img-4"></div>
						<h2>
							<a href="#">2 Bed Rooms and 1 Dinning Room Aparment on Sale</a>
						</h2>
						<blockquote>
							<p class="location">
								<span class="glyphicon glyphicon-map-marker"></span> 1890 Syndey, Australia
							</p>
							<p>Until he extends the circle of his compassion to all living things, man will not himself find peace.</p>
							<cite>$ 20,000,000</cite>
						</blockquote>
					</div>
				</div>

				<div class="sl-slide" data-orientation="horizontal" data-slice1-rotation="-5" data-slice2-rotation="10"
					data-slice1-scale="2" data-slice2-scale="1">
					<div class="sl-slide-inner">
						<div class="bg-img bg-img-5"></div>
						<h2>
							<a href="#">2 Bed Rooms and 1 Dinning Room Aparment on Sale</a>
						</h2>
						<blockquote>
							<p class="location">
								<span class="glyphicon glyphicon-map-marker"></span> 1890 Syndey, Australia
							</p>
							<p>Until he extends the circle of his compassion to all living things, man will not himself find peace.</p>
							<cite>$ 20,000,000</cite>
						</blockquote>
					</div>
				</div>
			</div>
			<!-- /sl-slider -->
			<nav id="nav-dots" class="nav-dots">
				<span class="nav-dot-current"></span>
			</nav>
		</div>
		<!-- /slider-wrapper -->
	</div>



	<div class="banner-search">
		<div class="container">
			<!-- banner -->
			<h3>Sell &amp; Rent</h3>
			<div class="searchbar">
				<div class="row">
					<div class="col-lg-6 col-sm-6">
						<form class="" role="form" action="property/list" method="POST" id="propertyListModelId" name="propertyListModel">
							<input type="text" class="form-control" name="searchBy" placeholder="Search of Properties">
							<div class="row">
								<div class="col-lg-4 col-sm-5 ">
									<select name="searchPurposeTypeId" class="form-control">
										<option value="0">-- Purpose type --</option>
										<option value="1">Rent</option>
										<option value="2">Sell</option>
									</select>
								</div>
								<div class="col-lg-4 col-sm-5">
									<select name="searchPropertyTypeId" class="form-control">
										<option value="0">-- Property type --</option>
										<option value="1">House</option>
										<option value="2">Apartment</option>
									</select>
								</div>
								<div class="col-lg-4 col-sm-5">
									<button type="submit" class="btn btn-primary">Find Now</button>
								</div>
							</div>
							<input type="hidden" name="order" value="0"> <input type="hidden" name="currentPage" value="1">
						</form>
					</div>
					<c:if test="${empty currentUser}">
						<div class="col-lg-5 col-lg-offset-1 col-sm-6 ">
							<p>Join now and get updated with all the properties deals.</p>
							<button class="btn btn-info" data-toggle="modal" data-target="#loginpop">Login</button>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<!-- banner -->
	<div class="container">
		<div class="properties-listing spacer">
			<a href="property/list" class="pull-right viewall">View All Listing</a>
			<h2>Featured Properties</h2>
			<div id="owl-example" class="owl-carousel">

				<c:forEach var="property" items="${hotProperties}">
					<!-- properties -->
					<div class="properties">
						<div class="image-holder">
							<img src="public/uploads/${property.mainPicturePath}" class="img-responsive" alt="properties">
							<c:choose>
								<c:when test="${property.purposeKey == 'Sell'}">
									<div class="status sold">
										<b>${property.getClass().simpleName}</b> - <i>${property.purposeKey}</i>
									</div>
								</c:when>
								<c:otherwise>
									<div class="status new">
										<b>${property.getClass().simpleName}</b> - <i>${property.purposeKey}</i>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<h4>
							<a href="property?id=${property.id}">${property.name}</a>
						</h4>
						<p class="price">
							Price:
							<fmt:setLocale value="en_US" />
							<fmt:formatNumber value="${property.purposeType.calculatePrice()}" type="currency" />
						</p>
						<div class="listing-detail">
							<span data-toggle="tooltip" data-placement="bottom" data-original-title="Bed Room">${property.bedroom}</span> <span
								data-toggle="tooltip" data-placement="bottom" data-original-title="Living Room">${property.livingroom}</span> <span
								data-toggle="tooltip" data-placement="bottom" data-original-title="Parking">${property.parking}</span> <span
								data-toggle="tooltip" data-placement="bottom" data-original-title="Kitchen">${property.kitchen}</span>
						</div>
						<a class="btn btn-primary" href="property?id=${property.id}">View Details</a>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="spacer">
			<div class="row">
				<div class="col-lg-6 col-sm-9 recent-view">
					<h3>About Us</h3>
					<p>
						The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32
						and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form,
						accompanied by English versions from the 1914 translation by H. Rackham.<br> <a href="about.php">Learn
							More</a>
					</p>

				</div>
				<div class="col-lg-5 col-lg-offset-1 col-sm-3 recommended">
					<h3>Recommended Properties</h3>
					<div id="myCarousel" class="carousel slide">
						<ol class="carousel-indicators">
							<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
							<li data-target="#myCarousel" data-slide-to="1" class=""></li>
							<li data-target="#myCarousel" data-slide-to="2" class=""></li>
							<li data-target="#myCarousel" data-slide-to="3" class=""></li>
						</ol>
						<!-- Carousel items -->
						<div class="carousel-inner">
							<div class="item active">
								<div class="row">
									<div class="col-lg-4">
										<img src="public/images/properties/1.jpg" class="img-responsive" alt="properties" />
									</div>
									<div class="col-lg-8">
										<h5>
											<a href="property/1">Integer sed porta quam</a>
										</h5>
										<p class="price">$300,000</p>
										<a href="property/1" class="more">More Detail</a>
									</div>
								</div>
							</div>
							<!-- 							<div class="item"> -->
							<!-- 								<div class="row"> -->
							<!-- 									<div class="col-lg-4"> -->
							<!-- 										<img src="public/images/properties/2.jpg" class="img-responsive" alt="properties" /> -->
							<!-- 									</div> -->
							<!-- 									<div class="col-lg-8"> -->
							<!-- 										<h5> -->
							<!-- 											<a href="/property/1">Integer sed porta quam</a> -->
							<!-- 										</h5> -->
							<!-- 										<p class="price">$300,000</p> -->
							<!-- 										<a href="/property/1" class="more">More Detail</a> -->
							<!-- 									</div> -->
							<!-- 								</div> -->
							<!-- 							</div> -->
							<!-- 							<div class="item"> -->
							<!-- 								<div class="row"> -->
							<!-- 									<div class="col-lg-4"> -->
							<!-- 										<img src="public/images/properties/3.jpg" class="img-responsive" alt="properties" /> -->
							<!-- 									</div> -->
							<!-- 									<div class="col-lg-8"> -->
							<!-- 										<h5> -->
							<!-- 											<a href="/property/1">Integer sed porta quam</a> -->
							<!-- 										</h5> -->
							<!-- 										<p class="price">$300,000</p> -->
							<!-- 										<a href="/property/1" class="more">More Detail</a> -->
							<!-- 									</div> -->
							<!-- 								</div> -->
							<!-- 							</div> -->
							<!-- 							<div class="item"> -->
							<!-- 								<div class="row"> -->
							<!-- 									<div class="col-lg-4"> -->
							<!-- 										<img src="public/images/properties/4.jpg" class="img-responsive" alt="properties" /> -->
							<!-- 									</div> -->
							<!-- 									<div class="col-lg-8"> -->
							<!-- 										<h5> -->
							<!-- 											<a href="/property/1">Integer sed porta quam</a> -->
							<!-- 										</h5> -->
							<!-- 										<p class="price">$300,000</p> -->
							<!-- 										<a href="/property/1" class="more">More Detail</a> -->
							<!-- 									</div> -->
							<!-- 								</div> -->
							<!-- 							</div> -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</t:layout>