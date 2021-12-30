package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MainCommand {
	public String action(HttpServletRequest request, 
			 HttpServletResponse response) throws Exception;
}