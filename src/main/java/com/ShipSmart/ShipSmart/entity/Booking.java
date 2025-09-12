package com.ShipSmart.ShipSmart.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pickupAddress;
    private String dropAddress;

    private Double pickupLat;
    private Double pickupLng;

    private Double dropLat;
    private Double dropLng;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;  // PENDING, ASSIGNED, PICKED_UP, DELIVERED, CANCELLED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // --- Relationships ---
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "courier_id")
    private Courier courier;  // assigned courier
}

