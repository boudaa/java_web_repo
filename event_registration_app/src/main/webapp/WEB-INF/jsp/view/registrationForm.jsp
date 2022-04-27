<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<title>Registration</title>
</head>
<body>
	<div>
		<h3>Registration Form</h3>
	</div>
	<div>
		<f:form action="addPerson" method="POST" modelAttribute="personModel">
			<p style="color: green">${msg}</p>
			<label>First Name</label><br>
				<f:input path="firstName" type="text" placeholder="First Name" /><br>
				<label>Last Name</label><br>
				<f:input path="lastName" type="text" placeholder="Last Name" /><br>
				<label>Email</label><br>
				<f:input path="email" placeholder="Email" /><br>
				<button type="submit">Register</button>
		</f:form>
	</div>
</body>
</html>