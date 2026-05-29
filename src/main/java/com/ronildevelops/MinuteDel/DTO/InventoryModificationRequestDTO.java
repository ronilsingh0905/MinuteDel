package com.ronildevelops.MinuteDel.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryModificationRequestDTO {
    private Long storeId;
    private long productId;
    private Double storePrice;
    private int availableQuantity;




}
