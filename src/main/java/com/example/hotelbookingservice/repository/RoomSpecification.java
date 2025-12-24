package com.example.hotelbookingservice.repository;

import com.example.hotelbookingservice.dto.RoomFilter;
import com.example.hotelbookingservice.entity.Room;
import com.example.hotelbookingservice.entity.UnavailableDate;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface RoomSpecification {

    static Specification<Room> withFilter(RoomFilter filter) {

        return Specification.where(byName(filter.getName()))
                .and(byRoomId(filter.getId()))
                .and(byCost(filter.getMinCost(), filter.getMaxCost()))
                .and(byCapacity(filter.getCapacity()))
                .and(byAccessiblePeriod(filter.getOccupancyStart(), filter.getOccupancyEnd()))
                .and(byHotelId(filter.getHotelId()));
    }

    static Specification<Room> byRoomId(Long id) {
        return (root, query, cb) -> {
            if (id == null) {
                return null;
            }
            return cb.equal(root.get("id"), id);
        };
    }

    static Specification<Room> byName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return null;
            }
            return cb.equal(root.get("name"), name);
        };
    }

    static Specification<Room> byCost(BigDecimal minCost, BigDecimal maxCost) {
        return (root, query, cb) -> {
            if (minCost == null && maxCost == null) {
                return null;
            }
            if (minCost == null) {
                return cb.lessThanOrEqualTo(root.get("cost"), maxCost);
            }
            if (maxCost == null) {
                return cb.greaterThanOrEqualTo(root.get("cost"), minCost);
            }
            return cb.between(root.get("cost"), minCost, maxCost);

        };
    }

    static Specification<Room> byCapacity(Integer capacity) {
        return (root, query, cb) -> {
            if (capacity == null || capacity == 0) {
                return null;
            }
            return cb.greaterThanOrEqualTo(root.get("capacity"), capacity);
        };
    }

    static Specification<Room> byAccessiblePeriod(LocalDate occupancyStart, LocalDate occupancyEnd) {
        return (root, query, cb) -> {
            if (occupancyStart == null && occupancyEnd == null) {
                return null;
            }

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<UnavailableDate> subRoot = subquery.from(UnavailableDate.class);
            subquery.select(subRoot.get("id"));

            Predicate overlap;
            if (occupancyStart != null && occupancyEnd != null) {
                overlap = cb.and(
                        cb.lessThanOrEqualTo(subRoot.get("endOccupancy"), occupancyEnd),
                        cb.greaterThanOrEqualTo(subRoot.get("startOccupancy"), occupancyStart)
                );
            } else if (occupancyStart != null) {
                overlap = cb.greaterThanOrEqualTo(subRoot.get("startOccupancy"), occupancyStart);
            } else {
                overlap = cb.lessThanOrEqualTo(subRoot.get("endOccupancy"), occupancyEnd);
            }

            subquery.where(
                    cb.equal(subRoot.get("room"), root),
                    overlap
            );

            return cb.not(cb.exists(subquery));
        };

    }

    static Specification<Room> byHotelId(Long hotelId) {
        return (root, query, cb) -> {
            if (hotelId == null) {
                return null;
            }
            return cb.equal(root.get("hotel").get("id"), hotelId);
        };
    }
}
