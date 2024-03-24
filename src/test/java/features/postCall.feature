Feature:Create A Restful Booker For HerokuApp

  Background: url service
    * url 'https://restful-booker.herokuapp.com/'
    * def authToken = call read('token.feature')

  @Create
  Scenario: Create Restful Booker HerokuApp
    Given path 'booking'
    Given header Accept = 'application/json'
    And request read ('test.json')
    When method POST
    Then status 200
    And print response
    And match response.booking.firstname == 'Jim'
    And match response.booking.bookingdates.checkin == '#notnull'
    And match response.booking contains { 'additionalneeds' : 'Breakfast'}