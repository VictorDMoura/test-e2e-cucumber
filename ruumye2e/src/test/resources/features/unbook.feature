Feature: Unbook a room

  As a customer,
  I want to unbook a room,
  so that can I can reschedule my agenda when something occurs

  Scenario: Unbook a booked room
    Given the room "Sala América do Norte" exists
    And the room has no bookings for today
    And I book the room for one hour from now
    And I get the book number
    When I try to unbook the room
    Then the room should be successfully unbooked

  Scenario: Unbook a booked room after start time
    Given the room "Sala África" exists
    And the room has no bookings for today
    And I book the room for right now
    And I get the book number
    And the book time has already passed
    And I try to unbook the room
    Then the room cannot not be unbooked

