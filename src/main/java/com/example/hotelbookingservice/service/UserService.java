package com.example.hotelbookingservice.service;

import com.example.hotelbookingservice.entity.User;
import com.example.hotelbookingservice.repository.UserRepository;
import com.example.hotelbookingservice.utils.BeanUtils;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findByName(String name) {
        return repository.findByName(name);
    }

    public User create(User user) {
        if (repository.findByNameAndEmail(user.getName(), user.getEmail()) != null) {
            throw new EntityExistsException(
                    MessageFormat.format("User with name {0} and email {1} exists", user.getName(), user.getEmail()));
        }
        return repository.save(user);
    }

    public User update(Long id, User user) {
        User existedUser = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("User with id {0} not found", id)));
        if (existedUser != null) {
            BeanUtils.copyNonNullProperties(user, existedUser);
            return repository.save(existedUser);
        }
        return null;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
