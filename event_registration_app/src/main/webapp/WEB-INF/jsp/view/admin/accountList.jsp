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
		<h3>Accounts</h3>
	</div>

	<div>

		<c:if test="${accountModel.password !=null}">
			<div style="color: green">User created. The password generated
				is : ${accountModel.password}</div>

		</c:if>

		<table class="table">
			<thead>
				<tr>
					<th scope="col">Login</th>
					<th scope="col">Role</th>
					<th scope="col">Last Name</th>
					<th scope="col">First Name</th>
					<th scope="col">Email</th>

				</tr>
			</thead>
			<c:forEach items="${accountList}" var="a">
				<tr>
					<td><c:out value="${a.username}" /></td>
					<td><c:out value="${a.role.roleName}" /></td>
					<td><c:out value="${a.person.lastName}" /></td>
					<td><c:out value="${a.person.firstName}" /></td>
					<td><c:out value="${a.person.email}" /></td>

				</tr>

			</c:forEach>

		</table>
	</div>
</body>
</html>