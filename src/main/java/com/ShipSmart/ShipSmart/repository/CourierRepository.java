package com.ShipSmart.ShipSmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShipSmart.ShipSmart.entity.Courier;

@Repository
public interface CourierRepository extends JpaRepository<Courier, Long> {
    List<Courier> findByAvailableTrue();
}
