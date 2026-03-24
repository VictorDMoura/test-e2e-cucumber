package tech.buildrun.ruumye2e.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.springframework.http.MediaType;
import tech.buildrun.ruumye2e.config.RestConfig;
import tech.buildrun.ruumye2e.config.ScenarioContext;
import tech.buildrun.ruumye2e.dto.RoomDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoomStepsTest {


    private ScenarioContext scenarioContext;
    private RestConfig restConfig;

    public RoomStepsTest(ScenarioContext scenarioContext, RestConfig restConfig) {
        this.scenarioContext = scenarioContext;
        this.restConfig = restConfig;
    }

    @When("I list all the rooms")
    public void iListAllTheRooms() {
        var response = restConfig.givenBackend()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .get("/rooms");

        scenarioContext.put("response", response);

    }

    @Then("I detect that room {string} exists")
    public void iDetectThatRoomNameExists(String name) {

        var response = scenarioContext.get("response", Response.class);

        var rooms = response.then()
                .statusCode(200)
                .extract()
                .as(new TypeRef<List<RoomDto>>() {
                });

        assertTrue(rooms.stream()
                .anyMatch(room -> room.name().equals(name)));

    }
}
