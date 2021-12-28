package model.noticeBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBConnection;


public class NoticeDAO {

	//1.자신타입의 static 필드 선언
	  private static NoticeDAO instance;
	  //2.default생성자를 private로 선언
	  private NoticeDAO() {}
	  //3. public 접근제어타입의 getInstance()메소드 선언,getInstance()로만 접근 
	  public static NoticeDAO getInstance() {
		  if(instance==null) instance = new NoticeDAO();
		return instance;
	  }
	
	  
	
	  
	  
	//db에 저장하는 메소드
	  public void insertNotice(NoticeDTO board) {
		  Connection conn=null;
		  PreparedStatement pstmt=null;
		  
		  String name=null;
		  String sql = "insert into NoticeBoard(subject, content, readCount, reg_date, IP) "
		  		     + " values(?,?,?,?,?)";
		  
		  try {
			    //db연결
			    conn=DBConnection.getConnection();
			    pstmt=conn.prepareStatement(sql);
			    //값 설정
				pstmt.setString(1, board.getSubject());
				pstmt.setString(2, board.getContent());
				pstmt.setInt(3, board.getReadCount());
				pstmt.setString(4, board.getReg_date());
				pstmt.setString(5, board.getIP());
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
	  }//insertBoard()메소드 끝.
	  
	  
	  
	  
	  
	//board테이블의 레코드 가져오기
	  public List<NoticeDTO> getNoticeList(int pageNum, int limit, String items, String text){
		  Connection conn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs = null;
		  
		  String sql="";
		
		  if((items==null && text==null)||( items.length()==0 || text.length()==0))//검색 조건이 파라미터로 넘어오지 않은 경우
		   sql ="select * from NoticeBoard order by num desc";
		  else//검색 조건이 파라미터로 넘어온 경우
		   sql = "select * from NoticeBoard where "+items+" like '%"+text+"%' order by num desc";
		  
		  System.out.println("sql:"+sql);
		  
		  //전체 레코드 건수 구하기
		  int total_record = getListCount(items,text);
		  
		  int start  = (pageNum-1)*limit;// 예)1페이지-> start=0; 4페이지 -> start=(4-1)*5=>15
		  int index = start +1;//index = 1, index = 15+1 => 16
		  System.out.println("index:"+index);
		  //[1]1,2,3,4,5   [2]6,7,8,9,10, [3]11,12,13,14,15, [4]16,17,18,19,20
		  
		  //게시글 리스트 객체 생성
		  ArrayList<NoticeDTO> list = new ArrayList<>();
		  
		  try {
			    conn = DBConnection.getConnection();
			    pstmt = conn.prepareStatement(sql,
			    		                      ResultSet.TYPE_SCROLL_INSENSITIVE, 
			    		                      ResultSet.CONCUR_UPDATABLE);
			    rs = pstmt.executeQuery();
			    while(rs.absolute(index)) {
			    	//게시글 객체 생성
			    	NoticeDTO nb = new NoticeDTO();
			    	//조회된 레코드로부터 속성값 설정
			    	nb.setNum(rs.getInt("num"));
			    	System.out.println("글번호:"+rs.getInt("num"));
			    	nb.setSubject(rs.getString("subject"));
			    	nb.setContent(rs.getString("content"));
			    	nb.setReadCount(rs.getInt("readCount"));
			    	nb.setReg_date(rs.getString("reg_date"));
			    	nb.setIP(rs.getString("IP"));
			    	
			    	//리스트에 추가하기
			    	list.add(nb);
			    	
			    	//현재 페이지에 나타날범위 내이면 index증가
			    	if(index<(start + limit) && index <=total_record) index++;
			    	else
			    		break;	
			    }
			    return list;
		  }catch(Exception e) {
			  System.out.println("에러:"+e);
			  }finally {
				  try {
					    if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close();
					    if(conn!=null)conn.close();
				  }catch(Exception e) {
					  throw new RuntimeException(e.getMessage());
				  }
			  }
		  return null;
	  }//getNoticeList()메소드 끝.
	  
	  
	  
	  
	//전체 게시글 건 수 가져오기 
	  public int getListCount(String items, String text) {
	 	 Connection conn=null;
	 	 PreparedStatement pstmt=null;
	 	 ResultSet rs = null;
	 	 
	 	 //게시글 전체 건수 변수 
	 	 int x =0;
	 	 
	 	 String sql;
	 	 if((items==null && text==null)||( items.length()==0 || text.length()==0))//검색 조건이 파라미터로 넘어오지 않은 경우
	 	   sql = "select count(*) from NoticeBoard ";
	      else//검색 조건이 파라미터로 넘어온 경우
	 	   sql = "select count(*) from NoticeBoard where "+items+" like '%"+text+"%' ";
	 	 System.out.println("getListcount_SQL:"+sql);
	 	 
	 	 try {
	 		    conn=DBConnection.getConnection();
	 		    pstmt=conn.prepareStatement(sql);
	 		    rs=pstmt.executeQuery();
	 		    
	 		    if(rs.next()) x=rs.getInt(1);
	 	  }catch(Exception e) {
	 		  System.out.println("에러:"+e);
	 	  }finally {
	 		  try {
	 			    if(rs!=null) rs.close(); if(pstmt!=null) pstmt.close();
	 			    if(conn!=null)conn.close();
	 		  }catch(Exception e) {
	 			  throw new RuntimeException(e.getMessage());
	 		  }
	 	  }
	 	return x;
	 }//getListCount() 끝.
	  
	  
	  
	 
	//글 번호에 해당하는 글 정보 얻기 메소드
	  public NoticeDTO  getNoticeByNum(int num,int pageNum){
		  NoticeDTO nb = new NoticeDTO();
	 	 Connection conn=null;
	 	 PreparedStatement pstmt=null;
	 	 ResultSet rs = null;
	 	  
	 	 String sql = "select * from NoticeBoard where num=?";//글 번호에 해당하는 글 정보 얻기
	  
	 	 try {
	 		    conn=DBConnection.getConnection();
	 		    pstmt=conn.prepareStatement(sql);
	 		    pstmt.setInt(1, num);
	 		    rs=pstmt.executeQuery();
	 		    
	 		    if(rs.next()) {
	 		    	nb.setNum(rs.getInt(1));
	 		    	nb.setSubject(rs.getString(2));
	 		    	nb.setContent(rs.getString(3));
	 		    	nb.setReadCount(rs.getInt(4));
	 		    	nb.setReg_date(rs.getString(5));
	 		    	nb.setIP(rs.getString(6));
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
	 	return nb; 
	  }//getNoticeByNum() 끝.
	  
	  
	  
	  
	//글 내용 수정 처리
	  public void updateNotice(NoticeDTO notice) {
	  	  Connection conn=null;
	  	  PreparedStatement pstmt=null;
	  	  
	  	  String sql = "update NoticeBoard set subject=?, content=?, reg_date=?, ip=? where num=?";
	  	  
	  	  try {
	  		    //db연결
	  		    conn=DBConnection.getConnection();
	  		    pstmt=conn.prepareStatement(sql);
	  		    //값 설정
	  			pstmt.setString(1, notice.getSubject());
	  			pstmt.setString(2, notice.getContent());
	  			pstmt.setString(3, notice.getReg_date());
	  			pstmt.setString(4, notice.getIP());
	  			pstmt.setInt(5, notice.getNum());
	  			
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
	  }//updateNotice()끝.
	  
	  
	  
	//조회수 증가
	  public void updateReadCount(int num) {
	   Connection conn=null;
	   PreparedStatement pstmt=null;
	   
	   String sql="update NoticeBoard set readCount=readCount+1 where num=?";
	   try {
	  	   conn=DBConnection.getConnection();
	  	   pstmt = conn.prepareStatement(sql);
	  	   pstmt.setInt(1, num);
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
	  }//updateReadCount() 끝.
	  
	  
	  
	  
	  public void deleteNotice(int num){
			Connection conn=null;
			PreparedStatement pstmt=null;
			String sql="delete from NoticeBoard where num=?";
		 try {
			    conn=DBConnection.getConnection();
			    conn.setAutoCommit(false);//수동 transaction처리
			    pstmt = conn.prepareStatement(sql);
			    pstmt.setInt(1, num);
			    pstmt.executeUpdate();
			    conn.commit();//commit 처리
		} catch (Exception e) {
			 try {
				conn.rollback(); //rollback 처리
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			 System.out.println("에러:"+e);
		}finally {
			  try {
				   conn.setAutoCommit(true);//자동 transaction으로 처리
		         if(pstmt!=null) pstmt.close();
				    if(conn!=null)conn.close();
			  }catch(Exception e) {
				  throw new RuntimeException(e.getMessage());
			  }
		 }
			
		}//deleteNotice()끝.
	
}
