package com.pinkpaw.admin.model.vo;

public class ReportBoard {

	private String reportTableName;
	private int reportNo;
	private String reportTitle;
	private String reportReason;
	
		
	public ReportBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReportBoard(String reportTableName, int reportNo, String reportTitle, String reportReason) {
		super();
		this.reportTableName = reportTableName;
		this.reportNo = reportNo;
		this.reportTitle = reportTitle;
		this.reportReason = reportReason;
	}
	public String getReportTableName() {
		return reportTableName;
	}
	public void setReportTableName(String reportTableName) {
		this.reportTableName = reportTableName;
	}
	public int getReportNo() {
		return reportNo;
	}
	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public String getReportReason() {
		return reportReason;
	}
	public void setReportReason(String reportReason) {
		this.reportReason = reportReason;
	}
	@Override
	public String toString() {
		return "ReportBoard [reportTableName=" + reportTableName + ", reportNo=" + reportNo + ", reportTitle="
				+ reportTitle + ", reportReason=" + reportReason + "]";
	}
	
	
	
	
}
