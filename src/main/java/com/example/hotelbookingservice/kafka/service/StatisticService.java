package com.example.hotelbookingservice.kafka.service;

import com.example.hotelbookingservice.kafka.model.KafkaMessage;
import com.example.hotelbookingservice.kafka.repository.KafkaMessageRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticService {

    @Value("${app.path.statisticFile}")
    private String statisticFile;

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final KafkaMessageRepository messageRepository;

    public void saveMessage(KafkaMessage message) {
        messageRepository.save(message);
    }

    public void downloadStatistics() {
        List<KafkaMessage> messages = messageRepository.findAll();

        List<String[]> csvRows = parseMessagesToCsvRows(messages);

        try (CSVWriter writer = new CSVWriter(new FileWriter(statisticFile),
                ';',  // Разделитель (можно изменить на ',')
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {

            writer.writeAll(csvRows);
            //System.out.println("Экспорт завершён: " + filePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String[]> parseMessagesToCsvRows(List<KafkaMessage> messages) {
        List<String[]> csvRows = new ArrayList<>();

        csvRows.add(new String[]{"type", "userId", "checkIn", "checkOut"});

        for (KafkaMessage msg : messages) {
            String type = msg.getType();
            String userId = msg.getUserId() != null ? msg.getUserId() : "";

            String checkIn = "";
            String checkOut = "";

            if ("Booking room".equals(type)) {
                if (msg.getCheckIn() != null) {
                    checkIn = msg.getCheckIn().format(DATE_FORMATTER);
                }
                if (msg.getCheckOut() != null) {
                    checkOut = msg.getCheckOut().format(DATE_FORMATTER);
                }
            }
            csvRows.add(new String[]{type, userId, checkIn, checkOut});
        }
        return csvRows;
    }
}
