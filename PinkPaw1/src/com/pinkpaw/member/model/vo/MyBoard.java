package com.pinkpaw.member.model.vo;

import java.sql.Date;

public class MyBoard {
	
	private String myTable;
	private String myTitle;
	private int myNo;
	private Date myDate;
	
	
	public MyBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MyBoard(String myTable, String myTitle, int myNo, Date myDate) {
		super();
		this.myTable = myTable;
		this.myTitle = myTitle;
		this.myNo = myNo;
		this.myDate = myDate;
	}
	
	public String getMyTable() {
		return myTable;
	}
	public void setMyTable(String myTable) {
		this.myTable = myTable;
	}
	public String getMyTitle() {
		return myTitle;
	}
	public void setMyTitle(String myTitle) {
		this.myTitle = myTitle;
	}
	public int getMyNo() {
		return myNo;
	}
	public void setMyNo(int myNo) {
		this.myNo = myNo;
	}
	public Date getMyDate() {
		return myDate;
	}
	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}
	@Override
	public String toString() {
		return "MyBoard [myTable=" + myTable + ", myTitle=" + myTitle + ", myNo=" + myNo + ", myDate=" + myDate + "]";
	}
	

	

}
