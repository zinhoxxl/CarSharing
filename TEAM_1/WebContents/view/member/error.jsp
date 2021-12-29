<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><html><head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<style>
@import url('https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Gothic+A1&family=Nanum+Pen+Script&display=swap');
@import url('https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Do+Hyeon&family=Gothic+A1&family=Nanum+Pen+Script&family=Public+Sans:ital,wght@1,900&display=swap');
   div.jumbotron {
        font-family: 'Do Hyeon', sans-serif;
   }
    

</style>
<title>Server Error</title>
</head>
<body>
 <jsp:include page="../main/menu.jsp"/>
 <div class="jumbotron" style="background-color: #ffffff; text-align: center;">
   <div class="container">
   	 <h1 class="alert alert-danger">서버 내부에 오류가 발생하였습니다.</h1>
   	 <h3 class="alert alert-info">불편을 드려 정말 죄송합니다.<br>빠른시간내 조치하겠습니다. </h3>
   </div>
 </div>
 <br>
 <br>
 <br>
 <br>
 <br>
 <br>
<jsp:include page="../main/footer.jsp"/> 
</body>
</html>