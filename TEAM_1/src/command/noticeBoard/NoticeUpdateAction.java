package command.noticeBoard;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.MainCommand;
import model.noticeBoard.NoticeDAO;
import model.noticeBoard.NoticeDTO;


public class NoticeUpdateAction implements MainCommand{
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//글 수정 처리
		 //파라미터로 넘어온 값 얻기
		 int num = Integer.parseInt(request.getParameter("num"));
		 int pageNum =Integer.parseInt(request.getParameter("pageNum"));
		 //검색조회 파라미터 얻기
		 String items =request.getParameter("items");
		 String text = request.getParameter("text");
		 
		 //DB억세스 객체 생성
		 NoticeDAO dao = NoticeDAO.getInstance();
		 
		 //BoardDTO객체 생성
		 NoticeDTO nb = new NoticeDTO();
		 nb.setNum(num);
		 nb.setSubject(request.getParameter("subject"));
		 nb.setContent(request.getParameter("content"));
		 
		 //등록(수정)일자 변경
		 SimpleDateFormat formatter =new SimpleDateFormat("yyyy/MM/dd(HH:mm:ss)");
		 String reg_date = formatter.format(new Date());
		 
		 nb.setReg_date(reg_date);
		 nb.setIP(request.getRemoteAddr());
		
		 //수정 메소드 호출
		 dao.updateNotice(nb);
		 
			//상세 글정보를 상세 페이지로 전달 위해 request에 세팅
			request.setAttribute("num", num);//글번호-autoBoxing(기본타입-래퍼객체로 자동형변환)
			request.setAttribute("page", pageNum);//페이지 번호
			request.setAttribute("nb", nb);//글 정보
			request.setAttribute("items", items);//검색 타입
			request.setAttribute("text", text);//검색어
		
		return "/NoticeListAction.car";
	}

}