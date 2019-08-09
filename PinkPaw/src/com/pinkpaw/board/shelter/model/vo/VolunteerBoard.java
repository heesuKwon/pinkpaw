package com.pinkpaw.board.shelter.model.vo;

import java.sql.Date;

public class VolunteerBoard {
	private int volunteerNo;
    private String volunteerTitle;
    private String volunteerWriter;
    private String volunteerContent;
    private int volunteerImgNo;
    private Date volunteerEnrolldate;
    private int volunteerCount;
    
    public VolunteerBoard() {
    }
    public VolunteerBoard(int volunteerNo, String volunteerTitle, String volunteerWriter, String volunteerContent,
            int volunteerImgNo, Date volunteerEnrolldate, int volunteerCount) {
        super();
        this.volunteerNo = volunteerNo;
        this.volunteerTitle = volunteerTitle;
        this.volunteerWriter = volunteerWriter;
        this.volunteerContent = volunteerContent;
        this.volunteerImgNo = volunteerImgNo;
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
    public int getVolunteerImgNo() {
        return volunteerImgNo;
    }
    public void setVolunteerImgNo(int volunteerImgNo) {
        this.volunteerImgNo = volunteerImgNo;
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
        return "volunteer [volunteerNo=" + volunteerNo + ", volunteerTitle=" + volunteerTitle + ", volunteerWriter="
                + volunteerWriter + ", volunteerContent=" + volunteerContent + ", volunteerImgNo=" + volunteerImgNo
                + ", volunteerEnrolldate=" + volunteerEnrolldate + ", volunteerCount=" + volunteerCount + "]";
    }
}
