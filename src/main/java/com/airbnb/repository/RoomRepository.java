package com.airbnb.repository;

import com.airbnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {


//    @Query("SELECT r FROM Room r WHERE r.roomType.type_of_room = :roomTypeName AND r.property.id = :propertyId")
//    Room findRoomsByRoomTypeAndProperty(@Param("roomTypeName") String roomTypeName, @Param("propertyId") Long propertyId);

    @Query("SELECT r FROM Room r WHERE r.property.id = :propertyId AND r.roomType.typeOfRoom = :roomTypeName AND r.date = :date")
    Room findRoomsByPropertyAndRoomTypeAndDate(
            @Param("propertyId") Long propertyId,
            @Param("roomTypeName") String roomTypeName,
            @Param("date") LocalDate date
    );

}