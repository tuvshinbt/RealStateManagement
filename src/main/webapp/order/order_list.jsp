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
										<td>#if($order.status == 1) PENDING #elseif($order.status
											== 2) <b>APPROVED</b> #elseif($order.status == 3) BOUGHT
											#elseif($order.status == 4) RENTED #elseif($order.status ==
											5) CANCELLED #else DELETED #end
										</td>
										<td><a href="/order/$order.id">View</a></td>
										<td>#if($currentUser) #if($currentUser.Role == 2)
											#if($order.status == 1) <a href="/order/approve/$order.id">Approve</a>
											#end #end #end
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