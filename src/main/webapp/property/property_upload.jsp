<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:layout>
	<div class="inside-banner">
		<div class="container">
			<span class="pull-right"><a href="${ContextPath}/index">Home</a> / Upload property</span>
			<h2>Upload property</h2>
		</div>
	</div>
	<!-- banner -->

	<div class="container">
		<div class="spacer">
			<div class="row">
				<div class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
					<form action="property_upload" method="POST" enctype="multipart/form-data">
						<div class="row">
							<div class="col-lg-6">
								<label for="name">Property title</label> <input type="text" class="form-control" placeholder="Property title"
									id="name" name="name" required="required">
							</div>
							<div class="col-lg-6">
								<label for="address">Address</label> <input type="text" class="form-control" placeholder="Address" id="address"
									name="address" required="required">
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<label for="purpose">Purpose</label> <select class="form-control" placeholder="Purpose" name="purpose"
									id="purpose" onchange="setupPurpose()">
									<option value="rent">Rent</option>
									<option value="sell">Sell</option>
								</select>
							</div>
							<div class="col-lg-6">
								<label for="type">Property type</label> <select class="form-control" placeholder="Property type" name="p_type"
									id="type" onchange="setupType()">
									<option value="apartment">Apartment</option>
									<option value="house">House</option>
								</select>
							</div>
						</div>

						<div class="row" id="fields">
							<div class="col-lg-4">
								<label for="bedroom">Bedroom</label> <input type="number" min="0" value="0" class="form-control"
									placeholder="Bedroom" name="bedroom" id="bedroom" required="required">
							</div>
							<div class="col-lg-4">
								<label for="livingroom">Living room</label> <input type="number" min="0" value="0" class="form-control"
									placeholder="Living room" name="livingroom" id="livingroom" required="required">
							</div>
							<div class="col-lg-4">
								<label for="parking">Parking</label> <input type="number" min="0" value="0" class="form-control"
									placeholder="Parking" name="parking" id="parking" required="required">
							</div>
							<div class="col-lg-4">
								<label for="kitchen">Kitchen</label> <input type="number" min="0" value="0" class="form-control"
									placeholder="Kitchen" name="kitchen" id="kitchen" required="required">
							</div>
							<div class="col-lg-4">
								<label for="bathroom">Bathroom</label> <input type="number" min="0" value="0" class="form-control"
									placeholder="Bathroom" name="bathroom" id="bathroom" required="required">
							</div>
							<div class="col-lg-4" id="yards">
								<label for="yard">Yard</label> <input type="number" min="0" value="0" class="form-control" placeholder="Yard"
									name="yard" id="yard" required="required">
							</div>
							<div class="col-lg-4" id="floors">
								<label for="floor">Floor</label> <input type="number" min="0" value="0" class="form-control" placeholder="Floor"
									name="floor" id="floor" required="required">
							</div>
						</div>

						<div class="row">
							<div class="col-lg-6">
								<label for="agents">Agents</label> <select class="form-control" placeholder="Agent" name="agentaccount"
									onchange="setupPurpose()" id="agents" required="required">
									<option value="0"></option>
									<c:forEach var="agent" items="${agents}">
										<option value="${agent.id}">${agent.firstName}${agent.lastName}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-lg-6">
								<label for="ucost">Utilities cost</label> <input type="number" min="0" class="form-control" value="0"
									placeholder="Utilities cost" name="utilitiescost" required="required" id="ucost">
							</div>
						</div>

						<div class="row" id="sellFields">
							<div class="col-lg-6">
								<label for="ucost">Sell price</label> <input type="number" min="0" class="form-control" value="0"
									placeholder="Sell price" name="sellPrice" required="required">
							</div>
						</div>

						<div class="row" id="rentFields">
							<div class="col-lg-6">
								<label for="ucost">Monthly price</label> <input type="number" min="0" class="form-control" value="0"
									placeholder="Monthly price" name="monthlyPrice" required="required">
							</div>
							<div class="col-lg-6">
								<label for="ucost">Total month</label> <input type="number" min="0" class="form-control" value="0"
									placeholder="Total month" name="totalMonth" required="required">
							</div>
							<div class="col-lg-6">
								<label for="ucost">Deposit</label> <input type="number" min="0" class="form-control" value="0"
									placeholder="Deposit" name="deposit" required="required">
							</div>
						</div>

						<div class="form-group row" id="addImg">
							<div class="col-lg-6">
								<input type="file" class="btn btn-link" placeholder="Photo of the property 1" name="upload_image[]"
									accept="image/*" required="required">
							</div>
							<div class="col-lg-6">
								<button type="button" id="addImgBtn" class="btn btn-success" name="Submit">Add image</button>
							</div>
						</div>

						<div class="form-group">
							<textarea rows="6" class="form-control" placeholder="Property detail" name="message" required="required"></textarea>
						</div>

						<button type="submit" class="btn btn-success" name="Submit">Upload property</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</t:layout>