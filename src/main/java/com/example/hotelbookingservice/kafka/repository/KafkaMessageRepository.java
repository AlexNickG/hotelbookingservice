package com.example.hotelbookingservice.kafka.repository;

import com.example.hotelbookingservice.kafka.model.KafkaMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaMessageRepository extends MongoRepository<KafkaMessage, String> {
}
