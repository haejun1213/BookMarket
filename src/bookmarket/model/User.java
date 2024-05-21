package bookmarket.model;

public abstract class User {

	private String name;
	private String phone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String string) {
		this.phone = string;
	}
	
}


class Adim extends User {
	
	private String id;
	private String password;
}