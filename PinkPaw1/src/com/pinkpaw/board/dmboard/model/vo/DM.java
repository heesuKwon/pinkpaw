package com.pinkpaw.board.dmboard.model.vo;

import java.sql.Date;

public class DM {
	
	private int dmNo;
	private String dmSend;
	private String dmRecive;
	private String dmTitle;
	private String dmContent;
	private Date dmDate;
	private int dmRecvRead;
	private int recvDel;
	private int sentDel;
	private int dmReportCount;
	private String dmReportWriter;
	private String dmReportReason;
	
	public DM() {
	}

	public DM(int dmNo, String dmSend, String dmRecive, String dmTitle, String dmContent, Date dmDate, int dmRecvRead,
			int recvDel, int sentDel, int dmReportCount, String dmReportWriter, String dmReportReason) {
		super();
		this.dmNo = dmNo;
		this.dmSend = dmSend;
		this.dmRecive = dmRecive;
		this.dmTitle = dmTitle;
		this.dmContent = dmContent;
		this.dmDate = dmDate;
		this.dmRecvRead = dmRecvRead;
		this.recvDel = recvDel;
		this.sentDel = sentDel;
		this.dmReportCount = dmReportCount;
		this.dmReportWriter = dmReportWriter;
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

	public String getDmTitle() {
		return dmTitle;
	}

	public void setDmTitle(String dmTitle) {
		this.dmTitle = dmTitle;
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

	public int getDmRecvRead() {
		return dmRecvRead;
	}

	public void setDmRecvRead(int dmRecvRead) {
		this.dmRecvRead = dmRecvRead;
	}

	public int getRecvDel() {
		return recvDel;
	}

	public void setRecvDel(int recvDel) {
		this.recvDel = recvDel;
	}

	public int getSentDel() {
		return sentDel;
	}

	public void setSentDel(int sentDel) {
		this.sentDel = sentDel;
	}

	public int getDmReportCount() {
		return dmReportCount;
	}

	public void setDmReportCount(int dmReportCount) {
		this.dmReportCount = dmReportCount;
	}

	public String getDmReportWriter() {
		return dmReportWriter;
	}

	public void setDmReportWriter(String dmReportWriter) {
		this.dmReportWriter = dmReportWriter;
	}

	public String getDmReportReason() {
		return dmReportReason;
	}

	public void setDmReportReason(String dmReportReason) {
		this.dmReportReason = dmReportReason;
	}

	@Override
	public String toString() {
		return "DM [dmNo=" + dmNo + ", dmSend=" + dmSend + ", dmRecive=" + dmRecive + ", dmTitle=" + dmTitle
				+ ", dmContent=" + dmContent + ", dmDate=" + dmDate + ", dmRecvRead=" + dmRecvRead + ", recvDel="
				+ recvDel + ", sentDel=" + sentDel + ", dmReportCount=" + dmReportCount + ", dmReportWriter="
				+ dmReportWriter + ", dmReportReason=" + dmReportReason + "]";
	}

	
	
}
