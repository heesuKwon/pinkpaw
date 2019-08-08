package com.pinkpaw.board.shelter.model.vo;

public class Shelter {
	
	private int shelterNo;
	private String shelterCenterName;
	private String shelterInsName;
	private String shelterKind;
	private String shelterAnimalKind;
	private String shelterRdnmAddress;
	private String shelterInmAddress;
	private String shelterLatitude;
	private String shelterHardress;
	private String shelterCenterDate;
	private String shelterWdOpen;
	private String shelterWdEnd;
	private String shelterWkOpen;
	private String shelterWkEnd;
	private String shelterHoliday;
	private String shelterPhone;
	private String place;
	
	
	public Shelter() {
		super();
	}


	public Shelter(int shelterNo, String shelterCenterName, String shelterInsName, String shelterKind,
			String shelterAnimalKind, String shelterRdnmAddress, String shelterInmAddress, String shelterLatitude,
			String shelterHardress, String shelterCenterDate, String shelterWdOpen, String shelterWdEnd,
			String shelterWkOpen, String shelterWkEnd, String shelterHoliday, String shelterPhone, String place) {
		super();
		this.shelterNo = shelterNo;
		this.shelterCenterName = shelterCenterName;
		this.shelterInsName = shelterInsName;
		this.shelterKind = shelterKind;
		this.shelterAnimalKind = shelterAnimalKind;
		this.shelterRdnmAddress = shelterRdnmAddress;
		this.shelterInmAddress = shelterInmAddress;
		this.shelterLatitude = shelterLatitude;
		this.shelterHardress = shelterHardress;
		this.shelterCenterDate = shelterCenterDate;
		this.shelterWdOpen = shelterWdOpen;
		this.shelterWdEnd = shelterWdEnd;
		this.shelterWkOpen = shelterWkOpen;
		this.shelterWkEnd = shelterWkEnd;
		this.shelterHoliday = shelterHoliday;
		this.shelterPhone = shelterPhone;
		this.place = place;
	}


	public int getShelterNo() {
		return shelterNo;
	}


	public void setShelterNo(int shelterNo) {
		this.shelterNo = shelterNo;
	}


	public String getShelterCenterName() {
		return shelterCenterName;
	}


	public void setShelterCenterName(String shelterCenterName) {
		this.shelterCenterName = shelterCenterName;
	}


	public String getShelterInsName() {
		return shelterInsName;
	}


	public void setShelterInsName(String shelterInsName) {
		this.shelterInsName = shelterInsName;
	}


	public String getShelterKind() {
		return shelterKind;
	}


	public void setShelterKind(String shelterKind) {
		this.shelterKind = shelterKind;
	}


	public String getShelterAnimalKind() {
		return shelterAnimalKind;
	}


	public void setShelterAnimalKind(String shelterAnimalKind) {
		this.shelterAnimalKind = shelterAnimalKind;
	}


	public String getShelterRdnmAddress() {
		return shelterRdnmAddress;
	}


	public void setShelterRdnmAddress(String shelterRdnmAddress) {
		this.shelterRdnmAddress = shelterRdnmAddress;
	}


	public String getShelterInmAddress() {
		return shelterInmAddress;
	}


	public void setShelterInmAddress(String shelterInmAddress) {
		this.shelterInmAddress = shelterInmAddress;
	}


	public String getShelterLatitude() {
		return shelterLatitude;
	}


	public void setShelterLatitude(String shelterLatitude) {
		this.shelterLatitude = shelterLatitude;
	}


	public String getShelterHardress() {
		return shelterHardress;
	}


	public void setShelterHardress(String shelterHardress) {
		this.shelterHardress = shelterHardress;
	}


	public String getShelterCenterDate() {
		return shelterCenterDate;
	}


	public void setShelterCenterDate(String shelterCenterDate) {
		this.shelterCenterDate = shelterCenterDate;
	}


	public String getShelterWdOpen() {
		return shelterWdOpen;
	}


	public void setShelterWdOpen(String shelterWdOpen) {
		this.shelterWdOpen = shelterWdOpen;
	}


	public String getShelterWdEnd() {
		return shelterWdEnd;
	}


	public void setShelterWdEnd(String shelterWdEnd) {
		this.shelterWdEnd = shelterWdEnd;
	}


	public String getShelterWkOpen() {
		return shelterWkOpen;
	}


	public void setShelterWkOpen(String shelterWkOpen) {
		this.shelterWkOpen = shelterWkOpen;
	}


	public String getShelterWkEnd() {
		return shelterWkEnd;
	}


	public void setShelterWkEnd(String shelterWkEnd) {
		this.shelterWkEnd = shelterWkEnd;
	}


	public String getShelterHoliday() {
		return shelterHoliday;
	}


	public void setShelterHoliday(String shelterHoliday) {
		this.shelterHoliday = shelterHoliday;
	}


	public String getShelterPhone() {
		return shelterPhone;
	}


	public void setShelterPhone(String shelterPhone) {
		this.shelterPhone = shelterPhone;
	}
	
	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	@Override
	public String toString() {
		return "Shelter [shelterNo=" + shelterNo + ", shelterCenterName=" + shelterCenterName + ", shelterInsName="
				+ shelterInsName + ", shelterKind=" + shelterKind + ", shelterAnimalKind=" + shelterAnimalKind
				+ ", shelterRdnmAddress=" + shelterRdnmAddress + ", shelterInmAddress=" + shelterInmAddress
				+ ", shelterLatitude=" + shelterLatitude + ", shelterHardress=" + shelterHardress
				+ ", shelterCenterDate=" + shelterCenterDate + ", shelterWdOpen=" + shelterWdOpen + ", shelterWdEnd="
				+ shelterWdEnd + ", shelterWkOpen=" + shelterWkOpen + ", shelterWkEnd=" + shelterWkEnd
				+ ", shelterHoliday=" + shelterHoliday + ", shelterPhone=" + shelterPhone + ", place=" + place + "]";
	}


	
	
}