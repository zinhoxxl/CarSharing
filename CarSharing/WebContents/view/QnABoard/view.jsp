<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head>
<link rel="stylesheet" 
      href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>      
<meta charset="UTF-8">
<title>QnA 게시판</title>
<script>
function confirmDelete(num,pageNum,items,text){
 location.href="./QnADeleteProcess.car?num="+num+"&pageNum="+pageNum+"&items="+items+"&text="+text;
}
</script>
<style>
    div.jumbotron {
        font-family: 'Do Hyeon', sans-serif;
    }
    h5.control-label {
        font-family: 'Do Hyeon', sans-serif;
    }
    div.modal-header {
        font-family: 'Do Hyeon', sans-serif;
    }
</style>
</head>
<body>
<jsp:include page="/view/main/menu.jsp"/>
<div class="jumbotron" style="background-color: #ffffff; ">
   <div class="container">
     <h1 class="display-3">QnA 게시판</h1>
   </div>
</div>
<div class="container">
    <form name="newUpdate" 
          action="./QnAUpdateProcess.car?num=${qna.num}&pageNum=${page}&items=${items}&text=${text}"
          class="form-horizontal" 
          method="post">
         <input type="hidden" name="id" value="${sessionId}"><!-- request->session->application순으로 조회 -->
         <input name="ref" type="hidden" value="${qna.ref}">
         <input name="re_step" type="hidden" value="${qna.re_step}">
         <input name="re_level" type="hidden" value="${qna.re_level}">
    <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:auto;">작성자</h5>
        <div class="col-sm-3">
            <input name="writer" class="form-control" value="${qna.writer}">
        </div>
    </div>
        <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:auto;">제목</h5>
        <div class="col-sm-3">
            <input name="subject" class="form-control" value="${qna.subject}">
        </div>
    </div>
    <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:-20;">내용</h5>
        <div class="col-sm-8" style="word-break:break-all;">
            <textarea rows="5" cols="50" name="content"
               class="form-control">${qna.content}</textarea>
        </div>
    </div>
   
   
    <div class="form-group row">
        <div class="col-sm-offset-2 col-sm-10">
        	<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">삭제
        	</button>
             <input type="submit" class="btn btn-primary" value="수정">
            <a href="./QnABoardListProcess.car?pageNum=${page}&items=${items}&text=${text}" class="btn btn-primary">목록</a>
            <a href="./QnAReplyForm.car?id=${sessionId}&pageNum=${page}&items=${items}&text=${text}&num=${qna.num}&ref=${qna.ref}&re_step=${qna.re_step}&re_level=${qna.re_level}" 
            class="btn btn-primary">답변</a>
        <a href="./QnAGoodBadProcess.car?firstNum=${firstNum}&lastNum=${lastNum}&id=${sessionId}&pageNum=${page}&items=${items}&text=${text}&num=${qna.num}&ref=${qna.ref}&re_step=${qna.re_step}&re_level=${qna.re_level}&good=1&bad=0" class="btn btn-success">좋아요 : ${QnAGoodBad.good}</a>
        <a href="./QnAGoodBadProcess.car?firstNum=${firstNum}&lastNum=${lastNum}&id=${sessionId}&pageNum=${page}&items=${items}&text=${text}&num=${qna.num}&ref=${qna.ref}&re_step=${qna.re_step}&re_level=${qna.re_level}&good=0&bad=1" class="btn btn-secondary">싫어요 : ${QnAGoodBad.bad}</a>
    </div>
    </div>
    </form>
    <div>이전글:
      <c:if test="${firstNum!=qna.num}">
      <a href="./QnAViewProcess.car?firstNum=${firstNum}&lastNum=${lastNum}&num=${qna.num-1}&pageNum=${page}&items=${items}&text=${text}">${qna.num-1}</a>
      </c:if>
    </div>
    <div>다음글:
	 <c:if test="${lastNum!=qna.num}">
	  <a href="./QnAViewProcess.car?firstNum=${firstNum}&lastNum=${lastNum}&num=${qna.num+1}&pageNum=${page}&items=${items}&text=${text}">${qna.num+1}</a>
	 </c:if>
	 	
	</div>
    <hr>
</div>
<jsp:include page="/view/main/footer.jsp"/>


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">게시글 삭제</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
         ${board.num}번 글을 삭제하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">아니오</button>
        <button type="button" class="btn btn-primary" onclick="confirmDelete('${qna.num}','${page}','${items}','${text}')">예</button>
      </div>
    </div>
  </div>
</div>
</body>
</html>