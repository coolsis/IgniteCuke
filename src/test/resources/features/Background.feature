@eifB
Feature: Registration Enrollment InquiryForm successfully
  Background:
    Given User is in the "map" page

  @eifB-s
 Scenario Outline: selecting school with name
    Given user select the "<schoolName>" school
    Then user completes the registration
   Examples:
     |schoolName  |
     | EIF101     |
     | EIF102     |
     | EIF103     |
     | EIF104     |

   @eifB-i
  Scenario Outline: selecting school with name
    Given user select the <order> school
     Then user completes the registration
    Examples:
      |order  |
      | 1     |
      | 4     |
      | 3     |
      | 5     |