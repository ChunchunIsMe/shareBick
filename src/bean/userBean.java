package bean;
import java.text.SimpleDateFormat;
public class userBean {
	private String phone;
	private String password;
	private int money=0;
	private int bno=-1;
	private String datetime;
	public userBean(){
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long start = System.currentTimeMillis(); 
		this.datetime=f.format(start);
	}
	public void setPhone(String phone){
		this.phone=phone;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public void setMoney(int money){
		this.money=money;
	}
	public void setBno(int bno){
		this.bno=bno;
	}
	public void setDatetime(String datetime){
		this.datetime=datetime;
	}
	public String getPhone(){
		return phone;
	}
	public String getPassword(){
		return password;
	}
	public int getMoney(){
		return money;
	}
	public int getBno(){
		return bno;
	}
	public String getDatetime(){
		return datetime;
	}
}
