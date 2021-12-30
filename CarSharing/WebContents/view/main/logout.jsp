<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%

	session.invalidate();
	//response.sendRedirect("/CarSharing/mainViewProcess.car");
	response.sendRedirect("../member/loginForm.jsp");
%>