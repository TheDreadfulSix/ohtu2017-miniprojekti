
Feature: An user can edit reference
    
    Scenario Outline: Edit reference works
        Given user chooses to edit reference that exists
        When user gives valid change
        Then edit reference is success

    Scenario Outline: Edit reference does not work if required field changed to empty
        Given user chooses to edit reference that exists
        When user deletes text from required field
        Then did not edit reference
