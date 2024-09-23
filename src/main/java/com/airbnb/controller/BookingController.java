package com.airbnb.controller;


import com.airbnb.dto.BookingDto;
import com.airbnb.entity.AppUser;
import com.airbnb.entity.Property;
import com.airbnb.entity.Room;
import com.airbnb.service.*;
import com.airbnb.util.PDFService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/booking")
public class BookingController {
    private RoomService roomService;
    private PropertyService propertyService;
    private BookingService bookingService;
    private PDFService pdfService;
    private SmsService smsService;
    private WhatsAppService whatsAppService;

    @PostMapping("/createBooking")
    public ResponseEntity<?> createBooking(
            @RequestParam long propertyId,
            @RequestParam String roomTypeName,
            @RequestParam int numberOfRooms,
            @RequestBody BookingDto bookingDto,
            @AuthenticationPrincipal AppUser user
    ) {
        Property property = propertyService.getById(propertyId);
        List<LocalDate> datesBetween = bookingService.getDatesBetween(bookingDto.getCheckInDate(), bookingDto.getCheckOutDate());
        List<Room> rooms = new ArrayList<>();
        for (LocalDate date : datesBetween) {
            Room room = roomService.getByPropertyIdAndType(propertyId, roomTypeName, date);

            if (room.getCount() == 0) {
//                rooms.removeAll((Collection<?>) room);
                return new ResponseEntity<>("No Rooms Available ", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            rooms.add(room);

        }

        //booking
        float total = 0;
        for (Room room : rooms) {
            total = total + room.getPrice() * numberOfRooms;
        }
        System.err.println(total);

        bookingDto.setTotal_price(total);
        bookingDto.setProperty(property);
        bookingDto.setRoomsBooked(numberOfRooms);
        bookingDto.setAppUser(user);
        bookingDto.setTypeOfRoom(roomTypeName);
        BookingDto bookingDetails = bookingService.createBookingDetails(bookingDto);


        //if condition necessary bcz if booking is complete then Update the room
        if (bookingDetails != null) {
            for (Room room : rooms) {
                room.setCount(room.getCount() - numberOfRooms);
                roomService.updateRoom(room);


            }
        }

        //Generate a PDF
        pdfService.generatePDF(bookingDetails);

        //Send sms confirmation
        smsService.sendSms(bookingDetails.getMobile(),"Your booking is confirmed. Your Booking Id : "+bookingDetails.getId());

        //Send WhatsApp Confirmation

        whatsAppService.sendWhatsAppMessageWithMedia(bookingDetails.getMobile(),"Your Booking is Confirmed, Your Booking Id is : "+bookingDetails.getId(),"https://bappi599.s3.eu-north-1.amazonaws.com/DSC_08651.jpg");

        return new ResponseEntity<>(bookingDetails, HttpStatus.CREATED);
    }

}

