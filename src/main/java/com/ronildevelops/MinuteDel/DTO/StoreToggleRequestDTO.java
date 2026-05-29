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
public class StoreToggleRequestDTO {
    private String storeCode;

    @JsonProperty("isAvailable")

    private boolean isAvailable;
}
