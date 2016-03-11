<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
<meta charset="ISO-8859-1">
<title>MyCart</title>
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
        
        <sec:authorize access="isAuthenticated()">
    		<li><a href="<c:url value="/au/logout" />">Logout</a></li>
		</sec:authorize>
		<sec:authorize access="isAnonymous()">
        	<li><a href="<c:url value='/au/l'/>">Login</a></li>
        </sec:authorize>	
        <li><a href="<c:url value='/au/s'/>">Signup</a></li>
      </ul>
    </div>
  </div>
</nav>
  <!-- /.navbar -->
  <div style="height: 450px;width: 1000px">
  	<img src="<c:url value='/resources/img/title.png'/>" alt="MyCart" style="width:200px;height:150px;" align="middle">
  </div>
  
 <footer class="container-fluid text-center">
  <p>
  ................................................................................................................................................................................................................................................................................<br> MyComp©2016</p> 
</footer>
</body>
</html>