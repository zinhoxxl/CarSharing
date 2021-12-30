package command.QnABoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.MainCommand;
import model.QnABoard.QnABoardDAO_org;
import model.QnABoard.QnABoardDTO;

public class QnAUpdateProcess implements MainCommand {

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageNum=request.getParameter("pageNum");
		 String items=request.getParameter("items");
		 String text = request.getParameter("text");
	int ref=
	    request.getParameter("ref").equals("")?0:Integer.parseInt(request.getParameter("ref"));
	int re_step=
	request.getParameter("re_step").equals("")?0:Integer.parseInt(request.getParameter("re_step"));
	int re_level=
	request.getParameter("re_level").equals("")?0:Integer.parseInt(request.getParameter("re_level"));
		
	    int num = Integer.parseInt(request.getParameter("num"));
		String writer =request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String reg_date =request.getParameter("reg_date");
		String ip = request.getRemoteAddr();
		
		QnABoardDTO qna = new QnABoardDTO();
		qna.setNum(num);
		qna.setWriter(writer);
		qna.setSubject(subject);
		qna.setContent(content);
		qna.setIp(ip);
		//원글의 글 그룹, 스텝,레벨 세팅
		qna.setRef(ref);
		qna.setRe_step(re_step);
		qna.setRe_level(re_level);
		
		System.out.println(qna.getRef());
		System.out.println(qna.getRe_step());
		System.out.println(qna.getRe_level());
		
		//글 등록 처리
		QnABoardDAO_org dao = QnABoardDAO_org.getInstance();
		
		System.out.println("ref:"+ref+",re_step:"+re_step+",re_level:"+re_level);
		
		dao.updateQnA(qna);
		
		return "/QnABoardListProcess.car";
	}

}