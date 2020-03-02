package com.cdsxt.bao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cdsxt.vo.AgeArea;
import com.cdsxt.vo.Employee;

public class EmpDao {
	/**
	 * 用户登陆，如果登陆成功返回 true  登陆失败返回false
	 */

	public void empAdd(String name,int age,int gender){
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
	
	public void empDel(int id){
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

	public List<Employee> empQuery(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select * from e_emp");
			rs = ps.executeQuery();
			List<Employee> empList=new ArrayList<Employee>();
			while(rs.next()){
				empList.add(new Employee(rs.getInt("id"),rs.getString("name"),rs.getInt("age"),rs.getInt("gender")));
			}
			return empList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	public void empUpdata(int id,String name,int age,int gender){
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

	public Employee getEmp(int id){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select * from e_emp where id=?");
			ps.setInt(1,id);
			rs = ps.executeQuery();
			Employee emp=new Employee();
			while(rs.next()){
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setAge(rs.getInt("age"));
				emp.setGender(rs.getInt("gender"));
			}
			return emp;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public List<AgeArea> getAge(){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select temp.ageArea,count(*) count from (\n" +
					"select \n" +
					"\tcase when age between 0 and 10 then '0-10'\n" +
					"\t\t\t when age between 11 and 20 then '11-20'\n" +
					"\t\twhen age between 21 and 30 then '21-30'\n" +
					"\t\twhen age between 31 and 40 then '31-40'\n" +
					"\t\twhen age between 41 and 50 then '41-50'\n" +
					"\t\twhen age between 51 and 60 then '51-60'\n" +
					"\t\telse '60以上' end ageArea \n" +
					"\t\tfrom e_emp\n" +
					"\t\t) temp group by temp.ageArea;");
			rs = ps.executeQuery();
			List<AgeArea> ageList=new ArrayList<AgeArea>();
			while(rs.next()){
				ageList.add(new AgeArea(rs.getString("ageArea"),rs.getInt("count")));
			}
			return ageList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public List<Employee> quaryAge(int num1,int num2){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			ps=conn.prepareStatement("select * from e_emp where age between ? and ?");
			ps.setInt(1,num1);
			ps.setInt(2,num2);
			rs = ps.executeQuery();
			List<Employee> empList=new ArrayList<Employee>();
			while(rs.next()){
				empList.add(new Employee(rs.getInt("id"),rs.getString("name"),rs.getInt("age"),rs.getInt("gender")));
			}
			return empList;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
				rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}
	
	
}
