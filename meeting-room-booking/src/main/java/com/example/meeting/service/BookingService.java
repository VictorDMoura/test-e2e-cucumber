package com.example.meeting.service;

import com.example.meeting.exception.AlreadyBookedException;
import com.example.meeting.exception.UnbookException;
import com.example.meeting.model.Booking;
import com.example.meeting.model.Room;
import com.example.meeting.repository.BookingRepository;
import com.example.meeting.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    public Booking book(Long roomId, LocalDateTime start, LocalDateTime end) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        List<Booking> conflicts = bookingRepository
            .findByRoomAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(room, end, start);

        if (!conflicts.isEmpty()) {
            throw new AlreadyBookedException("Booking conflict!");
        }

        return bookingRepository.save(new Booking(room, start, end));
    }

    public void cancel(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        if (booking.getStartTime().isBefore(LocalDateTime.now())) {
            throw new UnbookException("Cannot cancel past or started booking.");
        }
        bookingRepository.deleteById(bookingId);
    }
}