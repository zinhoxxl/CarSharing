package command.member;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.MainCommand;
import model.member.memberDAO;
import model.member.memberDTO;

public class MemberManagementForm implements MainCommand {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession(false);
		String memberId = (String)session.getAttribute("sessionId");
		
		//admin일때는 관리팀에 문의하세요 메시지 전달 예정
		memberDAO dao = memberDAO.getInstacne();
		memberDTO dto = dao.memberInformation(memberId);
		
		//메일, 생일, 전화번호 분할하여 보내기
		String mail = dto.getMail();
		System.out.println("넘어온 메일 값"+mail); // 생일
		String [] mailToken = mail.split("@");
		request.setAttribute("mail1", mailToken[0]);
		request.setAttribute("mail2", mailToken[1]);

		String birth = dto.getBirth();
		String [] birthToken = birth.split("/");
		request.setAttribute("birthyy", birthToken[0]);
		request.setAttribute("birthmm", birthToken[1]);
		request.setAttribute("birthdd", birthToken[2]);
		
		String phone = dto.getPhone();
		String [] phoneToken = phone.split("-");
		request.setAttribute("phone1", phoneToken[0]);
		request.setAttribute("phone2", phoneToken[1]);
		request.setAttribute("phone3", phoneToken[2]);
		
		request.setAttribute("dto", dto);
		
		return "/view/member/updateMember.jsp";
	}

}
