package com.ronildevelops.MinuteDel.Exception;


import com.ronildevelops.MinuteDel.DTO.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleStoreNotFound(StoreNotFoundException ex){
        ErrorResponseDTO dto = new ErrorResponseDTO(
                404,
                "NOT FOUND",
                 ex.getMessage()
        );
        return  new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }
}
