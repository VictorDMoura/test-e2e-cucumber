package com.example.meeting.repository;

import com.example.meeting.dto.BookingDTO;
import com.example.meeting.model.Booking;
import com.example.meeting.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
        Room room, LocalDateTime endTime, LocalDateTime startTime
    );

    List<Booking> findByRoomId(Long roomId);
    void deleteAllByRoomId(Long roomId);
}