<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.wai.pi.mycart.web.MCURIConstants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Complete Profile</title>
<style type="text/css">
body{
    margin-top: 200px;
    margin-left: 500px;
}
</style>
</head>
<body>
<c:url value="${ MCURIConstants.userCompleteProfile }" var="completeprofile"/>
<c:url value="${ MCURIConstants.mycartAppUrl }" var="applink"/>
<p><b>Complete Profile</b>            <a href='${applink}'>Skip</a></p>

<form:form method="POST" action="${completeprofile}" modelAttribute="profile" name="form">
   <table>
    <tr>
        <!-- <td><form:label path="userLogin.companyCode">Company Code</form:label></td> -->
        <td><form:hidden path="userLogin.username"/></td>
        <td><form:hidden path="userLogin.companyCode"/></td>
    </tr>  
    <tr>
        <td><form:label path="firstName">Fisrt Name</form:label></td>
        <td><form:input path="firstName" /></td>
    </tr>
    <tr>
        <td><form:label path="lastName">Last Name</form:label></td>
        <td><form:input path="lastName" /></td>
    </tr>
     <tr>
        <td><form:label path="securityQuestion">Question</form:label></td>
        <td><form:select path="securityQuestion" items="${secQuestions}" style="width: 175px"/></td>
    </tr> 
    <tr>
        <td><form:label path="securityAnswer">Answer</form:label></td>
        <td><form:input path="securityAnswer" /></td>
    </tr>    
    <tr>
        <td colspan="2" align="right">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>    

</body>
</html>