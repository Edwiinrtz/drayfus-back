package com.example.xpertgroup.service;

import com.example.xpertgroup.model.user.User;
import com.example.xpertgroup.model.user.UserDTO;
import com.example.xpertgroup.repositories.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    protected IUserRepository iUserRepository;

    public boolean save(UserDTO user){
        try {
            User nUser = User.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build();
            iUserRepository.save(nUser);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public UserDTO getByCredentials(String username, String password){
        try{
            Optional<User> rUser = iUserRepository.getByUsernameAndPassword(username, password);
            if(rUser.isEmpty()) return  null;

            User user = rUser.get();
            return UserDTO.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
