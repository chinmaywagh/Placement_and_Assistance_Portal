package user;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	protected int uid;
	protected String u_name;
	protected String u_uname;
	protected String u_pass;
	protected String u_dob;
	protected String u_mobno;
	protected String u_gender;
	protected String u_address;
	protected String u_status;
	protected String u_email;

	public User() {
	}

	public User(int uid, String u_name, String u_uname, String u_gender, String u_dob, String u_pass, String u_email,
			String u_mobno, String u_address, String u_status) {
		super();
		this.uid = uid;
		this.u_name = u_name;
		this.u_uname = u_uname;
		this.u_pass = u_pass;
		this.u_dob = u_dob;
		this.u_email = u_email;
		this.u_mobno = u_mobno;
		this.u_gender = u_gender;
		this.u_address = u_address;
		this.u_status = u_status;
	}

	public User(int uid, String u_name, String u_email, String u_mobno, String u_status) {
		super();
		this.uid = uid;
		this.u_name = u_name;
		this.u_email = u_email;
		this.u_mobno = u_mobno;
		this.u_status = u_status;
	}
	public User(int uid) {
		super();
		this.uid = uid;
		
	}

	public User(String u_name, String u_uname, String u_gender, String u_dob, String u_pass, String u_email,
			String u_mobno, String u_address, String u_status) {
		super();

		this.u_name = u_name;
		this.u_uname = u_uname;
		this.u_pass = u_pass;
		this.u_dob = u_dob;
		this.u_email = u_email;
		this.u_mobno = u_mobno;
		this.u_gender = u_gender;
		this.u_address = u_address;
		this.u_status = u_status;

	}

	public String getu_address() {
		return u_address;
	}

	public String getu_dob() {
		return u_dob;
	}

	public String getu_email() {
		return u_email;
	}

	public String getu_gender() {
		return u_gender;
	}

	public String getu_mobno() {
		return u_mobno;
	}

	public String getu_name() {
		return u_name;
	}

	public String getu_pass() {
		return u_pass;
	}

	public String getu_status() {
		return u_status;
	}

	public String getu_uname() {
		return u_name;
	}

	public int getuid() {
		return uid;
	}

	public void setu_address(String u_address) {
		this.u_address = u_address;
	}

	public void setu_dob(String u_dob) {
		this.u_dob = u_dob;
	}

	public void setu_email(String u_email) {
		this.u_email = u_email;
	}

	public void setu_gender(String u_gender) {
		this.u_gender = u_gender;
	}

	public void setu_mobno(String u_mobno) {
		this.u_mobno = u_mobno;
	}

	public void setu_name(String u_name) {
		this.u_name = u_name;
	}

	public void setu_pass(String u_pass) {
		this.u_pass = u_pass;
	}

	public void setu_status(String u_status) {
		this.u_status = u_status;
	}

	public void setu_uname(String u_uname) {
		this.u_uname = u_uname;
	}

	public void setuid(int uid) {
		this.uid = uid;
	}

}