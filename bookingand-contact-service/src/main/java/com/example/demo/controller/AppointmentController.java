package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Appointment;
import com.example.demo.models.Customers;

@RestController
@RequestMapping("/bookings")
public class AppointmentController {
	
	@PostMapping("/placebooking")
	public Integer placeBooking(@RequestBody Appointment appointment)
	{
		return Integer.valueOf(10);
	}
		
	@PostMapping("/contactus")
	public String contactUs(@RequestBody Customers customerDetails)
	{
		return "Your query query# is 11123 and you will be contacted within 24-48 hours";
	}
	
	@GetMapping("/dummy")
	public String dummyDetails()
	{
		System.out.println("dummy call for booking details ");
		return "dummy endpoint for booking and contact details";
	}

}
