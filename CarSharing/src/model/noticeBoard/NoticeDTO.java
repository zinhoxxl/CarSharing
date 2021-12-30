package model.noticeBoard;

public class NoticeDTO {

	private int num; //자동 생성
	private String subject; //글 제목
	private String content; //글 내용
	private int readCount; //글 읽은 횟수
	private String reg_date; //글 등록일
	private String IP; //접속 IP는 request에서 구함.
	
	
	//getter / setter
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	
	
}
