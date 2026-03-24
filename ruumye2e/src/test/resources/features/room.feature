Feature: List Rooms

  As a customer,
  I want to know all the available rooms
  so that i can book then

  Scenario Outline: List Rooms
    When I list all the rooms
    Then I detect that room "<name>" exists

    Examples:
    | name |
    | Sala América do Sul |
    | Sala Europa |
    | Sala América do Norte |
    | Sala África |
