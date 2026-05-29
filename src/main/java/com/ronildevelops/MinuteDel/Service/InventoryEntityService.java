package com.ronildevelops.MinuteDel.Service;

import com.ronildevelops.MinuteDel.DTO.*;
import com.ronildevelops.MinuteDel.Entity.DarkStoreEntity;
import com.ronildevelops.MinuteDel.Entity.InventoryEntity;
import com.ronildevelops.MinuteDel.Entity.ProductEntity;
import com.ronildevelops.MinuteDel.Repository.DarkStoreEntityRepository;
import com.ronildevelops.MinuteDel.Repository.InventoryEntityRepository;
import com.ronildevelops.MinuteDel.Repository.ProductEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryEntityService {

    @Autowired
    InventoryEntityRepository inventoryEntityRepository;

    @Autowired
    ProductEntityRepository productEntityRepository;

    @Autowired
    DarkStoreEntityRepository darkStoreEntityRepository;

    public InventoryCreationResponseDTO createInventory(InventoryCreationRequestDTO dto1){
        if(dto1.getStorePrice() != null && dto1.getStorePrice() <= 0){
            InventoryCreationResponseDTO responseDTO = new InventoryCreationResponseDTO();
            responseDTO.setSuccess(false);
            responseDTO.setMessage("FATAL ERROR: product cannot be priced equal or less than 0!");
            return responseDTO;
        }
        if(inventoryEntityRepository.existsByProductEntityIdAndDarkStoreEntityId(dto1.getProductId(), dto1.getStoreId())){
            InventoryCreationResponseDTO responseDTO = new InventoryCreationResponseDTO();
            responseDTO.setSuccess(false);
            responseDTO.setMessage("ERROR: Inventory for this product  already exists");
            return responseDTO;
        }

        InventoryEntity newProduct = new InventoryEntity();
        newProduct.setAvailableQuantity(dto1.getAvailableQuantity());
        if(dto1.getStorePrice() == null){
            ProductEntity productEntity = productEntityRepository.findById(dto1.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            newProduct.setCurrPrice(productEntity.getPrice());
        }else{
            newProduct.setCurrPrice(dto1.getStorePrice());
        }
        ProductEntity productEntity = productEntityRepository.findById(dto1.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        DarkStoreEntity storeEntity = darkStoreEntityRepository.findById(dto1.getStoreId())
                .orElseThrow(() -> new RuntimeException("Dark Store hub location not found"));

        newProduct.setProductEntity(productEntity);
        newProduct.setDarkStoreEntity(storeEntity);

        InventoryEntity saved = inventoryEntityRepository.save(newProduct);
        InventoryCreationResponseDTO responseDTO = new InventoryCreationResponseDTO();
        responseDTO.setSuccess(true);
        responseDTO.setMessage("Inventory with following details created!");
        responseDTO.setInventoryId(saved.getId());
        responseDTO.setAvailableQuantity(saved.getAvailableQuantity());
        responseDTO.setPrice(saved.getCurrPrice());

        return responseDTO;






    }

    public InventoryModificationResponseDTO  modifyInventory(InventoryModificationRequestDTO dto1){
        if(dto1.getStorePrice()<=0){
            InventoryModificationResponseDTO responseDTO = new InventoryModificationResponseDTO();
            responseDTO.setSuccess(false);
            responseDTO.setMessage("FATAL ERROR: product cannot be priced equal or less than 0!");
            return responseDTO;
        }
        Optional<InventoryEntity> inventoryOptional = inventoryEntityRepository.findByProductEntityIdAndDarkStoreEntityIdAndProductEntityActiveTrue(dto1.getProductId(),dto1.getStoreId());

        if (inventoryOptional.isEmpty()) {
            InventoryModificationResponseDTO responseDTO = new InventoryModificationResponseDTO();
            responseDTO.setSuccess(false);
            responseDTO.setMessage("ERROR: No product with this ID exists!");
            return responseDTO;
        }

        InventoryEntity newProduct = inventoryOptional.get();

        newProduct.setCurrPrice(dto1.getStorePrice());
        newProduct.setAvailableQuantity(dto1.getAvailableQuantity());


        InventoryEntity saved = inventoryEntityRepository.save(newProduct);

        InventoryModificationResponseDTO responseDTO = new InventoryModificationResponseDTO();
        responseDTO.setSuccess(true);
        responseDTO.setMessage("Inventory updated with the following parameters:");
        responseDTO.setAvailableQuantity(saved.getAvailableQuantity());
        responseDTO.setStorePrice(saved.getCurrPrice());



        return responseDTO;
    }





}
