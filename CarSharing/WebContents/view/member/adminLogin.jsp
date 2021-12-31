<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<title>관리자 로그인</title>
</head>
<body>
<jsp:include page="/view/main/menu.jsp"/>
	<div class="jumbotron">
		<div class="container">
			<h1 class="display-3">관리자 로그인</h1>
		</div>	
	</div>
	<div class="container" align="center">
		<div class="col-md-4 col-md-offset-4">
			<h3 class="form-signin-headding">Please sign in</h3>
			<%
				String error = request.getParameter("error");
			if(error!=null){
				out.print("<div class='alert alert-danger'>");
				out.print("아이디와 비밀번호를 확인해주세요");
				out.print("</div>");
			}
			%>
			<form class="form-signin" action="j_security_check" method = "post">
				<div class="form-group">
						<label for="inputUserName" class="sr-only">User Name</label>
						<input type="text" class="form-control" placeholder="ID" name="j_username" required autofocus>			
				</div>
				<div class="form-group">
						<label for="inputPassword" class="sr-only">Password</label>
						<input type="password" class="form-control" placeholder="Password" name="j_password" required>			
				</div>
				<button class="btn btn btn-lg btn-primary btn-block" type="submit">로그인</button>
			</form>
		</div>
	</div>
</body>
</html>