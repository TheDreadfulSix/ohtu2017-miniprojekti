"""
Feature: An user can edit reference

    Scenario: edit reference successfully
        Given user has selected edit Reference for some reference
        And user makes changes to reference
        When user selects "Save"-button
        Then reference is saved
    
    Scenario: edit reference unsuccessfully
        Given user has selected edit Reference for some reference
        And user removes required field
        When user selects "Save"-button
        Then reference is NOT saved, user alerted
"""
