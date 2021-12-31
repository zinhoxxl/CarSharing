package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.MainCommand;
import model.member.memberDAO;

public class MemberDeleteProcess implements MainCommand{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("sessionId");
		
		memberDAO dao = memberDAO.getInstacne();
		int result = dao.memberDelete(memberId);
		String test = Integer.toString(result);
		
		request.setAttribute("msg", test);
		
		return "/view/member/resultMember.jsp";
	}

}
