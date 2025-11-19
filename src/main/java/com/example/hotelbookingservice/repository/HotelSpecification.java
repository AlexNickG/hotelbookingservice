package com.example.hotelbookingservice.repository;

import com.example.hotelbookingservice.dto.HotelFilter;
import com.example.hotelbookingservice.entity.Hotel;
import com.example.hotelbookingservice.entity.Room;
import org.springframework.data.jpa.domain.Specification;

public interface HotelSpecification {

    static Specification<Hotel> withFilter(HotelFilter filter) {
        return Specification.where(byName(filter.getName()))
                .and(byHotelId(filter.getId()))
                .and(byAdTitle(filter.getAdTitle()))
                .and(byCity(filter.getHotelCity()))
                .and(byAddress(filter.getAddress()))
                .and(byDistance(filter.getDistance()))
                .and(byRating(filter.getRating()))
                .and(byRatingsNum(filter.getRatingsNum()));
    }

    static Specification<Hotel> byHotelId(Long id) {
        return (root, query, cb) -> {
            if (id == null) {
                return null;
            }
            return cb.equal(root.get("id"), id);
        };
    }
    static Specification<Hotel> byName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isBlank()) {
                return null;
            }
            return cb.equal(root.get("name"), name);
        };
    }

    static Specification<Hotel> byAdTitle(String adTitle) {
        return (root, query, cb) -> {
            if (adTitle == null || adTitle.isBlank()) {
                return null;
            }
            return cb.equal(root.get("ad_title"), adTitle);
        };
    }

    static Specification<Hotel> byCity(String hotelCity) {
        return (root, query, cb) -> {
            if (hotelCity == null || hotelCity.isBlank()) {
                return null;
            }
            return cb.equal(root.get("city"), hotelCity);
        };
    }

    static Specification<Hotel> byAddress(String address) {
        return (root, query, cb) -> {
            if (address == null || address.isBlank()) {
                return null;
            }
            return cb.equal(root.get("address"), address);
        };
    }

    static Specification<Hotel> byDistance(Integer distance) {
        return (root, query, cb) -> {
            if (distance == null || distance == 0) {
                return null;
            }
            return cb.lessThanOrEqualTo(root.get("distance"), distance);
        };
    }

    static Specification<Hotel> byRating(Float rating) {
        return (root, query, cb) -> {
            if (rating == null || rating == 0) {
                return null;
            }
            return cb.greaterThanOrEqualTo(root.get("rating"), rating);
        };
    }

    static Specification<Hotel> byRatingsNum(Integer ratingsNum) {
        return (root, query, cb) -> {
            if (ratingsNum == null || ratingsNum == 0) {
                return null;
            }
            return cb.equal(root.get("number_of_reviews"), ratingsNum);
        };
    }
}
