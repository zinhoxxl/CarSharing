<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>중앙렌트카</title> 
</head>
<body>
<jsp:include page="./menu.jsp"/>
<div class="jumbotron">
  <div class="container">
  	<h1 class="display-3">중앙렌트카</h1>
  </div>
</div>  
<div class="container">
  <div class="text-center">
     <h3>중앙렌트카 홈페이지에 방문해주셔서 감사드립니다.</h3>
     <p>현재 접속 시각 : ${nowTime}
    </div>
  <div class="text-center">
 	<br>
  </div>
<hr>
</div>
<jsp:include page="./footer.jsp"/>
</body>
</html>
<!-- web.xml에 welcome-file-list에 페이지를 등록하면 
    welcome페이지로 사용가능 
http://localhost:8080/WebMarket/welcome.jsp
-->