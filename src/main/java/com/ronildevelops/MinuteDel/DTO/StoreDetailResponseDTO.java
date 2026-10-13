package com.ronildevelops.MinuteDel.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreDetailResponseDTO {
    private String message;
    private String storeCode;
    private String storeName;
    private double storeDistance;

}
