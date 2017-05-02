#Tests only gui-side of the creation, Junit-tests take care of functionality.
"""
Feature: As user i can create a .bib file

    Scenario: create .bib file successfully
        Given user selects "create .bib file"
        When user enters valid name for the file
        And user selects "create"-button
        Then file successfully created 
    
    Scenario: create .bib file with wrong filename
        Given user selects "create .bib file"
        When user enters invalid name for the file
        And user selects "create"-button
        Then file unsuccessfully created, user alerted

    Scenario: create .bib file with custom path
        Given user selects "create .bib file"
        When user enters valid name for the file
        And user enters valid path for the file
        And user selects "create"-button
        Then file successfully created

    Scenario: create .bib file with custom path
        Given user selects "create .bib file"
        When user enters valid name for the file
        And user enters invalid path for the file
        And user selects "create"-button
        Then file unsuccessfully created, user alerted
"""