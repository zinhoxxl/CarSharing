package command.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import command.MainCommand;

public class MainLoginProcess implements MainCommand{
	
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("memberId");
		HttpSession session = request.getSession();
		session.setAttribute("sessionId", id);
        return "/view/main/welcome.jsp";
	}
}
