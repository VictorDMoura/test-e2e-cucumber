package tech.buildrun.ruumye2e.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import tech.buildrun.ruumye2e.config.RestConfig;
import tech.buildrun.ruumye2e.config.ScenarioContext;
import tech.buildrun.ruumye2e.dto.BookingRequest;

import java.time.LocalDateTime;

public class BookStepsTest {

    private ScenarioContext scenarioContext;
    private RestConfig restConfig;

    public BookStepsTest(ScenarioContext scenarioContext,
                         RestConfig restConfig) {
        this.scenarioContext = scenarioContext;
        this.restConfig = restConfig;
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

        var request = new BookingRequest(roomId, startTime, endTime);

        var response = restConfig.givenBackend()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request)
                .post("/bookings");

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
}
