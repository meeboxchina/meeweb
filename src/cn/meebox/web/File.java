package cn.meebox.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class File
 */
@WebServlet("/file")
public class File extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public File() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filename = "";
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username"); 
		Integer uid = (Integer)session.getAttribute("uid"); 
		
		if(username == null){
			JSONObject json=new JSONObject();  
			
		    JSONArray jsonMembers = new JSONArray();  
		    JSONObject member1 = new JSONObject(); 
		    
		    
		    member1.put("authentication", "failed");  
			member1.put("goto", "http://meebox.cn/login");  
			member1.put("errorcode","404");  
			member1.put("errormsg", "");  
			member1.put("sid", ""); 
		    
		    jsonMembers.put(member1);  
			out.print(member1.toString());
		}else{
			Connection conn = null; //定义一个MYSQL链接对象
			try {
				JSONObject json=new JSONObject();  
				
			    JSONArray jsonMembers = new JSONArray();  
			    JSONObject member1 = new JSONObject(); 
			    
			    
			    
			    JSONArray userfiles = new JSONArray(); 
			    
				member1.put("authentication", "successfully");  
			    member1.put("username", (String)username.toString()); 
				member1.put("goto", "");  
				member1.put("errorcode","");  
				member1.put("errormsg", "");  
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://db.meebox.cn:3306/meebox", "meebox", "meebox");
				Statement stmt = conn.createStatement();
				String sql = "select * from file where owner=" + uid.toString() ;
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					filename = rs.getString("filename");
					JSONObject file = new JSONObject();
					file.put("filename", filename);
					userfiles.put(file);
				}
				
				member1.put("file", userfiles); 
				
			    jsonMembers.put(member1);  
				out.print(member1.toString());
			        
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
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filename = "";
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		Object username = session.getAttribute("username"); 
		Integer uid = (Integer)session.getAttribute("uid"); 
		
		if(username == null){
			JSONObject json=new JSONObject();  
			
		    JSONArray jsonMembers = new JSONArray();  
		    JSONObject member1 = new JSONObject(); 
		    
		    member1.put("authentication", "failed");  
			member1.put("goto", "http://meebox.cn/login");  
			member1.put("errorcode","404");  
			member1.put("errormsg", "");  
			member1.put("sid", ""); 
		    
		    jsonMembers.put(member1);  
			out.print(member1.toString());
		}else{
			Connection conn = null; //定义一个MYSQL链接对象
			try {
				JSONObject json=new JSONObject();  
				
			    JSONArray jsonMembers = new JSONArray();  
			    JSONObject member1 = new JSONObject(); 
			    
			    
			    
			    JSONArray userfiles = new JSONArray(); 
			    
				member1.put("authentication", "successfully");  
			    member1.put("username", (String)username.toString()); 
				member1.put("goto", "");  
				member1.put("errorcode","");  
				member1.put("errormsg", "");  
				
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection("jdbc:mysql://db.meebox.cn:3306/meebox", "meebox", "meebox");
				Statement stmt = conn.createStatement();
				String sql = "select * from file where owner=" + uid;
				ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					filename = rs.getString("filename");
					JSONObject file = new JSONObject();
					file.put("filename", filename);
					userfiles.put(file);
				}
				
				member1.put("file", userfiles); 
				
			    jsonMembers.put(member1);  
				out.print(member1.toString());
			        
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
		}
	}

}
