package com.ShipSmart.ShipSmart.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends User {

    private String phoneNumber;

    private String defaultAddress;

    // can add relation to multiple addresses later
}

