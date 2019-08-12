package com.pinkpaw.board.parceloutboard.model.vo;

import java.sql.Date;

public class ParceloutBoard {
	
	private int parceloutNo;
	private String parceloutTitle;
	private String parceloutWriter;
	private String parceloutPlace;
	private int parceloutMoney;
	private String parceloutKind;
	private String parceloutGender;
	private String parceloutContent;
	private String parceloutOriginalImg;
	private String parceloutRenamedImg;
	private Date parceloutEnrolldate;
	private int parceloutCount;
	private int parceloutReportCount;
	private String parceloutReportReason;
			
	public ParceloutBoard() {
	}
	
	


	public ParceloutBoard(int parceloutNo, String parceloutTitle, String parceloutWriter, String parceloutPlace,
			int parceloutMoney, String parceloutKind, String parceloutGender, String parceloutContent,
			String parceloutOriginalImg, String parceloutRenamedImg, Date parceloutEnrolldate, int parceloutCount,
			int parceloutReportCount, String parceloutReportReason) {
		super();
		this.parceloutNo = parceloutNo;
		this.parceloutTitle = parceloutTitle;
		this.parceloutWriter = parceloutWriter;
		this.parceloutPlace = parceloutPlace;
		this.parceloutMoney = parceloutMoney;
		this.parceloutKind = parceloutKind;
		this.parceloutGender = parceloutGender;
		this.parceloutContent = parceloutContent;
		this.parceloutOriginalImg = parceloutOriginalImg;
		this.parceloutRenamedImg = parceloutRenamedImg;
		this.parceloutEnrolldate = parceloutEnrolldate;
		this.parceloutCount = parceloutCount;
		this.parceloutReportCount = parceloutReportCount;
		this.parceloutReportReason = parceloutReportReason;
	}




	public int getParceloutNo() {
		return parceloutNo;
	}
	public void setParceloutNo(int parceloutNo) {
		this.parceloutNo = parceloutNo;
	}
	public String getParceloutTitle() {
		return parceloutTitle;
	}
	public void setParceloutTitle(String parceloutTitle) {
		this.parceloutTitle = parceloutTitle;
	}
	public String getParceloutWriter() {
		return parceloutWriter;
	}
	public void setParceloutWriter(String parceloutWriter) {
		this.parceloutWriter = parceloutWriter;
	}
	public String getParceloutPlace() {
		return parceloutPlace;
	}
	public void setParceloutPlace(String parceloutPlace) {
		this.parceloutPlace = parceloutPlace;
	}
	public int getParceloutMoney() {
		return parceloutMoney;
	}
	public void setParceloutMoney(int parceloutMoney) {
		this.parceloutMoney = parceloutMoney;
	}
	public String getParceloutKind() {
		return parceloutKind;
	}
	public void setParceloutKind(String parceloutKind) {
		this.parceloutKind = parceloutKind;
	}
	public String getParceloutGender() {
		return parceloutGender;
	}
	public void setParceloutGender(String parceloutGender) {
		this.parceloutGender = parceloutGender;
	}
	public String getParceloutContent() {
		return parceloutContent;
	}
	public void setParceloutContent(String parceloutContent) {
		this.parceloutContent = parceloutContent;
	}
	public Date getParceloutEnrolldate() {
		return parceloutEnrolldate;
	}
	public void setParceloutEnrolldate(Date parceloutEnrolldate) {
		this.parceloutEnrolldate = parceloutEnrolldate;
	}
	public int getParceloutCount() {
		return parceloutCount;
	}
	public void setParceloutCount(int parceloutCount) {
		this.parceloutCount = parceloutCount;
	}
	public int getParceloutReportCount() {
		return parceloutReportCount;
	}
	public void setParceloutReportCount(int parceloutReportCount) {
		this.parceloutReportCount = parceloutReportCount;
	}
	public String getParceloutReportReason() {
		return parceloutReportReason;
	}
	public void setParceloutReportReason(String parceloutReportReason) {
		this.parceloutReportReason = parceloutReportReason;
	}

	public String getParceloutOriginalImg() {
		return parceloutOriginalImg;
	}

	public void setParceloutOriginalImg(String parceloutOriginalImg) {
		this.parceloutOriginalImg = parceloutOriginalImg;
	}

	public String getParceloutRenamedImg() {
		return parceloutRenamedImg;
	}

	public void setParceloutRenamedImg(String parceloutRenamedImg) {
		this.parceloutRenamedImg = parceloutRenamedImg;
	}




	@Override
	public String toString() {
		return "ParceloutBoard [parceloutNo=" + parceloutNo + ", parceloutTitle=" + parceloutTitle
				+ ", parceloutWriter=" + parceloutWriter + ", parceloutPlace=" + parceloutPlace + ", parceloutMoney="
				+ parceloutMoney + ", parceloutKind=" + parceloutKind + ", parceloutGender=" + parceloutGender
				+ ", parceloutContent=" + parceloutContent + ", parceloutOriginalImg=" + parceloutOriginalImg
				+ ", parceloutRenamedImg=" + parceloutRenamedImg + ", parceloutEnrolldate=" + parceloutEnrolldate
				+ ", parceloutCount=" + parceloutCount + ", parceloutReportCount=" + parceloutReportCount
				+ ", parceloutReportReason=" + parceloutReportReason + "]";
	}




	
	

}