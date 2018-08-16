package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import bean.bike;
import db.DbConner;
public class bikeDao {
	private static final String field="bno,place,lend,fixtime";//bike 表中所有属性
	private static final String insert="insert into bike("+field+") value(?,?,?,?)";//创建单车
	
	private static final String select="select * from bike where lend=0";//查找没有被借出的单车
	private static final String select1="select * from bike where fixtime>=3";//查找被报修的单车
	//private static final String select2="select * from bike where lend=0 and bno=?";//查找没有被借出的单车
	
	private static final String update="update bike set fixtime=fixtime+1 where bno=?";//报修单车
	private static final String update2="update bike set fixtime=0 where bno=?";//单车修好
	private static final String update3="update bike set lend=1 where bno=?";//单车借出
	private static final String update4="update bike set lend=0,place=? where bno=?";//单车归还
	
	public int create(bike bike){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		int a=0;
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(insert);
			prepStmt.setInt(1, bike.getBno());
			prepStmt.setString(2, bike.getPlace());
			prepStmt.setInt(3, bike.getLend());
			prepStmt.setInt(4, bike.getFixtime());
			a=prepStmt.executeUpdate();
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
	
	public List<bike> selNotLend(){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		List<bike> bike=new ArrayList<bike>();
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(select);
			rs=prepStmt.executeQuery();
			while(rs.next()){
				bike u=new bike();
				u.setplace(rs.getString(2));
				bike.add(u);
			}
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return bike;
	}
	
	public List<bike> selNeedFix(){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		List<bike> bike=new ArrayList<bike>();
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(select1);
			rs=prepStmt.executeQuery();
			while(rs.next()){
				bike u=new bike();
				u.setplace(rs.getString(2));
				bike.add(u);
			}
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return bike;
	}
	//保修单车
	public int updateNeedFix(int bno){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		int a=0;
		try{
			con=DbConner.getDBconnection();
			System.out.println(bno);
			prepStmt=con.prepareStatement(update);
			prepStmt.setInt(1, bno);
			a=prepStmt.executeUpdate();
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
	//单车借出
	public int updateBikeLend(int bno){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		int a=0;
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(update3);
			prepStmt.setInt(1, bno);
			a=prepStmt.executeUpdate();
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
	
	
	
	//单车归还
	public int updateBikeReturn(bike bike){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		int a=0;
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(update4);
			prepStmt.setString(1, bike.getPlace());
			prepStmt.setInt(2, bike.getBno());
			a=prepStmt.executeUpdate();
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
	
	//单车修好
	public int updateFixFinish(int bno){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		int a=0;
		try{
			con=DbConner.getDBconnection();
			prepStmt=con.prepareStatement(update2);
			prepStmt.setInt(1, bno);
			a=prepStmt.executeUpdate();
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return a;
	}
	
	
	
	public static int createBno(){
		Connection con=null;
		PreparedStatement prepStmt=null;
		ResultSet rs=null;
		int bno=1;
		try{
			while(true){
				int check=bno;
				con=DbConner.getDBconnection();
				prepStmt=con.prepareStatement("select bno from bike where bno=?");
				prepStmt.setInt(1, bno);
				rs=prepStmt.executeQuery();
				while(rs.next()){
					bno++;
				}
				if(check==bno){
					break;
				}
			}
			
		}catch(Exception e){
			
		}finally{
			DbConner.closeDB(con, prepStmt, rs);
		}
		return bno;
	}
}
