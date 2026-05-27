package com.ronildevelops.MinuteDel.Controller;


import com.ronildevelops.MinuteDel.DTO.*;
import com.ronildevelops.MinuteDel.Repository.DarkStoreEntityRepository;
import com.ronildevelops.MinuteDel.Service.DarkStoreEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/darkStore")
public class DarkStoreEntityController {



    @Autowired
    DarkStoreEntityRepository darkStoreEntityRepository;

    @Autowired
    DarkStoreEntityService darkStoreEntityService;

    @PostMapping("/onBoarding")
    public ResponseEntity<?> storeOnboarder(@RequestBody DarkStoreCreationRequestDTO dto1){
        DarkStoreCreationResponseDTO responseDTO = darkStoreEntityService.createDarkStore(dto1);
        if(!responseDTO.isSuccess()){
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(responseDTO.getMessage(),HttpStatus.OK);

    }

    @PutMapping("/toggle")
    public ResponseEntity<?> storeToggler(@RequestBody StoreToggleRequestDTO dto1){
        StoreToggleResponseDTO responseDTO = darkStoreEntityService.toggleStore(dto1);
        if(!responseDTO.isSuccess()){
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(responseDTO.getMessage(),HttpStatus.OK);

    }

    @PutMapping("/capacity")
    public ResponseEntity<?> capacityFull(@RequestBody StoreCapacityFullRequestDTO dto1){
        StoreCapacityFullResponseDTO responseDTO = darkStoreEntityService.toggleCapacityFull(dto1);
        if(!responseDTO.isSuccess()){
            return new ResponseEntity<>(responseDTO.getMessage(), HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(responseDTO.getMessage(),HttpStatus.OK);

    }
}
