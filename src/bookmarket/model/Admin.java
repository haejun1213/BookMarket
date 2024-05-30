package bookmarket.model;

public class Admin extends User {

	private String id = "admin";
	private String password= "!@!#";
	
	public boolean login(String id, String password) {
		if(this.id.equals(id) && this.password.equals(password))
			return true;
		return false;
	}
	
}