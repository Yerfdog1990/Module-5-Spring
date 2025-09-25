<%--
  Created by IntelliJ IDEA.
  User: godfrey
  Date: 22/9/2025 AD
  Time: 05:46
  To change this template use  File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>registration form</title>
    <link href="${pageContext.request.contextPath}/resources/register.css" rel="stylesheet">
</head>
<body>
<form:form class="register-form" action="${pageContext.request.contextPath}/registered-user" method="POST" modelAttribute="registrationDTO">
    Username: <form:input path="username"/>
    <br>
    Password: <form:password path="password"/>
    <br>
    <input type="submit" value="register">
</form:form>

</body>
</html>
