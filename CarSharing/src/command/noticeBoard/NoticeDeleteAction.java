package command.noticeBoard;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import command.MainCommand;
import model.noticeBoard.NoticeDAO;


public class NoticeDeleteAction implements MainCommand{
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum=request.getParameter("pageNum");
		String items=request.getParameter("items");
		String text = request.getParameter("text");
		int num = Integer.parseInt(request.getParameter("num"));
		 
			//글 삭제 처리
			NoticeDAO dao = NoticeDAO.getInstance();
			dao.deleteNotice(num);
	
	   request.setAttribute("pageNum",request.getParameter("pageNum"));
	   request.setAttribute("items",request.getParameter("items"));
	   request.setAttribute("text",request.getParameter("text"));
		
		return "/NoticeListAction.car";
	}

}
