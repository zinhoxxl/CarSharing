package command.member;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.MainCommand;
import model.member.MemberDAO;
import model.member.MemberDTO;
import model.noticeBoard.NoticeDAO;

public class SignInAction implements MainCommand{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String memberId = request.getParameter("memberId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		String birthyy = request.getParameter("birthyy");
		String birthmm = request.getParameter("birthmm");
		String birthdd = request.getParameter("birthdd");
		String birth = birthyy + "." + birthmm + "." + birthdd;
		String mail1 = request.getParameter("mail1");
		String mail2 = request.getParameter("mail2");
		String mail = mail1 + "@" + mail2;
		String zipcode = request.getParameter("zipcode");
		String roadAddress = request.getParameter("roadAddress");
		String jibunAddress = request.getParameter("jibunAddress");
		String detailAddress = request.getParameter("detailAddres");
		String extraAddress = request.getParameter("extraAddress");
		
		//등록일자 정보 생성
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		String register_day = formatter.format(new Date());
		
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
