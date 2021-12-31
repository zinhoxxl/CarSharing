<%@page import="javax.naming.Context"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Connection conn=null;

try{
	 String url="jdbc:mysql://localhost:3306/carsharing";
	 String user="root";
	 String password="1234";
	 
	 Class.forName("com.mysql.cj.jdbc.Driver");
	 conn=DriverManager.getConnection(url,user,password);
	 if(conn==null){
		 Context init = new InitialContext();
		 
		 DataSource ds = 
		     (DataSource)init.lookup("java:comp/env/jdbc/carsharing");
		      conn=ds.getConnection();
	 }
}catch(Exception e){
	 out.println("데이터베이스 연결이 실패했습니다");
	 out.print("SQLException : " +e.getMessage());
}


	String id = request.getParameter("memberId");
     
    String sql="select count(*) from member where memberId=?";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1,id);
    //id에 해당하는 결과가 없으면 0이 리턴, 있으면 1이 리턴
    ResultSet rs=pstmt.executeQuery();    
    if(rs.next()){
    	if(rs.getInt(1)>0){
%>
<script>
alert('이미 존재하는 id입니다.');
 opener.newMember.id.value='';
 opener.newMember.id.focus();
 self.close();/* 자신을 닫는 함수  */
</script>
<%}else{%>
<script>alert("가입 가능한 ID입니다.");
        window.close();
</script>
<%  }
  }%>
