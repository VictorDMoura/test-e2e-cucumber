package com.example.meeting.dto;

import java.time.LocalDateTime;

public record BookingDTO(Long id, Long roomId, LocalDateTime startTime, LocalDateTime endTime) {
}