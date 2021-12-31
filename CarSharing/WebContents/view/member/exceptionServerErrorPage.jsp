<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>internal server page</title>
</head>
<body>
<jsp:include page="/view/main/menu.jsp"/>
<div class="jumbotron">
	<div class="container">
		<h2 class="alert alert-danger">서버 내부 오류 발생.</h2>
		<h4 class="alert alert-info">빠른 시간내 조치하겠습니다.</h4>
	</div>
</div>
<div class="container">
	<p><%=request.getRequestURI()%>?<%=request.getQueryString()%></p> <!--요청페이지 경로명-->
	<a href="products.jsp" class="btn btn-secondary">상품 목록 &raquo;</a> <!-- &raquo;오른쪽 꺽쇠 두개  -->
</div>
<jsp:include page="/view/main/footer.jsp"/>

</body>
</html>