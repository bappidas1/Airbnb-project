package com.airbnb.service;

import com.airbnb.dto.BookingDto;
import com.airbnb.entity.Booking;
import com.airbnb.repository.BookingRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService{
    private BookingRepository bookingRepository;
    private ModelMapper modelMapper;




    @Override
    public BookingDto createBookingDetails(BookingDto bookingDto) {
        Booking bookingEntity = modelMapper.map(bookingDto, Booking.class);
        Booking save = bookingRepository.save(bookingEntity);
        return modelMapper.map(save, BookingDto.class);
    }

    @Override
    public List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> dates = new ArrayList<>();
        LocalDate currentDate = startDate;
        while(!currentDate.isAfter(endDate)){
            dates.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        return dates;
    }
}
