package com.ronildevelops.MinuteDel.Service;

import com.ronildevelops.MinuteDel.DTO.*;
import com.ronildevelops.MinuteDel.Entity.DarkStoreEntity;
import com.ronildevelops.MinuteDel.Repository.DarkStoreEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DarkStoreEntityService {

    @Autowired
    DarkStoreEntityRepository darkStoreEntityRepository;


    public DarkStoreCreationResponseDTO createDarkStore(DarkStoreCreationRequestDTO dto1){
        DarkStoreCreationResponseDTO responseDTO = new DarkStoreCreationResponseDTO();
        if(darkStoreEntityRepository.existsByStoreCode(dto1.getStoreCode())){
            responseDTO.setMessage("Store by this Store code already exists");
            responseDTO.setSuccess(false);
        }
        if(darkStoreEntityRepository.existsByLatitudeAndLongitude(dto1.getLatitude(), dto1.getLongitude())){
            responseDTO.setSuccess(false);
            responseDTO.setMessage("Store already exists at the provided location");
        }
        DarkStoreEntity newStore = new DarkStoreEntity();
        newStore.setStoreCode(dto1.getStoreCode());
        newStore.setLongitude(dto1.getLongitude());
        newStore.setLatitude(dto1.getLatitude());
        newStore.setAtCapacity(false);
        newStore.setAvailable(true);


        darkStoreEntityRepository.save(newStore);

        responseDTO.setMessage("Dark store successfully onboard");
        responseDTO.setSuccess(true);

        return responseDTO;
    }

    public StoreToggleResponseDTO toggleStore(StoreToggleRequestDTO dto1){
        DarkStoreEntity store = darkStoreEntityRepository.findByStoreCode(dto1.getStoreCode()).orElseThrow(() -> new RuntimeException("Store with this store code not found"));
        store.setAvailable(dto1.isAvailable());
        darkStoreEntityRepository.save(store);

        StoreToggleResponseDTO responseDTO = new StoreToggleResponseDTO();
        if(dto1.isAvailable()){
            responseDTO.setMessage("Store set to available");
        }
        responseDTO.setMessage("Store set to Closed");
        responseDTO.setSuccess(true);

        return responseDTO;

    }

    public StoreCapacityFullResponseDTO toggleCapacityFull(StoreCapacityFullRequestDTO dto1){
        DarkStoreEntity store = darkStoreEntityRepository.findByStoreCode(dto1.getStoreCode()).orElseThrow(() -> new RuntimeException("Store with this store code not found"));
        store.setAtCapacity(dto1.isAtCapacity());
        darkStoreEntityRepository.save(store);

        StoreCapacityFullResponseDTO responseDTO = new StoreCapacityFullResponseDTO();
        if(dto1.isAtCapacity()){
            responseDTO.setMessage("Store set to At Capacity . Store will not receive any new orders! ");
        }
        responseDTO.setMessage("Store will now receive orders ");
        responseDTO.setSuccess(true);

        return responseDTO;

    }
}
