package command.member;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.core.ApplicationServletRegistration;

import command.MainCommand;

public class LoginProcess implements MainCommand{
	
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String memberId = request.getParameter("memberId");
		HttpSession session = request.getSession();
		session.setAttribute("sessionId", memberId);
       return "/view/member/loginForm.jsp";
	}
}
