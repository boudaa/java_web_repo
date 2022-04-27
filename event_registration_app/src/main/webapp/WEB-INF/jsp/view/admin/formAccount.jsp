<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<title>Admin</title>
</head>
<body>
	<ul>
		<li><a
			href="${pageContext.request.contextPath}/admin/showAdminHome">Home</a></li>

		<li><a
			href="${pageContext.request.contextPath}/admin/createAccounts">Create
				Accounts</a></li>
		<li><a
			href="${pageContext.request.contextPath}/admin/manageAccounts">List
				Accounts</a></li>
		<li><a
			href="${pageContext.request.contextPath}/common/managePersons">Manage
				Persons</a></li>

		<li><f:form action="${pageContext.request.contextPath}/logout"
				method="POST">
				<button type="submit">logout</button>
			</f:form></li>
	</ul>


	<div>
		<h3>Account Creation Form</h3>
	</div>
	<div>

		<f:form action="${pageContext.request.contextPath}/admin/addAccount"
			method="POST" modelAttribute="accountModel">

			<f:hidden path="personId" />


			<label>Please select the user role</label>

			<f:select path="roleId" multiple="false" size="1">
				<f:options items="${roleList}" itemValue="id" itemLabel="roleName" />
			</f:select>

			<button type="submit">Create</button>

		</f:form>
	</div>

</body>
</html>