<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.wai.pi.mycart.web.MCURIConstants" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" 
integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>MyComp: Home</title>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Add a gray background color and some padding to the footer */
    footer {
	  background-color: #f2f2f2;
      padding: 50px;
    }
  </style>
</head>
<body>
<!-- Begin Navbar -->
<nav class="navbar" >
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li class="active"><a href="#">Home</a></li>
        <li><a href="<c:url value='/pu/about'/>">About</a></li>
        <sec:authorize access="hasRole('ADMIN')">
        <li><a href="<c:url value='${ MCURIConstants.userCreate }'/>">Create User</a></li>
        </sec:authorize>
        <sec:authorize access="isAuthenticated()">
    		<li><a href="<c:url value="/au/logout" />">Logout</a></li>
		</sec:authorize>
      </ul>
    </div>
  </div>
</nav>
  <!-- /.navbar -->
  <div style="height: 800px">
  	 <b>Secure Space of MC <br>
  		Welcome ${myuser} !! 
  </div>
  
 <footer class="container-fluid text-center">
  <p>Austin TX, USA</p>
</footer>
</body>

</body>
</html>