package com.ronildevelops.MinuteDel.Repository;

import com.ronildevelops.MinuteDel.Entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryEntityRepository extends JpaRepository<InventoryEntity,Long> {
    Optional<InventoryEntity> findByProductEntityIdAndDarkStoreEntityIdAndProductEntityActiveTrue(Long productId, Long darkStoreId);

  boolean existsByProductEntityIdAndDarkStoreEntityId(Long productId, Long darkStoreId);


}
