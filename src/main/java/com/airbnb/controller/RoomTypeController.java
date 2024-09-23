package com.airbnb.controller;


import com.airbnb.dto.RoomTypeDto;
import com.airbnb.service.RoomTypeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/roomType")
public class RoomTypeController {

    private RoomTypeService roomTypeService;

    @PostMapping
    public ResponseEntity<RoomTypeDto> createRoomType(@RequestBody RoomTypeDto roomTypeDto){
        RoomTypeDto roomType = roomTypeService.createRoomType(roomTypeDto);
        return new ResponseEntity<>(roomType, HttpStatus.CREATED);
    }
}
