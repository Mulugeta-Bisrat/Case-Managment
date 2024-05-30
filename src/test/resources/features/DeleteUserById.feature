Feature: Delete User by ID
  As an administrator
  I want to delete a user by ID
  So that the user is marked as inactive

  Scenario: Successfully delete an existing user by ID
    Given a user exists with ID 1 and is active
    When I delete the user with ID 1
    Then the user with ID 1 should be marked as inactive

  Scenario: Attempt to delete a non-existent user by ID
    When I delete the user with ID 999
    Then I should receive a "User not found with id: 999" error

