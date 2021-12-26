package command.main;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.core.ApplicationServletRegistration;

import command.MainCommand;

public class mainViewProcess implements MainCommand{
	
	@Override
	public String action(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 기본 메인페이지 및 메뉴
		Date day = new java.util.Date();

		String am_pm;
		int result = 3/12;
		int hour = day.getHours();
	    int minute = day.getMinutes();
	    int second = day.getSeconds();
    	if(hour/12==0){
    		am_pm="AM";
    	}else{
	       	am_pm = "PM";
	       	hour = hour - 12;
	    }
       String CT = (hour<10?"0"+hour:hour) + ":" 
                 + (minute<10?"0"+minute:minute) + ":" 
       		  + (second<10?"0"+second:second) + " "+am_pm;
 
       
       request.setAttribute("nowTime",CT);

       return "/view/main/welcome.jsp";
	}
}
