package com.ShipSmart.ShipSmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShipSmart.ShipSmart.entity.Booking;
import com.ShipSmart.ShipSmart.entity.BookingStatus;
import com.ShipSmart.ShipSmart.entity.Customer;
import com.ShipSmart.ShipSmart.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    private Customer getLoggedInCustomer(){
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("JohnDoe");
        return customer;
    }

    @PostMapping("/create")
    public Booking createBooking(@RequestBody Booking bookingRequest) {
        Customer customer = getLoggedInCustomer();
        return bookingService.createBooking(
                customer,
                bookingRequest.getPickupAddress(),
                bookingRequest.getDropAddress(),
                bookingRequest.getPickupLat(),
                bookingRequest.getPickupLng(),
                bookingRequest.getDropLat(),
                bookingRequest.getDropLng()
        );

    }

    @PutMapping("/{id}/status")
    public Booking updateStatus(@PathVariable Long id,@RequestParam BookingStatus status){

            Booking updateBooking = bookingService.updateBookingStatus(id,status);

            return updateBooking;

    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {

    Booking booking = bookingService.getBookingDetails(id);
    return booking;

    }


}