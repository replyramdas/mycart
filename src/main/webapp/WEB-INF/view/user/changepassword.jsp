<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.wai.pi.mycart.web.MCURIConstants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<c:url value="${ MCURIConstants.userChangePassword }" var="changepassword"/>
<form:form method="POST" action="${changepassword}" modelAttribute="userLogin" name="form">
   <table>
    <tr>
        <td><form:hidden path="username"/></td>
    </tr>  
    <tr>
        <td><form:hidden path="companyCode"/></td>
    </tr>     
    <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><form:password path="password"/></td>
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