package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.MainCommand;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class SignInAction implements MainCommand{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = request.getParameter("memberId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String birth = request.getParameter("birth");
		String mail = request.getParameter("mail");
		String zipcode = request.getParameter("zipcode");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String detailAddress = request.getParameter("detailAddres");
		String extraAddress = request.getParameter("extraAddress");
		String register_day = request.getParameter("register_day");
		
		MemberDTO member = new MemberDTO();
		member.setMemberId(id);
		member.setPassword(password);
		member.setName(name);
		member.setGender(gender);
		member.setPhone(phone);
		member.setBirth(birth);
		member.setMail(mail);
		member.setZipcode(zipcode);
		member.setRoadAddress(roadAddress);
		member.setJibunAddress(jibunAddress);
		member.setDetailAddress(detailAddress);
		member.setExtraAddress(extraAddress);
		member.setRegister_day(register_day);
		
		//VO 존재 유무 확인
		if (member != null) 
			//모델과 연동하여 DB에 정보 INSERT
			MemberDAO.getInstance().signIn(member);
		
		return "/mainViewProcess.car";
	
	}

}
