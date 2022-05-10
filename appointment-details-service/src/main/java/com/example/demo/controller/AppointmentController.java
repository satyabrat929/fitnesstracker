package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.AppointmentDto;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
	
	@GetMapping("/view")
	public List<AppointmentDto> fetchAppointMentDetails()
	{
		System.out.println("appointmentdetails are getting fetched");
		return new ArrayList<AppointmentDto>();
	}
	
	@GetMapping("/dummy")
	public String dummyDetails()
	{
		System.out.println("appointmentdetails are getting fetched");
		return "test";
	}

}
