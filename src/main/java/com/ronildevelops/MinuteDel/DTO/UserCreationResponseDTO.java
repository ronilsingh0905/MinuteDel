package com.ronildevelops.MinuteDel.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationResponseDTO {
    private boolean success;
    private String message;

}
