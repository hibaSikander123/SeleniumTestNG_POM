# Booking.com Web Automation Test Suite

A Page Object Model (POM)-based Selenium automation framework for testing the Booking.com website's sign-in flow. This project demonstrates modular, maintainable test automation structure with multi-browser support and environment-based configuration.

BookingWebTest_POM/  
├── src/  
│ ├── main/  
│ │ ├── java/com/booking/qa/  
│ │ │ ├── base/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Base setup logic (WebDriver, config)  
│ │ │ ├── config/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Configuration (.properties file)  
│ │ │ ├── pages/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Page Object classes  
│ │ │ └── util/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Reusable utility constants  
│ └── test/  
│ └── java/com/booking/qa/testcases/ &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# TestNG Test Cases  
├── config.properties   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# URL, browser, wait settings  
├── .env &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Secure email config (dotenv)  
├── .gitignore &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Ignored files/folders  
└── pom.xml &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;# Maven dependencies and plugins  


## Features

- Selenium WebDriver with Chrome and Firefox support
- TestNG-based test lifecycle
- Dotenv Integration for secure credential management
- WebDriverManager for automatic driver management
- Page Object Model (POM) design pattern
- Tab handling and navigation
- Explicit and implicit waits

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/yourusername/BookingWebTest_POM.git
   cd BookingWebTest_POM

2. **Configure Environment Variables**  
Create a .env file in the root directory:
   ```bash
   EMAIL = your_email@example.com  
   PROPERTIES_FILE_PATH = your_file_path_of_config.properties_file
3.  **Download Dependencies & Verify Setup**  
    Ensure Maven is installed, then run:
   ```bash
   mvn clean install 
```
4.  **Run the Tests**  
    Execute the test suite (including tests from both HomeTest and SigninTest):
   ```bash
   mvn clean install 
```
* No need to run HomeTest separately, it is inherited and executed automatically.

## Test Flow Overview
1. ### HomeTest.java
- Navigates to the homepage
- Clicks on the sign-in button
- Validates the heading text on the sign-in page

2. ### SigninTest.java
- Inputs the email address from .env
- Navigates to ProtonMail in a new browser tab
- Verifies ProtonMail opened successfully

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
- Ensure browser pop-ups or tab opening restrictions are disabled.
- ProtonMail is accessed via JS tab injection due to CDP issues with newer browsers.
- Credentials are managed securely via the .env file (do not commit this to version control).

# Future Improvements
- Implement OTP reading from ProtonMail inbox
- Extend tests for flight booking workflows


