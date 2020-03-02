package com.cdsxt.controller;

import com.cdsxt.bao.EmpDao;
import com.cdsxt.vo.AgeArea;
import com.cdsxt.vo.Employee;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServlet extends javax.servlet.http.HttpServlet {
    private  EmpDao empDao = new EmpDao();
    private List<Employee> employees = new ArrayList<>();
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        //消除乱码
        response.setHeader("content-type", "text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");

        String mark = request.getParameter("mark");
        if ("query".equals(mark)){
           empQuery(request,response);
        }else if("delete".equals(mark)){
            //进入删除方法
            delete(request,response);
        }else if("add".equals(mark)){
            //进入添加方法
            add(request,response);
        }else if("revise".equals(mark)){
            //进入修改方法
            revise(request,response);
        }else if("getemp".equals(mark)){
            //进入修改方法
            getemp(request,response);
        }else if("getAge".equals(mark)){
            getAge(request,response);
        }else if("queryDAge".equals(mark)){
            String queryDAge = request.getParameter("queryDAge");
            String[] ages = queryDAge.split("-");
            request.setAttribute("list",empDao.quaryAge(Integer.parseInt(ages[0]),Integer.parseInt(ages[1])));
            request.getRequestDispatcher("agePage.jsp").forward(request,response);
            return;
        }
    }

    private void getAge(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<AgeArea> agelist = empDao.getAge();
        PrintWriter pw = response.getWriter();
        pw.write(new Gson().toJson(agelist));
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    public void init() {
        //初始化员工
//        for (int i = 0; i < 50 ; i++) {
//            empDao.empAdd("员工"+i,(int)(Math.random()*61),(int)(Math.random()*2));
//        }
    }

    private void empQuery(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException  {
        //存入list跳转到成功页面
        employees = empDao.empQuery();
        PrintWriter pw = response.getWriter();
        pw.write(new Gson().toJson(employees));
    }

    //删除方法
    private void delete(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException  {
        int id = Integer.parseInt(request.getParameter("id"));
        empDao.empDel(id);

    }
    //添加方法
    private void add(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        int age = Integer.parseInt(request.getParameter("age"));
        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        empDao.empAdd(name,age,gender);

    }
    //修改方法
    private void revise(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{
        //获取修改信息
        int id = Integer.parseInt(request.getParameter("id"));
        int age = Integer.parseInt(request.getParameter("age"));
        String name = request.getParameter("name");
        int gender = Integer.parseInt(request.getParameter("gender"));
        empDao.empUpdata(id,name,age,gender);
    }
    //获取员工信息
    private void getemp(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException{
        //根据id获取员工信息存入request作用域
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("emp",empDao.getEmp(id));
        request.getRequestDispatcher("revise.jsp").forward(request, response);
    }
}
