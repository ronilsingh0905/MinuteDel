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
@Table(name = "store_manager")
public class StoreManagerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private UserEntity userEntity;
    @OneToOne
    @JoinColumn(name="dark_store",nullable = false)
    private DarkStoreEntity darkStoreEntity;








}
