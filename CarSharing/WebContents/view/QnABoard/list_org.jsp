<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html><html><head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.min.js" integrity="sha384-VHvPCCyXqtD5DqJeNxl2dtTyhF78xXNXdkwX1CZeRusQfRKp+tA7hAShOK/B/fQ2" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<script>
//현재 sessionId 얻을 수 없음으로 작동안함
function checkForm(){
	console.log('${sessionScope.sessionId}');
	if(${sessionScope.sessionId==null}){
		$('#myModal').modal('show');
	}else{
		location.href="./QnAWriteForm.go?id=${sessionScope.sessionId}&pageNum=${pageNum}&items=${items}&text=${text}";
	}	
}
</script>
<title>QnA게시판</title>
</head>
<body>
<jsp:include page="/view/main/menu.jsp"/>
<div class="jumbotron">
   <div class="container">
      <h1 class="display-3">QnA게시판</h1>
   </div>
</div>
<div class="container">
  <form action="<c:url value="QnABoardListProcess.car"/>" method="post">  
    <div>
      <div class="text-right">
             <span class="badge badge-success">전체 ${total_record}</span>
      </div>
    </div>
    <div style="padding-top:50px">
       <table class="table table-hover">
            <tr>
            <th>번호</th>
             <th>제목</th>
             <th>작성일</th>
             <th>조회</th>
             <th>좋아요</th>
             <th>싫어요</th>
             <th>글쓴이</th>
            </tr>
     
   <c:if test="${not empty qnaList }">
     <c:forEach items="${qnaList}"  var="qna">          
        <tr>
         <td>${qna.num}</td>
         <td>
         <c:forEach var="blank" begin="1" end="${qna.re_level}">
		<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-return-right" viewBox="0 0 16 16">
		  <path fill-rule="evenodd" d="M1.5 1.5A.5.5 0 0 0 1 2v4.8a2.5 2.5 0 0 0 2.5 2.5h9.793l-3.347 3.346a.5.5 0 0 0 .708.708l4.2-4.2a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 8.3H3.5A1.5 1.5 0 0 1 2 6.8V2a.5.5 0 0 0-.5-.5z"/>
		</svg>
      </c:forEach>
         <a href="./QnAViewProcess.car?firstNum=${firstNum}&lastNum=${lastNum}&num=${qna.num}&pageNum=${pageNum}&items=${items}&text=${text}">${qna.subject}</a></td>
         <td>${qna.reg_date}</td>
         <td>${qna.readCount}</td>
         <td>${qna.good}</td> <!-- 좋아요 자리-->
         <td>${qna.bad}</td>   <!-- 싫어요 자리-->
         <td>${qna.writer}</td>
        </tr>        	    	       
      </c:forEach>
   </c:if>  
       </table>
       
    </div><!-- 페이지별 게시글 리스트 출력 영역 끝. -->
   <div align="center">
     <c:set var="pageNum" value="${pageNum}"/>
   <nav aria-label="...">
   <ul class="pagination justify-content-center">
  
   <c:if test="${startPage-1==1 }">
   <li class="page-item  disabled"> 
     <a  class="page-link" href="<c:url value="./QnABoardListProcess.car?pageNum=${startPage-1}"/>">Previous</a> 
    </li>
   </c:if>
   <c:if test="${startPage-1>1 }">
    <li class="page-item"> 
     <a  class="page-link" href="<c:url value="./QnABoardListProcess.car?pageNum=${startPage-1}&items=${items}&text=${text}"/>">Previous</a> 
    </li>
  </c:if>
      
     <c:forEach var="i" begin="${startPage}" end="${endPage}">
         <c:choose>
            <c:when test="${pageNum==i }">
                 <li class="page-item active" aria-current="page">
                    <a class="page-link" href="<c:url value="./QnABoardListProcess.car?pageNum=${i}&items=${items}&text=${text}"/>">${i}</a>
                  </li>
            </c:when>
            <c:otherwise>
                   <li class="page-item"><a class="page-link" href="<c:url value="./QnABoardListProcess.car?pageNum=${i}&items=${items}&text=${text}"/>">${i}</a></li>
            </c:otherwise>
         </c:choose>
     </c:forEach>
     <c:if test="${endPage+1==finalPage }">
   <li class="page-item  disabled"> 
     <a  class="page-link" href="<c:url value="./QnABoardListProcess.car?pageNum=${endPage+1}&items=${items}&text=${text}"/>">Next</a> 
    </li>
   </c:if>
   <c:if test="${endPage+1 < finalPage }">
    <li class="page-item"> 
     <a  class="page-link" href="<c:url value="./QnABoardListProcess.car?pageNum=${endPage+1}&items=${items}&text=${text}"/>">Next</a> 
    </li>
  </c:if>
   </ul>
</nav>
   </div>
   <div align="left">
      <table>
        <tr>
         <td width="100%" align="left">&nbsp;&nbsp;
          <select name="items" class="txt">
          		<option value="none" selected="selected">선택</option>
                <option value="subject"  <c:if test="${items=='subject'}">selected</c:if>>제목에서</option>
                <option value="content" <c:if test="${items=='content'}">selected</c:if>>본문에서</option>
                <option value="writer" <c:if test="${items=='writer'}">selected</c:if> >글쓴이에서</option>
          </select>
                <input name="text" type="search" value="${text}">
                <input type="submit" id="btnAdd" class="btn btn-primary" value="검색">
         </td>
         <td width="100%" align="right">
        <a href="./QnAWriteForm.car?id=hong&pageNum=${pageNum}&items=${items}&text=${text}" class="btn btn-primary">&laquo;글쓰기</a>
         </td>
        </tr>
      </table>
   </div>
  </form> 
  <hr>
</div>
<div class="modal" id="myModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">글쓰기</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <p>로그인 해주세요</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">아니오</button>
        <button type="button" class="btn btn-primary" onclick='javascript:location.href="./QnAWriteForm.go?id=${sessionScope.sessionId}&pageNum=${pageNum}&items=${items}&text=${text}"'>예</button>
      </div>
    </div>
  </div>
</div>

<jsp:include page="/view/main/footer.jsp"/>
</body>
</html>