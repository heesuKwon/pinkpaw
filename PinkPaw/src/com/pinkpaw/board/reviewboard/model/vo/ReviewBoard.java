package com.pinkpaw.board.reviewboard.model.vo;

import java.sql.Date;

public class ReviewBoard {

	private int reviewNo; //후기 번호
	private String reviewTitle; //제목
	private String reviewWriter; //작성자
	private String reviewKind; //게시물 종류
	private String reviewContent; //상세설명
	private String reviewOriginalImg; //업로드이미지
	private String reviewRenamedImg; //서버상이미지
	private Date reviewEnrollDate; //게시일
	private int reviewCount; //조회수
	private int reviewLike; //좋아요
	private int reviewReportCount; //신고수
	private String reviewReportReason; //신고사유
			
	public ReviewBoard() {
		super();
	}

	public ReviewBoard(int reviewNo, String reviewTitle, String reviewWriter, String reviewKind, String reviewContent,
			String reviewOriginalImg, String reviewRenamedImg, Date reviewEnrollDate, int reviewCount, int reviewLike,
			int reviewReportCount, String reviewReportReason) {
		super();
		this.reviewNo = reviewNo;
		this.reviewTitle = reviewTitle;
		this.reviewWriter = reviewWriter;
		this.reviewKind = reviewKind;
		this.reviewContent = reviewContent;
		this.reviewOriginalImg = reviewOriginalImg;
		this.reviewRenamedImg = reviewRenamedImg;
		this.reviewEnrollDate = reviewEnrollDate;
		this.reviewCount = reviewCount;
		this.reviewLike = reviewLike;
		this.reviewReportCount = reviewReportCount;
		this.reviewReportReason = reviewReportReason;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewWriter() {
		return reviewWriter;
	}

	public void setReviewWriter(String reviewWriter) {
		this.reviewWriter = reviewWriter;
	}

	public String getReviewKind() {
		return reviewKind;
	}

	public void setReviewKind(String reviewKind) {
		this.reviewKind = reviewKind;
	}

	public String getReviewContent() {
		return reviewContent;
	}

	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}

	public String getReviewOriginalImg() {
		return reviewOriginalImg;
	}

	public void setReviewOriginalImg(String reviewOriginalImg) {
		this.reviewOriginalImg = reviewOriginalImg;
	}

	public String getReviewRenamedImg() {
		return reviewRenamedImg;
	}

	public void setReviewRenamedImg(String reviewRenamedImg) {
		this.reviewRenamedImg = reviewRenamedImg;
	}

	public Date getReviewEnrollDate() {
		return reviewEnrollDate;
	}

	public void setReviewEnrollDate(Date reviewEnrollDate) {
		this.reviewEnrollDate = reviewEnrollDate;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public int getReviewLike() {
		return reviewLike;
	}

	public void setReviewLike(int reviewLike) {
		this.reviewLike = reviewLike;
	}

	public int getReviewReportCount() {
		return reviewReportCount;
	}

	public void setReviewReportCount(int reviewReportCount) {
		this.reviewReportCount = reviewReportCount;
	}

	public String getReviewReportReason() {
		return reviewReportReason;
	}

	public void setReviewReportReason(String reviewReportReason) {
		this.reviewReportReason = reviewReportReason;
	}

	@Override
	public String toString() {
		return "ReviewBoard [reviewNo=" + reviewNo + ", reviewTitle=" + reviewTitle + ", reviewWriter=" + reviewWriter
				+ ", reviewKind=" + reviewKind + ", reviewContent=" + reviewContent + ", reviewOriginalImg="
				+ reviewOriginalImg + ", reviewRenamedImg=" + reviewRenamedImg + ", reviewEnrollDate="
				+ reviewEnrollDate + ", reviewCount=" + reviewCount + ", reviewLike=" + reviewLike
				+ ", reviewReportCount=" + reviewReportCount + ", reviewReportReason=" + reviewReportReason + "]";
	}

	
	
	
}
