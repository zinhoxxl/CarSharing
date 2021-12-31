package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.MainCommand;
import model.member.memberDAO;
import model.member.memberDTO;

public class LoginProcess implements MainCommand{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String memberId = request.getParameter("id");
		String password = request.getParameter("password");
		
		memberDTO dto = new memberDTO();
		dto.setMemberId(memberId);
		dto.setPassword(password);
		memberDAO dao = memberDAO.getInstacne();
		
		int result = dao.checkIdPw(dto);
		String test = Integer.toString(result);
		
		if(test.equals("0")||test.equals("1")||test.equals("2"))
		{		
		request.setAttribute("msg", test);
		
		HttpSession session = request.getSession();
		session.setAttribute("sessionId", memberId);

		}else {
			HttpSession session = request.getSession();
			session.invalidate();
			request.setAttribute("msg", test);
		}
		return "/view/member/resultMember.jsp";
	}

}
