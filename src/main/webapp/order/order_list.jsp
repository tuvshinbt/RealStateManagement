<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="en_US" />
<t:layout>
	<div class="inside-banner">
		<div class="container">
			<span class="pull-right"><a href="/">Home</a> / orders</span>
			<h2>List of orders</h2>
		</div>
	</div>
	<!-- banner -->

	<div class="container">
		<div class="spacer">
			<div class="row register">
				<div class="col-lg-12 col-sm-12">

					<div class="table-responsive">
						<table class="table table-hover">
							<tr class="active">
								<th>ID</th>
								<th>Requestor</th>
								<th>Date</th>
								<th>Name</th>
								<th>Address</th>
								<th>Purpose</th>
								<th>Owner of property</th>
								<th>Status</th>
								<th></th>
								<th></th>
							</tr>

							<c:if test="${not empty orderList}">
								<c:forEach var="order" items="orderList">
									<tr class="active">
										<td>${order.getId()}</td>
										<td>${order.getAccount().getFirstName()}</td>
										<td>${order.getRegisterDate()}</td>
										<td>${order.getProperty().getName()}</td>
										<td>${order.getProperty().getAddress()}</td>
										<td>${order.getProperty().getPurposeKey()}</td>
										<td>${order.getSeller().getFirstName()} - ${order.getSeller().getLastName()}</td>
										<td>
											<c:choose>
												<c:when test="${order.getStatus() == 1}">
													PENDING
												</c:when>
												<c:when test="${order.getStatus() == 2}">
													<b>APPROVED</b>
												</c:when>
												<c:when test="${order.getStatus() == 3}">
													BOUGHT
												</c:when>
												<c:when test="${order.getStatus() == 4}">
													RENTED
												</c:when>
												<c:when test="${order.getStatus() == 4}">
													CANCELLED
												</c:when>
												<c:otherwise>
													DELETED
												</c:otherwise>
											</c:choose>
										</td>
										<td><a href="${ContextPath}/order?orderId=${order.getId()}">View</a></td>
										<td>
											<c:if test="${not empty currentUser}">
												<c:if test="${currentUser.Role == 2}">
													<c:if test="${order.getStatus() == 1}">
														<a href="${ContextPath}/order/approve?orderId=${order.getId()}">Approve</a>
													</c:if>
												</c:if>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:if>

						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
</t:layout>