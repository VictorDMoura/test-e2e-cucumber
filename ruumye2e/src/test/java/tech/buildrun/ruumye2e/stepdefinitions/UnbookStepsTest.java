package tech.buildrun.ruumye2e.stepdefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import tech.buildrun.ruumye2e.config.RestConfig;
import tech.buildrun.ruumye2e.config.ScenarioContext;

public class UnbookStepsTest {

    private ScenarioContext scenarioContext;
    private RestConfig restConfig;

    public UnbookStepsTest(ScenarioContext scenarioContext,
                           RestConfig restConfig) {
        this.scenarioContext = scenarioContext;
        this.restConfig = restConfig;
    }

    @When("I try to unbook the room")
    public void iTryToUnbookTheRoom() {
        var bookNumber = scenarioContext.get("bookNumber", Long.class);

        var response = restConfig.givenBackend()
                .pathParam("bookNumber", bookNumber)
                .delete("/bookings/{bookNumber}");

        scenarioContext.put("response", response);
    }

    @Then("the room should be successfully unbooked")
    public void theRoomShouldBeSuccessfullyUnbooked() {
        var response = scenarioContext.get("response", Response.class);

        response.then()
                .statusCode(HttpStatus.OK.value());
    }

    @And("the book time has already passed")
    public void theBookTimeHasAlreadyPassed() throws InterruptedException {
        Thread.sleep(5000);
    }

    @Then("the room cannot not be unbooked")
    public void theRoomCannotNotBeUnbooked() {
        var response = scenarioContext.get("response", Response.class);
        response.then()
                .statusCode(HttpStatus.UNPROCESSABLE_CONTENT.value());
    }
}
