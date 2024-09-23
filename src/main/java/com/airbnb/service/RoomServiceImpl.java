package com.airbnb.service;

import com.airbnb.dto.RoomDto;
import com.airbnb.entity.Room;
import com.airbnb.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private ModelMapper modelMapper;

    @Override
    public RoomDto createRoomDetails(RoomDto roomDto) {
        // Convert DTO to entity
        Room roomEntity = modelMapper.map(roomDto, Room.class);
        // Save entity to the database
        Room savedRoom = roomRepository.save(roomEntity);
        // Convert the saved entity back to DTO
        return modelMapper.map(savedRoom, RoomDto.class);
    }

    @Override
    public Room getByPropertyIdAndType(long propertyId, String roomType, LocalDate date) {
        // Fetch the room entity based on property ID and room type
        return roomRepository.findRoomsByPropertyAndRoomTypeAndDate(propertyId,roomType,  date);
    }



    @Override
    public Room saveRoom(Room room) {

        // Save entity to the database
        return roomRepository.save(room);
        // Convert the saved entity back to DTO

    }

    @Override
    public String updateRoom(Room room) {
        roomRepository.save(room);
        return "rooms are booked";
    }


//    private RoomDto mapToDto(Room room) {
//        RoomDto dto = new RoomDto();
//        dto.setId(room.getId());
//        dto.setCount(room.getCount());
//        dto.setPrice(room.getPrice());
//        dto.setRoomType(room.getRoomType());
//        dto.setDate(room.getDate());
//        dto.setProperty(room.getProperty());
//        return dto;
//    }
//
//    private Room mapToEntity(RoomDto dto) {
//        Room room = new Room();
//        room.setId(dto.getId());
//        room.setCount(dto.getCount());
//        room.setRoomType(dto.getRoomType());
//        room.setPrice(dto.getPrice());
//        room.setDate(dto.getDate());
//        room.setProperty(dto.getProperty());
//        return room;
//    }
}
