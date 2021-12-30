package model.member;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.DBConnection;


//singleton pattern 적용
//DBCP 사용
public class MemberDAO {
	
	
	//1.자신타입의 static 필드 선언
	  private static MemberDAO instance;
	  //2.default생성자를 private로 선언
	  private MemberDAO() {}
	  //3. public 접근제어타입의 getInstance()메소드 선언,getInstance()로만 접근 
	  public static MemberDAO getInstance() {
		  if(instance==null) instance = new MemberDAO();
		return instance;
	  }
	  


	
	
	//회원가입
	public void signIn(MemberDTO member) {
		  Connection conn=null;
		  PreparedStatement pstmt=null;
		  String sql = "INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			//db연결
		    conn=DBConnection.getConnection();
		    pstmt=conn.prepareStatement(sql);
		    //값 설정
			pstmt.setString(1, member.getMemberId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender());
			pstmt.setString(5, member.getPhone());
			pstmt.setString(6, member.getBirth());
			pstmt.setString(7, member.getMail());
			pstmt.setString(8, member.getZipcode());
			pstmt.setString(9, member.getRoadAddress());
			pstmt.setString(10, member.getJibunAddress());
			pstmt.setString(11, member.getDetailAddress());
			pstmt.setString(12, member.getExtraAddress());
			pstmt.setString(13, member.getRegister_day());
			//db저장처리
			pstmt.executeUpdate();		  
	  }catch(Exception e) {
		  System.out.println("에러:"+e);
	  }finally {
		  try {
                if(pstmt!=null) pstmt.close();
			    if(conn!=null)conn.close();
		  }catch(Exception e) {
			  throw new RuntimeException(e.getMessage());
		  }
	  }
  }//signIn() 끝.
	
	
	
	
	
	
	//아이디 중복 확인 - 중복된 아이디가 없으면 true(ok), 있으면 false (fail)
	public boolean checkDuplicatedId(String memberId) throws SQLException {
		boolean flag = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM member WHERE memberId=?";
		
		try {
			conn=DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery(); 
			if (rs.next() && rs.getInt(1) > 0) {
				flag = false;
			}
		} catch(Exception e) {
	 		 System.out.println("에러:"+e);// e.toString() 자동 호출
	 	 }finally {
	 		  try {
	 			    if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close();
	 			    if(conn!=null)conn.close();
	 		  }catch(Exception e) {
	 			  throw new RuntimeException(e.getMessage());
	 		  }
	 	  }
		return flag;
	}
	
	
	
	  // 로그인 체크
	public MemberDTO login(MemberDTO vo) {
	    String name = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
			
	    String sql = "SELECT NAME FROM member WHERE memberId=? and password=?";
	    
	    try {
		      conn=DBConnection.getConnection();
			  pstmt = conn.prepareStatement(sql);
		      pstmt.setString(1, vo.getMemberId());
		      pstmt.setString(2, vo.getPassword());
					
		      rs = pstmt.executeQuery();
		      if(rs.next()) {
		        name = rs.getString("name");
		      }
		    }catch(Exception e) {
		 		 System.out.println("에러:"+e);// e.toString() 자동 호출
		 	 }finally {
		 		  try {
		 			    if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close();
		 			    if(conn!=null)conn.close();
		 		  }catch(Exception e) {
		 			  throw new RuntimeException(e.getMessage());
		 		  }
		 	  }
		    return vo;
		  }
	
	
}