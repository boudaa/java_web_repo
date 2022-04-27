<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Event Application</title>
</head>
<body>
	<div>
		<h3>Event management application</h3>
		<ul>
			<li><a href="${pageContext.request.contextPath}/showForm">
					sign up</a></li>
			<li><a href="${pageContext.request.contextPath}/showMyLoginPage">
					log in</a></li>
		</ul>
	</div>
</body>
</html>