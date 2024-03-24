Feature: Validate patch call

  Scenario: Testing valid patch endpoint
    * def query = {bookingid :128}
    Given url 'https://restful-booker.herokuapp.com/booking'
    And params query
    And header Accept = 'application/json'
    * def reqPayload
    """
 {
    "firstname" : "James",
    "lastname" : "Brown"
}"""
    And request reqPayload
    When method patch
    Then status 200
    And print response
    And match response.booking.firstname == 'James'
    And match response.booking.lastname == 'Brown'