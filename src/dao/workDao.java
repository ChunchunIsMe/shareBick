package dao;
import java.sql.*;

import bean.worker;
import db.DbConner;
public class workDao {
	private static final String field="phone,password,name,id";//bike 表中所有属性
	private static final String insert="insert into worker("+field+") value(?,?,?,?)";//创建用户
	private static final String select="select * from worker where phone=? and password=?";//确认账号密码
	public int create(worker worker){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		int a=0;
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(insert);
			prepStmt.setString(1, worker.getPhone());
			prepStmt.setString(2, worker.getPassword());
			prepStmt.setString(3, worker.getName());
			prepStmt.setString(4, worker.getId());
			a=prepStmt.executeUpdate();
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
	public int checkPassword(worker worker){
		int a=0;
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		try{
			System.out.println("aaa");
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(select);
			prepStmt.setString(1, worker.getPhone());
			prepStmt.setString(2, worker.getPassword());
			rs=prepStmt.executeQuery();
			while(rs.next()){
				a=1;
			}
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		
		return a;
	}
}
