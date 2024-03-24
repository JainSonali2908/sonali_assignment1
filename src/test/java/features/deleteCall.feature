Feature: Validate delete call

  Background: url service
    * def authToken = call read('token.feature')


  Scenario: Testing valid delete endpoint
    * def query = {bookingid :127}
    Given url 'https://restful-booker.herokuapp.com/booking'
    And params query
    And header Accept = 'application/json'
    When method delete
    Then status 200