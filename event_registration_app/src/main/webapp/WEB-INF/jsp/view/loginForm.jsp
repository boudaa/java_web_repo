<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Authentication form</title>
</head>
<body>
	<div>
		<h3>Authentication Form</h3>
	</div>
	<div>
		<f:form
			action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">			
			<div style="color: red">
				<c:choose>
					<c:when test="${param.error=='other'}">
                      Please verify your login or password
                   </c:when>
					<c:when test="${param.error=='locked'}">
                      your account is locked
                   </c:when>
					<c:when test="${param.error=='expired'}">
                      your account is expired
                   </c:when>
					<c:when test="${param.error=='disabled'}">
                     your account is disabled
                   </c:when>
					<c:otherwise>
						<c:if test="${param.error!=null}">
                       An error has occurred, please contact the administrator
                       </c:if>
					</c:otherwise>
				</c:choose>
			</div>
			<c:if test="${param.logout != null}">
				<div style="color: green">You are logged out of the system</div>
			</c:if>
			<label>Login</label>
			<br>
			<input name="username" type="text" placeholder="Login" />
			<br>
			<label>Password</label>
			<br>
			<input name="password" type="password" placeholder="Password" />
			<br>
			<div>
				<button type="submit">Log in</button>
			</div>
		</f:form>
	</div>
</body>
</html>