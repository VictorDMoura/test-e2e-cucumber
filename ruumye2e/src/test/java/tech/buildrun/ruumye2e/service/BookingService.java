package tech.buildrun.ruumye2e.service;

import io.restassured.response.Response;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import tech.buildrun.ruumye2e.config.RestConfig;
import tech.buildrun.ruumye2e.dto.BookingRequest;

import java.time.LocalDateTime;

@Service
public class BookingService {

    private final RestConfig restConfig;

    public BookingService(RestConfig restConfig) {
        this.restConfig = restConfig;
    }

    public Response book(Long roomId, LocalDateTime startTime, LocalDateTime endTime) {

        var request = new BookingRequest(roomId, startTime, endTime);

        return restConfig.givenBackend()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .post("/bookings");
    }
}
