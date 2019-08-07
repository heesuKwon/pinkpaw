package com.pinkpaw.board.shelter.model.vo;

import java.sql.Date;

public class VolunteerBoard {
	private int volunteerNo;
    private String volunteerTitle;
    private String volunteerWriter;
    private String volunteerContent;
    private String volunteerOriginalImg;
    private String volunteerRenamedImg;
    private Date volunteerEnrolldate;
    private int volunteerCount;
    
    public VolunteerBoard() {
    }

	public VolunteerBoard(int volunteerNo, String volunteerTitle, String volunteerWriter, String volunteerContent,
			String volunteerOriginalImg, String volunteerRenamedImg, Date volunteerEnrolldate, int volunteerCount) {
		super();
		this.volunteerNo = volunteerNo;
		this.volunteerTitle = volunteerTitle;
		this.volunteerWriter = volunteerWriter;
		this.volunteerContent = volunteerContent;
		this.volunteerOriginalImg = volunteerOriginalImg;
		this.volunteerRenamedImg = volunteerRenamedImg;
		this.volunteerEnrolldate = volunteerEnrolldate;
		this.volunteerCount = volunteerCount;
	}

	public int getVolunteerNo() {
		return volunteerNo;
	}

	public void setVolunteerNo(int volunteerNo) {
		this.volunteerNo = volunteerNo;
	}

	public String getVolunteerTitle() {
		return volunteerTitle;
	}

	public void setVolunteerTitle(String volunteerTitle) {
		this.volunteerTitle = volunteerTitle;
	}

	public String getVolunteerWriter() {
		return volunteerWriter;
	}

	public void setVolunteerWriter(String volunteerWriter) {
		this.volunteerWriter = volunteerWriter;
	}

	public String getVolunteerContent() {
		return volunteerContent;
	}

	public void setVolunteerContent(String volunteerContent) {
		this.volunteerContent = volunteerContent;
	}

	public String getVolunteerOriginalImg() {
		return volunteerOriginalImg;
	}

	public void setVolunteerOriginalImg(String volunteerOriginalImg) {
		this.volunteerOriginalImg = volunteerOriginalImg;
	}

	public String getVolunteerRenamedImg() {
		return volunteerRenamedImg;
	}

	public void setVolunteerRenamedImg(String volunteerRenamedImg) {
		this.volunteerRenamedImg = volunteerRenamedImg;
	}

	public Date getVolunteerEnrolldate() {
		return volunteerEnrolldate;
	}

	public void setVolunteerEnrolldate(Date volunteerEnrolldate) {
		this.volunteerEnrolldate = volunteerEnrolldate;
	}

	public int getVolunteerCount() {
		return volunteerCount;
	}

	public void setVolunteerCount(int volunteerCount) {
		this.volunteerCount = volunteerCount;
	}

	@Override
	public String toString() {
		return "VolunteerBoard [volunteerNo=" + volunteerNo + ", volunteerTitle=" + volunteerTitle
				+ ", volunteerWriter=" + volunteerWriter + ", volunteerContent=" + volunteerContent
				+ ", volunteerOriginalImg=" + volunteerOriginalImg + ", volunteerRenamedImg=" + volunteerRenamedImg
				+ ", volunteerEnrolldate=" + volunteerEnrolldate + ", volunteerCount=" + volunteerCount + "]";
	}
    
}
