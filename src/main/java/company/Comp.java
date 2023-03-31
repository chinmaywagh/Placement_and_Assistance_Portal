package company;

import java.io.Serializable;

public class Comp implements Serializable {

	private static final long serialVersionUID = 1L;

	protected int cid;
	protected String c_name;
	protected String c_address;
	protected String c_uname;
	protected String c_pass;
	protected String c_mobno;
	protected String c_email;

	public Comp() {
	}

	public Comp(int cid, String c_name, String c_email, String c_address, String c_mobno) {
		super();
		this.cid = cid;
		this.c_name = c_name;
		this.c_email = c_email;
		this.c_address = c_address;
		this.c_mobno = c_mobno;
	}

	public Comp(String c_name, String c_email, String c_address, String c_uname, String c_pass, String c_mobno) {
		super();
		this.c_name = c_name;
		this.c_uname = c_uname;
		this.c_email = c_email;
		this.c_pass = c_pass;
		this.c_address = c_address;
		this.c_mobno = c_mobno;
	}
	public Comp(int cid, String c_name, String c_email, String c_address, String c_uname, String c_mobno) {
		super();
		this.cid = cid;
		this.c_name = c_name;
		this.c_uname = c_uname;
		this.c_email = c_email;
		this.c_address = c_address;
		this.c_mobno = c_mobno;
	}

	public String getc_address() {
		return c_address;
	}

	public String getc_email() {
		return c_email;
	}

	public String getc_mobno() {
		return c_mobno;
	}

	public String getc_name() {
		return c_name;
	}

	public String getc_pass() {
		return c_pass;
	}

	public String getc_uname() {
		return c_uname;
	}

	public int getcid() {
		return cid;
	}

	public void setc_address(String c_address) {
		this.c_address = c_address;
	}

	public void setc_email(String c_email) {
		this.c_email = c_email;
	}

	public void setc_mobno(String c_mobno) {
		this.c_mobno = c_mobno;
	}

	public void setc_name(String c_name) {
		this.c_name = c_name;
	}

	public void setc_pass(String c_pass) {
		this.c_pass = c_pass;
	}

	public void setc_uname(String c_uname) {
		this.c_uname = c_uname;
	}

	public void setcid(int cid) {
		this.cid = cid;
	}
}