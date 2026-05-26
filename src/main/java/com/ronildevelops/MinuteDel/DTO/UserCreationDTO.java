package com.ronildevelops.MinuteDel.DTO;

import com.ronildevelops.MinuteDel.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationDTO {

    private String username;
    private String password;
    private String phoneNumber;
    private String email;
    private Role role;

}
