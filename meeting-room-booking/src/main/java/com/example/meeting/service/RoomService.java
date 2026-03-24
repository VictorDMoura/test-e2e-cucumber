package com.example.meeting.service;

import com.example.meeting.dto.BookingDTO;
import com.example.meeting.model.Booking;
import com.example.meeting.model.Room;
import com.example.meeting.repository.BookingRepository;
import com.example.meeting.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

    public RoomService(RoomRepository roomRepository,
                       BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Room> listRooms() {
        return roomRepository.findAll();
    }

    public List<Booking> listBookings(Long roomId) {
        return bookingRepository.findByRoomId(roomId);
    }
}