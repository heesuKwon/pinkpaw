package com.pinkpaw.board.missingboard.model.vo;

import java.sql.Date;

public class MissingBoard {
	private int missingNo;
	private String missingTitle;
	private String missingWriter;
	private String missingHpPlace;
	private Date missingHpDate;
	private int missingMoney;
	private String missingPhone;
	private String missingKind;
	private String missingContent;
	private String missingOriginalImg;
	private String missingRenamedImg;
	private Date missingEnrollDate;
	private int missingCount;
	private int missingLike;
	private int missingReportCount;
	private String missingReportReason;
	
	public MissingBoard() {
		super();
	}

	public MissingBoard(int missingNo, String missingTitle, String missingWriter, String missingHpPlace,
			Date missingHpDate, int missingMoney, String missingPhone, String missingKind, String missingContent,
			String missingOriginalImg, String missingRenamedImg, Date missingEnrollDate, int missingCount,
			int missingLike, int missingReportCount, String missingReportReason) {
		super();
		this.missingNo = missingNo;
		this.missingTitle = missingTitle;
		this.missingWriter = missingWriter;
		this.missingHpPlace = missingHpPlace;
		this.missingHpDate = missingHpDate;
		this.missingMoney = missingMoney;
		this.missingPhone = missingPhone;
		this.missingKind = missingKind;
		this.missingContent = missingContent;
		this.missingOriginalImg = missingOriginalImg;
		this.missingRenamedImg = missingRenamedImg;
		this.missingEnrollDate = missingEnrollDate;
		this.missingCount = missingCount;
		this.missingLike = missingLike;
		this.missingReportCount = missingReportCount;
		this.missingReportReason = missingReportReason;
	}

	public int getMissingNo() {
		return missingNo;
	}

	public void setMissingNo(int missingNo) {
		this.missingNo = missingNo;
	}

	public String getMissingTitle() {
		return missingTitle;
	}

	public void setMissingTitle(String missingTitle) {
		this.missingTitle = missingTitle;
	}

	public String getMissingWriter() {
		return missingWriter;
	}

	public void setMissingWriter(String missingWriter) {
		this.missingWriter = missingWriter;
	}

	public String getMissingHpPlace() {
		return missingHpPlace;
	}

	public void setMissingHpPlace(String missingHpPlace) {
		this.missingHpPlace = missingHpPlace;
	}

	public Date getMissingHpDate() {
		return missingHpDate;
	}

	public void setMissingHpDate(Date missingHpDate) {
		this.missingHpDate = missingHpDate;
	}

	public int getMissingMoney() {
		return missingMoney;
	}

	public void setMissingMoney(int missingMoney) {
		this.missingMoney = missingMoney;
	}

	public String getMissingPhone() {
		return missingPhone;
	}

	public void setMissingPhone(String missingPhone) {
		this.missingPhone = missingPhone;
	}

	public String getMissingKind() {
		return missingKind;
	}

	public void setMissingKind(String missingKind) {
		this.missingKind = missingKind;
	}

	public String getMissingContent() {
		return missingContent;
	}

	public void setMissingContent(String missingContent) {
		this.missingContent = missingContent;
	}

	public String getMissingOriginalImg() {
		return missingOriginalImg;
	}

	public void setMissingOriginalImg(String missingOriginalImg) {
		this.missingOriginalImg = missingOriginalImg;
	}

	public String getMissingRenamedImg() {
		return missingRenamedImg;
	}

	public void setMissingRenamedImg(String missingRenamedImg) {
		this.missingRenamedImg = missingRenamedImg;
	}

	public Date getMissingEnrollDate() {
		return missingEnrollDate;
	}

	public void setMissingEnrollDate(Date missingEnrollDate) {
		this.missingEnrollDate = missingEnrollDate;
	}

	public int getMissingCount() {
		return missingCount;
	}

	public void setMissingCount(int missingCount) {
		this.missingCount = missingCount;
	}

	public int getMissingLike() {
		return missingLike;
	}

	public void setMissingLike(int missingLike) {
		this.missingLike = missingLike;
	}

	public int getMissingReportCount() {
		return missingReportCount;
	}

	public void setMissingReportCount(int missingReportCount) {
		this.missingReportCount = missingReportCount;
	}

	public String getMissingReportReason() {
		return missingReportReason;
	}

	public void setMissingReportReason(String missingReportReason) {
		this.missingReportReason = missingReportReason;
	}

	@Override
	public String toString() {
		return "MissingBoard [missingNo=" + missingNo + ", missingTitle=" + missingTitle + ", missingWriter="
				+ missingWriter + ", missingHpPlace=" + missingHpPlace + ", missingHpDate=" + missingHpDate
				+ ", missingMoney=" + missingMoney + ", missingPhone=" + missingPhone + ", missingKind=" + missingKind
				+ ", missingContent=" + missingContent + ", missingOriginalImg=" + missingOriginalImg
				+ ", missingRenamedImg=" + missingRenamedImg + ", missingEnrollDate=" + missingEnrollDate
				+ ", missingCount=" + missingCount + ", missingLike=" + missingLike + ", missingReportCount="
				+ missingReportCount + ", missingReportReason=" + missingReportReason + "]";
	}

	
}
