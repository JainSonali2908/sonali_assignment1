Feature:Token generation

  Scenario: Create Restful Booker HerokuApp
    Given url 'https://restful-booker.herokuapp.com/auth'
    Given header Accept = 'application/json'
    * def reqPayload =
     """
     {
     "username" : "admin",
     "password" : "password123"
     }
     """
    And request reqPayload
    When method POST
    Then status 200
    And print response