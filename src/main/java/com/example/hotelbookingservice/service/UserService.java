package com.example.hotelbookingservice.service;

import com.example.hotelbookingservice.entity.Role;
import com.example.hotelbookingservice.entity.User;
import com.example.hotelbookingservice.exception.EntityExistsException;
import com.example.hotelbookingservice.exception.EntityNotFoundException;
import com.example.hotelbookingservice.kafka.mapper.EventMapper;
import com.example.hotelbookingservice.kafka.model.KafkaMessage;
import com.example.hotelbookingservice.repository.UserRepository;
import com.example.hotelbookingservice.utils.BeanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    private final EventMapper eventMapper;

    @Value("${app.kafka.registerUserTopic}")
    private String topicName;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String name) {
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("User with name {0} not found", name)));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("User with id {0} not found", id)));
    }

    public User save(User user, Role role) {
        if (userRepository.findByUsernameAndEmail(user.getUsername(), user.getEmail()).isPresent()) {//TODO: проверять отдельно существование имени пользователя и его почты
            throw new EntityExistsException(
                    MessageFormat.format("User with name {0} and email already {1} exists", user.getUsername(), user.getEmail()));
        }
        user.setRoles(Collections.singletonList(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        role.setUser(user);
        User savedUser = userRepository.save(user);
        kafkaTemplate.send(topicName, eventMapper.registerUserToKafkaMessage(savedUser));
        return savedUser;
    }

    public User update(User user) {
        User existedUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("User with id {0} not found", user.getId())));
        //if (existedUser != null) {
        BeanUtils.copyNonNullProperties(user, existedUser);
        if(user.getPassword() != null) {
            existedUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(existedUser);
        //}
        //return null;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }


}
