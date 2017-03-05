package oncall.model;

public class Department {

	private int sequence;
	private String name;
	private String description;
	private boolean active;
	
	public Department() {
		
	}
	
	public int getSequence() {
		return this.sequence;
	}
	
	public void setSequence(int value) {
		this.sequence = value;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String value) {
		this.description = value;
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	public void setActive(boolean value) {
		this.active = value;
	}
	
	@Override
	public String toString() {
		return "Department [seq: " + this.sequence + " name: " + this.name + " description: " + this.description + " active: " + this.active + "]";
	}
}
