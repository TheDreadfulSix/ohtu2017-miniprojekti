"""
Feature: As user i can delete reference
    
    Scenario: delete reference successfully
        Given user has selected delete Reference for some reference
        And user confirms delete when alerted
        When user selects "Ok"-button
        Then reference is deleted

    Scenario: delete reference successfully cancelled
        Given user has selected delete Reference for some reference
        And user confirms delete when alerted
        When user selects "Cancel"-button
        Then reference is NOT deleted
"""