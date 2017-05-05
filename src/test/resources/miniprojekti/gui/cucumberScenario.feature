
Feature: An user can edit reference
    
    Scenario: Edit reference works
        Given user chooses to edit reference that exists
        When user gives valid change
        Then edit reference is success
