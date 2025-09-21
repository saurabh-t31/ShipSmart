package com.ShipSmart.ShipSmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShipSmart.ShipSmart.entity.Courier;
import com.ShipSmart.ShipSmart.repository.CourierRepository;

@RestController
@RequestMapping("/api/couriers")
public class CourierController {
    
    @Autowired
    private CourierRepository courierRepository;

    @PutMapping("/{courierId}/location")
    public Courier updateLocation(@PathVariable Long courierId,@RequestParam Double lat, @RequestParam Double lng){

        Courier courier = courierRepository.findById(courierId)
                            .orElseThrow(()-> new RuntimeException("Courier Not Found"));

        courier.setCurrentLat(lat);
        courier.setCurrentLng(lng);
        courier.setAvailable(true);  // mark available if courier is free
        return courierRepository.save(courier);

    }


}
