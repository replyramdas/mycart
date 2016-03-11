<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.wai.pi.mycart.web.MCURIConstants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="userApp">
<head>
<style type="text/css">
form { 
    margin: 0 auto; 
    width:400px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
<script src="<c:url value='/resources/js/user.js'/>"></script>
<link   data-require="bootstrap@*" data-semver="3.3.1" rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" />
<script data-require="jquery@*" data-semver="2.1.3" src="https://code.jquery.com/jquery-2.1.3.min.js"></script>
<script data-require="bootstrap@*" data-semver="3.3.1" src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<title>Create User</title>
</head>
<body ng-controller="userController">
<c:url value="${ MCURIConstants.userCreate }" var="createuser"/>
<div style="height: 50px">
</div>
<form:form method="POST" action="${createuser}" modelAttribute="profile" name="form">
   <table>
    <tr>
        <!-- <td><form:label path="userLogin.companyCode">Company Code</form:label></td> -->
        <td><form:hidden path="userLogin.companyCode" ng-model="companyCode"/></td>
    </tr>  
    <tr>
        <td><form:label path="userLogin.username">User Name</form:label></td>
        <td><form:input path="userLogin.username" ng-model="userName"/></td>
        <td><label ng-if="isNotUnique" style="color: red">User Name is already taken!!</label></td>
    </tr>
    <tr>
        <td><form:label path="userLogin.password">Password</form:label></td>
        <td><form:password path="userLogin.password" ng-focus="checkUniqueUserName()" 
        	id="password" name="password" ng-model="credentials.password" ng-model-options="{allowInvalid: true}" 
        	pattern-validator="((?=.*\d)(?=.*[A-Z])(?=.*\W).{8,8})" /></td>
    </tr>
    <tr>
        <td><form:label path="userLogin.enabled">Enabled</form:label></td>
        <td><form:input path="userLogin.enabled" value="true"/></td>
    </tr>
     <tr>
        <td><form:label path="role.name">Role</form:label></td>
        <td><form:select path="role.id" items="${roles}" /></td>
    </tr> 
    <!-- move below two feilds to profile complete page -->
     <tr>
        <td><form:label path="securityQuestion">Question</form:label></td>
        <td><form:select path="securityQuestion" items="${secQuestions}" /></td>
    </tr> 
    <tr>
        <td><form:label path="securityAnswer">Answer</form:label></td>
        <td><form:input path="securityAnswer" /></td>
    </tr>    
    <tr>
     <td>
        <label>Password Strength</label>
     </td>
     <td><password-strength ng-model="credentials.password"></password-strength> </td>
    </tr>
    <!-- <tr>
    	<td>
      		<div class="alert alert-error" ng-show="form.password.$error.passwordPattern">The password does not meet requirements!</div>
      </td>
    </tr> -->
    <tr>
        <td>
        <span style="width: 50px"></span>
        </td>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>	

</body>
</html>