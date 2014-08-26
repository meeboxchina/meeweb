package cn.meebox.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class Login
 */
@WebServlet("/user")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User(username, password);
		String auth = user.auth();
		response.setHeader("content-type", "text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		
		JSONObject json=new JSONObject();  
	    JSONArray jsonMembers = new JSONArray();  
	    JSONObject member1 = new JSONObject();  
	    
	    if(auth=="ok"){
	    	member1.put("state", "true");  
		    member1.put("goto", "http://meebox.cn/getfile");  
		    member1.put("errorcode","404");  
		    member1.put("errormsg", "密码错误");  
	    }
	    
	    jsonMembers.put(member1);  
	    
		out.print(jsonMembers.toString());
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User(username, password);
		String auth = user.auth();
		PrintWriter out = response.getWriter();
		
		
		JSONObject json=new JSONObject();  
	    JSONArray jsonMembers = new JSONArray();  
	    JSONObject member1 = new JSONObject();  
	    
	    if(auth=="ok"){
	    	member1.put("state", "true");  
		    member1.put("goto", "http://meebox.cn/getfile");  
		    member1.put("errorcode","404");  
		    member1.put("errormsg", "密码错误");  
	    }
	    
	    jsonMembers.put(member1);  
	    
		out.print(jsonMembers.toString());
		out.close();
	}

}
