package com.example.demo.models;

import java.util.List;

public class AppointmentDto {
	private List<Appointment> appointmentList;

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}
}
