package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

import database.DBConnection;

public class memberDAO {
	private static memberDAO instance;
	private memberDAO() {}
	String result;
	
	public static memberDAO getInstacne() {
		if(instance==null) instance = new memberDAO();	
		return instance;
	}
	
	//회원정보 조회 후 신규가입 혹은 수정처리
	public int memberManagement(memberDTO dto) {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int resultNumber = 0;
		
		String sql = "select count(*) from member where memberId=?"; 
		// select count(*)는 반드시 값이 있고 대신 select *는 빈 테이블일 경우 값이 없다.
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt(1)!=0) {//회원 수정
					sql = "update member set password=?,name=?,"
							+ " gender=?, phone=?, birth=?, mail=?, zipcode=?,"
							+ " roadAddress=?, jibunAddress=?, detailAddress=?, extraAddress=?, "
							+ " register_day=curdate() where memberId=?";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,dto.getPassword());
					pstmt.setString(2,dto.getName());
					pstmt.setString(3,dto.getGender());
					pstmt.setString(4,dto.getPhone());
					pstmt.setString(5,dto.getBirth());
					pstmt.setString(6,dto.getMail());
					pstmt.setString(7,dto.getZipcode());
					pstmt.setString(8,dto.getRoadAddress());
					pstmt.setString(9,dto.getJibunAddress());
					pstmt.setString(10,dto.getDetailAddress());
					pstmt.setString(11,dto.getExtraAddress());
					pstmt.setString(12, dto.getMemberId());
					pstmt.executeUpdate();
	
					resultNumber = 0;	
				
					}else {
						sql="insert member values(?,?,?,?,?,?,?,?,?,?,?,?,curdate())";
						
						pstmt = conn.prepareStatement(sql);
			
						pstmt.setString(1,dto.getMemberId());
						pstmt.setString(2,dto.getPassword());
						pstmt.setString(3,dto.getName());
						pstmt.setString(4,dto.getGender());
						pstmt.setString(5,dto.getPhone());
						pstmt.setString(6,dto.getBirth());
						pstmt.setString(7,dto.getMail());
						pstmt.setString(8,dto.getZipcode());
						pstmt.setString(9,dto.getRoadAddress());
						pstmt.setString(10,dto.getJibunAddress());
						pstmt.setString(11,dto.getDetailAddress());
						pstmt.setString(12,dto.getExtraAddress());
						pstmt.executeUpdate();
						
						resultNumber =  1;
						}
			}
		}catch(Exception e) {
			  System.out.println("에러:"+e);
		  }finally {
			  try {
				    if(pstmt!=null) pstmt.close();
				    if(conn!=null)conn.close();
				    if(rs!=null) rs.close();
			  }catch(Exception e) {
				  throw new RuntimeException(e.getMessage());
			  }
	}
	return resultNumber;
	}

	
	public int checkIdPw(memberDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int resultNumber = 0;
		
		String sql = "select * from member where memberId=? and password=?"; 
		// select count(*)는 반드시 값이 있고 대신 select *는 빈 테이블일 경우 값이 없다.
		
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberId());
			pstmt.setString(2, dto.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
						resultNumber =  2;
		
			}else
							resultNumber =  4; //에러 메시지 표기
		}catch(Exception e) {
			  System.out.println("에러:"+e);
		  }finally {
			  try {
				    if(pstmt!=null) pstmt.close();
				    if(conn!=null)conn.close();
				    if(rs!=null) rs.close();
			  }catch(Exception e) {
				  throw new RuntimeException(e.getMessage());
			  }
	}
	return resultNumber;

	}//checkIdPw 끝

	public memberDTO memberInformation(String memberId) {
		memberDTO dto = new memberDTO();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int resultNumber = 0;
		
		String sql = "select * from member where memberId=?"; 
			
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				dto.setMemberId(rs.getString(1));
				dto.setPassword(rs.getString(2));
				dto.setName(rs.getString(3));
				dto.setGender(rs.getString(4));
				dto.setPhone(rs.getString(5));
				dto.setBirth(rs.getString(6));
				dto.setMail(rs.getString(7));
				dto.setZipcode(rs.getString(8));
				dto.setRoadAddress(rs.getString(9));
				dto.setJibunAddress(rs.getString(10));
				dto.setDetailAddress(rs.getString(11));
				dto.setExtraAddress(rs.getString(12));
				//dto.setRegister_day(rs.getString(13));
			}
		}catch(Exception e) {
			  System.out.println("에러:"+e);
		  }finally {
			  try {
				    if(pstmt!=null) pstmt.close();
				    if(conn!=null)conn.close();
				    if(rs!=null) rs.close();
			  }catch(Exception e) {
				  throw new RuntimeException(e.getMessage());
			  }
		  }

		return dto;
	}

	public int memberDelete(String memberId) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int resultNumber = 3;
		
		String sql = "delete from member where memberId=?"; 
			
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
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

		return resultNumber;
	}//memberDelete끝
}