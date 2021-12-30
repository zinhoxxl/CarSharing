package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.MainCommand;

public class LogoutAction implements MainCommand{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		HttpSession session = request.getSession(false);
		
		//로그인 정보가 있으면 세션정보 초기화
		if (session != null)
			session.invalidate();
		
		return "/mainLoginProcess.car";
	}

}
