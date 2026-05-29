package com.ronildevelops.MinuteDel.DTO;


import com.ronildevelops.MinuteDel.Entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreationResponseDTO {
    private Long id;
    private String message;

    private boolean success;



}
