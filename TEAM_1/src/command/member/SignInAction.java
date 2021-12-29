package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.MainCommand;
import model.member.MemberDAO;
import model.member.MemberDTO;

public class SignInAction implements MainCommand{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String memberId = request.getParameter("memberId");
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
		
		MemberDTO vo = new MemberDTO();
		vo.setMemberId(memberId);
		vo.setPassword(password);
		vo.setName(name);
		vo.setGender(gender);
		vo.setPhone(phone);
		vo.setBirth(birth);
		vo.setMail(mail);
		vo.setZipcode(zipcode);
		vo.setRoadAddress(roadAddress);
		vo.setJibunAddress(jibunAddress);
		vo.setDetailAddress(detailAddress);
		vo.setExtraAddress(extraAddress);
		vo.setRegister_day(register_day);
		
		//VO 존재 유무 확인
		if (vo != null) 
			//모델과 연동하여 DB에 정보 INSERT
			MemberDAO.getInstance().signIn(vo);
		
		return "/mainViewProcess.car";
	
	}

}
