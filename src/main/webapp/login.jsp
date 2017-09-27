<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<t:layout>

	<div class="inside-banner">
		<div class="container">
			<span class="pull-right"><a href="${ContextPath}/index">Home</a> / <a href="${ContextPath}/register">Register</a></span>
			<h2>Register</h2>
		</div>
	</div>
	<!-- banner -->
	<div class="container">
		<div class="spacer">
			<div class="row register">
				<div class="col-lg-6 col-lg-offset-3 col-sm-6 col-sm-offset-3 col-xs-12 ">
					<c:if test="${not empty msg}">
						<div class="alert alert-danger alert-dismissable">
							<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>${msg}
						</div>
					</c:if>
					<form method="POST" action="${ContextPath}/login">
						<div class="form-group">
							<input type="email" class="form-control" placeholder="Enter Email" name="email" required="required">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="Password" name="password" required="required">
						</div>
						<button type="submit" class="btn btn-success" name="Submit">Login</button>
						<div class="form-group">
						</div>
						<div class="form-group">
							<a href="${ContextPath}/register" class="btn btn-warning btn-block"  class="form-control" role="button">Register</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</t:layout>