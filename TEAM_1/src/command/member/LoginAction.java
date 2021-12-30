package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.MainCommand;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class LoginAction implements MainCommand{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "";
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		
		//vo : 입력받은 값으로 만든 MemebrVO 객체		
     	MemberDTO vo = new MemberDTO(memberId,password);
		//cvo : model과 연동하여 vo를 넣어 결과값으로 받은 CustomerVO 객체
		MemberDTO cvo = MemberDAO.getInstance().login(vo);
		
		if (cvo == null) { //로그인 실패
			url = "redirect:loginFail.jsp";
		} else { //로그인 성공
			url = "redirect:/view/main/welcome.jsp";
			//세션 생성
			HttpSession session = request.getSession();
			String sessionId=(String)session.getAttribute("sessionId");
			sessionId = request.getParameter("sessionId");
			MemberDAO dao = MemberDAO.getInstance();
			request.setAttribute("memberId", sessionId);
			//세션에 정보 할당 - View에 정보 공유
			session.setAttribute("memberId", cvo);
		}
		
		return url;
	}
}