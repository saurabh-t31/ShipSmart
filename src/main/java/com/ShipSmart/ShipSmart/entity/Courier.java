package com.ShipSmart.ShipSmart.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "couriers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Courier extends User {

    private String licenseNumber;

    private String vehicleType;  // bike, van, etc.

    private String vehicleNumber;

    private boolean available;

    private Double currentLat;
    private Double currentLng;
}

