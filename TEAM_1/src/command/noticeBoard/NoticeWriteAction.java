package command.noticeBoard;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.noticeBoard.NoticeDAO;
import model.noticeBoard.NoticeDTO;

public class NoticeWriteAction implements Command{
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//새로운 글 등록하기
			//DB저장 객체 생성
			NoticeDAO dao = NoticeDAO.getInstance();
			//request로 부터 파라미터 이름에 해당하는 값 얻기
			String memberId = request.getParameter("memberId");
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			//등록일자 정보 생성
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
			String reg_date = formatter.format(new Date());
			String IP = request.getRemoteAddr();
			
			//insertBoard()메소드에 넘길 객체 생성 후, 속성에 값 설정
			NoticeDTO nb = new NoticeDTO();
			nb.setMemberId(memberId);
			nb.setWriter(writer);
			nb.setSubject(subject);
			nb.setContent(content);
			nb.setReadCount(0);
			nb.setReg_date(reg_date);
			nb.setIP(IP);
			
			//DAO에서 DB에 저장하기 위해 메소드 호출
			dao.insertNotice(nb);

		return "/NoticeListAction.car";
	}
}
