
Feature: An user can edit reference
    
    Scenario: Edit reference works
        Given user chooses to edit reference that exists
        When user gives valid change
        Then edit reference is success

    Scenario: Filtering causes no errors and shows only filtered
        Given user has added multiple references
        When  user filters references with a keyword
        Then logic return only filtered list
