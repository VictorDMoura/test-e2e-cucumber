package com.example.meeting.controller;

import com.example.meeting.repository.BookingRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-utils")
@Profile("test")
public class TestUtilsController {

    private final BookingRepository bookingRepository;

    public TestUtilsController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    @DeleteMapping("/bookings")
    public ResponseEntity<Void> deleteAllBookings(@RequestParam("room_id") Long roomId) {
        bookingRepository.deleteAllByRoomId(roomId);
        return ResponseEntity.noContent().build();
    }
}

