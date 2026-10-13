package com.ronildevelops.MinuteDel.Service;

import com.ronildevelops.MinuteDel.DTO.*;
import com.ronildevelops.MinuteDel.Entity.DarkStoreEntity;
import com.ronildevelops.MinuteDel.Repository.DarkStoreEntityRepository;
import com.ronildevelops.MinuteDel.Service.geo.StoreGeoIndexRedis;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
@Slf4j

@Service
public class DarkStoreEntityService {

    @Autowired
    DarkStoreEntityRepository darkStoreEntityRepository;

    @Autowired
    StoreGeoIndexRedis geoIndexRedis;

    public DarkStoreCreationResponseDTO createDarkStore(DarkStoreCreationRequestDTO dto1){

        if(dto1.getLatitude()>38 || dto1.getLatitude()<6 || dto1.getLongitude()>98 || dto1.getLongitude()<68){ //India region taken into consideration
            DarkStoreCreationResponseDTO responseDTO = new DarkStoreCreationResponseDTO();
            responseDTO.setMessage("ERROR: Coordinates outside service bounds");
            responseDTO.setSuccess(false);
            return responseDTO;
        }
        if(darkStoreEntityRepository.existsByStoreCode(dto1.getStoreCode())){
            DarkStoreCreationResponseDTO responseDTO = new DarkStoreCreationResponseDTO();
            responseDTO.setMessage("ERROR: Store by this Store code already exists");
            responseDTO.setSuccess(false);
            return responseDTO;
        }

        if(darkStoreEntityRepository.existsByLatitudeAndLongitude(dto1.getLatitude(), dto1.getLongitude())){
            DarkStoreCreationResponseDTO responseDTO = new DarkStoreCreationResponseDTO();

            responseDTO.setSuccess(false);
            responseDTO.setMessage("ERROR: Store already exists at the provided location.");
            return responseDTO;
        }


        DarkStoreEntity newStore = new DarkStoreEntity();
        newStore.setStoreCode(dto1.getStoreCode());
        newStore.setLongitude(dto1.getLongitude());
        newStore.setLatitude(dto1.getLatitude());
        newStore.setCapacity(false);
        newStore.setAvailable(true);


        DarkStoreEntity fetched = darkStoreEntityRepository.save(newStore);

        double storeLong = fetched.getLongitude();
        double storeLat = fetched.getLatitude();
        Long storeId = fetched.getId();

        try{
            geoIndexRedis.storeUpdate(storeId,storeLong,storeLat);

        } catch (DataAccessException e) {
            log.error("Redis Geo Index write failed , store saved but not indexed for storeId :{} , lat :{},long:{} ,Error:{}", storeId,storeLat,storeLong,e.getMessage(), e);
        }


        DarkStoreCreationResponseDTO responseDTO = new DarkStoreCreationResponseDTO();
        responseDTO.setMessage("Dark store successfully onboard");
        responseDTO.setSuccess(true);
        return responseDTO;


    }

    @Transactional
    public StoreToggleResponseDTO toggleStore(StoreToggleRequestDTO dto1){
        DarkStoreEntity store = darkStoreEntityRepository.findByStoreCode(dto1.getStoreCode()).orElseThrow(() -> new RuntimeException("Store with this store code not found"));
        store.setAvailable(dto1.isAvailable());
        darkStoreEntityRepository.save(store);


        StoreToggleResponseDTO responseDTO = new StoreToggleResponseDTO();
        if(dto1.isAvailable()){
            responseDTO.setMessage("Store set to available");
        }else{
            responseDTO.setMessage("Store set to Closed");


        }
        responseDTO.setSuccess(true);
        return responseDTO;

    }


    @Transactional
    public StoreCapacityFullResponseDTO toggleCapacityFull(StoreCapacityFullRequestDTO dto1){
        DarkStoreEntity store = darkStoreEntityRepository.findByStoreCode(dto1.getStoreCode()).orElseThrow(() -> new RuntimeException("Store with this store code not found"));
        store.setCapacity(dto1.isAtCapacity());
        darkStoreEntityRepository.save(store);

        StoreCapacityFullResponseDTO responseDTO = new StoreCapacityFullResponseDTO();
        if(dto1.isAtCapacity()){
            responseDTO.setMessage("Store set to At Capacity . Store will not receive any new orders! ");
        }else{
            responseDTO.setMessage("Store will now receive orders ");
        }

        responseDTO.setSuccess(true);

        return responseDTO;

    }

    public
}
