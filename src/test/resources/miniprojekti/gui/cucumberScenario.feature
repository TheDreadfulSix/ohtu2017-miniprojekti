<<<<<<< HEAD

=======
"""
This scenario test does not test the GUI, but the functionality.
Slightly redundant, but works as test.
"""
Feature: An user can edit reference
    
    Scenario: edit reference works
        Given user chooses to edit reference that exists
        When user gives valid change
        Then edit reference is success

    Scenario: edit reference does not work if required field changed to empty
        Given user chooses to edit reference that exists
        When user deletes text from required field
        Then did not edit reference
>>>>>>> master
