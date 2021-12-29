<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="../login_failed.jsp" %>
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<style>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Gothic+A1&family=Nanum+Pen+Script&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Gothic+A1&family=Nanum+Pen+Script&family=Public+Sans:ital,wght@1,900&display=swap');
    div.jumbotron {
        font-family: 'Do Hyeon', sans-serif;
    }
    h3.form-signin-heading {
        font-family: 'Public Sans', sans-serif;
    }
</style>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../main/menu.jsp"/>
<div class="jumbotron" style="background-color: #e6ffff; text-align: center;">
	<div class="container">
	    <h1 class="display-3">관리자 로그인</h1>
	</div>
</div>
 <div class="container" align="center">
  <div class="col-md-4 col-md-offset-4" >
      <h3 class="form-signin-heading" 
          style="font-family: 'Do Hyeon', sans-serif;">관리자님 환영합니다!</h3>
      <%
      	 String error = request.getParameter("error");
         if(error!=null){
        	 out.print("<div class='alert alert-danger'>");
        	 out.print("아이디와 비밀번호를 확인해주세요");
        	 out.print("</div>");
         }
      %>
      <form class="form-signin" action="j_security_check" method="post" >
          <div class="from-group">
             <label for="inputUserName" class="sr-only">User Name</label>
             <input type="text" class="form-control" placeholder="ID" name='j_username' required autofocus>
          </div>
          <div class="form-group">
             <label for="inputPassword" class="sr-only">Password</label>
             <input type="password" class="form-control" placeholder="Password" name='j_password' required>
          </div>
          <button class="btn btn btn-lg btn-warning btn-block" type="submit"
                  style="font-family: 'Do Hyeon', sans-serif;">로그인</button>
      </form>
  </div>
 </div>
</body>
</html>