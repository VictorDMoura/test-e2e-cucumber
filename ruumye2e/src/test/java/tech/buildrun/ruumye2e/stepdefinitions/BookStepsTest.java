package tech.buildrun.ruumye2e.stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tech.buildrun.ruumye2e.config.RestConfig;
import tech.buildrun.ruumye2e.config.ScenarioContext;
import tech.buildrun.ruumye2e.dto.BookingRequest;
import tech.buildrun.ruumye2e.service.BookingService;

import java.time.LocalDateTime;

public class BookStepsTest {

    private final ScenarioContext scenarioContext;
    private final RestConfig restConfig;
    private final BookingService bookingService;

    public BookStepsTest(ScenarioContext scenarioContext,
                         RestConfig restConfig,
                         BookingService bookingService) {
        this.scenarioContext = scenarioContext;
        this.restConfig = restConfig;
        this.bookingService = bookingService;
    }

    @And("the room has no bookings for today")
    public void theRoomHasNoBookingsForToday() {
        Long roomId = scenarioContext.get("roomId", Long.class);

        restConfig.givenBackend()
                .queryParam("room_id", roomId)
                .delete("/test-utils/bookings")
                .then()
                .statusCode(204);

    }

    @And("one user book the room for one hour from now")
    @When("I book the room for one hour from now")
    public void iBookTheRoomForOneHourFromNow() {
        var roomId = scenarioContext.get("roomId", Long.class);

        var startTime = LocalDateTime.now().plusHours(1);
        var endTime = startTime.plusHours(1);

        var response = bookingService.book(roomId, startTime, endTime);

        scenarioContext.put("response", response);
    }

    @Then("them room should be successfully booked")
    public void themRoomShouldBeSuccessfullyBooked() {

        var response = scenarioContext.get("response", Response.class);
        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @Then("the booking should conflict")
    public void theBookingShouldConflict() {
        var response = scenarioContext.get("response", Response.class);
        response.then()
                .statusCode(HttpStatus.CONFLICT.value());
    }

    @And("I get the book number")
    public void iGetTheBookNumber() {
        var response = scenarioContext.get("response", Response.class);
        var bookNumber = response.body().jsonPath().getLong("id");

        scenarioContext.put("bookNumber", bookNumber);
    }

    @And("I book the room for right now")
    public void iBookTheRoomForRightNow() {

        var roomId = scenarioContext.get("roomId", Long.class);

        var startTime = LocalDateTime.now().plusSeconds(3);
        var endTime = startTime.plusHours(1);

        var response = bookingService.book(roomId, startTime, endTime);

        scenarioContext.put("response", response);

    }
}
