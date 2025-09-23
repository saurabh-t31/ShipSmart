package com.ShipSmart.ShipSmart.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShipSmart.ShipSmart.entity.Booking;
import com.ShipSmart.ShipSmart.entity.BookingStatus;
import com.ShipSmart.ShipSmart.entity.Courier;
import com.ShipSmart.ShipSmart.entity.Customer;
import com.ShipSmart.ShipSmart.repository.BookingRepository;
import com.ShipSmart.ShipSmart.repository.CourierRepository;

@Service
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CourierRepository courierRepository;

    public Booking createBooking(Customer customer,String pickupAddress,String dropAddress,Double pickupLat,Double pickupLng,Double dropLat,Double dropLng){
        
        Booking booking = Booking.builder()
                        .customer(customer)
                        .pickupAddress(pickupAddress)
                        .dropAddress(dropAddress)
                        .pickupLat(pickupLat)
                        .pickupLng(pickupLng)
                        .dropLat(dropLat)
                        .dropLng(dropLng)
                        .status(BookingStatus.PENDING)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();

        Booking savedBooking = bookingRepository.save(booking);
        
                // Try to assign nearest courier
        Courier nearest = findNearestCourier(pickupLat, pickupLng);
        if (nearest != null) {
            nearest.setAvailable(false);
            savedBooking.setCourier(nearest);
            savedBooking.setStatus(BookingStatus.ASSIGNED);
            courierRepository.save(nearest);
            bookingRepository.save(savedBooking);
        }

        return savedBooking;

    }

    private Courier findNearestCourier(Double pickupLat,Double pickupLng){
        List<Courier> couriers = courierRepository.findByAvailableTrue();

        Courier nearest = null;

        double minDist = Double.MAX_VALUE;

        for(Courier c:couriers){
            if (c.getCurrentLat() != null && c.getCurrentLng() != null) {
                double dist = haversine(pickupLat,pickupLng,c.getCurrentLat(),c.getCurrentLng());
                if (dist < minDist) {
                    minDist = dist;
                    nearest = c;
                }
            }
        }

        return nearest;
    } 

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                 + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                 * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public Booking updateBookingStatus(Long bookingId, BookingStatus status) {

    Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

    booking.setStatus(status);
    booking.setUpdatedAt(LocalDateTime.now());

    if (status == BookingStatus.DELIVERED && booking.getCourier() != null) {
        Courier courier = booking.getCourier();
        courier.setAvailable(true);
        courierRepository.save(courier);
    }

    return bookingRepository.save(booking);
}


}
