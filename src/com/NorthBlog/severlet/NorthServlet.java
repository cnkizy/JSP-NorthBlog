package com.NorthBlog.severlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.NorthBlog.dao.NorthBlogDao;
import com.NorthBlog.dto.users_password;

public class NorthServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public NorthServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		String action=request.getParameter("action");
		

		if("login".equals(action)){
			login(request,response);		
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
	
	
	public void login(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		PrintWriter out = response.getWriter();
		
		
		String name="";
		String pass="";
		String code="";
		String rcode=String.valueOf(session.getAttribute("rCode"));
		code=String.valueOf(request.getParameter("code"));

		if(!rcode.equals(code) || "".equals(code)){
		response.setHeader("refresh","0;url=login.jsp");
		out.print("<script type=\"text/javascript\">alert('请输入正确的验证码');</script>");
		return;
		}

		if(request.getParameter("username") !=null){
		name=request.getParameter("username");
		}
		if(request.getParameter("password") !=null){
		pass=request.getParameter("password");
		}

		//System.out.println(name);
		//System.out.println(pass);
		//System.out.println(code);
		//System.out.println(rcode);

		if( "".equals(name) || "".equals(pass)){
		response.setHeader("refresh","0;url=login.jsp");
		out.print("<script type=\"text/javascript\">alert('密码或用户名不能为空');</script>");
		return;
		}

		
		NorthBlogDao NBD=new NorthBlogDao();
		users_password psd=new users_password();
		
		psd.setU_username("123");
		psd.setU_password("123");
		
		int pow=0;
		try {
			pow = NBD.Login(psd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(pow != -1 ){//登录成功

		int id=-1;
		try {
			id = NBD.GetUserId(psd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//获取id

		session.setAttribute("loginid",id);
		session.setAttribute("pow",pow);
		session.setAttribute("loginname",name);

		response.sendRedirect("index.jsp?id="+id);

		}else{//登录失败
		response.setHeader("refresh","0;url=login.jsp");
		out.print("<script type=\"text/javascript\">alert('密码或用户名有误,登录失败');</script>");
		return;
		}
		
		
		
		
		
	}
	
	
	
	
	
	

}
