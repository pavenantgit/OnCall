package oncall.model;

import java.sql.Date;

public class Schedule {

	private int sequence;
	private Date startdate;
	private Date enddate;
	private int department;
	private int resource;
	private boolean active;
	
	public Schedule() {
		
	}
	
	public int getSequence() {
		return this.sequence;
	}
	
	public void setSequence(int value) {
		this.sequence = value;
	}

	public Date getStartDate() {
		return this.startdate;
	}
	
	public void setStartDate(Date value) {
		this.startdate = value;
	}

	public Date getEndDate() {
		return this.enddate;
	}
	
	public void setEndDate(Date value) {
		this.enddate = value;
	}

	public int getDepartment() {
		return this.department;
	}
	
	public void setDepartment(int value) {
		this.department = value;
	}

	public int getResource() {
		return this.resource;
	}
	
	public void setResource(int value) {
		this.resource = value;
	}

	public boolean getActive() {
		return this.active;
	}
	
	public void setActive(boolean value) {
		this.active = value;
	}
}
