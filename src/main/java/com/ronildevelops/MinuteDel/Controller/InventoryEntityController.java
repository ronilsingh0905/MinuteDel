package com.ronildevelops.MinuteDel.Controller;


import com.ronildevelops.MinuteDel.DTO.*;

import com.ronildevelops.MinuteDel.Repository.InventoryEntityRepository;
import com.ronildevelops.MinuteDel.Service.InventoryEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryEntityController {



    @Autowired
    InventoryEntityService inventoryEntityService;



    @PostMapping("/create")
    public ResponseEntity<InventoryCreationResponseDTO> inventoryCreator(@RequestBody InventoryCreationRequestDTO dto1){
        InventoryCreationResponseDTO responseDTO = inventoryEntityService.createInventory(dto1);
        if(!responseDTO.isSuccess()){
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(responseDTO,HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<InventoryModificationResponseDTO> inventoryUpdate(@RequestBody InventoryModificationRequestDTO dto1){
        InventoryModificationResponseDTO responseDTO = inventoryEntityService.modifyInventory(dto1);
        if(!responseDTO.isSuccess()){
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }


        return new ResponseEntity<>(responseDTO,HttpStatus.OK);

    }


}
