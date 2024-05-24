#Feature: Update user
#
#  Scenario: Successfully update a user
#    Given a user with ID 1 exists
#    When I send a PUT request to 1 with the following data
#      | fieldName | newValue     |
#      | firstname | UpdatedFirstName|
#      | lastname  | UpdatedLastName |
#      | email     | updated@example.com|
#      | username  | UpdatedUsername |
#      | password  | UpdatedPassword |
#
#    Then the response status should be 200
#    And the response should contain the updated user details
#      | firstname | UpdatedFirstName|
#      | lastname  | UpdatedLastName |
#      | email     | updated@example.com|
#      | username  | UpdatedUsername |
#      | password  | UpdatedPassword |