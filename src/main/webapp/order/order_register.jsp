<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="en_US" />
<t:layout>
	<div class="inside-banner">
		<div class="container">
			<span class="pull-right"><a href="${ContextPath}">Home</a> / orders</span>
			<h2>List of orders</h2>
		</div>
	</div>
	<!-- banner -->

	<div class="container">
		<div class="spacer">
			<div class="row register">
				<div
					class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
					<c:if test="${msg}">
						<div>
							<span>$msg</span>
						</div>
					</c:if>

					<form method="POST" action="${ContextPath}/order/register">
						<input type="hidden" name="propertyId" value="${propertyId}">
						<input type="hidden" name="orderType" value="${orderType}">
						<label for="role">Your offer</label>

						<c:choose>
							<c:when test="${orderType == 'Sell'}">
								<div class="form-group row">
									<input type="text" class="form-control" placeholder="Amount"
										name="offerBuyAmount" required="required">
								</div>
							</c:when>
							<c:otherwise>

								<div class="form-group row">
									<input type="text" class="form-control"
										placeholder="Total months" name="offerRentMonths"
										required="required"> <input type="text"
										class="form-control" placeholder="Amount (Per month)"
										name="offerRentPerMonthAmount" required="required"> <input
										type="text" class="form-control" placeholder="Deposit"
										name="offerRentDespositAmount" required="required"> <label
										for="role">To extend : </label> <input type="radio"
										name="offerIsExtend" value="1"> <label for="Yes">
										Yes</label> <input type="radio" name="offerIsExtend" value="0"
										checked="checked"> <label for="No"> No</label>
								</div>
							</c:otherwise>
						</c:choose>

						<div class="form-group row">
							<textarea rows="3" class="form-control" placeholder="Description"
								name="offerDescription" required="required"></textarea>
						</div>
						<button type="submit" class="btn btn-success" name="Submit">Create</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</t:layout>