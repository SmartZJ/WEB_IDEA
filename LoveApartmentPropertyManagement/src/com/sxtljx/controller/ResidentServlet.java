package com.sxtljx.controller;

import com.google.gson.Gson;
import com.sxtljx.dao.ResidentDao;
import com.sxtljx.util.PageUtil;
import com.sxtljx.vo.Resident;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class ResidentServlet extends HttpServlet {
    ResidentDao residentDao = new ResidentDao();

    @Override
    public void init() throws ServletException {
//        String name ,gender ,age ,idNum ,edu ,phoneNum ,imgPath ,build ,unit ,room,email,startDate,endDate;
//        for (int i = 0; i < 287; i++) {
//            name = "住户" + (i + 1);
//            gender = (int) (Math.random() * 2) + "";
//            age = (int) (Math.random() * 81) + "";
//            idNum = "510623" + (2020 - (int) (Math.random() * 21 + 20)) + "0" + (int) (Math.random() * 10) + "" + (int) (Math.random() * 21 + 10) + "" + (int) (Math.random() * 9) + "" + (int) (Math.random() * 9) + "" + (int) (Math.random() * 9) + "" + (int) (Math.random() * 9);
//            edu = (int) (Math.random() * 7 + 1) + "";
//            email = "";
//            phoneNum = "181" + (int) (Math.random() * 9) + "" + (int) (Math.random() * 9) + "" + (int) (Math.random() * 9) + "" + (int) (Math.random() * 9) + "" + (int) (Math.random() * 9) + "" + (int) (Math.random() * 9) + "" + (int) (Math.random() * 9) + "" + (int) (Math.random() * 9);
//            startDate = "2019-05-01";
//            endDate = "2020-02-29";
//            imgPath = "";
//            build = (int) (Math.random() * 6 + 1) + "";
//            unit = (int) (Math.random() * 3 + 1) + "";
//            room = (int) (Math.random() * 16 + 1) + "";
//            if (residentDao.qureyResidentIdNumIsExist(idNum)&&residentDao.qureyRoomIsEmpty(Integer.parseInt(build), Integer.parseInt(unit), Integer.parseInt(room))) {
//                //存住户信息
//                residentDao.addResident(name, gender, age, idNum, edu, email, phoneNum, startDate, endDate, imgPath);
//                //存住户和房间的关系
//                residentDao.updataResidentWithRoom(idNum, build, unit, room);
//            } else {
//                --i;
//            }
//        }

        //关系添加
//        for (int b=1;b<7;b++){
//            for (int u=1;u<4;u++ ){
//                for (int r=1;r<17;r++){
//                    residentDao.addBUR(b,u,r);
//                }
//            }
//        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码
        request.setCharacterEncoding("utf-8");
        //设置响应编码
        response.setHeader("content-type", "text/html;charset=utf-8");
        //获取请求标识
        String mark = request.getParameter("mark");
        if ("addResident".equals(mark)) {
            try {
                //添加住户
                addResident(request, response);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        } else if ("delResident".equals(mark)) {
            delResident(request, response);
        }else if ("queryResident".equals(mark)) {
            //获取住户列表
            queryResident(request, response);
        } else if ("queryResidentById".equals(mark)) {
            //根据ID获取住户
            queryResidentById(request, response);
        } else if ("queryEduList".equals(mark)) {
            //获取学历列表
            response.getWriter().write(new Gson().toJson(residentDao.queryEduList()));
        } else if ("queryBuilds".equals(mark)) {
            //获取楼列表
            response.getWriter().write(new Gson().toJson(residentDao.queryBuilds()));
        } else if ("queryUnits".equals(mark)) {
            //获取单元列表
            response.getWriter().write(new Gson().toJson(residentDao.queryUnits()));
        } else if ("queryRooms".equals(mark)) {
            //获取房间列表
            response.getWriter().write(new Gson().toJson(residentDao.queryRooms()));
        } else if ("queryResidentsByAges".equals(mark)) {

            //根据年龄段获取住户
            queryResidentsByAges(request, response);
        } else if ("queryCountEduByBuildId".equals(mark)) {

            queryCountEduByBuildId(request, response);
        } else if ("queryCountByAges".equals(mark)) {
            queryCountByAges(request, response);
        }  else if ("updataResident".equals(mark)) {
            try {
                //修改用户信息
                updataResident(request, response);
            } catch (FileUploadException e) {
                e.printStackTrace();
            }
        } else if ("showImg".equals(mark)) {
            showImg(request, response);
        } else if ("showResident".equals(mark)) {
            showResident(request, response);
        } else if ("downloadResident".equals(mark)) {
            downloadResident(request, response);
        }else if ("qureyRoomIsEmpty".equals(mark)) {
            qureyRoomIsEmpty(request, response);
        }
    }

    //查询房间是否为空
    private void qureyRoomIsEmpty(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String build = request.getParameter("build");
        String unit = request.getParameter("unit");
        String room = request.getParameter("room");
        if (residentDao.qureyRoomIsEmpty(Integer.parseInt(build),Integer.parseInt(unit), Integer.parseInt(room))){
            response.getWriter().print("<font color='green'>房间可以使用</font>");
        }else {
            response.getWriter().write("<font color='red'>房间不可以使用</font>");

        }
    }


    /**
     * 显示用户信息
     *
     * @param request
     * @param response
     */
    private void showResident(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String curPage = request.getParameter("curPage");
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("curPage", curPage);
        request.setAttribute("id", id);
        request.setAttribute("resident", residentDao.queryResidentById(id));
        request.getRequestDispatcher("residentPage.jsp").forward(request, response);

    }


    /**
     * 添加住户
     */
    private void addResident(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException {
        //初始化表单数据
        String name = "";
        String gender = "";
        String age = "";
        String idNum = "";
        String edu = "";
        String email = "";
        String build = "";
        String unit = "";
        String room = "";
        String phoneNum = "";
        String startDate = "";
        String endDate = "";
        String imgPath = "";

        FileItem photoItem = null;
        // 创建一个生产解析请求对象的工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 配置工厂的临时文件夹
        ServletContext servletContext = this.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // 通过工厂生产解析请求的工具对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决的是文件名为中文
        upload.setHeaderEncoding("utf-8");
        //设置响应编码
        response.setHeader("content-type", "text/html;charset=utf-8");
        // 解析请求
        List<org.apache.commons.fileupload.FileItem> items = upload.parseRequest(request);
        //遍历集合
        for (org.apache.commons.fileupload.FileItem item : items) {
            //获取表单域元素的名字
            String fieldName = item.getFieldName();
            //判断当前元素是否为普通表单域元素   true 普通表单域元素  false 文件域元素
            boolean result = item.isFormField();
            if (result) {
                //普通表单域元素
                //获取值
                String value = item.getString("utf-8");
                if (fieldName.equals("name")) {
                    name = value;
                } else if (fieldName.equals("gender")) {
                    gender = value;
                } else if (fieldName.equals("age")) {
                    age = value;
                } else if (fieldName.equals("idNum")) {
                    idNum = value;
                } else if (fieldName.equals("edu")) {
                    edu = value;
                } else if (fieldName.equals("email")) {
                    email = value;
                } else if (fieldName.equals("build")) {
                    build = value;
                } else if (fieldName.equals("unit")) {
                    unit = value;
                } else if (fieldName.equals("room")) {
                    room = value;
                } else if (fieldName.equals("phoneNum")) {
                    phoneNum = value;
                } else if (fieldName.equals("startDate")) {
                    startDate = value;
                } else if (fieldName.equals("endDate")) {
                    endDate = value;
                }
            } else {
                photoItem = item;
            }
        }
        //照片不为空时上传
        if (photoItem.getSize() != 0) {
            //获取路径名
            String photoPath = servletContext.getRealPath("residentImgs");
            //获取文件名
            String photoName = idNum + photoItem.getName();
            System.out.println(photoPath);
            System.out.println(photoName);
            //获取文件的流信息
            InputStream in = photoItem.getInputStream();
            OutputStream out = new FileOutputStream(photoPath + "\\" + photoName);
            byte[] b = new byte[1024];
            int temp = 0;
            while ((temp = in.read(b)) != -1) {
                out.write(b, 0, temp);
            }
            out.flush();
            out.close();
            in.close();
            imgPath = "residentImgs" + "/" + photoName;
            System.out.println(imgPath);
        }
        if (residentDao.qureyRoomIsEmpty(Integer.parseInt(build), Integer.parseInt(unit), Integer.parseInt(room)) && residentDao.qureyResidentIdNumIsExist(idNum)) {
            //存住户信息
            residentDao.addResident(name, gender, age, idNum, edu, email, phoneNum, startDate, endDate, imgPath);
            //存住户和房间的关系
            residentDao.updataResidentWithRoom(idNum, build, unit, room);
        } else {
            //向添加页面返提示
            request.setAttribute("msg", "<font color='red'>房间已被使用</font>");
            request.getRequestDispatcher("addResident.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("residentServlet?mark=queryResident&curPage=1").forward(request, response);
    }


    /**
     * 删除住户
     */
    private void delResident(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String idNum = request.getParameter("idNum");
        int id = Integer.parseInt(request.getParameter("id"));
        String curPage = request.getParameter("curPage");
        //删除关系表中删除住户
        residentDao.delResInBUR(idNum);
        //删除住户
        residentDao.delResidentById(id);
        request.getRequestDispatcher("residentServlet?mark=queryResident&curPage=" + curPage).forward(request, response);
    }
    /**
     * 查询住户信息
     */

    private void queryResident(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前页
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        //查询员工的总数量
        int count = residentDao.queryCountResident();
        //创建PageUtil对象
        PageUtil page = new PageUtil(count, curPage);
        //获取员工信息设置于 request作用域
        request.setAttribute("list", residentDao.queryResident(page.getStartRow(), page.getPageRow()));
        //page信息存于作用域
        request.setAttribute("page", page);
        //请求转发
        request.getRequestDispatcher("showResident.jsp").forward(request, response);
    }

    /**
     * 修改住户方法
     */

    //查询类方法

    /**
     * 查询住户
     */


    /**
     * 通过id查住户
     */
    private void queryResidentById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String idNum = request.getParameter("idNum");
        String curPage = request.getParameter("curPage");
        request.setAttribute("curPage", curPage);
        request.setAttribute("resident", residentDao.queryResidentById(id));
        request.getRequestDispatcher("updataResident.jsp").forward(request, response);
    }

    /**
     * 按年龄段查询用户信息
     *
     * @param request
     * @param response
     */
    private void queryResidentsByAges(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String queryAges = request.getParameter("Ages");
        int count = Integer.parseInt(request.getParameter("count"));
        List<Resident> list = new ArrayList<>();
        String[] ages = queryAges.split("-");
        //获取当前页
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        //创建PageUtil对象
        PageUtil page = new PageUtil(count, curPage);

        if (ages.length == 2) {
            //获取最小和最大年龄
            int minAge = Integer.parseInt(ages[0]);
            int maxAge = Integer.parseInt(ages[1]);
            list = residentDao.queryResidentsByAges(minAge, maxAge, page.getStartRow(), page.getPageRow());
        } else if (ages.length == 1) {
            //获取最小年龄
            int minAge = Integer.parseInt(queryAges.substring(0, queryAges.length() - 2));
            list = residentDao.queryResidentsByAges(minAge, page.getStartRow(), page.getPageRow());
        }
        request.setAttribute("ages", queryAges);
        request.setAttribute("count", count);
        //设置页码数据
        request.setAttribute("page", page);
        //设置查询到的数据
        request.setAttribute("list", list);
        request.getRequestDispatcher("agePage.jsp").forward(request, response);
    }

    /**
     * 查询各学历人数
     *
     * @param request
     * @param response
     */
    private void queryCountEduByBuildId(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int buildId = Integer.parseInt(request.getParameter("buildId"));
        response.getWriter().print(new Gson().toJson(residentDao.queryCountEduByBuildId(buildId)));
    }

    /**
     * 各查询年龄段人数
     *
     * @param request
     * @param response
     */
    private void queryCountByAges(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(new Gson().toJson(residentDao.queryCountByAges()));
    }

    public void showImg(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取页面出入的path值
        String path = request.getParameter("path");
        //获取输入流
        InputStream in = this.getServletContext().getResourceAsStream(path);
        OutputStream out = response.getOutputStream();
        byte[] b = new byte[1024];
        int temp = 0;
        while ((temp = in.read(b)) != -1) {
            out.write(b, 0, temp);
        }

        out.flush();
        out.close();
        in.close();
    }

    private void downloadResident(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String fileName = new String("爱情公寓住户信息.xls".getBytes("utf-8"), "iso8859-1");
        response.setHeader("content-disposition", "attachment;filename=" + fileName);
        try {
            Workbook wb = new HSSFWorkbook();
            Sheet sheet1 = wb.createSheet("MySheet1");
            //创建表头
            sheet1.createRow(0).createCell(0).setCellValue("爱情公寓住户信息");
            //合并单元格
            sheet1.addMergedRegion(new CellRangeAddress(
                    0, //first row (0-based)
                    0, //last row (0-based)
                    0, //first column (0-based)
                    10 //last column (0-based)
            ));
            Row row = sheet1.createRow(1);
            row.createCell(0).setCellValue("编号");
            row.createCell(1).setCellValue("姓名");
            row.createCell(2).setCellValue("年龄");
            row.createCell(3).setCellValue("性别");
            row.createCell(4).setCellValue("身份证号");
            row.createCell(5).setCellValue("学历");
            row.createCell(6).setCellValue("电话号码");
            row.createCell(7).setCellValue("起租日期");
            row.createCell(8).setCellValue("到期日期");
            row.createCell(9).setCellValue("地址");

            //拿到数据
            List<Resident> residents = residentDao.queryResident();
            //写入数据
            for (int i = 2; i < residents.size() + 1; i++) {
                Row nrow = sheet1.createRow(i);
                nrow.createCell(0).setCellValue(residents.get(i - 2).getId());
                nrow.createCell(1).setCellValue(residents.get(i - 2).getName());
                nrow.createCell(2).setCellValue(residents.get(i - 2).getAge());
                nrow.createCell(3).setCellValue(residents.get(i - 2).getGender() == 1 ? "男" : "女");
                nrow.createCell(4).setCellValue(residents.get(i - 2).getIdNum());
                nrow.createCell(5).setCellValue(residents.get(i - 2).getEduName());
                nrow.createCell(6).setCellValue(residents.get(i - 2).getPhoneNum());
                nrow.createCell(7).setCellValue(residents.get(i - 2).getStartDate().substring(0, 10));
                nrow.createCell(8).setCellValue(residents.get(i - 2).getEndDate().substring(0, 10));
                nrow.createCell(9).setCellValue(residents.get(i - 2).getAddress());
            }
            //创建输出流，将工作簿以流的方式输出
            OutputStream out = response.getOutputStream();
            wb.write(out);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
    private void updataResident(HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException, ServletException {
        String curPage = request.getParameter("curPage");
        //初始化表单数据
        String id = "";
        String name = "";
        String gender = "";
        String age = "";
        String idNum = "";
        String edu = "";
        String email = "";
        String build = "";
        String unit = "";
        String room = "";
        String phoneNum = "";
        String startDate = "";
        String endDate = "";
        String imgPath = "";

        FileItem photoItem = null;
        // 创建一个生产解析请求对象的工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // 配置工厂的临时文件夹
        ServletContext servletContext = this.getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // 通过工厂生产解析请求的工具对象
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决的是文件名为中文
        upload.setHeaderEncoding("utf-8");
        //设置响应编码
        response.setHeader("content-type", "text/html;charset=utf-8");
        // 解析请求
        List<org.apache.commons.fileupload.FileItem> items = upload.parseRequest(request);
        //遍历集合
        for (org.apache.commons.fileupload.FileItem item : items) {
            //获取表单域元素的名字
            String fieldName = item.getFieldName();
            //判断当前元素是否为普通表单域元素   true 普通表单域元素  false 文件域元素
            boolean result = item.isFormField();
            if (result) {
                //普通表单域元素
                //获取值
                String value = item.getString("utf-8");
                if (fieldName.equals("id")) {
                    id = value;
                } else if (fieldName.equals("name")) {
                    name = value;
                } else if (fieldName.equals("gender")) {
                    gender = value;
                } else if (fieldName.equals("age")) {
                    age = value;
                } else if (fieldName.equals("idNum")) {
                    idNum = value;
                } else if (fieldName.equals("edu")) {
                    edu = value;
                } else if (fieldName.equals("email")) {
                    email = value;
                } else if (fieldName.equals("build")) {
                    build = value;
                } else if (fieldName.equals("unit")) {
                    unit = value;
                } else if (fieldName.equals("room")) {
                    room = value;
                } else if (fieldName.equals("phoneNum")) {
                    phoneNum = value;
                } else if (fieldName.equals("startDate")) {
                    startDate = value;
                } else if (fieldName.equals("endDate")) {
                    endDate = value;
                }
            } else {
                photoItem = item;
            }
        }
        if (photoItem.getSize() != 0) {
            //获取路径名
            String photoPath = servletContext.getRealPath("residentImgs");
            //获取文件名
            String photoName = idNum + photoItem.getName();
            System.out.println(photoPath);
            System.out.println(photoName);
            //获取文件的流信息
            InputStream in = photoItem.getInputStream();
            OutputStream out = new FileOutputStream(photoPath + "\\" + photoName);
            byte[] b = new byte[1024];
            int temp = 0;
            while ((temp = in.read(b)) != -1) {
                out.write(b, 0, temp);
            }
            out.flush();
            out.close();
            in.close();
            imgPath = "residentImgs" + "/" + photoName;
        }
        //删除关系表中旧数据
        residentDao.delResInBUR(idNum);
        //修改户信息
        residentDao.updataResident(id, name, gender, age, idNum, edu, email, phoneNum, startDate, endDate, imgPath);
        //添加关系表中新数据
        residentDao.updataResidentWithRoom(idNum, build, unit, room);
        request.getRequestDispatcher("residentServlet?mark=queryResident&curPage=" + curPage).forward(request, response);
        return;

//        if (residentDao.qureyRommIsEmpty(Integer.parseInt(build),Integer.parseInt(unit),Integer.parseInt(room))){
//        }else {
//            request.setAttribute("msg","<font color='red'>房间已被使用</font>");
//            request.setAttribute("curPage",curPage);
//            request.setAttribute("resident",residentDao.newQueryResidentById(Integer.parseInt(id)));
//            request.getRequestDispatcher("updataResident.jsp").forward(request,response);
//            return;
//        }
    }

    //---------------------------------------------------------------------------------------------





    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
