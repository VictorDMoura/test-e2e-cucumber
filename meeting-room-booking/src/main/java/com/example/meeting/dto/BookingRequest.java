package com.example.meeting.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BookingRequest(
    @NotNull Long roomId,
    @NotNull @Future LocalDateTime startTime,
    @NotNull @Future LocalDateTime endTime
) {}