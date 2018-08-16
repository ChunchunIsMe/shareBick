package dao;
import java.sql.*;
import bean.userBean;
import db.DbConner;
public class userDao {
	private static final String field="phone,password,money,bno,lendbiketime";//bike ������������
	private static final String insert="insert into user("+field+") value(?,?,?,?,?)";//�����û�
	
	private static final String select="select * from user where phone=? and password=?";//ȷ���˺�����
	private static final String select1="select money from user where phone=?";//��ѯ���
	private static final String select2="select bno from user where phone=?";//��ѯ�û��Ƿ�賵
	private static final String select3="select lendbiketime from user where phone=?";//ȷ���˺�����
	
	private static final String update="update user set bno=?,lendbiketime=? where phone=?";//�軹����
	private static final String update2="update user set money=money+? where phone=?";//��Ǯ/��Ǯ
	
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
