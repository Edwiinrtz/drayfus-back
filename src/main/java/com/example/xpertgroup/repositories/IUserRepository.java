package com.example.xpertgroup.repositories;

import com.example.xpertgroup.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {

    Optional<User> getByUsernameAndPassword(String username, String Password);
}
