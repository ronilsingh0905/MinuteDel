package com.ronildevelops.MinuteDel.DTO;

import com.ronildevelops.MinuteDel.Entity.Role;
import com.ronildevelops.MinuteDel.Entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiderUpdateDTO {

    private double latitude;
    private double longitude;

    private UserEntity userid;

    private boolean isBusy;



}
