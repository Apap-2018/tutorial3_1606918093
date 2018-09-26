package com.apap.tutorial3.model;

public class PilotModel {
	private String id;
	private String licenseNumber;
	private String name;
	private Integer flyHour;
	
	public PilotModel(String pid, String plicenseNumber, String pname, int pflyHour) {
		this.id = pid ;
		this.licenseNumber = plicenseNumber;
		this.name = pname;
		this.flyHour = pflyHour;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFlyHour() {
		return flyHour;
	}

	public void setFlyHour(Integer flyHour) {
		this.flyHour = flyHour;
	}

	
	
}
