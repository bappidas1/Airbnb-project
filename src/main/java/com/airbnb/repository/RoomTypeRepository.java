package com.airbnb.repository;

import com.airbnb.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomTypeRepository extends JpaRepository<RoomType, Long> {
    Optional<RoomType> findByTypeOfRoom(String typeOfRoom);
}
