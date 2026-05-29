package com.ronildevelops.MinuteDel.Controller;


import com.ronildevelops.MinuteDel.DTO.ProductCreationRequestDTO;
import com.ronildevelops.MinuteDel.DTO.ProductCreationResponseDTO;
import com.ronildevelops.MinuteDel.DTO.ProductModificationRequestDTO;
import com.ronildevelops.MinuteDel.DTO.ProductModificationResponseDTO;
import com.ronildevelops.MinuteDel.Repository.ProductEntityRepository;
import com.ronildevelops.MinuteDel.Service.ProductEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductEntityController {

    @Autowired
    ProductEntityService productEntityService;


    @PostMapping("/addProduct")
    public ResponseEntity<ProductCreationResponseDTO> createProduct(@RequestBody ProductCreationRequestDTO dto1){
        ProductCreationResponseDTO responseDTO = productEntityService.createProduct(dto1);
        if(!responseDTO.isSuccess()){
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);

    }
    @PutMapping("/modify")
    public ResponseEntity<ProductModificationResponseDTO> modifyProduct(@RequestBody ProductModificationRequestDTO dto1){

        ProductModificationResponseDTO responseDTO =  productEntityService.modifyProduct(dto1);

        if(!responseDTO.isSuccess()){
            return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }



}
