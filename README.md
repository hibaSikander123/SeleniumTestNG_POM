# Booking.com Web Automation Test Suite

A Page Object Model (POM)-based Selenium automation framework for testing the Booking.com website's complete sign-in and verification flow. This project demonstrates modular, maintainable test automation structure with multi-browser support, environment-based configuration, and TestNG-based test orchestration.

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
│   │   │   └── util/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Reusable utility constants  
│   └── test/  
│   │   └── java/com/booking/qa/testcases/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# TestNG Test Cases \
│   │   │   ├── BaseTest \
│   │   │   ├── HomeTest \
│   │   │   ├── SigninTest \
│   │   │   ├── VerificationCodeTest \
├── config.properties &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; # URL, browser, wait settings  
├── .env &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; # Secure email config (dotenv)  
├── testng.xml &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; # TestNG suite configuration  
├── .gitignore &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; # Ignored files/folders  
└── pom.xml &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; # Maven dependencies and plugins


## Features

- Complete authentication flow, testing the entire sign-in process including email verification
- Email Integration involving the Logging into ProtonMail to retrieve verification code
- Selenium WebDriver with Chrome and Firefox support 
- TestNG-based test orchestration with suite-level setup/teardown 
- Dotenv Integration for secure credential management 
- WebDriverManager for automatic driver management 
- Page Object Model (POM) design pattern 
- Continuous test execution flow across multiple test classes 
- Tab handling and navigation 
- Explicit and implicit waits

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
 
3.  **Download Dependencies & Verify Setup**  
    Ensure Maven is installed, then run:
   ```bash
   mvn clean install 
```
4.  **Run the Tests**  
    Execute the test suite (including tests from both HomeTest and SigninTest):
   ```bash
   mvn test
  ```
   No need to run HomeTest separately, it is inherited and executed automatically.
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
5. ### Suite Teardown (BaseTest)
- Browser cleanup and session termination

## TestNG Configuration
The test execution is controlled by `testng.xml` which:
- Defines test execution order between classes 
- Maintains a single browser session across all tests 
- Uses `preserve-order="true"` to ensure sequential execution 
- Sets up dependencies between test methods


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
- The framework maintains a single browser session across all test classes 
- Test execution order is controlled through TestNG dependencies and configuration 
- Ensure browser pop-ups or tab opening restrictions are disabled 
- Credentials are managed securely via the .env file (do not commit this to version control)

# Future Improvements
- Extend tests for flight booking workflows


