package command.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.MainCommand;

public class mainView implements MainCommand{

	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 기본 메인페이지 및 메뉴
		return null;
	}

}
