package com.example.hotelbookingservice.repository;

import com.example.hotelbookingservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    Page<User> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"roles"})
    Optional<User> findByUsername(String name);

    Optional<User> findByUsernameAndEmail(String name, String email);

}
