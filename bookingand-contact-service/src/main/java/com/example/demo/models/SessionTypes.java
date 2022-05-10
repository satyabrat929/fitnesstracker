package com.example.demo.models;

public enum SessionTypes {
	OneTimeAppointment("OneTimeAppointment"),
	fourSessionPerWeek("fourSessionPerWeek"),
	fiveSessionPerWeek("fiveSessionPerWeek"),
	;
	
	private String appointmentTypes;

	SessionTypes(String appointmentTypes) {
		this.appointmentTypes = appointmentTypes;
	}
	
	public String getSessionTypes() {
		return this.appointmentTypes;
	}
}
