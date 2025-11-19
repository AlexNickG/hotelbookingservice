package com.example.hotelbookingservice.kafka.service;

import com.example.hotelbookingservice.kafka.model.BookingRoom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StatisticService {

//    @Value("${app.path.statisticFile}")
//    private String statisticFile;

    private final Path path = Path.of("${app.path.statisticFile}");


    public Function<BookingRoom, List<String>> getRowMapper() {
        return bookingRoom -> {
            DateTimeFormatter df = DateTimeFormatter.ISO_LOCAL_DATE;
            return List.of(
                    Objects.toString(bookingRoom.getUserId(), ""),
                    bookingRoom.getCheckIn() != null ? bookingRoom.getCheckIn().format(df) : "",
                    bookingRoom.getCheckOut() != null ? bookingRoom.getCheckOut().format(df) : ""
            );
        };
    }

    public void writeBookingRoomsToCsv(List<BookingRoom> items) throws IOException {
        Path parent = path.getParent() == null ? Path.of(".") : path.getParent();
        Files.createDirectories(parent);

        try (BufferedWriter writer = Files.newBufferedWriter(path,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

            for (BookingRoom item : items) {
                List<String> cols = getRowMapper().apply(item);
                String row = cols.stream()
                        .map(s -> escapeCsv(Objects.toString(s, "")))
                        .collect(Collectors.joining(","));
                writer.write(row);
                writer.newLine();
            }
        }
    }

    private String escapeCsv(String s) {
        if (s == null || s.isEmpty()) return "";
        String escaped = s.replace("\"", "\"\"");
        boolean mustQuote = s.contains(",") || s.contains("\"") || s.contains("\n") || s.contains("\r");
        return mustQuote ? "\"" + escaped + "\"" : escaped;
    }


}
