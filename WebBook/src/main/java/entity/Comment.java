package entity;

import java.sql.Timestamp;

public class Comment {
	private int cmtid,fromuser, tobook,numstar;
	private String content,uname;
	private Timestamp time;
	
	public Comment() {
	}

	public Comment(int cmtid, int fromuser, int tobook, int numstar, String content, Timestamp time) {
		super();
		this.cmtid = cmtid;
		this.fromuser = fromuser;
		this.tobook = tobook;
		this.numstar = numstar;
		this.content = content;
		this.time = time;
	}

	public int getCmtid() {
		return cmtid;
	}

	public void setCmtid(int cmtid) {
		this.cmtid = cmtid;
	}

	public int getFromuser() {
		return fromuser;
	}

	public void setFromuser(int fromuser) {
		this.fromuser = fromuser;
	}

	public int getTobook() {
		return tobook;
	}

	public void setTobook(int tobook) {
		this.tobook = tobook;
	}

	public int getNumstar() {
		return numstar;
	}

	public void setNumstar(int numstar) {
		this.numstar = numstar;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUname() {
		return uname;
	}
	
	public void setUname(String uname) {
		this.uname = uname;
	}
	
	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Comment [cmtid=" + cmtid + ", fromuser=" + fromuser + ", tobook=" + tobook + ", numstar=" + numstar
				+ ", content=" + content + ", uname=" + uname + ", time=" + time + "]";
	}
	
	
	
}
