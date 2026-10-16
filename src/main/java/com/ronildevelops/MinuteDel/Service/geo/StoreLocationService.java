package com.ronildevelops.MinuteDel.Service.geo;


import com.ronildevelops.MinuteDel.Entity.DarkStoreEntity;
import com.ronildevelops.MinuteDel.DTO.StoreFoundDTO;
import com.ronildevelops.MinuteDel.Exception.StoreNotFoundException;
import com.ronildevelops.MinuteDel.Repository.DarkStoreEntityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StoreLocationService {

    private final StoreGeoIndexRedis storeGeoIndexRedis;
    private final DarkStoreEntityRepository darkStoreEntityRepository;

    public StoreLocationService(StoreGeoIndexRedis storeGeoIndexRedis, DarkStoreEntityRepository darkStoreEntityRepository){
        this.darkStoreEntityRepository = darkStoreEntityRepository;
        this.storeGeoIndexRedis = storeGeoIndexRedis;
    }

    public StoreFoundDTO storeFinder(double latitude,double longitude){
        if (latitude < 6.0 || latitude > 38.0 || longitude < 68.0 || longitude > 98.0) {
            throw new StoreNotFoundException("Coordinates are outside serviceable regions");
        }
        Map<Long, Double> nearbyStores = storeGeoIndexRedis.storeLocationFinder(longitude, latitude, 3.0);
        if (nearbyStores.isEmpty()) {
            throw new StoreNotFoundException("No store available in your area");
        }

        List<Long> storeIds = new ArrayList<>(nearbyStores.keySet());
        List<DarkStoreEntity> eligibleStores = darkStoreEntityRepository.findByIdInAndAvailableTrueAndCapacityFalse(storeIds);

        if(eligibleStores.isEmpty()){
            throw new StoreNotFoundException("No store is currently available for service in your area");


        }
        Map<Long,DarkStoreEntity> storeMap = eligibleStores.stream().collect(Collectors.toMap(DarkStoreEntity::getId,store -> store));


        for(Long storeId : storeIds){
            if(storeMap.containsKey(storeId)){
                DarkStoreEntity matchedStore = storeMap.get(storeId);

                double distance = nearbyStores.get(storeId);
                double roundedDistance = Math.round(distance*100.0) / 100.0;

                return new StoreFoundDTO(matchedStore.getStoreCode(),roundedDistance);
            }
        }



        throw new StoreNotFoundException("No store is currently available for service in your area");



    }






}
