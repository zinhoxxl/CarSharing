package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Connection conn=null;
	
		String url="jdbc:mysql://localhost:3306/carsharing"; //MySQL에서 carsharing이름으로 스키마/schema 만드시고 거기에 테이블 create하시면 됩니다.
		String user="root";
		String password="root";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		conn = DriverManager.getConnection(url,user,password);
		return conn;
		
	}
	//연결 잘되는지 테스트용 main메소드
	 public static void main(String[] args) throws ClassNotFoundException,
	  SQLException { if(DBConnection.getConnection()!=null)
	 System.out.println("연결 성공!"); else System.out.println("연결 실패!"); }
	
}
