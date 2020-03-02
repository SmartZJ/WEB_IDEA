package com.sxtljx.controller;

import com.sxtljx.vo.Student;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;

public class StudentServlet extends HttpServlet {
    private List<Student> students = new ArrayList<>();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决请求乱码
        request.setCharacterEncoding("utf-8");
        //解决响应乱码
        response.setHeader("content-type", "text/html;charset=utf-8");
        String mark = request.getParameter("mark");
        if ("success".equals(mark)){
            List<Student> classOne = new ArrayList<>();
            for (Student s:students) {
                if (s.getClassNum()==1){
                    classOne.add(s);
                }
            }
            request.setAttribute("stuclass",classOne);
            request.getRequestDispatcher("success.jsp").forward(request, response);
            return;
        }else if ("change".equals(mark)){
            String classNum = request.getParameter("classNum");
            List<Student> nextClass = new ArrayList<>();
            for (Student s:students) {
                if (s.getClassNum()==Integer.parseInt(classNum)){
                    nextClass.add(s);
                }
            }
            Gson gson=new Gson();
            String result=gson.toJson(nextClass);
            response.getWriter().write(result);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    public void init() throws ServletException {
        for (int i = 0; i <50 ; i++) {
            students.add(new Student((i+1),"学生"+i,(int)(Math.random()*4+20),(int)(Math.random()*3+1)));
        }
    }
}
