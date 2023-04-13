@Grid
Feature: title_test

  @chome
  Scenario: TC01_test_chrome
    Given user is on the homepage "https://www.managementonschools.com/" by "chrome"
    Then verify title is "Managementon Schools" by "chrome"
    And close the remote driver

  @edge
  Scenario: TC02_test_edge
    Given user is on the homepage "https://www.managementonschools.com/" by "edge"
    Then verify title is "Managementon Schools" by "edge"
    And close the remote driver

  @firefox
  Scenario: TC03_test_firefox
    Given user is on the homepage "https://www.managementonschools.com/" by "firefox"
    Then verify title is "Managementon Schools" by "firefox"
    And close the remote driver


