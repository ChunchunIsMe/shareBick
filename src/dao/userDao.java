package dao;
import java.sql.*;
import bean.userBean;
import db.DbConner;
public class userDao {
	private static final String field="phone,password,money,bno,lendbiketime";//bike 表中所有属性
	private static final String insert="insert into user("+field+") value(?,?,?,?,?)";//创建用户
	
	private static final String select="select * from user where phone=? and password=?";//确认账号密码
	private static final String select1="select money from user where phone=?";//查询余额
	private static final String select2="select bno from user where phone=?";//查询用户是否借车
	private static final String select3="select lendbiketime from user where phone=?";//确认账号密码
	
	private static final String update="update user set bno=?,lendbiketime=? where phone=?";//借还单车
	private static final String update2="update user set money=money+? where phone=?";//充钱/扣钱
	
	public int create(userBean user){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		int a=0;
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(insert);
			prepStmt.setString(1, user.getPhone());
			prepStmt.setString(2, user.getPassword());
			prepStmt.setInt(3, user.getMoney());
			prepStmt.setInt(4, user.getBno());
			prepStmt.setString(5, user.getDatetime());
			
			a=prepStmt.executeUpdate();
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
	
	public int checkPassword(userBean user){
		int a=0;
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		try{
			System.out.println(user.getPhone()+user.getPassword());
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(select);
			prepStmt.setString(1, user.getPhone());
			prepStmt.setString(2, user.getPassword());
			rs=prepStmt.executeQuery();
			while(rs.next()){
				a++;
			}
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
	
	public String selTime(String phone){
		String a="";
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(select3);
			prepStmt.setString(1, phone);
			rs=prepStmt.executeQuery();
			while(rs.next()){
				a=rs.getString(1);
			}
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
	public int selBno(String phone){
		int a=0;
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(select2);
			prepStmt.setString(1, phone);
			rs=prepStmt.executeQuery();
			while(rs.next()){
				a=rs.getInt(1);
			}
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
	
	public int updateBikeBuff(userBean user){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		int a=0;
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(update);
			prepStmt.setInt(1, user.getBno());
			prepStmt.setString(2, user.getDatetime());
			prepStmt.setString(3, user.getPhone());
			a=prepStmt.executeUpdate();
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
	
	public int updateMoney(userBean user){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		int a=0;
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(update2);
			prepStmt.setInt(1, user.getMoney());
			prepStmt.setString(2, user.getPhone());
			a=prepStmt.executeUpdate();
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
}
