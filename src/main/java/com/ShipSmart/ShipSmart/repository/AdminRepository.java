package com.ShipSmart.ShipSmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ShipSmart.ShipSmart.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>{
    
}
