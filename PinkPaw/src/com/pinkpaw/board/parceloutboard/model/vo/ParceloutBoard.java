package com.pinkpaw.board.parceloutboard.model.vo;

import java.sql.Date;

public class ParceloutBoard {
	
	private int parceloutNo;
	private String parceloutTitle;
	private String parceloutWriter;
	private String parceloutPlace;
	private int parceloutMoney;
	private String parceloutPhone;
	private String parceloutKind;
	private String parceloutGender;
	private String parceloutContent;
	private String parceloutOriginalImg;
	private String parceloutRenamedImg;
	private Date parceloutEnrolldate;
	private int parceloutCount;
	private int parceloutLike;
	private int parceloutReportCount;
	private String parceloutReportReason;
			
	public ParceloutBoard() {
		super();
	}

	public ParceloutBoard(int parceloutNo, String parceloutTitle, String parceloutWriter, String parceloutPlace,
			int parceloutMoney, String parceloutPhone, String parceloutKind, String parceloutGender,
			String parceloutContent, String parceloutOriginalImg, String parceloutRenamedImg, Date parceloutEnrolldate,
			int parceloutCount, int parceloutLike, int parceloutReportCount, String parceloutReportReason) {
		super();
		this.parceloutNo = parceloutNo;
		this.parceloutTitle = parceloutTitle;
		this.parceloutWriter = parceloutWriter;
		this.parceloutPlace = parceloutPlace;
		this.parceloutMoney = parceloutMoney;
		this.parceloutPhone = parceloutPhone;
		this.parceloutKind = parceloutKind;
		this.parceloutGender = parceloutGender;
		this.parceloutContent = parceloutContent;
		this.parceloutOriginalImg = parceloutOriginalImg;
		this.parceloutRenamedImg = parceloutRenamedImg;
		this.parceloutEnrolldate = parceloutEnrolldate;
		this.parceloutCount = parceloutCount;
		this.parceloutLike = parceloutLike;
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

	public String getParceloutPhone() {
		return parceloutPhone;
	}

	public void setParceloutPhone(String parceloutPhone) {
		this.parceloutPhone = parceloutPhone;
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

	public int getParceloutLike() {
		return parceloutLike;
	}

	public void setParceloutLike(int parceloutLike) {
		this.parceloutLike = parceloutLike;
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

	@Override
	public String toString() {
		return "ParceloutBoard [parceloutNo=" + parceloutNo + ", parceloutTitle=" + parceloutTitle
				+ ", parceloutWriter=" + parceloutWriter + ", parceloutPlace=" + parceloutPlace + ", parceloutMoney="
				+ parceloutMoney + ", parceloutPhone=" + parceloutPhone + ", parceloutKind=" + parceloutKind
				+ ", parceloutGender=" + parceloutGender + ", parceloutContent=" + parceloutContent
				+ ", parceloutOriginalImg=" + parceloutOriginalImg + ", parceloutRenamedImg=" + parceloutRenamedImg
				+ ", parceloutEnrolldate=" + parceloutEnrolldate + ", parceloutCount=" + parceloutCount
				+ ", parceloutLike=" + parceloutLike + ", parceloutReportCount=" + parceloutReportCount
				+ ", parceloutReportReason=" + parceloutReportReason + "]";
	}
		
	

}