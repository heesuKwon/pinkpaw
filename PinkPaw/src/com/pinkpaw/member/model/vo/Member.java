package com.pinkpaw.member.model.vo;

import java.sql.Date;

public class Member {
	private String memberId; 
	private String password;
	private String memberName;
	private String email;
	private String address;
	private Date enrolldate;
	private int reportCount;
	private String grade;
	
	public Member() {
		super();
	}

	public Member(String memberId, String password, String memberName, String email, String address, Date enrolldate,
			int reportCount, String grade) {
		super();
		this.memberId = memberId;
		this.password = password;
		this.memberName = memberName;
		this.email = email;
		this.address = address;
		this.enrolldate = enrolldate;
		this.reportCount = reportCount;
		this.grade = grade;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}

	public int getReportCount() {
		return reportCount;
	}

	public void setReportCount(int reportCount) {
		this.reportCount = reportCount;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", password=" + password + ", memberName=" + memberName + ", email="
				+ email + ", address=" + address + ", enrolldate=" + enrolldate + ", reportCount=" + reportCount
				+ ", grade=" + grade + "]";
	}
}
