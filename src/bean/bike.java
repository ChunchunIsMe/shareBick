package bean;
import dao.bikeDao;
public class bike {
	private int bno=bikeDao.createBno();
	private String place="";
	private int lend=0;
	private int fixtime=0;
	public int getBno(){
		return bno;
	}
	public String getPlace(){
		return place;
	}
	public int getLend(){
		return lend;
	}
	public int getFixtime(){
		return fixtime;
	}
	public void setBno(int bno){
		this.bno=bno;
	}
	public void setLend(int lend){
		this.lend=lend;
	}
	public void setFixtime(int fixtime){
		this.fixtime=fixtime;
	}
	public void setplace(String place){
		this.place=place;
	}
}
