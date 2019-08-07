package com.pinkpaw.board.freeboard.model.vo;

import java.sql.Date;

public class FreeBoard {

	private int freeNo;
	private String freeTitle;
	private String freeWriter;
	private String freeContent;
	private String freeOriginalImg;
	private String freeRenamedImg;
	private Date freeEnrolldate;
	private int freeCount;
	private int freeLike;
	private int freeReportCount;
	private String freeReportReason;
		
	public FreeBoard() {
	}

	public FreeBoard(int freeNo, String freeTitle, String freeWriter, String freeContent, String freeOriginalImg,
			String freeRenamedImg, Date freeEnrolldate, int freeCount, int freeLike, int freeReportCount,
			String freeReportReason) {
		super();
		this.freeNo = freeNo;
		this.freeTitle = freeTitle;
		this.freeWriter = freeWriter;
		this.freeContent = freeContent;
		this.freeOriginalImg = freeOriginalImg;
		this.freeRenamedImg = freeRenamedImg;
		this.freeEnrolldate = freeEnrolldate;
		this.freeCount = freeCount;
		this.freeLike = freeLike;
		this.freeReportCount = freeReportCount;
		this.freeReportReason = freeReportReason;
	}

	public int getFreeNo() {
		return freeNo;
	}

	public void setFreeNo(int freeNo) {
		this.freeNo = freeNo;
	}

	public String getFreeTitle() {
		return freeTitle;
	}

	public void setFreeTitle(String freeTitle) {
		this.freeTitle = freeTitle;
	}

	public String getFreeWriter() {
		return freeWriter;
	}

	public void setFreeWriter(String freeWriter) {
		this.freeWriter = freeWriter;
	}

	public String getFreeContent() {
		return freeContent;
	}

	public void setFreeContent(String freeContent) {
		this.freeContent = freeContent;
	}

	public String getFreeOriginalImg() {
		return freeOriginalImg;
	}

	public void setFreeOriginalImg(String freeOriginalImg) {
		this.freeOriginalImg = freeOriginalImg;
	}

	public String getFreeRenamedImg() {
		return freeRenamedImg;
	}

	public void setFreeRenamedImg(String freeRenamedImg) {
		this.freeRenamedImg = freeRenamedImg;
	}

	public Date getFreeEnrolldate() {
		return freeEnrolldate;
	}

	public void setFreeEnrolldate(Date freeEnrolldate) {
		this.freeEnrolldate = freeEnrolldate;
	}

	public int getFreeCount() {
		return freeCount;
	}

	public void setFreeCount(int freeCount) {
		this.freeCount = freeCount;
	}

	public int getFreeLike() {
		return freeLike;
	}

	public void setFreeLike(int freeLike) {
		this.freeLike = freeLike;
	}

	public int getFreeReportCount() {
		return freeReportCount;
	}

	public void setFreeReportCount(int freeReportCount) {
		this.freeReportCount = freeReportCount;
	}

	public String getFreeReportReason() {
		return freeReportReason;
	}

	public void setFreeReportReason(String freeReportReason) {
		this.freeReportReason = freeReportReason;
	}

	@Override
	public String toString() {
		return "FreeBoard [freeNo=" + freeNo + ", freeTitle=" + freeTitle + ", freeWriter=" + freeWriter
				+ ", freeContent=" + freeContent + ", freeOriginalImg=" + freeOriginalImg + ", freeRenamedImg="
				+ freeRenamedImg + ", freeEnrolldate=" + freeEnrolldate + ", freeCount=" + freeCount + ", freeLike="
				+ freeLike + ", freeReportCount=" + freeReportCount + ", freeReportReason=" + freeReportReason + "]";
	}

	
	
}