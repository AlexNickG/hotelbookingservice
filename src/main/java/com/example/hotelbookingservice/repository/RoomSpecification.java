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

            // предикат пересечения интервалов
            Predicate overlap;
            if (occupancyStart != null && occupancyEnd != null) {
                // u.startDate <= end && u.endDate >= start
                overlap = cb.and(
                        cb.lessThanOrEqualTo(subRoot.get("endOccupancy"), occupancyEnd),
                        cb.greaterThanOrEqualTo(subRoot.get("startOccupancy"), occupancyStart)
                );
            } else if (occupancyStart != null) {
                // ищем записи с u.endDate >= start
                overlap = cb.greaterThanOrEqualTo(subRoot.get("startOccupancy"), occupancyStart);
            } else {
                // occupancyEnd != null — ищем записи с u.startDate <= end
                overlap = cb.lessThanOrEqualTo(subRoot.get("endOccupancy"), occupancyEnd);
            }

            subquery.where(
                    cb.equal(subRoot.get("room"), root),
                    overlap
            );

            // возвращаем комнаты, для которых НЕ существует пересекающейся unavailableDate
            return cb.not(cb.exists(subquery));
        };

//        Specification<Room> roomSpecification =
//         (root, query, cb) -> {
//            if (occupancyStart == null && occupancyEnd == null) {
//                return null;
//            }
//            var subquery = query.subquery(Long.class);
//            var subRoot = subquery.from(Room.class);
//            var joinDates = subRoot.join("occupancyDates");
//            subquery.select(subRoot.get("id"))
//                    .where(
//                            cb.equal(subRoot.get("id"), root.get("id")),
//                            cb.between(joinDates.as(LocalDate.class), occupancyStart, occupancyEnd)
//                    );
//
//            // Основной запрос: исключаем комнаты, у которых есть занятые даты в диапазоне
//            return cb.not(cb.exists(subquery));
//            //if ()
//            //return cb.equal(root.get("room").get("occupancyDates"), occupancyStart);
//        };
//return roomSpecification;
    }

    static Specification<Room> byHotelId(Long hotelId) {
        return (root, query, cb) -> {
            if (hotelId == null) {
                return null;
            }
            return cb.equal(root.get("hotel_id"), hotelId);
        };
    }
}
