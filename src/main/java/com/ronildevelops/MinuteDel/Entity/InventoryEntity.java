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
@Table(name = "inventory")
public class InventoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name ="DarkStoreEntity")
    private DarkStoreEntity darkStoreEntity;

    @ManyToOne
    @JoinColumn(name ="ProductEntity")
    private ProductEntity productEntity;

    @Column(nullable = false)
    private int availableQuantity;

    @Version
    private Long version;












}
