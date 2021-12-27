<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand navbar-dark bg-dark">
<div class="container">
  <div class="navbar-header">
  	<a class="navbar-brand" href="<c:url value="" />">홈</a>
  </div>
  <div>
  	<ul class="navbar-nav mr-auto">
  	   <c:choose>
  	     <c:when test="${empty sessionId}"><%-- ${sessionId==null} --%>
  	        <li class="nav-item"><a class="nav-link" href="<c:url value="#"/>">로그인</a></li>
  	        <li class="nav-item"><a class="nav-link" href="<c:url value="#"/>">회원가입</a></li>
  	        
  	     </c:when>
  	     <c:otherwise>
  	       <li style="padding-top:7px; color:white;">[${sessionId}님]</li>
  	       <li class="nav-item"><a class="nav-link" href="<c:url value="#"/>">로그아웃</a></li>
  	       <li class="nav-item"><a class="nav-link" href="<c:url value="#"/>">회원관리</a></li>
  	     </c:otherwise>
  	  </c:choose>
  	  <li class="nav-item"><a class="nav-link" href="<c:url value="#"/>">차량관리</a> <!--sessionId가 admin일때 메뉴활성화-->
  	  <li class="nav-item"><a class="nav-link" href="<c:url value="#"/>">렌트카 예약</a>
  	  <li class="nav-item"><a class="nav-link" href="<c:url value="#"/>">Q&A</a>
 	  <li class="nav-item"><a class="nav-link" href="<c:url value="/NoticeListAction.car"/>">공지사항</a>
  	</ul>
  </div>
</div>
</nav>