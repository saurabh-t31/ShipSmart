package com.ShipSmart.ShipSmart.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ShipSmart.ShipSmart.entity.Booking;
import com.ShipSmart.ShipSmart.entity.BookingStatus;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerId(Long customerId);
    List<Booking> findByCourierId(Long courierId);
    List<Booking> findByStatus(BookingStatus status);
}

