package com.ronildevelops.MinuteDel.Repository;


import com.ronildevelops.MinuteDel.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductEntityRepository extends JpaRepository<ProductEntity,Long> {
    boolean existsByName(String name);
    Optional<ProductEntity> findById(Long Id);
    boolean existsById(Long Id);


}
