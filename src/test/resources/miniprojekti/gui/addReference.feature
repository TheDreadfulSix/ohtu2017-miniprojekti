"""
Feature: As user i can add reference

    Scenario: Add an reference successfully
        Given user has selected create Reference
        And user has entered required fields
        When user selects "create"-button
        Then create new article with given attributes

    Scenario: Add an reference successfully cancelled
        Given user has selected create Reference
        And user has entered required fields
        When user selects "cancel"-button
        Then new reference not created

    Scenario: Required field empty when adding reference
        Given user has selected create Reference
        And user has NOT entered all required fields
        When user selects "create"-button
        Then alert user with: "required fields can not be empty"
    
    Scenario: User gives both required alternative fields
        Given user has selected create Reference
        And user selects reference that has alternative fields
        And user has entered something to both alternative fields
        When user selects "create"-button
        Then alert user with: "only one alternative field can be filled"
"""
