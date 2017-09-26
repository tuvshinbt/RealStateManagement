<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="en_US" />
<t:layout>
	<div class="inside-banner">
		<div class="container">
			<span class="pull-right"><a href="/property/list">View All Listing</a> / Back</span>
			<h2>Buy</h2>
		</div>
	</div>

	<div class="container">
		<div class="properties-listing spacer">
			<div class="row">

				<div class="col-lg-3 col-sm-4 hidden-xs">
					<!-- start of MOST VIEWED PROPERTY -->
					<div class="hot-properties hidden-xs">
						<h4>Hot Properties</h4>

						<c:forEach var="hotProperty" items="${property.getHotPropertyList()}" varStatus="loop">
							<div class="row">
								<div class="col-lg-4 col-sm-5">
									<img src="${ContextPath}/public/uploads/${hotProperty.getMainPicturePath()}" class="img-responsive img-circle"
										alt="properties">
								</div>
								<div class="col-lg-8 col-sm-7">
									<h5>
										<a href="${ContextPath}/property/item?id=${hotProperty.getId()}"> <c:out value="${hotProperty.getName()}" />
										</a>
									</h5>
									<p class="price">
										<fmt:formatNumber value="${property.getPurposeType().calculatePrice()}" type="currency" />
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
					<!-- end of MOST VIEWED PROPERTY -->

					<div class="advertisement">
						<h4>Advertisements</h4>
						<img src="${ContextPath}/public/images/advertisements.jpg" class="img-responsive" alt="advertisement">
					</div>
				</div>
				<!-- col-lg-3 col-sm-4 hidden-xs -->

				<div class="col-lg-9 col-sm-8 ">
					<c:choose>
						<c:when test="${not empty req.propertyModel}">
							<h1 style="color: black;">Not found item!</h1>
						</c:when>
						<c:otherwise>
							<c:set var="property" value="${propertyModel.getProperty()}" />
							<h2>
								<c:out value="${property.getName()}" />
								<span class="title-status"> - <c:out value="${property.getStatus().getName()}" /></span>
							</h2>

							<div class="row">
								<div class="col-lg-8">
									<div class="property-/images">
										<!-- Slider Starts -->
										<div id="myCarousel" class="carousel slide" data-ride="carousel">
											<!-- Indicators -->
											<ol class="carousel-indicators hidden-xs">
												<c:forEach var="pageNum" begin="1" end="${property.getPicturePathList().size()}">
													<c:choose>
														<c:when test="${pageNum == 1}">
															<li data-target="#myCarousel" data-slide-to="${pageNum}" class="active"></li>
														</c:when>
														<c:otherwise>
															<li data-target="#myCarousel" data-slide-to="${pageNum}" class=""></li>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</ol>

											<div class="carousel-inner">
												<!-- Item -->
												<c:set var="firstPic" value="true" />
												<c:forEach var="picturePath" items="${property.getPicturePathList()}">
													<c:choose>
														<c:when test="${firstPic}">
															<div class="item active">
																<img src="${ContextPath}/public/uploads/${picturePath}" class="properties" alt="properties" />
															</div>
														</c:when>
														<c:otherwise>
															<div class="item">
																<img src="${ContextPath}/public/uploads/${picturePath}" class="properties" alt="properties" />
															</div>
														</c:otherwise>
													</c:choose>
												</c:forEach>
												<!-- #Item -->
											</div>

											<a class="left carousel-control" href="#myCarousel" data-slide="prev"><span
												class="glyphicon glyphicon-chevron-left"></span></a> <a class="right carousel-control" href="#myCarousel"
												data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
										</div>
										<!-- #Slider Ends -->
									</div>
									<!-- property-/images -->

									<div class="spacer">
										<h4>
											<span class="glyphicon glyphicon-th-list"></span> Description
										</h4>
										<p>${property.getDescription()}</p>
									</div><!-- spacer -->
									
									<!-- start of FEEDBACK -->
									<div>
									    <h4><span class="glyphicon glyphicon-comment"></span> Feedback</h4> 
										    <c:forEach var="propertyFeedback" items="${propertyModel.getPropertyFeedbackList()}">
												<div class="panel panel-default feedback">
										        <div class="panel-heading feedback_header">(<b>
										            <c:choose>
														<c:when test="${propertyFeedback.getAccount().getRole() == 1}">
															Buyer
														</c:when>
														<c:when test="${propertyFeedback.getAccount().getRole() == 2}">
															Seller
														</c:when>
														<c:when test="${propertyFeedback.getAccount().getRole() == 3}">
															Agent
														</c:when>
														<c:when test="${propertyFeedback.getAccount().getRole() == 4}">
															ADMIN
														</c:when>
													</c:choose></b>) - 
													${propertyFeedback.getAccount().getFirstName()} 
										            ${propertyFeedback.getAccount().getLastName()}
										        </div>
										        <div class="panel-body feedback_content">
										        	${propertyFeedback.getComment()}
										            <div>Posted on : 
										            	<fmt:formatDate type="date" pattern="MMM d, yyyy - h:m a"
														value="${propertyFeedback.getProperty().getRegisterDate()}" /></div>
										        </div>
										    </div>
										</c:forEach>

									    <form class="" role="form" action="${ContextPath}/property" method="POST" id="feedbackId" name="feedback">
									        <div class="feedback">
									            <input type="hidden" name="feedbackPropertyId" value="${property.getId()}">
									            <textarea rows="2" class="form-control" name="comment" placeholder="Message"></textarea>
									            <button type="submit" class="btn btn-success" name="Submit">Send Message</button>
									        </div>
									    </form>
									</div>
									<!-- end of FEEDBACK -->
									

								</div>
								<!-- col-lg-8 -->

								<div class="col-lg-4">
									<div class="col-lg-12 col-sm-6">
										<div class="property-info">
											<p class="price">
												<b>${property.getClass().getSimpleName()}</b> - <i>${property.getPurposeKey()}</i>
											</p>
											<c:choose>
												<c:when test="${property.getPurposeKey() == 'Sell'}">
													<p class="price">
														<span class="glyphicon glyphicon-home"></span>
														<fmt:formatNumber value="${property.getPurposeType().calculatePrice()}" type="currency" />
													</p>
												</c:when>
												<c:otherwise>
													<p class="price">
														<span class="glyphicon glyphicon-home"></span>
														<fmt:formatNumber value="${property.getPurposeType().calculatePrice()}" type="currency" />
													</p>
													<p class="area">
														<span class="glyphicon glyphicon-time"></span>
														<fmt:formatNumber value="${property.getPurposeType().rentMonth}" type="currency" />
														month
													</p>
													<p class="area">
														<span class="glyphicon glyphicon-lock"></span>deposit -
														<fmt:formatNumber value="${property.getPurposeType().deposit}" type="currency" />
													</p>
												</c:otherwise>
											</c:choose>

											<p class="area">
												<span class="glyphicon glyphicon-usd"></span>Utilities -
												<fmt:formatNumber value="${property.getUtilitiesCost()}" type="currency" />
											</p>
											<p class="area">
												<span class="glyphicon glyphicon-map-marker"></span>${property.getAddress()}
											</p>

											<c:if test="${property.getApprovedDate() != null}">
												<p class="area">
													<span class="glyphicon glyphicon-calendar"></span>Posted on:
													<fmt:formatDate type="date" pattern="MMM d, yyyy - h:m a" value="${property.getApprovedDate()}" />
												</p>
											</c:if>

											<div class="profile">
												<c:choose>
													<c:when test="${property.getAgentAccount() != null}">
														<span class="glyphicon glyphicon-user"></span>Owner Details
														<p>${property.getOwner().getFirstName()}${property.getOwner().getLastName()}</p>
														<span class="glyphicon glyphicon-briefcase"></span>Agent
														Details
														<p>
															${property.getAgentAccount().getFirstName()} ${property.getAgentAccount().getLastName()} <br>${property.getAgentAccount().getMobile()}
														</p>
													</c:when>
													<c:otherwise>
														<span class="glyphicon glyphicon-user"></span>Owner Details
														<p>
															${property.getOwner().getFirstName()} ${property.getOwner().getLastName()} <br>${property.getOwner().getMobile()}
														</p>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
										<!-- property-info -->

										<h6>
											<span class="glyphicon glyphicon-home"></span> ${property.getClass().getSimpleName()}
										</h6>
										<div class="listing-detail">
											<span data-toggle="tooltip" data-placement="bottom" data-original-title="Bed Room">${property.getBedroom()}</span>
											<span data-toggle="tooltip" data-placement="bottom" data-original-title="Living Room">${property.getLivingroom()}</span>
											<span data-toggle="tooltip" data-placement="bottom" data-original-title="Parking">${property.getParking()}</span>
											<span data-toggle="tooltip" data-placement="bottom" data-original-title="Kitchen">${property.getKitchen()}</span>
										</div>
										<!-- listing-detail -->
										<div class="listing-detail">
											<c:choose>
												<c:when test="${property.getClass().getSimpleName() == 'House'}">
													<span id="listing-detail-add" data-toggle="tooltip" data-placement="bottom" data-original-title="Yard">${property.getYard()}</span>
												</c:when>
												<c:otherwise>
													<span id="listing-detail-add" data-toggle="tooltip" data-placement="bottom" data-original-title="Floor">${property.getFloor
														()}</span>
												</c:otherwise>
											</c:choose>


										</div>
										<!-- listing-detail -->

									</div>
									<!-- col-lg-12 col-sm-6 -->

									<div class="col-lg-12 col-sm-6 ">
										<div class="enquiry">
											<h6>
												<span class="glyphicon glyphicon-envelope"></span> Send request
											</h6>
											<form role="form" action="bookappointment" method="POST">
												<input type="datetime-local" id="datepicker" class="form-control" placeholder="Appointment Date Time"
													name="appointment_date" required="required" /> <input type="hidden" name="property_id"
													value="${property.getId()}" />
												<textarea rows="6" class="form-control" placeholder="What do you want to tell about appointment?"
													name="appointment_info" required="required"></textarea>
												<button type="submit" class="btn btn-primary" name="Submit">Request appointment</button>
											</form>
											<br /> <a
												href="${ContextPath}/order/register?propertyId=${property.getId()}&orderType=${property.getPurposeKey()}"
												class="btn btn-primary">Create order</a>
										</div>
										<!-- enquiry -->
									</div>
									<!-- col-lg-12 col-sm-6 -->

								</div>
								<!-- col-lg-4 -->

							</div>
							<!-- row -->

						</c:otherwise>
					</c:choose>
				</div>
				<!-- col-lg-9 col-sm-8 -->

			</div>
			<!-- row -->
		</div>
		<!-- properties-listing spacer -->
	</div>
	<!-- container -->
</t:layout>