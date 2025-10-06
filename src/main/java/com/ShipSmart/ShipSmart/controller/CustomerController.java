package com.ShipSmart.ShipSmart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ShipSmart.ShipSmart.entity.Customer;
import com.ShipSmart.ShipSmart.repository.CustomerRepository;
import com.ShipSmart.ShipSmart.service.BookingService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public String homePage(){
        return "customerDashboard";
    }

    @GetMapping("/book")
    public String bookingPage(){
        return "custbookcourier";
    }

   // Show booking form
    @GetMapping("/customer/book")
    public String showBookingForm(Model model) {
    return "book-courier"; // Thymeleaf template
    }

    @PostMapping("/book")
    public String createBooking(@RequestParam String pickupAddress,
                            @RequestParam String dropAddress,
                            @RequestParam Double pickupLat,
                            @RequestParam Double pickupLng,
                            @RequestParam Double dropLat,
                            @RequestParam Double dropLng,
                            Principal principal) {

    Customer customer = customerRepository.findByEmail(principal.getName())  
            .orElseThrow(() -> new RuntimeException("Customer not found"));

    bookingService.createBooking(customer, pickupAddress, dropAddress, pickupLat, pickupLng, dropLat, dropLng);

    return "redirect:/customer/dashboard"; 
    
}
   
    


}
