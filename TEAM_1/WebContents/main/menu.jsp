<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value='<%=request.getParameter("language") %>'/>
<fmt:bundle basename="resourceBundle.message">
<%-- <%
	String sessionId =(String)session.getAttribute("sessionId");
%> --%>
<nav class="navbar navbar-expand navbar-dark bg-dark">
<div class="container">
  <div class="navbar-header">
  	<a class="navbar-brand" href="<c:url value="/welcome.jsp" />"><fmt:message key="Home"/></a>
  </div>
  <div>
  	<ul class="navbar-nav mr-auto">
  	   <c:choose>
  	     <c:when test="${empty sessionId}"><%-- ${sessionId==null} --%>
  	        <li class="nav-item">
  	          <a class="nav-link" href="<c:url value="/member/loginMember.jsp" />"><fmt:message key="Login"/></a>
  	          <a class="nav-link" href="<c:url value="/member/addMember.jsp" />"><fmt:message key="SignIn"/></a>
  	        </li>
  	     </c:when>
  	     <c:otherwise>
  	       <li style="padding-top:7px; color:white;">[${sessionId}님]</li>
  	       <li class="nav-item"><a class="nav-link" href="<c:url value="/member/logoutMember.jsp"/>"><fmt:message key="Logout"/></a></li>
  	       <li class="nav-item"><a class="nav-link" href="<c:url value="/member/updateMember.jsp"/>"><fmt:message key="editMember"/></a></li>
  	     </c:otherwise>
  	  </c:choose>
  	  <li class="nav-item"><a class="nav-link" href="<c:url value="/products.jsp"/>"><fmt:message key="listTitle"/></a>
  	  <li class="nav-item"><a class="nav-link" href="<c:url value="/addProduct.jsp"/>"><fmt:message key="title"/></a>
  	  <li class="nav-item"><a class="nav-link" href="<c:url value="/editProduct.jsp?edit=update"/>"><fmt:message key="editTitle"/></a>
  	  <li class="nav-item"><a class="nav-link" href="<c:url value="/editProduct.jsp?edit=delete"/>"><fmt:message key="deleteTitle"/></a>
 	  <li class="nav-item"><a class="nav-link" href="<c:url value="/BoardListAction.do?pageNum=1"/>">게시판</a>
  	  <li class="nav-item"><a class="nav-link" href="<c:url value="/BbsListAction.go?pageNum=1&items=&text="/>">bbs</a>
  	  <li class="nav-item"><a class="nav-link" href="?language=en">English</a>
  	  <li class="nav-item"><a class="nav-link disabled" href="#">/</a>
  	  <li class="nav-item"><a class="nav-link" href="?language=ko">Korean</a>
  	</ul>
  </div>
</div>
</nav>
  </fmt:bundle>