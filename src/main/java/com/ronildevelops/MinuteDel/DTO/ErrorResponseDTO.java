package com.ronildevelops.MinuteDel.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDTO {

    private int status;
    private String error;
    private String message;

}
