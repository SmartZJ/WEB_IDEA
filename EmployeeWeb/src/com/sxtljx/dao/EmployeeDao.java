package com.sxtljx.dao;

import com.sxtljx.vo.AgeArea;
import com.sxtljx.vo.Employee;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    /**
     * 添加员工
     */
    public void addEmployee(String name,int age,int gender){
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps=conn.prepareStatement("insert into e_emp values(null,?,?,?)");
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, gender);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public void addEmployee(int id,String name,int age,int gender){
        Connection conn=null;
        PreparedStatement ps=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps=conn.prepareStatement("insert into e_emp values(?,?,?,?)");
            ps.setInt(1,id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setInt(4, gender);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    /**
     *删除员工
     */
    public void delEmployee(int id){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps=conn.prepareStatement("delete from e_emp where id=?");
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

    /**
     *修改员工信息
     */
    public void updataEmployee(int id,String name,int age,int gender){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps=conn.prepareStatement("update e_emp set name=?,age=?,gender=? where id=?");
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3,gender);
            ps.setInt(4,id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }



    public List<Employee> queryEmployees(int startRow, int pageRow) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Employee> empList=new ArrayList<Employee>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps=conn.prepareStatement("select * from e_emp limit ?,?");
            ps.setInt(1, startRow);
            ps.setInt(2, pageRow);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                int age=rs.getInt(3);
                int gender=rs.getInt(4);

                empList.add(new Employee(id, name, age, gender));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return empList;
    }
    public boolean queryEmployeeById(int id) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps=conn.prepareStatement("select * from e_emp where id=?");
            ps.setInt(1, id);
            rs=ps.executeQuery();
            while(rs.next()){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return false;
    }
    public int queryEmpCount() {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps=conn.prepareStatement("select count(*) from e_emp");
            rs=ps.executeQuery();
            while(rs.next()){
                count=rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return count;
    }

    public List<AgeArea> queryAgeCount() {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<AgeArea> ageList=new ArrayList<AgeArea>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            String sql="select temp.ageArea ageArea,count(*) count from ( "+
                    "select  "+
                    "case when age between 0 and 10 then '0-10' "+
                    "when age between 11 and 20 then '11-20' "+
                    "when age between 21 and 30 then '21-30' "+
                    "when age between 31 and 40 then '31-40' "+
                    "when age between 41 and 50 then '41-50' "+
                    "when age between 51 and 60 then '51-60' "+
                    "else '60以上' end ageArea "+
                    "from e_emp  "+
                    ") temp group by temp.ageArea;";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                String ageArea=rs.getString(1);
                int count=rs.getInt(2);

                ageList.add(new AgeArea(ageArea, count));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return ageList;
    }

//    public List<Employee> quaryByAges(int num1,int num2){
//        Connection conn=null;
//        PreparedStatement ps=null;
//        ResultSet rs=null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
//            ps=conn.prepareStatement("select * from e_emp where age between ? and ?");
//            ps.setInt(1,num1);
//            ps.setInt(2,num2);
//            rs = ps.executeQuery();
//            List<Employee> empList=new ArrayList<Employee>();
//            while(rs.next()){
//                empList.add(new Employee(rs.getInt("id"),rs.getString("name"),rs.getInt("age"),rs.getInt("gender")));
//            }
//            return empList;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally{
//            try {
//                ps.close();
//                conn.close();
//                rs.close();
//            } catch (Exception e2) {
//                e2.printStackTrace();
//            }
//        }
//        return null;
//    }
    public int queryEmpCountByAge(int num1,int num2) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        int count=0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps=conn.prepareStatement("select count(*) from e_emp where age between ? and ?");
            ps.setInt(1,num1);
            ps.setInt(2,num2);
            rs=ps.executeQuery();
            while(rs.next()){
                count=rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return count;
    }
    public List<Employee> queryEmployeesByAge(int num1,int num2,int startRow, int pageRow) {
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<Employee> empList=new ArrayList<Employee>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
            ps=conn.prepareStatement("select * from e_emp where age between ? and ? limit ?,?");
            ps.setInt(1, num1);
            ps.setInt(2, num2);
            ps.setInt(3, startRow);
            ps.setInt(4, pageRow);
            rs=ps.executeQuery();
            while(rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                int age=rs.getInt(3);
                int gender=rs.getInt(4);

                empList.add(new Employee(id, name, age, gender));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                conn.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }

        }
        return empList;
    }

}
