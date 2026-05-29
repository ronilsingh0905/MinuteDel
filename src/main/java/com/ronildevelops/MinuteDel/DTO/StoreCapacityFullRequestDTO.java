package com.ronildevelops.MinuteDel.DTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreCapacityFullRequestDTO {
    private String storeCode;
    @JsonProperty("isAtCapacity")
    private boolean isAtCapacity;
}
