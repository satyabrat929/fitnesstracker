package com.example.demo.models;

import java.util.List;

public class Customers {
	private List<Address> addressList;
	private String Name;
	private String age;
	private String email;
	private String trainerPreference; // male/female/none
	private Boolean physioRequired;

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTrainerPreference() {
		return trainerPreference;
	}

	public void setTrainerPreference(String trainerPreference) {
		this.trainerPreference = trainerPreference;
	}

	public Boolean getPhysioRequired() {
		return physioRequired;
	}

	public void setPhysioRequired(Boolean physioRequired) {
		this.physioRequired = physioRequired;
	}
}
