package com.ShipSmart.ShipSmart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ShipSmart.ShipSmart.entity.Booking;
import com.ShipSmart.ShipSmart.entity.Courier;
import com.ShipSmart.ShipSmart.repository.CourierRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/couriers")
public class CourierController {
    
    @Autowired
    private CourierRepository courierRepository;

    @PostMapping("/add")
    public Courier addCourier(@RequestBody Courier courier) {
      return courierRepository.save(courier);
    }
    

    @PutMapping("/{courierId}/location")
    public Courier updateLocation(@PathVariable Long courierId,@RequestParam Double lat, @RequestParam Double lng){

        Courier courier = courierRepository.findById(courierId).orElseThrow(()-> new RuntimeException("Courier Not Found"));

        courier.setCurrentLat(lat);
        courier.setCurrentLng(lng);
        courier.setAvailable(true);  // mark available if courier is free
        return courierRepository.save(courier);

    }

    @GetMapping()
    public List<Courier> getAllCourier(){
      return courierRepository.findAll();
    }

    @GetMapping("/{id}")
    public Courier getCourierByIdCourier(@PathVariable Long id) {
        return courierRepository.findById(id).get();
    }

    @GetMapping("/avalaibles")
    public List<Courier> findAvalaibleCourier(){
      return courierRepository.findByAvailableTrue();
    }
    

}
