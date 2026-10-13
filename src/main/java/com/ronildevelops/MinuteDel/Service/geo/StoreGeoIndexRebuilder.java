package com.ronildevelops.MinuteDel.Service.geo;


import com.ronildevelops.MinuteDel.Entity.DarkStoreEntity;
import com.ronildevelops.MinuteDel.Repository.DarkStoreEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class StoreGeoIndexRebuilder implements CommandLineRunner {

    private final DarkStoreEntityRepository darkStoreEntityRepository;
    private final StoreGeoIndexRedis geoIndexRedis;


    public StoreGeoIndexRebuilder(DarkStoreEntityRepository darkStoreEntityRepository , StoreGeoIndexRedis geoIndexRedis){
        this.darkStoreEntityRepository = darkStoreEntityRepository;
        this.geoIndexRedis = geoIndexRedis;
    }

    @Override
    public void run(String... args){
        log.info("Geo index rebuild started");

        List<DarkStoreEntity> stores = darkStoreEntityRepository.findAll();

        for (int i = 0; i < stores.size(); i++) {
            DarkStoreEntity store = stores.get(i);

            try{
                geoIndexRedis.storeUpdate(store.getId(),store.getLongitude(),store.getLatitude());
            }catch(DataAccessException e){
                log.error("Failed to Index storeID:{} , store_Longitude:{} , store_Latitude:{}, Error:{}" , store.getId(),store.getLongitude(),store.getLatitude(),e.getMessage(),e);
            }
        }
    }


}
