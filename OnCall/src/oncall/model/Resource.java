package oncall.model;

public class Resource {

	private int seq;
	private String firstname;
	private String lastname;
	private String contactnumber;
	private String email;
	private boolean active;
	private String[] departments;
	
	public Resource() {
		
	}
	
	public int getSequence() {
		return this.seq;
	}
	
	public void setSequence(int value) {
		this.seq = value;
	}
	
	public String getFirstName() {
		return this.firstname;
	}
	
	public void setFirstName(String value) {
		this.firstname = value;
	}
	
	public String getLastName() {
		return this.lastname;
	}
	
	public void setLastName(String value) {
		this.lastname = value;
	}

	public String getContactNumber() {
		return this.contactnumber;
	}
	
	public void setContactNumber(String value) {
		this.contactnumber = value;
	}

	public String getEmail() {
		return this.email;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}

	public boolean getActive() {
		return this.active;
	}
	
	public void setActive(boolean value) {
		this.active = value;
	}

	public String[] getDepartments() {
		return this.departments;
	}
	
	public void setDepartments(String[] value) {
		this.departments = value;
	}
}

