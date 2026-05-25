package com.ronildevelops.MinuteDel.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "store")
    private DarkStoreEntity darkStoreEntity;



    @ManyToOne
    @JoinColumn(name = "rider",nullable = true)
    private RiderEntity riderEntity;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;



    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Coloum(nullable = false)
    private double totalAmount;

    @Column(nullable = false)
    private LocalDateTime orderedAt;






}
