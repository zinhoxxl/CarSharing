package command.noticeBoard;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.MainCommand;
import model.noticeBoard.NoticeDAO;

public class NoticeWriteForm implements MainCommand{
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 //세션으로 부터 로그인 아이디 얻기
	       HttpSession session = request.getSession();
	       String sessionId=(String)session.getAttribute("sessionId");
	      //로그인 아이디가 없으면 로그인 페이지로 이동 처리
	      if(sessionId==null || "".equals(sessionId)) {
	    	   response.sendRedirect("./view/member/login.jsp");
	    	   return null;
	      }
       //로그인 후 게시글 등록 페이지로 이동했는지, 로그인 한 작성자 이름 얻기
	       requestLoginName(request); 
		return "./view/NoticeBoard/noticeWriteForm.jsp";
	       //return "/NoticeWriteForm.car";
	}
	
	//인증된 사용자명 얻기
	private void requestLoginName(HttpServletRequest request) {
        //파라미터로 넘어온 request의 id에 해당하는 값 얻기
		String memberId = request.getParameter("memberId");
		
		//DB에서 memberId에 해당하는 writer정보 얻기
		NoticeDAO dao = NoticeDAO.getInstance();
		String writer = dao.getLoginNameById(memberId);//id에 해당하는 name 얻기메소드
		
		request.setAttribute("writer", writer);
	}

}
