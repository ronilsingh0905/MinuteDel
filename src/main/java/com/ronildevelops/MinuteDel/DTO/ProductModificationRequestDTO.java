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
public class ProductModificationRequestDTO {
    private long id;
    private String name;
    private double price;
    private Category category;
    private boolean active;



}
