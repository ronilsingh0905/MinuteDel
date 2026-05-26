package com.ronildevelops.MinuteDel.Service;


import com.ronildevelops.MinuteDel.DTO.UserCreationDTO;
import com.ronildevelops.MinuteDel.DTO.UserCreationResponseDTO;
import com.ronildevelops.MinuteDel.Entity.UserEntity;
import com.ronildevelops.MinuteDel.Repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService {

    @Autowired
    UserEntityRepository userEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserCreationResponseDTO createUser(UserCreationDTO dto){
        if(userEntityRepository.existsByUsername(dto.getUsername())){
            UserCreationResponseDTO dto1 = new UserCreationResponseDTO();
            dto1.setMessage("Username already exists");
            dto1.setSuccess(false);
            return dto1;
        }
        else if (userEntityRepository.existsByPhoneNumber(dto.getPhoneNumber())){
            UserCreationResponseDTO dto1 = new UserCreationResponseDTO();
            dto1.setMessage("User with Phone number already exists");
            dto1.setSuccess(false);
            return dto1;
        }
        else if(userEntityRepository.existsByEmail(dto.getEmail())){
            UserCreationResponseDTO dto1 = new UserCreationResponseDTO();
            dto1.setMessage("User with this email already exists");
            dto1.setSuccess(false);
            return dto1;
        }
        UserEntity user = new UserEntity();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        user.setPhoneNumber(dto.getPhoneNumber());

        userEntityRepository.save(user);







        UserCreationResponseDTO finaldto = new UserCreationResponseDTO();
        finaldto.setSuccess(true);
        finaldto.setMessage("User successfully created!");

        return finaldto;
    }
}
