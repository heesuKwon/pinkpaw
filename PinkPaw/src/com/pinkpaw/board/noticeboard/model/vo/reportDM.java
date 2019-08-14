package com.pinkpaw.board.noticeboard.model.vo;

import java.sql.Date;

public class reportDM {
	
	private int dmNo;
	private String dmTitle;
	private String dmSend;
	private String dmRecive;
	private String dmContent;
	private Date dmDate;
	private int dmReportCount;
	private String dmReportReason;
	
	
	
	
	
	public reportDM() {
		super();
		// TODO Auto-generated constructor stub
	}



	public reportDM(int dmNo, String dmTitle, String dmSend, String dmRecive, String dmContent, Date dmDate,
			int dmReportCount, String dmReportReason) {
		super();
		this.dmNo = dmNo;
		this.dmTitle = dmTitle;
		this.dmSend = dmSend;
		this.dmRecive = dmRecive;
		this.dmContent = dmContent;
		this.dmDate = dmDate;
		this.dmReportCount = dmReportCount;
		this.dmReportReason = dmReportReason;
	}





	public int getDmNo() {
		return dmNo;
	}





	public void setDmNo(int dmNo) {
		this.dmNo = dmNo;
	}





	public String getDmTitle() {
		return dmTitle;
	}





	public void setDmTitle(String dmTitle) {
		this.dmTitle = dmTitle;
	}





	public String getDmSend() {
		return dmSend;
	}





	public void setDmSend(String dmSend) {
		this.dmSend = dmSend;
	}





	public String getDmRecive() {
		return dmRecive;
	}





	public void setDmRecive(String dmRecive) {
		this.dmRecive = dmRecive;
	}





	public String getDmContent() {
		return dmContent;
	}





	public void setDmContent(String dmContent) {
		this.dmContent = dmContent;
	}





	public Date getDmDate() {
		return dmDate;
	}





	public void setDmDate(Date dmDate) {
		this.dmDate = dmDate;
	}





	public int getDmReportCount() {
		return dmReportCount;
	}





	public void setDmReportCount(int dmReportCount) {
		this.dmReportCount = dmReportCount;
	}





	public String getDmReportReason() {
		return dmReportReason;
	}





	public void setDmReportReason(String dmReportReason) {
		this.dmReportReason = dmReportReason;
	}





	@Override
	public String toString() {
		return "reportDM [dmNo=" + dmNo + ", dmTitle=" + dmTitle + ", dmSend=" + dmSend + ", dmRecive=" + dmRecive
				+ ", dmContent=" + dmContent + ", dmDate=" + dmDate + ", dmReportCount=" + dmReportCount
				+ ", dmReportReason=" + dmReportReason + "]";
	}
	
	
	
	
	
	
	
	
	
}
