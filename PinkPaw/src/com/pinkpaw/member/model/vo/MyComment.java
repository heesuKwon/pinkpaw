package com.pinkpaw.member.model.vo;

import java.sql.Date;

public class MyComment {
	private String myCoTable;
	private int myCoREF;
	private String myCoContent;
	private Date myCoDate;
	
	
	public MyComment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyComment(String myCoTable, int myCoREF, String myCoContent, Date myCoDate) {
		super();
		this.myCoTable = myCoTable;
		this.myCoREF = myCoREF;
		this.myCoContent = myCoContent;
		this.myCoDate = myCoDate;
	}
	public String getMyCoTable() {
		return myCoTable;
	}
	public void setMyCoTable(String myCoTable) {
		this.myCoTable = myCoTable;
	}
	public int getMyCoREF() {
		return myCoREF;
	}
	public void setMyCoREF(int myCoREF) {
		this.myCoREF = myCoREF;
	}
	public String getMyCoContent() {
		return myCoContent;
	}
	public void setMyCoContent(String myCoContent) {
		this.myCoContent = myCoContent;
	}
	public Date getMyCoDate() {
		return myCoDate;
	}
	public void setMyCoDate(Date myCoDate) {
		this.myCoDate = myCoDate;
	}
	@Override
	public String toString() {
		return "MyComment [myCoTable=" + myCoTable + ", myCoREF=" + myCoREF + ", myCoContent=" + myCoContent
				+ ", myCoDate=" + myCoDate + "]";
	}
	
	
	
}
