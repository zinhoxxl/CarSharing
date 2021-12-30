package command.QnABoard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.MainCommand;
import model.QnABoard.QnABoardDAO_org;
import model.QnABoard.QnABoardDTO;
import model.QnABoard.QnAGoodBadDTO;

public class QnAGoodBadProcess implements MainCommand {
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//상세 글 페이지 가져오기
				//DB억세스 객체  생성
				QnABoardDAO_org dao = QnABoardDAO_org.getInstance();
				//파라미터로 넘어온 글 번호와 페이지 번호(리스트로 다시 이동시 해당 페이지 블럭으로 이동처리위해)
				int num = Integer.parseInt(request.getParameter("num"));
				int pageNum = Integer.parseInt(request.getParameter("pageNum"));
				//검색조회 파라미터 얻기
				String items =request.getParameter("items");
				String text = request.getParameter("text");
				
				int good =request.getParameter("good").equals("")?0:Integer.parseInt(request.getParameter("good"));
				int bad =request.getParameter("bad").equals("")?0: Integer.parseInt(request.getParameter("bad"));
				QnAGoodBadDTO QnAGoodBad = new QnAGoodBadDTO();
				QnAGoodBad.setNum(num);
				QnAGoodBad.setGood(good);
				QnAGoodBad.setBad(bad);
				//
				dao.insertUpdateQnAGoodBad(QnAGoodBad);
				
				//개별 속성 변수를 묶어서 처리할 DTO 객체 생성
				QnABoardDTO qna = new QnABoardDTO();
								
				//DAO에 상세글번호와 페이지 번호를 넘겨서 DB로 부터 얻은 글 정보를 다시 받음.
				qna = dao.getQnAByNum(num,pageNum);
				QnAGoodBad = dao.getQnAGoodBadByNum(num);
				
				//상세 글정보를 상세 페이지로 전달 위해 request에 세팅
				request.setAttribute("num", num);//글번호-autoBoxing(기본타입-래퍼객체로 자동형변환)
				request.setAttribute("page", pageNum);//페이지 번호
				request.setAttribute("qna", qna);//글 정보
				request.setAttribute("items", items);//검색 타입
				request.setAttribute("text", text);//검색어
				
				//
				request.setAttribute("firstNum", request.getParameter("firstNum"));
				request.setAttribute("lastNum", request.getParameter("lastNum"));
			
		        request.setAttribute("QnAGoodBad", QnAGoodBad);
		  return "/view/QnABoard/view.jsp";
	}
	
}