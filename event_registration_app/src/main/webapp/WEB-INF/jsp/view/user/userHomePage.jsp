<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<link rel="stylesheet" 	href="${pageContext.request.contextPath}/resources/css/style.css">
<title>User</title>
</head>
<body>
<ul>
	<li><a href="${pageContext.request.contextPath}/user/showUserHome">Home</a></li>
	<li><a
		href="${pageContext.request.contextPath}/common/managePersons">Manage Persons</a></li>			
	<li><f:form action="${pageContext.request.contextPath}/logout" method="POST">			
			<button type="submit" class="btn btn-link">logout</button>
		</f:form></li>

</ul>

<div>
	<h3>User home page</h3>
	<p>Hello and welcome to your application</p>

	<s:authorize access="isAuthenticated()">
    			
    			You are connected with:  <s:authentication property="principal.username" /><br>		
				Your Email : <s:authentication property="principal.email" /><br>		
				Your First Name : <s:authentication property="principal.firstName" /><br>		
				Your Last name : <s:authentication property="principal.LastName" /><br>
	
	</s:authorize>
</div>
</body>
</html>

