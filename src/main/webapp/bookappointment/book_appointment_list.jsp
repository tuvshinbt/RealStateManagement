<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:layout>
	<div class="inside-banner">
		<div class="container">
			<span class="pull-right"><a href="/">Home</a> / Requested properties</span>
			<h2>Requested properties</h2>
		</div>
	</div>
	<!-- banner -->
	<div class="container">
		<div class="spacer">
			<div class="row register">
				<div class="col-lg-12 col-sm-12 col-xs-12 ">

					<div class="table-responsive">
						<table class="table table-hover">
							<tr class="active">
								<th>Property Name</th>
								<th>Property Address</th>
								<th>From Who</th>
								<th>Appointment</th>
								<th>Date</th>
								<th></th>
								<th></th>
							</tr>
							<c:forEach var="appointment" items="${appointments}">
								<tr class="active">
									<td>${appointment.property.name}</td>
									<td>${appointment.property.address}</td>
									<td>${appointment.buyer.firstName}</td>
									<td>${appointment.appointmentComment}</td>
									<td>${appointment.appointmentDate}</td>
									<td><a href="${ContextPath}/property?id=${appointment.property.id }">View</a></td>
									<td><a href="approve?id=${appointment.id}">Approve</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>

				</div>
			</div>
		</div>
	</div>
</t:layout>