package com.example.demo.models;

public class PackageDetails {

	private Integer numberOfPersons;
	private String pacakageType;
	private Float totalCost;
	private String session;

	public PackageDetails(Integer numberOfPersons, String pacakageType, String session) {
		super();
		this.numberOfPersons = numberOfPersons;
		this.pacakageType = pacakageType;
		this.session = session;
		switch (session) {
		case "OneTimeAppointment":
			this.totalCost = (float) (500 * this.numberOfPersons);
			break;
		case "fourSessionPerWeek":
			this.totalCost = (float) (400 * this.numberOfPersons);
			break;
		case "fiveSessionPerWeek":
			this.totalCost = (float) (300 * this.numberOfPersons);
			break;
		default:
			this.totalCost = 0F;
			break;
		}
	}

	public Integer getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	public String getPacakageType() {
		return pacakageType;
	}

	public void setPacakageType(String pacakageType) {
		this.pacakageType = pacakageType;
	}

	public Float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Float totalCost) {
		this.totalCost = totalCost;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}
}
