<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.wai.pi.mycart.web.MCURIConstants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<link href="<c:url value="/resources/css/signin.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MyCart: Login</title>
</head>
<body>
<c:url value="${ MCURIConstants.loginProcessingUrl }" var="loginProcessingUrl"/>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in to continue to MyCart</h1>
            <div class="account-wall">
                <form class="form-signin" action='${loginProcessingUrl}' method='POST'>
                 <c:if test="${not empty param['error']}">
					<div class="alert alert-danger" role="alert">
					  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
					  <span class="sr-only">Error:</span>
					  <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
					  <c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" />
					</div>				      
				</c:if>   
                <input type="text" name="username" class="form-control" placeholder="Email" required autofocus>
                <input type="password" name="password" class="form-control" placeholder="Password" required>
                <!-- removed required attribute for below field for time being -->
                <input type="text" name="companycode" class="form-control" placeholder="Company Code">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                <label class="checkbox pull-left">
                <input type="checkbox" value="remember-me">Remember me
                </label>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>