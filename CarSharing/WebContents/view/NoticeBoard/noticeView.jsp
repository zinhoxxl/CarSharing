<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><html><head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>공지사항 내용</title>

<script>
function confirmDelete(num,pageNum,items,text){
 location.href="./NoticeDeleteAction.car?num="+num+"&pageNum="+pageNum+"&items="+items+"&text="+text;
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
<jsp:include page="../main/menu.jsp"/>
<div class="jumbotron" style="background-color: #ffffff; ">
   <div class="container">
     <h1 class="display-3">공지사항</h1>
   </div>
</div>

<div class="container">
    <form name="newUpdate" 
          action="NoticeUpdateAction.car?num=${nb.num}&pageNum=${page}&items=${items}&text=${text}"
          class="form-horizontal" method="post" >
         <input type="hidden" name="id" value="${sessionId}"><!-- request->session->application순으로 조회 -->
    <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:auto;">작성자  </h5>
        <div class="col-sm-2">
            <input name="writer" class="form-control" value="관 리 자" style="text-align: center; font-family: 'Do Hyeon', sans-serif;" placeholder="관 리 자" disabled>
        </div>
    </div>
    <hr class="form-group-row">
        <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:auto;">제목  </h5>
        <div class="col-sm-8">
            <input name="subject" class="form-control" value="${nb.subject}">
        </div>
    </div>
    <hr class="form-group-row">
        <div class="form-group row">
        <h5 class="col-sm-2 control-label" style="text-align: right; margin-top:-20;">내용  </h5>
        <div class="col-sm-8" style="word-break:break-all;">
            <textarea rows="8" cols="50" name="content"
               class="form-control">${nb.content}</textarea>
        </div>
    </div>
    <hr class="form-group-row">
    <div class="form-group row">
        <div class="col-sm-offset-2 col-sm-10">
            <c:set var="userId" value="${sessionId}" />
              <p>
         <%
           String sessionId = (String)session.getAttribute("sessionId");
           if(sessionId==null || sessionId.length()==0) sessionId="";
           if(sessionId.equals("admin")){
         %> 
        <%-- 수정 삭제 --%>
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#exampleModal">삭제</button>
        <input type="submit" class="btn btn-info" value="수정">
         <%
           }
         %> 
            <a href="./NoticeListAction.car?pageNum=${page}&items=${items}&text=${text}" class="btn btn-primary">목록</a>
        </div>
    </div>
    </form>
</div>
<jsp:include page="../main/footer.jsp"/>


<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">게시글 삭제</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">${nb.num}번 글을 삭제하시겠습니까?</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">아니오</button>
					<button type="button" class="btn btn-primary"
						onclick="confirmDelete('${nb.num}','${page}','${items}','${text}')">예</button>
				</div>
			</div>
		</div>
	</div>
    
</body>
</html>