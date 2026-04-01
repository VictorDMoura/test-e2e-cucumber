package tech.buildrun.ruumye2e.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record BookingRequest(
        Long roomId,
        @JsonFormat(shape = JsonFormat.Shape.STRING) LocalDateTime startTime,
        @JsonFormat(shape = JsonFormat.Shape.STRING) LocalDateTime endTime

) {
}
