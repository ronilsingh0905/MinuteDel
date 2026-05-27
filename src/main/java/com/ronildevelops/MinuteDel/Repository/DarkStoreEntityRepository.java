package com.ronildevelops.MinuteDel.Repository;

import com.ronildevelops.MinuteDel.Entity.DarkStoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DarkStoreEntityRepository extends JpaRepository<DarkStoreEntity,Long> {
    boolean existsByStoreCode(String storeCode);
    boolean existsByLatitudeAndLongitude(double latitude, double longitude);
    Optional<DarkStoreEntity> findByStoreCode(String storeCode);


}
