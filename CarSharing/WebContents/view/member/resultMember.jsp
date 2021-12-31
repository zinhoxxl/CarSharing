<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<body>
  <jsp:include page="/view/main/menu.jsp"/>
  <div class="jumbotron">
      <div class="container">
        <h1 class="display-3">회원 정보</h1>
      </div>
  </div>
  <div class="container" align="center">
      <%
             /* 파라미터로 넘어온 msg 값 얻기 */
      		String msg = (String)request.getAttribute("msg");
    		/* String userid=(String)request.getAttribute("sessionId"); */
            /* 파라미터 값: 0-수정, 1-가입(입력), 2-로그인, 3-회원삭제 */
            if(msg !=null){
            	     if(msg.equals("0"))
            	       	  out.print("<h2 class='alert alert-parimary'>회원정보가 수정되었습니다.</h2>");
            	     else if(msg.equals("1"))
           	       	  out.print("<h2 class='alert alert-success'>회원가입을 축하드립니다.</h2>");
            	     else if(msg.equals("2")){
              	       	out.print("<h2 class='alert alert-info'>");%>${sessionId}<%out.print("님 환영합니다.</h2>");
            	     }else if(msg.equals("3")){
            	    	 session.invalidate();
            	    	 out.print("<h2 class='alert alert-secondary'>회원정보가 삭제되었습니다.</h2>");
            	     }else{
            	    	 session.invalidate();
            	    	 out.print("<h2 class='alert alert-secondary'>아이디와 비밀번호의 정보가 일치 하지 않습니다.</h2>");
            	     }
            }else{
	             session.invalidate();
	   	    	 out.print("<h2 class='alert alert-secondary'>지속된 문제 발생시 웹사이트 관리자와 연락바랍니다.</h2>");
            }
            	
      %>
  </div>
</body>
</html>