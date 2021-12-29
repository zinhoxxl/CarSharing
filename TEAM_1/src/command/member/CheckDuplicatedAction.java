package command.member;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.MainCommand;
import model.member.MemberDAO;

public class CheckDuplicatedAction implements MainCommand{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
	    String url = "";
		
		String id = request.getParameter("memberId");
		//중복된 아이디가 없으면 true(ok), 있으면 false (fail)
		Boolean flag = MemberDAO.getInstance().checkDuplicatedId(id);
		if (flag)
			url = "./view/member/idCheck.jsp";
		else
			url = "./view/member/idCheck.jsp";		
		return url;
	}

}
