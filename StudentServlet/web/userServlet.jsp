<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.IOException"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String mark=request.getParameter("mark");
	if("login".equals(mark)){
		userLogin(request,response);
	}else if("regis".equals(mark)){
		userRegis(request, response);
	}
%>
<%!
	private Map<String, String> userMap=new HashMap<String, String>();

	public void userLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置响应编码
		response.setHeader("content-type", "text/html;charset=utf-8");
		
		//获取表单参数
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		
		
		if(uname.isEmpty()||pwd.isEmpty()){
			request.setAttribute("msg", "<font color='red'>登陆信息填写不完整</font>");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}else{
			//通过用户名尝试从map容器中获取正确密码
			String rightPwd=userMap.get(uname);
			
			
			if(pwd.equals(rightPwd)){
				PrintWriter pw=response.getWriter();
//				pw.write("<h1>登陆成功，欢迎"+uname+"使用本系统!</h1>");
				request.setAttribute("uname",uname);
				request.getRequestDispatcher("studentServlet?mark=success").forward(request, response);
				return;
			}else{

				request.setAttribute("msg", "<font color='red'>用户名或密码错误</font>");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}
	}


	public void userRegis(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		//设置响应编码
		response.setHeader("content-type", "text/html;charset=utf-8");
		
		//获取表单参数
		String uname=request.getParameter("uname");
		String pwd=request.getParameter("pwd");
		String repwd=request.getParameter("repwd");
		
		
		if(uname.isEmpty()||pwd.isEmpty()||repwd.isEmpty()){
			request.setAttribute("msg", "<font color='red'>注册信息填写不完整</font>");
			request.getRequestDispatcher("regis.jsp").forward(request, response);
			return;
		}else if(!pwd.equals(repwd)){
			request.setAttribute("msg", "<font color='red'>两次密码输入不一致</font>");
			request.getRequestDispatcher("regis.jsp").forward(request, response);
			return;
			
			
		}else if(userMap.containsKey(uname)){
			request.setAttribute("msg", "<font color='red'>该用户名已经被使用</font>");
			request.getRequestDispatcher("regis.jsp").forward(request, response);
			return;
		}else{
			userMap.put(uname, pwd);
			request.setAttribute("msg", "<font color='green'>注册成功，请登陆</font>");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
	}
%>
