package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.MainCommand;
import model.member.memberDAO;
import model.member.memberDTO;

public class MemberRegisterProcess implements MainCommand{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String memberId = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String birthyy = request.getParameter("birthyy");
		String birthmm = request.getParameter("birthmm");
		String birthdd = request.getParameter("birthdd");
		String birth = birthyy+"/"+birthmm+"/"+birthdd;
		
		String mail1 = request.getParameter("mail1");
		String mail2 = request.getParameter("mail2");
		String mail = mail1+"@"+mail2;
		
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phone = phone1+"-"+phone2+"-"+phone3;
		
		String zipcode = request.getParameter("zipcode");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		
		memberDTO dto = new memberDTO();
		memberDAO dao = memberDAO.getInstacne();
		
		dto.setMemberId(memberId);
		dto.setPassword(password);
		dto.setName(name);
		dto.setGender(gender);
		dto.setPhone(phone);
		dto.setBirth(birth);
		dto.setMail(mail);
		dto.setZipcode(zipcode);
		dto.setRoadAddress(roadAddress);
		dto.setJibunAddress(jibunAddress);
		dto.setDetailAddress(detailAddress);
		dto.setExtraAddress(extraAddress);
		
		int result = dao.memberManagement(dto);
		String test = Integer.toString(result);
		
		request.setAttribute("msg", test);
		
		HttpSession session = request.getSession();
		session.setAttribute("sessionId", memberId);
		
		
		return "/view/member/resultMember.jsp";
	}
}