package com.ronildevelops.MinuteDel.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stores")
public class DarkStoreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String storeCode;



    @Column(nullable = false,updatable = false)
    private double latitude;

    @Column(nullable = false,updatable = false)
    private double longitude;

    @Column(nullable = false)
    private boolean isAtCapacity; // for capacity overflow


    @Column(nullable = false)
    private boolean isAvailable;






}
