package com.sxtljx.controller;

import com.google.gson.Gson;
import com.sxtljx.dao.EmployeeDao;
import com.sxtljx.util.PageUtil;
import com.sxtljx.vo.AgeArea;
import com.sxtljx.vo.Employee;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet(name = "EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    EmployeeDao employeeDao = new EmployeeDao();
    @Override
    public void init() {
        //初始化员工
//        for (int i = 0; i < 350 ; i++) {
//           employeeDao.addEmployee("员工"+(50+i),(int)(Math.random()*61),(int)(Math.random()*2));
//        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码
        request.setCharacterEncoding("utf-8");
        //设置响应编码
        response.setHeader("content-type", "text/html;charset=utf-8");
        //获取请求标识
        String mark = request.getParameter("mark");
        //根据标识选择对应方法
        if ("query".equals(mark)){
            queryEmployees(request,response);
        }else if("queryid".equals(mark)){
            queryEmployeeById(request,response);
        }else if("add".equals(mark)){
            addEmployee(request,response);
        }else if("del".equals(mark)){
            delEmployee(request,response);
        }else if ("updata".equals(mark)){
            updataEmployee(request,response);
        }else if ("queryAges".equals(mark)){
            queryAges(request,response);
        }else if ("queryByAges".equals(mark)){
            queryByAges(request,response);
        }else if ("downloadEmployees".equals(mark)){
            downloadEmployees(request,response);
        }
    }

    private void downloadEmployees(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName=new String("员工信息.xls".getBytes("utf-8"),"iso8859-1");
        response.setHeader("content-disposition", "attachment;filename="+fileName);
        try {
            Workbook wb = new HSSFWorkbook();
            Sheet sheet1=wb.createSheet("MySheet1");
            //创建表头
            sheet1.createRow(0).createCell(0).setCellValue("员工信息");
            //合并单元格
            sheet1.addMergedRegion(new CellRangeAddress(
                    0, //first row (0-based)
                    0, //last row (0-based)
                    0, //first column (0-based)
                    4 //last column (0-based)
            ));
            Row row=sheet1.createRow(1);
            row.createCell(0).setCellValue("编号");
            row.createCell(1).setCellValue("姓名");
            row.createCell(2).setCellValue("年龄");
            row.createCell(3).setCellValue("性别");
            //拿到数据
            EmployeeDao employeeDao = new EmployeeDao();
            List<Employee> emps = employeeDao.queryEmployee();
            //写入数据
            for (int i=2;i<emps.size()+1;i++){
                Row nrow=sheet1.createRow(i);
                nrow.createCell(0).setCellValue(emps.get(i-2).getId());
                nrow.createCell(1).setCellValue(emps.get(i-2).getName());
                nrow.createCell(2).setCellValue(emps.get(i-2).getAge());
                nrow.createCell(3).setCellValue(emps.get(i-2).getGender()==1?"男":"女");
            }
            //创建输出流，将工作簿以流的方式输出
            OutputStream out=response.getOutputStream();
            wb.write(out);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }
    }


    //增加员工信息
    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        employeeDao.addEmployee(id,name,age,gender);
        request.getRequestDispatcher("employeeServlet?mark=query&curPage=1").forward(request, response);
    }
    //删除员工信息
    private void delEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        employeeDao.delEmployee(id);
    }
    //查询员工信息
    private void queryEmployees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前页
        int curPage=Integer.parseInt(request.getParameter("curPage"));
        //查询员工的总数量
        int count=employeeDao.queryEmpCount();
        //创建PageUtil对象
        PageUtil page=new PageUtil(count, curPage);
        //获取员工信息设置于 request作用域
        request.setAttribute("list",employeeDao.queryEmployees(page.getStartRow(),page.getPageRow()));
        //page信息存于作用域
        request.setAttribute("page",page);
        //请求转发
        request.getRequestDispatcher("success.jsp").forward(request,response);
        return;
    }
    private void queryEmployeeById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int id = Integer.parseInt(request.getParameter("id"));
       PrintWriter pw = response.getWriter();
       pw.print( employeeDao.queryEmployeeById(id));
    }
    public void queryAges(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        List<AgeArea> ageList=employeeDao.queryAgeCount();
        response.getWriter().write(new Gson().toJson(ageList));
    }
    private void queryByAges(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryDAge = request.getParameter("Ages");
        String[] ages = queryDAge.split("-");
        //获取当前页
        int curPage=Integer.parseInt(request.getParameter("curPage"));
        //查询员工的总数量
        int count=employeeDao.queryEmpCountByAge(Integer.parseInt(ages[0]),Integer.parseInt(ages[1]));
        //创建PageUtil对象
        PageUtil page=new PageUtil(count, curPage);
        //获取员工信息设置于 request作用域
        request.setAttribute("list",employeeDao.queryEmployeesByAge(Integer.parseInt(ages[0]),Integer.parseInt(ages[1]),page.getStartRow(),page.getPageRow()));
        //page信息存于作用域
        request.setAttribute("page",page);
        //年龄段信息存于作用域
        request.setAttribute("Ages",queryDAge);
//      request.setAttribute("list",employeeDao.quaryByAges(Integer.parseInt(ages[0]),Integer.parseInt(ages[1])));
        //请求转发
        request.getRequestDispatcher("agePage.jsp").forward(request,response);
        return;
    }
    //修改员工信息
    private void updataEmployee(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        employeeDao.updataEmployee(id,name,age,gender);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
