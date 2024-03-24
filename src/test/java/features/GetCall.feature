Feature: Validate get call


  Scenario: Testing valid GET endpoint
    * def query = {bookingid :1227}
    Given url 'https://restful-booker.herokuapp.com/booking'
    And params query
    When method GET
    Then status 200
    And print response
    And match response contains { 'bookingid' : '#notnull'}