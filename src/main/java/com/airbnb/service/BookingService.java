package com.airbnb.service;

import com.airbnb.dto.BookingDto;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {
    BookingDto createBookingDetails(BookingDto bookingDto);

    List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate);
}
