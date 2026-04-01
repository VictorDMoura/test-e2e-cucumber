Feature: Book a room

  As a customer,
  I want to bok a room
  So that i can make a meeting

  Scenario: Book an available room
    Given the room "Sala América do Sul" exists
    And the room has no bookings for today
    When I book the room for one hour from now
    Then them room should be successfully booked

  Scenario: Conflict whe a room is already booked
    Given the room "Sala Europa" exists
    And the room has no bookings for today
    And one user book the room for one hour from now
    When I book the room for one hour from now
    Then the booking should conflict

