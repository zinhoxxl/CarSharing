<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html><html><head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>공지사항</title>
<style>
    div.jumbotron {
        font-family: 'Do Hyeon', sans-serif;
    }
    h5.control-label {
        font-family: 'Do Hyeon', sans-serif;
    }
    
</style>
</head>
<body>
<jsp:include page="../main/menu.jsp"/>
<div class="jumbotron" style="background-color: #ffffff; ">
   <div class="container">
      <h1 class="display-3">공지사항_작성</h1>
   </div>
</div>
<div class="container">
   <form name="newWrite" action="./NoticeWriteAction.do"
      class="form-horizontal" method="post" onsubmit="return checkForm()">
      <input name="id" type="hidden" class="form-control" value="${sessionId}">
      <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:auto;">작성자  </h5>
        <div class="col-sm-2">
              <%-- ${}의 속성값은 자동 형변환처리 및 null 처리, String 인 경우 빈 문자열("")로 처리 --%>
           <input name="writer" class="form-control" value="${writer}" style="text-align: center;" placeholder="관 리 자" disabled>
        </div>
      </div>
      <hr class="form-group-row">
      
      <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:auto;">제목  </h5>
        <div class="col-sm-8">
           <input name="subject" class="form-control" placeholder="제목을 입력하세요">
        </div>
      </div>
      <hr class="form-group-row">
      
      <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:-20;">내용  </h5>
        <div class="col-sm-8">
           <textarea rows="5" cols="50" class="form-control" placeholder="내용을 입력하세요" name="content"></textarea>
        </div>
      </div>
      <hr class="form-group-row">
   
     <div class="form-group row">
        <div class="col-sm-offset-2 col-sm-10">
           <input type="reset" class="btn btn-warning" value="취소">
           <input type="submit" class="btn btn-info" value="등록">
        </div>
      </div>
   </form>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>