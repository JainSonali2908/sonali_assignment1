Feature: Validate put call

  Background: url service
    * def authToken = call read('token.feature')


  Scenario: Testing valid put endpoint
    * def query = {bookingid :1227}
    Given url 'https://restful-booker.herokuapp.com/booking'
    And params query
    And header Accept = 'application/json'
    And request read ('test.json')
    When method put
    Then status 200
    And print response
    And match response.booking.firstname == 'Jim'