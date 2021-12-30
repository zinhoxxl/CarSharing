package model.QnABoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import database.DBConnection;

public class QnABoardDAO {
	//1.자신타입의 static 필드 선언
	private static QnABoardDAO instance;
	//2.default생성자를 private로 선언
	private QnABoardDAO(){}
	public static QnABoardDAO getInstance() {
		if(instance==null) instance = new QnABoardDAO();	
		return instance;
	}
	public List<QnABoardDTO> getqnaList(int pageNum, int limit, String items, String text) {
		List<QnABoardDTO> qnalist = new ArrayList<>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		if(items!=null)
		items = items.trim();
		if(text!=null) {
		text=text.trim();
		 System.out.println("넘어온 text의 길이 : "+text.length()+"넘어온 텍스트 의 값 :"+text);
		 System.out.println("넘어온 items의 길이 : "+items.length()+"넘어온 텍스트 의 값 :"+items);
		}
		String sql="";
		
		  if((items==null && text==null)||(items.length()==0 || text.length()==0)) {//검색 조건이 파라미터로 넘어오지 않은 경우
			sql = " select b.num num, "
				  	+ " b.writer writer, "
				  	+ " b.subject subject, "
				  	+ " b.content content, "
				  	+ " b.readCount readCount, "
				  	+ " b.reg_date reg_date, "
				  	+ " b.ip ip, "
				  	+ " b.ref ref, "
				  	+ " b.re_step re_step, "
				  	+ " b.re_level re_level, "
				  	+ " ifnull(q.good,0) good, "
				  	+ " ifnull(q.bad,0) bad "
				  	+ " from qnaboard b "
				  	+ " left outer join qnagoodbad q on b.num=q.num "
				  	+ " order by ref desc, re_step asc "
				  	+ " where rowNum limit ?,?";
			  	
			  	
			}else { //검색 조건이 파라미터로 넘어온 경우 
				sql = " select b.num num, "
					  	+ " b.writer writer, "
					  	+ " b.subject subject, "
					  	+ " b.content content, "
					  	+ " b.readCount readCount, "
					  	+ " b.reg_date reg_date, "
					  	+ " b.ip ip, "
					  	+ " b.ref ref, "
					  	+ " b.re_step re_step, "
					  	+ " b.re_level re_level, "
					  	+ " ifnull(q.good,0) good, "
					  	+ " ifnull(q.bad,0) bad "
					  	+ " from qnaboard b "
					  	+ " left outer join qnagoodbad q on b.num=q.num "
					  	+ " where "+items+" like concat('%',?,'%') "
					  	+ " order by ref desc, re_step asc "
					  	+ " where rowNum limit ?,?";
			 }
			System.out.println("sql:"+sql);
			
			//int pageNum=1, int limit=10
			
			//페이지 번호로 해당 페이지의 시작 글번호와 끝 글번호 구하기
			int start = (pageNum-1)*limit; //예)1페이지->0 2페이지 10 3페이지 20
			int index = start +1; //예)index=21, 1
			int end = index +9; //예)21+9=30, 1+9=10
			
			System.out.println("index:"+start); // pagenum1일
			System.out.println("end:"+end);
			
			try {
				//1.OracleDB 연결객체 생성
				conn = DBConnection.getConnection();
				if((items==null && text==null)||( items.length()==0 || text.length()==0)) {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
				}else {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, text);
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				}
				rs = pstmt.executeQuery();
				while(rs.next()) {
					QnABoardDTO qna = new QnABoardDTO();
					qna.setNum(rs.getInt(2));
					qna.setWriter(rs.getString(3));
					qna.setSubject(rs.getString(4));
					qna.setContent(rs.getString(5));
					qna.setReadCount(rs.getInt(6));
					qna.setReg_date(rs.getString(7));
					qna.setIp(rs.getString(8));
					qna.setRef(rs.getInt(9));
					qna.setRe_step(rs.getInt(10));
					qna.setRe_level(rs.getInt(11));
					//좋아요, 싫어요 추가
					qna.setGood(rs.getInt(12));
					qna.setBad(rs.getInt(13));
                   
					//리스트에 추가
					qnalist.add(qna);
				}
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
	return qnalist;   
  }
	public int getQnaCount(int pageNum, int limit, String items, String text) {
		 int count =0;
		 Connection conn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		 
		  String sql="";
		  
			  if((items==null && text==null)||( items.length()==0 || text.length()==0)) {//검색 조건이 파라미터로 넘어오지 않은 경우
					sql = "select count(*) from qnaboard ";	
				}else { //검색 조건이 파라미터로 넘어온 경우 
					sql = "select count(*) "
						+ "  from qnaboard "
						+ " where "+items+" like concat('%',?,'%') " ;
				 }
				System.out.println("sql:"+sql);
				
				//페이지 번호로 해당 페이지의 시작 글번호와 끝 글번호 구하기
				int start = (pageNum-1)*limit; //예)3페이지 -> (3-1)*10=20, 1페이지 ->0
				int index = start +1; //예)index=21, 1
				int end = index +9; //예)21+9=30, 1+9=10
				
				try {
					//1.OracleDB 연결객체 생성
					conn = DBConnection.getConnection();
					if((items==null && text==null)||( items.length()==0 || text.length()==0)) {
						pstmt = conn.prepareStatement(sql);
					}else {
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, text);
					}
					rs = pstmt.executeQuery();
					if(rs.next()) {
						count = rs.getInt(1);
					}
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
		 return count;
	}//getQnaCount 종료
	
	public int getFirstNum() {
		int minNum =0;
		  Connection conn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		 
		  String sql="select ifnull(min(num),0) from qnaboard ";	
		try {
			 //1.OracleDB 연결객체 생성
			 conn = DBConnection.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 if(rs.next()) minNum = rs.getInt(1);
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
		return minNum;
	}//getFirstNum종료
	public int getLastNum() {
		 int maxNum =0;
		 Connection conn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		 
		  String sql="select ifnull(max(num),0) from qnaboard ";	
		try {
			 //1.mySql 연결객체 생성
			 conn = DBConnection.getConnection();
			 pstmt = conn.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 if(rs.next()) maxNum = rs.getInt(1);
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
		return maxNum;
	}//getLastNum끝
	
	public void insertQnA(QnABoardDTO qna) {
		 	Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs=null;
			
			int curNum=0; //현재 qnaboard num의 max값
			String sql_Current_Num = "select max(num) from qnaboard";
			String sql = "";
			String updateSql = "";
			
			
			try { //다음 현재 qnaboard num의 max/최대값을 얻어냄 => ref에 max값+1해서 값을 매칭치킬예정
				 conn = DBConnection.getConnection();
				 pstmt = conn.prepareStatement(sql_Current_Num);
				 rs = pstmt.executeQuery();
				 if(rs.next()) curNum = rs.getInt(1);
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
			
			if(qna.getRef()==0) { //ref가 0이면 신규글 등록
				sql = "insert into qnaboard(writer,subject,content,reg_date,ip,ref) "
					+ "values (?,?,?,curdate(),?,?)";
			
			}else { //ref가 0이 아니면 답변글(댓글) 등록 (답변글은 글번호가 원글과 같음)
				System.out.println("x");
				//원글 중 댓글이 있으면, 신규 댓글 입력 전 등록하려는 댓글과 같은 ref 그룹의 기존 댓글의 스텝을 1씩 증가 처리
				updateSql = "update qnaboard set re_step=re_step+1 where ref=? and re_step > ?"; 
				
				//원글에 대한 답변글(댓글) 등록  //빠진게 readcount, reg_date, 
				sql = "insert into qnaboard(writer,subject,content,reg_date,ip,ref,re_step,re_level) "
						+ "values (?,?,?,curdate(),?,?,?,?)";			
			}
			
			try { //신규글 등록 처리
				conn = DBConnection.getConnection();
				if(qna.getRef()==0) {
					pstmt = conn.prepareStatement(sql);
					
					
					pstmt.setString(1, qna.getWriter());
					pstmt.setString(2, qna.getSubject());
					pstmt.setString(3, qna.getContent());
					//신규 등록 시 : 글번호 = ref default 0, re_step=default 0, re_level=default 0
					pstmt.setString(4, qna.getIp());
					pstmt.setInt(5, curNum+1);
					
					pstmt.executeUpdate();
				}else {
					System.out.println("y");
					
					//기존 댓글 update 처리
					pstmt = conn.prepareStatement(updateSql);
					pstmt.setInt(1,qna.getRef());
					pstmt.setInt(2,qna.getRe_step());
					
					//update처리
					pstmt.executeUpdate();
					
					//댓글 입력 처리
					pstmt = conn.prepareStatement(sql);
					System.out.println("sql:"+sql);
					
					
					pstmt.setString(1, qna.getWriter());
					pstmt.setString(2, qna.getSubject());
					pstmt.setString(3, qna.getContent());
					pstmt.setString(4, qna.getIp());
					//신규 등록 시 : 글번호=ref, re_step=0, re_level=0
					pstmt.setInt(5, qna.getRef());
					pstmt.setInt(6, qna.getRe_step()+1); //댓글 등록 시 re_step = re_step+1
					pstmt.setInt(7, qna.getRe_level()+1); //댓글 등록 시 re_level = re_level+1
					
					pstmt.executeUpdate();
			}
		 }catch(Exception e){
			  System.out.println("에러:"+e);
		  }finally {
			  try {
				    if(pstmt!=null) pstmt.close();
				    if(conn!=null)conn.close();
			  }catch(Exception e) {
				  throw new RuntimeException(e.getMessage());
			  }
	}
	}//insertQnA 끝
	public String getLoginNameById(String id) {
		  Connection conn=null;
		  PreparedStatement pstmt=null;
		  ResultSet rs=null;
		  
		  String name=null;
		  String sql="select * from member where id=?";
		  
		  try {
			    //Mysql 접속용 DBConnection객체 
			    conn=DBConnection.getConnection();
			    pstmt=conn.prepareStatement(sql);
			    pstmt.setString(1, id); 
			
			    rs=pstmt.executeQuery();
			    
			    if(rs.next()) {
			    	name=rs.getString("name");//rs.getString(칼럼명)
			    }
			    return name;//값을 db에서 얻어왔으면 name을 리턴, 아니면 null값 그대로 리턴.
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
	}//getLoginNameById끝
	
	public void deleteQnA(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String replyCountSql="select count(*) from qnaboard where ref=? and re_step >0";
		String sql = "delete from qnaboard where num=?";			
		
		try { //삭제 처리
			conn = DBConnection.getConnection();
			
			System.out.println("sql:"+sql);
				//댓글 존재 여부확인
				pstmt = conn.prepareStatement(replyCountSql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
	            int replyCount=0;
				if(rs.next()) replyCount = rs.getInt(1);
				if(replyCount ==0 ) { //댓글이 없는 글들만 삭제
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);			
	                pstmt.executeUpdate();
				}
	}catch(Exception e){
		  System.out.println("에러:"+e);
	}finally {
		  try {
			    if(pstmt!=null) pstmt.close();
			    if(conn!=null)conn.close();
		  }catch(Exception e) {
			  throw new RuntimeException(e.getMessage());
		  }
	}
}//deleteQnA끝
	
	 //글번호에 해당하는 QnABoard정보 얻기
	public QnABoardDTO getQnAByNum(int num, int pageNum) {
		QnABoardDTO qna =null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		 
		String sql="select * from qnaboard where num=?";
		 
		System.out.println("sql:"+sql);
		  
				try {
					//1.OracleDB 연결객체 생성
					conn = DBConnection.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, num);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
					qna = new QnABoardDTO();
					qna.setNum(rs.getInt(1));
					qna.setWriter(rs.getString(2));
					qna.setSubject(rs.getString(3));
					qna.setContent(rs.getString(4));
					qna.setReadCount(rs.getInt(5));
					qna.setReg_date(rs.getString(6));
					qna.setIp(rs.getString(7));
					qna.setRef(rs.getInt(8));
					qna.setRe_step(rs.getInt(9));
					qna.setRe_level(rs.getInt(10));				
					}
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
		  return qna;
		 }//getQnAByNum() 끝.
	
	public void updateQnAReadcount(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update qnaboard set readCount=readCount+1 where num=?";
		
		try { //조회수 증가 처리
			conn = DBConnection.getConnection();	
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,num);
				//update처리
				pstmt.executeUpdate();
	 }catch(Exception e){
		  System.out.println("에러:"+e);
	  }finally {
		  try {
			    if(pstmt!=null) pstmt.close();
			    if(conn!=null)conn.close();
		  }catch(Exception e) {
			  throw new RuntimeException(e.getMessage());
		  }
	  } 
	}//updateQnAReadcount끝
	
	public QnAGoodBadDTO getQnAGoodBadByNum(int num) {
		QnAGoodBadDTO goodBad =null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String sql = "select * from qnagoodbad where num=?";
		
		try { //신규글 등록 처리
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
		    pstmt.setInt(1, num);	
			
			rs = pstmt.executeQuery();
		    if(rs.next()) {
		    	goodBad = new QnAGoodBadDTO();
		    	goodBad.setNum(num);
	            goodBad.setGood(rs.getInt(2));
	            goodBad.setBad(rs.getInt(3));
		    }
	    }catch(Exception e){
		  System.out.println("에러:"+e);
		  e.printStackTrace();
	  }finally {
		  try {
			    if(rs!=null) rs.close();
			    if(pstmt!=null) pstmt.close();
			    if(conn!=null)conn.close();
		  }catch(Exception e) {
			  throw new RuntimeException(e.getMessage());
		  }
	   } 
		return goodBad;// select했을때 없으면 null가져감
	}//getQnAGoodBadByNum 종료
	
	
	public void insertUpdateQnAGoodBad(QnAGoodBadDTO qnAGoodBad) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		
		String sql = "select count(*) from qnagoodbad where num=?";//rs.next() =true, 0, > 0     만약 num해당는 db테이블의 값이 없으면 rs.next() 또는 count가 false처리됨 그래서 count(*)사용
		String insertSql = "insert into qnagoodbad values(?,?,?)";
		String updateSql = "update qnagoodbad set good=good+?,bad=bad+? where num=?";
		int count=0;
		try { //신규글 등록 처리
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnAGoodBad.getNum());
			
			rs = pstmt.executeQuery();
		    if(rs.next()) {
		       count = rs.getInt(1);
		    }
		    rs.close();
		    pstmt.close();
		    if(count==0){//입력
		    		pstmt = conn.prepareStatement(insertSql);
		    		pstmt.setInt(1, qnAGoodBad.getNum());
		    		pstmt.setInt(2, qnAGoodBad.getGood());
		    		pstmt.setInt(3, qnAGoodBad.getBad());
		    		pstmt.executeUpdate();
		    }else {//수정
		    		pstmt = conn.prepareStatement(updateSql);
		    		pstmt.setInt(1, qnAGoodBad.getGood());
		    		pstmt.setInt(2, qnAGoodBad.getBad());
		    		pstmt.setInt(3, qnAGoodBad.getNum());
		    		pstmt.executeUpdate();	
		    	}
	 }catch(Exception e){
		  System.out.println("에러:"+e);
		  e.printStackTrace();
	  }finally {
		  try {
			    if(rs!=null) rs.close();
			    if(pstmt!=null) pstmt.close();
			    if(conn!=null)conn.close();
		  }catch(Exception e) {
			  throw new RuntimeException(e.getMessage());
		  }
	   } 	
	}//insertUpdateQnAGoodBad끝


	public void updateQnA(QnABoardDTO qna) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "update qnaboard set writer=?,subject=?,content=?,ip=? where num=?";			
		
		
		try { //신규글 등록 처리
			conn = DBConnection.getConnection();
			
				//댓글 입력 처리
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql:"+sql);
				
				pstmt.setString(1, qna.getWriter());
				pstmt.setString(2, qna.getSubject());
				pstmt.setString(3, qna.getContent());
				pstmt.setString(4, qna.getIp());
	            pstmt.setInt(5, qna.getNum());			
				//update처리
				pstmt.executeUpdate();
				
	 }catch(Exception e){
		  System.out.println("에러:"+e);
	  }finally {
		  try {
			    if(pstmt!=null) pstmt.close();
			    if(conn!=null)conn.close();
		  }catch(Exception e) {
			  throw new RuntimeException(e.getMessage());
		  }
	  }
	}//updateQnA끝
}