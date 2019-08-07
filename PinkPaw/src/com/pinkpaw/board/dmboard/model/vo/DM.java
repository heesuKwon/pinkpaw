package com.pinkpaw.board.dmboard.model.vo;

import java.sql.Date;

public class DM {
	
	private int dmNo;
	private String dmSend;
	private String dmRecive;
	private String dmContent;
	private Date dmDate;
	private int dmReportCount;
	private String dmReportReason;
	
	public DM() {
	}

	public DM(int dmNo, String dmSend, String dmRecive, String dmContent, Date dmDate, int dmReportCount,
			String dmReportReason) {
		super();
		this.dmNo = dmNo;
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
		return "dm [dmNo=" + dmNo + ", dmSend=" + dmSend + ", dmRecive=" + dmRecive + ", dmContent=" + dmContent
				+ ", dmDate=" + dmDate + ", dmReportCount=" + dmReportCount + ", dmReportReason=" + dmReportReason
				+ "]";
	}

		
	
}
