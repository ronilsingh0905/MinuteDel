package com.ronildevelops.MinuteDel.Entity;

import io.lettuce.core.GeoSearch;
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
@Table(name = "riders")
public class RiderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id",nullable = false)
    private UserEntity userEntity;

    @Column(nullable = false)
    private double currentLatitude;

    @Column(nullable = false)
    private double currentLongitude;

    @Column(nullable = false)
    private boolean isBusy;

    @Version
    private Long version;


}
