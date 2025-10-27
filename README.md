# Booking.com Web Automation Test Suite

A comprehensive  Page Object Model (POM) based Selenium automation framework for testing the Booking.com website's complete flight booking workflow. This project demonstrates modular, maintainable test automation with multi-browser support, environment-based configuration, and TestNG-based test orchestration.

SeleniumTestNG_POM/  
├── src/  
│   ├── main/  
│   │   ├── java/com/booking/qa/  
│   │   │   ├── base/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Base setup logic (TestBase with suite-level setup) \
│   │   │   ├── config/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Configuration (.properties file)  
│   │   │   ├── pages/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Page Object classes  
│   │   │   │   ├── HomePage \
│   │   │   │   ├── SigninPage \
│   │   │   │   ├── VerificationCodePage \
│   │   │   │   ├── FlightsTabPage \
│   │   │   │   ├── SelectFlightPage \
│   │   │   │   ├── TravelerDetailsPage \
│   │   │   │   ├── ExtrasPage \
│   │   │   │   ├── SeatSelectionPage \
│   │   │   └── util/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Reusable utility constants  
│   └── test/  
│   │   └── java/com/booking/qa/testcases/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# TestNG Test Cases \
│   │   │   ├── BaseTest \
│   │   │   ├── HomeTest \
│   │   │   ├── SigninTest \
│   │   │   ├── VerificationCodeTest \
│   │   │   ├── FlightsTabTest \
│   │   │   ├── SelectFlightTest \
│   │   │   ├── TravelerDetailsTest \
│   │   │   ├── ExtrasTest \
│   │   │   ├── SeatSelectionTest \
├── config.properties &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; # URL, browser, wait settings  
├── .env &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; # Secure email config (dotenv)  
├── testng.xml &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; # TestNG suite configuration  
├── .gitignore &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; # Ignored files/folders  
└── pom.xml &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; # Maven dependencies and plugins


## Features

- **Complete authentication flow**: end-to-end testing of sign-in process including email verification
- **Email Integration**: Automated login to ProtonMail to retrieve verification codes
- **Flight Booking Automation**: Comprehensive flight search,filtering and selection workflow
- **Traveler Details Management**: Complete passenger information entry with conditional field handling
- **Extras Selection**: Flexible ticket and travel protection insurance with conditional availability checks
- **Seat Selection**: Automated seat selection with fallback handling
- **Advanced Retry Logic**: Includes sophisticated retry logic to handle dynamic content and availability issues
- **TestNG-based test orchestration**: with suite-level setup/teardown
- **Dotenv Integration**: for secure credential management 
- **Multi-Browser Support**: Chrome and Firefox support via Selenium WebDriverManager
- **Page Object Model (POM)**: Maintainable and reusable code structure
- **Tab handling and navigation** 
- **Explicit and implicit waits**

## Prerequisites
- Java 21 or higher 
- Maven 3.6 or higher 
- ProtonMail account (for email verification)

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/BookingWebTest_POM.git
   cd BookingWebTest_POM

2. **Configure Environment Variables**  
Create a .env file in the root directory:
   ```bash
    PROPERTIES_FILE_PATH = "your_file_path_of_config.properties_file"
    USER_EMAIL = "your_protonmail@email.com"
    PASSWORD = "your_protonmail_password"
    FIRSTNAME1 = "Traveler1FirstName"
    LASTNAME1 = "Traveler1LastName"
    BIRTHMONTH1 = "birth_month_value_1"
    NATIONALITY1 = "nationality_code_1"
    BIRTHDATE1 = "DD_forTraveler1"
    BIRTHYEAR1 = "YYYY_forTraveler1"
    FIRSTNAME2 = "Traveler2FirstName"
    LASTNAME2 = "Traveler2LastName"
    BIRTHMONTH2 = "birth_month_value_2"
    NATIONALITY2 = "nationality_code_2"
    BIRTHDATE2 = "DD_forTraveler2"
    BIRTHYEAR2 = "YYYY_forTraveler2"
    PHONENUM = "your_phone_number"
 
3.  **Download Dependencies & Verify Setup**  
    Ensure Maven is installed, then run:
   ```bash
   mvn clean install 
```
4.  **Run the Tests**  
    Run with specific TestNG suite:
   ```bash
   mvn test -DsuiteXmlFile=testng.xml
  ```
   
## Complete Test Flow Overview
The tests execute in a continuous flow within a single browser session using advanced multi-tab handling:


1. ### Suite Setup (BaseTest)
- Single browser initialization 
- Configuration loading
2. ### Navigation to Signin Process (HomeTest)
- Navigates to Booking.com homepage 
- Clicks sign-in button 
- Verifies sign-in page title
3. ### Sign-in Process (SigninTest)
- Enters email address from environment variables 
- Opens ProtonMail in a new browser tab 
- Verifies ProtonMail URL
4. ### Email Verification (VerificationCodeTest)
- Logs into ProtonMail account 
- Navigates to inbox and retrieves latest email 
- Extracts verification code from email body 
- Returns to Booking.com tab and inputs verification code
- Verifies successful authentication
5. ### Flight Search (FlightsTabTest)
-  Navigates to Flights tab and handles cookie consent 
- Selects source location (Berlin) with fallback logic
- Selects destination location (Paris)
- Smart calendar date selection with retry logic
- Adds additional passenger
- Flight search with automatic retry for unavailable dates
6. ### Flight Selection (SelectFlightTest)
- Filters flights by cheapest option
- Selects cheapest flight
- Chooses standard ticket type 
- Navigates to traveler details page
7. ### Traveler Details Entry (TravelerDetailsTest)
- Traveler 1 Details: Enters first passenger details with conditional field handling for DOB and nationality 
- Traveler 2 Details: Enters second passenger details with conditional field handling for DOB and nationality
- Baggage Selection: Conditionally adds cabin baggage if available 
- Contact Information: Enters phone number with country code selection 
- Navigation: Proceeds to next step (extras) completing the traveler details step
8. ### Extras Selection (ExtrasTest)
- Flexible Ticket: Conditionally selects flexible ticket option if available 
- Travel Protection: Conditionally adds travel protection insurance if available 
- Navigation: Navigates to next step (seat selection or direct to payment)
9. ### Seat Selection (SeatSelectionTest)
- First Available Seat: Selects first available seat (if seat selection page is present)
- Checkout: Proceeds to final checkout step
10. ### Suite Teardown (BaseTest)
- Browser cleanup and session termination

## TestNG Configuration
The test execution is controlled by `testng.xml` which:
- Defines test execution order between classes 
- Maintains a single browser session across all tests 
- Uses `preserve-order="true"` to ensure sequential execution 
- Method-level dependencies ensuring proper workflow
- Suite-level setup and teardown



## ️ Tech Stack

| Tool               | Description                               |
|--------------------|-------------------------------------------|
| Java 21            | Programming language                      |
| Selenium 4.33.0    | Browser automation                        |
| TestNG 7.10.2      | Test framework                            |
| Maven              | Build automation                          |
| WebDriverManager   | Auto-manages browser drivers              |
| Dotenv Java        | Loads env variables from `.env` file      |

# Notes
- Ensure browser pop-ups or tab opening restrictions are disabled 
- Credentials are managed securely via the .env file (do not commit this to version control)



