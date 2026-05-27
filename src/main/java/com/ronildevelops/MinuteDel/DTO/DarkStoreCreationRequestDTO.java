package com.ronildevelops.MinuteDel.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DarkStoreCreationRequestDTO {
    private String storeCode;
    private double latitude;
    private double longitude;

    private boolean isAtCapacity;
    private boolean isAvailable;



}
