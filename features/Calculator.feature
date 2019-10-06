# borrowing calculator
# Tags: optional
    
Feature: Test borrowing calculator

Scenario: Test borrowing calculator

  Given Open chrome and start application
  When User enters valid details
  Then borrowing estimate is displayed correctly
  When User clicks start over button
  Then Form is cleared
  When User does not enter all details
  Then Correct error is displayed
  Then Application should be closed





