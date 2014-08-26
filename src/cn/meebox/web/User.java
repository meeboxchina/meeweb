package cn.meebox.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
	private String username;
	private String password;
	
	public User(String username, String password) {
		// TODO Auto-generated constructor stub
		this.username = username;
		this.password = password;
	}
	
	public String auth() {
		String auth = "";
		
		Connection conn = null; //定义一个MYSQL链接对象
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/meebox", "meebox", "meebox");
			Statement stmt = conn.createStatement();
			String sql = "select * from user where username=\"" + username + "\" and password=\"" + password + "\"";
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				auth = "ok";
			}else{
				auth = "error";
			}
		        
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return auth;
	}
	
	public String test(){
		return "test";
	}
}
