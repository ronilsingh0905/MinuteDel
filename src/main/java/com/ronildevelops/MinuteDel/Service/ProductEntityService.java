package com.ronildevelops.MinuteDel.Service;

import com.ronildevelops.MinuteDel.DTO.*;

import com.ronildevelops.MinuteDel.Entity.ProductEntity;
import com.ronildevelops.MinuteDel.Repository.ProductEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductEntityService {

    @Autowired
    ProductEntityRepository productEntityRepository;

    public ProductCreationResponseDTO createProduct(ProductCreationRequestDTO dto1){
        if(dto1.getPrice()<=0){
            ProductCreationResponseDTO responseDTO = new ProductCreationResponseDTO();
            responseDTO.setSuccess(false);
            responseDTO.setMessage("FATAL ERROR: product cannot be priced equal or less than 0!");
            return responseDTO;
        }
        if(productEntityRepository.existsByName(dto1.getName())){
            ProductCreationResponseDTO responseDTO = new ProductCreationResponseDTO();
            responseDTO.setSuccess(false);
            responseDTO.setMessage("ERROR: product with this name already exists");
            return responseDTO;
        }

        ProductEntity newProduct = new ProductEntity();
        newProduct.setCategory(dto1.getCategory());
        newProduct.setName(dto1.getName());
        newProduct.setPrice(dto1.getPrice());
        newProduct.setActive(true);

        ProductEntity saved = productEntityRepository.save(newProduct);
        ProductCreationResponseDTO responseDTO = new ProductCreationResponseDTO();
        responseDTO.setMessage("Product successfully added to the catalogue");
        responseDTO.setId(saved.getId());
        responseDTO.setSuccess(true);

        return responseDTO;

    }

    public ProductModificationResponseDTO modifyProduct(ProductModificationRequestDTO dto1){
        if(dto1.getPrice()<=0){
            ProductModificationResponseDTO responseDTO = new ProductModificationResponseDTO();
            responseDTO.setSuccess(false);
            responseDTO.setMessage("FATAL ERROR: product cannot be priced equal or less than 0!");
            return responseDTO;
        }
        Optional<ProductEntity> productOptional = productEntityRepository.findById(dto1.getId());

        if (productOptional.isEmpty()) {
            ProductModificationResponseDTO responseDTO = new ProductModificationResponseDTO();
            responseDTO.setSuccess(false);
            responseDTO.setMessage("ERROR: No product with this ID exists!");
            return responseDTO;
        }

        ProductEntity newProduct = productOptional.get();

        newProduct.setCategory(dto1.getCategory());
        newProduct.setName(dto1.getName());
        newProduct.setPrice(dto1.getPrice());
        newProduct.setActive(dto1.isActive());


        ProductEntity saved = productEntityRepository.save(newProduct);

        ProductModificationResponseDTO responseDTO = new ProductModificationResponseDTO();
        responseDTO.setId(saved.getId());
        responseDTO.setCategory(saved.getCategory());
        responseDTO.setName(saved.getName());
        responseDTO.setPrice(saved.getPrice());
        responseDTO.setSuccess(true);
        responseDTO.setActive(saved.isActive());
        responseDTO.setMessage("Product updated with following parameters");


        return responseDTO;
    }





}
