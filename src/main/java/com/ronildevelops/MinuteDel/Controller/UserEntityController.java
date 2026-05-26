package com.ronildevelops.MinuteDel.Controller;


import com.ronildevelops.MinuteDel.DTO.LoginRequestDTO;
import com.ronildevelops.MinuteDel.DTO.LoginResponseDTO;
import com.ronildevelops.MinuteDel.DTO.UserCreationDTO;
import com.ronildevelops.MinuteDel.DTO.UserCreationResponseDTO;
import com.ronildevelops.MinuteDel.Security.JwtService;
import com.ronildevelops.MinuteDel.Service.CustomUserDetailsService;
import com.ronildevelops.MinuteDel.Service.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserEntityController {

    @Autowired
    UserEntityService  userEntityService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserCreationDTO userCreationDTO){
        UserCreationResponseDTO dto1 = userEntityService.createUser(userCreationDTO);
        if(!dto1.isSuccess()){
            return new ResponseEntity<>(dto1.getMessage(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(dto1.getMessage(),HttpStatus.OK);
    }

    @PostMapping("/login")
    public LoginResponseDTO userLogin(@RequestBody LoginRequestDTO loginRequestDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),loginRequestDTO.getPassword()));
        String token = jwtService.generateToken(loginRequestDTO.getUsername());

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setMessage("Logged in successfully");
        loginResponseDTO.setSuccess(true);
        loginResponseDTO.setToken(token);

        return loginResponseDTO;
    }


}
