package com.example.hotelbookingservice.repository;

import com.example.hotelbookingservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);

    User findByNameAndEmail(String name, String email);

}
