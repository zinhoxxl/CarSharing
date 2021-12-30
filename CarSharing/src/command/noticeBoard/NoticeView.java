package command.noticeBoard;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import command.MainCommand;
import model.noticeBoard.NoticeDAO;


public class NoticeView implements MainCommand{
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//게시글 조회수 증가 처리
			int num = Integer.parseInt(request.getParameter("num"));
		    //DB억세스 객체 생성
			NoticeDAO dao = NoticeDAO.getInstance();
			dao.updateReadCount(num);
		
		return "./view/NoticeBoard/noticeView.jsp";
	}

}
