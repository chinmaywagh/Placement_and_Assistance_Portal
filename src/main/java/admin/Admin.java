package admin;

import java.io.Serializable;

public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;

	protected int aid;
	protected String a_name;
	protected String a_uname;
	protected String a_pass;

	protected String a_email;

	public Admin() {
	}

	public Admin(int aid, String a_name, String a_uname, String a_pass, String a_email) {
		super();
		this.aid = aid;
		this.a_name = a_name;
		this.a_uname = a_uname;
		this.a_pass = a_pass;
		this.a_email = a_email;

	}

	public Admin(String a_name, String a_email, String a_uname, String a_pass) {
		super();
		this.a_name = a_name;
		this.a_email = a_email;
		this.a_uname = a_uname;
		this.a_pass = a_pass;
	}

	public String geta_email() {
		return a_email;
	}

	public String geta_name() {
		return a_name;
	}

	public String geta_pass() {
		return a_pass;
	}

	public String geta_uname() {
		return a_uname;
	}

	public int getaid() {
		return aid;
	}

	public void seta_email(String a_email) {
		this.a_email = a_email;
	}

	public void seta_name(String a_name) {
		this.a_name = a_name;
	}

	public void seta_pass(String a_pass) {
		this.a_pass = a_pass;
	}

	public void seta_uname(String a_uname) {
		this.a_uname = a_uname;
	}

	public void setaid(int aid) {
		this.aid = aid;
	}

}