<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"/>
<meta charset="UTF-8">
<title>QnA 게시판</title>
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
<jsp:include page="/view/main/menu.jsp"/>
<div class="jumbotron" style="background-color: #ffffff; ">
   <div class="container">
      <h1 class="display-3">QnA 게시판 작성</h1>
   </div>
</div>
<div class="container">
   <form name="newWrite" action="./QnAWriteProcess.car" class="form-horizontal" method="post">
      <input name="writer" type="hidden" class="form-control" value="${sessionId}">
      <input name="pageNum" type="hidden" value="${pageNum}">
      <input name="items" type="hidden" value="${items}">
      <input name="text" type="hidden" value="${text}">
      <input name="ref" type="hidden" value="${ref}">
      <input name="re_step" type="hidden" value="${re_step}">
      <input name="re_level" type="hidden" value="${re_level}">
      
      <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:auto;">작성자</h5>
        <div class="col-sm-3">
              <%-- ${}의 속성값은 자동 형변환처리 및 null 처리, String 인 경우 빈 문자열("")로 처리 --%>
           <input name="writer" class="form-control" value="${writer}" placeholder="writer">
        </div>
      </div>
      
        <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:auto;">회원아이디</h5>
        <div class="col-sm-3">
              <%-- ${}의 속성값은 자동 형변환처리 및 null 처리, String 인 경우 빈 문자열("")로 처리 --%>
           <input name="writer" class="form-control" value="${sessionId}" placeholder="id" disabled="disabled">
        </div>
      </div>
      
      
      <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:auto;">제목</h5>
        <div class="col-sm-5">
           <input name="subject" class="form-control" placeholder="subject" 
           <c:if test='${num!=null}'>
             value="답변 :"
           </c:if>
           >
         </div>
      </div>
      
      <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:-20;">내용</h5>
        <div class="col-sm-8">
           <textarea rows="8" cols="50" class="form-control" placeholder="내용을 입력하세요" name="content"></textarea>
        </div>
      </div>
      <hr class="form-group-row">
      
     <div class="form-group row">
        <div class="col-sm-offset-2 col-sm-10">
           <input type="submit" class="btn btn-success" value="등록">
           <input type="reset" class="btn btn-primary" value="취소">
        </div>
      </div>
 
   </form>
</div>
<jsp:include page="/view/main/footer.jsp"/>
</body>
</html>