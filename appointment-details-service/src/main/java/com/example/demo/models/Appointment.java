package com.example.demo.models;

public class Appointment {
	private Integer sNo;
	private Customers customer;
	private PackageDetails packagedetails;

	public Integer getsNo() {
		return sNo;
	}

	public void setsNo(Integer sNo) {
		this.sNo = sNo;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public PackageDetails getPackagedetails() {
		return packagedetails;
	}

	public void setPackagedetails(PackageDetails packagedetails) {
		this.packagedetails = packagedetails;
	}

}
