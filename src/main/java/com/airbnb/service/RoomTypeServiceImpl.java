package com.airbnb.service;

import com.airbnb.dto.RoomTypeDto;
import com.airbnb.entity.RoomType;
import com.airbnb.repository.RoomTypeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService{
    private RoomTypeRepository roomTypeRepository;
    private ModelMapper modelMapper;

    @Override
    public RoomTypeDto createRoomType(RoomTypeDto roomTypeDto) {
//        RoomType roomType = mapToEntity(roomTypeDto);
        RoomType roomType = modelMapper.map(roomTypeDto, RoomType.class);
        RoomType saved = roomTypeRepository.save(roomType);
//        RoomTypeDto roomTypeDto1 = mapToDto(save);
        RoomTypeDto roomTypeDto1 = modelMapper.map(saved, RoomTypeDto.class);
        return roomTypeDto1;
    }

    @Override
    public RoomType getByRoomType(String type_of_room) {
        RoomType roomType = roomTypeRepository.findByTypeOfRoom(type_of_room).get();
        return roomType;
    }

}
