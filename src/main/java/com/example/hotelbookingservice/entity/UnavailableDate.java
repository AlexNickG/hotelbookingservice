package com.example.hotelbookingservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnavailableDate implements Comparable<UnavailableDate> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne
//    @JoinColumn
    private LocalDate startOccupancy;

    private LocalDate endOccupancy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Override
    public int compareTo(UnavailableDate other) {
        int compare = this.startOccupancy.compareTo(other.startOccupancy);
        if (compare != 0) return compare;
        compare = this.endOccupancy.compareTo(other.endOccupancy);
        if (compare != 0) return compare;
        return Long.compare(this.id == null ? 0 : this.id, other.id == null ? 0 : other.id);
    }
}
