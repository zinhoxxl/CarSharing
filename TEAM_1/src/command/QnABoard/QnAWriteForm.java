package command.QnABoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import command.MainCommand;
import model.QnABoard.QnABoardDAO_org;

public class QnAWriteForm implements MainCommand{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 //로그인 후 게시글 등록 페이지로 이동했는지, 로그인 한 작성자 이름 얻기
		//현재 member테이블이 없음으로 아래 메소드는 일시 참조처리
	       //requestLoginName(request); 
	       
	       request.setAttribute("pageNum",request.getParameter("pageNum"));
		   request.setAttribute("items",request.getParameter("items"));
		   request.setAttribute("text",request.getParameter("text"));
		   request.setAttribute("num",request.getParameter("num"));
		   request.setAttribute("ref",request.getParameter("ref"));
		   request.setAttribute("re_step",request.getParameter("re_step"));
		   request.setAttribute("re_level",request.getParameter("re_level"));
		   request.setAttribute("writer", request.getParameter("writer"));// 임시생성 writer
		   request.setAttribute("memberId", request.getParameter("memberId"));
		     
		    
		return "/view/QnABoard/writeForm.jsp";
	}
	
	//인증된 작성자/writer칼럼값 얻기
		private void requestLoginName(HttpServletRequest request) {
	        //파라미터로 넘어온 request의 id에 해당하는 값 얻기
			String id = request.getParameter("id");
			
			//DB에서 id에 해당하는 name정보 얻기
			
			//테스트상 주석처리(추후 memeber테이블 작성시 주석해지)
			QnABoardDAO_org dao = QnABoardDAO_org.getInstance();
			String name = dao.getLoginNameById(id);//id에 해당하는 name 얻기메소드
			
			request.setAttribute("writer", name);
	}
}
