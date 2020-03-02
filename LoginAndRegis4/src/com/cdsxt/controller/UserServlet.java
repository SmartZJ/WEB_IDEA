package com.cdsxt.controller;

import com.cdsxt.bao.UserDao;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
	private UserDao userDao = new UserDao();
	@Override
	public void init() throws ServletException {

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String mark=request.getParameter("mark");
		if("login".equals(mark)){
			userLogin(request,response);
		}else if("regis".equals(mark)){
			userRegis(request, response);
		}
	}

	/**
	 * 用户登陆
	 * 
	 */
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
			if(userDao.userLogin(uname,pwd)){
				request.getSession().setAttribute("uname", uname);
				request.getRequestDispatcher("success.jsp").forward(request, response);
			}else{
				request.setAttribute("msg", "<font color='red'>用户名或密码错误</font>");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		}
	}
	
	
	/**
	 * 用户注册
	 */
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
		}else if(userDao.valiUname(uname)){
			request.setAttribute("msg", "<font color='red'>该用户名已经被使用</font>");
			request.getRequestDispatcher("regis.jsp").forward(request, response);
			return;
		}else{
			userDao.userRegis(uname,pwd);
			request.setAttribute("msg", "<font color='green'>注册成功，请登陆</font>");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
	}
	
}
