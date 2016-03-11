<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<c:url value='/app/u?changepassword' var="changepassword"/>
<form:form method="POST" action="${changepassword}" modelAttribute="userLogin" name="form">
   <table>
    <tr>
        <td><form:label path="username">User Name</form:label></td>
        <td><form:input path="username" ng-model="companyCode"/></td>
    </tr>  
    <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:input path="password"/></td>
    </tr>
    <tr>
        <td><form:label path="confirmPassword">Confirm Password</form:label></td>
        <td><form:password path="confirmPassword"/></td>
    </tr>
    <tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>    
</body>
</html>