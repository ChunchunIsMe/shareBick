package bean;

public class worker {
	private String phone;
	private String password;
	private String name;
	private String id;
	public void setPhone(String phone){
		this.phone=phone;
	}
	public void setPassword(String password){
		this.password=password;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setId(String id){
		this.id=id;
	}
	
	public String getPhone(){
		return phone;
	}
	public String getPassword(){
		return password;
	}
	public String getName(){
		return name;
	}
	public String getId(){
		return id;
	}
}
