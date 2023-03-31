package company;

import java.io.Serializable;

public class Job implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int job_id;
	protected String job_role;
	protected String job_description;
	protected String job_type;
	protected String job_location;
	protected String job_xp;
	protected int company_cid1;

	public Job() {
	}

	public Job(int job_id, String job_role, String job_description, String job_type, String job_location, String job_xp,
			int company_cid1) {
		super();
		this.job_id = job_id;
		this.job_role = job_role;
		this.job_description = job_description;
		this.job_type = job_type;
		this.job_location = job_location;
		this.job_xp = job_xp;
		this.company_cid1 = company_cid1;
	}

	public Job(int job_id, String job_role, String job_description, String job_type, String job_location,
			String job_xp) {
		super();
		this.job_id = job_id;
		this.job_role = job_role;
		this.job_description = job_description;
		this.job_type = job_type;
		this.job_location = job_location;
		this.job_xp = job_xp;
	}

	public String getjob_xp() {
		return job_xp;
	}

	public String getjob_role() {
		return job_role;
	}

	public String getjob_description() {
		return job_description;
	}

	public int getjob_id() {
		return job_id;
	}

	public int getcompany_cid1() {
		return company_cid1;
	}

	public String getjob_type() {
		return job_type;
	}

	public String getjob_location() {
		return job_location;
	}

	public void setjob_xp(String job_xp) {
		this.job_xp = job_xp;
	}

	public void setjob_role(String job_role) {
		this.job_role = job_role;
	}

	public void setjob_description(String job_description) {
		this.job_description = job_description;
	}

	public void setjob_id(int job_id) {
		this.job_id = job_id;
	}

	public void setcompany_cid1(int company_cid1) {
		this.company_cid1 = company_cid1;
	}

	public void setjob_type(String job_type) {
		this.job_type = job_type;
	}

	public void setjob_location(String job_location) {
		this.job_location = job_location;
	}

}