package company;

import java.io.Serializable;

public class Intern implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int intern_id;
	protected String intern_role;
	protected String intern_description;
	protected String intern_type;
	protected String intern_duration;
	protected String intern_stipend;
	protected String intern_location;
	protected String company_name;


	protected int company_cid1;

	public Intern() {
	}

	public Intern(int intern_id, String intern_role, String intern_description, String intern_type,
			String intern_duration, String intern_stipend, String intern_location, int company_cid1,String company_name) {
		super();
		this.intern_id = intern_id;
		this.intern_role = intern_role;
		this.intern_description = intern_description;
		this.intern_type = intern_type;
		this.intern_duration = intern_duration;
		this.intern_stipend = intern_stipend;
		this.intern_location = intern_location;
		this.company_name = company_name;
		this.company_cid1 = company_cid1;
	}

	public Intern(int intern_id, String intern_role, String intern_description, String intern_type,
			String intern_duration, String intern_stipend, String intern_location,String company_name) {
		super();
		this.intern_id = intern_id;
		this.intern_role = intern_role;
		this.intern_description = intern_description;
		this.intern_type = intern_type;
		this.intern_duration = intern_duration;
		this.intern_stipend = intern_stipend;
		this.intern_location = intern_location;
		this.company_name = company_name;

	}

	public String getintern_stipend() {
		return intern_stipend;
	}

	public String getintern_role() {
		return intern_role;
	}

	public String getintern_description() {
		return intern_description;
	}

	public int getintern_id() {
		return intern_id;
	}

	public String getintern_location() {
		return intern_location;
	}

	public int getcompany_cid1() {
		return company_cid1;
	}

	public String getintern_type() {
		return intern_type;
	}
	
	public String getcompany_name() {
		return company_name;
	}

	public String getintern_duration() {
		return intern_duration;
	}

	public void setintern_stipend(String intern_stipend) {
		this.intern_stipend = intern_stipend;
	}

	public void setintern_role(String intern_role) {
		this.intern_role = intern_role;
	}

	public void setintern_description(String intern_description) {
		this.intern_description = intern_description;
	}

	public void setintern_id(int intern_id) {
		this.intern_id = intern_id;
	}

	public void setcompany_cid1(int company_cid1) {
		this.company_cid1 = company_cid1;
	}

	public void setintern_type(String intern_type) {
		this.intern_type = intern_type;
	}

	public void setcompany_name(String company_name) {
		this.company_name = company_name;
	}
	
	public void setintern_duration(String intern_duration) {
		this.intern_duration = intern_duration;
	}

	public void setintern_location(String intern_location) {
		this.intern_location = intern_location;
	}

}