package com.example.meeting.controller;

import com.example.meeting.dto.BookingDTO;
import com.example.meeting.dto.BookingRequest;
import com.example.meeting.model.Booking;
import com.example.meeting.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingDTO book(@RequestBody @Valid BookingRequest request) {
        Booking booking = bookingService.book(
            request.roomId(), request.startTime(), request.endTime()
        );
        return new BookingDTO(
            booking.getId(),
            booking.getRoom().getId(),
            booking.getStartTime(),
            booking.getEndTime()
        );
    }

    @DeleteMapping("/{id}")
    public void cancel(@PathVariable Long id) {
        bookingService.cancel(id);
    }
}