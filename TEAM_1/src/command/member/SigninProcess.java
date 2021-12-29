package command.member;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import command.MainCommand;

public class SigninProcess implements MainCommand{
	
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("sessionId", id);
       return "/view/member/signinForm.jsp";
	}
}
