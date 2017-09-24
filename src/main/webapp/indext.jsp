<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	${testVal}
	<br />
	<%
		out.println(request.getAttribute("testVal"));
	%>
	<br />
	<c:out value="${requestScope[\"testVal\"]}" />
	<br />
	<c:out value="hi ${testVal}"></c:out>
	<c:if test="${now lt old}">
					${now}
				</c:if>
	${old}
	<c:forEach var="daily" items="${hotProperties}">
		<div>${daily.purposeKey}</div>
	</c:forEach>

	<!-- <h1>Hello JSP</h1> -->
	<!-- <p> -->
	<!-- 	Current date &amp; time on server: -->
	<%-- 	<c:set var="old" value="<%=new Date(0)%>" scope="page" /> --%>
	<%-- 	<c:set var="now" value="<%=new Date()%>" scope="page" /> --%>
	<%-- 	<c:if test="${now gt old}"> --%>
	<%--         ${now} --%>
	<%--       </c:if> --%>
	<!-- </p> -->

</body>
</html>