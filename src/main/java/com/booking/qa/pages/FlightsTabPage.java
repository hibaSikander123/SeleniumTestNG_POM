package com.booking.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

public class FlightsTabPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By locatorFlightTab = By.xpath("//*[@id=\"flights\"]");
    private By locatorDenyCookieButton = By.id("onetrust-reject-all-handler");
    private By locatorFromLocation = By.xpath("//button[@class = 'ButtonFlip-module__flipButton___XVKxZ']/preceding-sibling::button[1]");
    private By locatorFromLocationInput = By.xpath("//input[@class= 'AutoComplete-module__textInput___Qh3I- ']");
    private By locatorFromLocationDropdown = By.xpath("//span[@class = \"List-module__content___48Y6B\"]/*/*");
    private By locatorFromLocation2 = By.xpath("//div[@class= \"fee1919d4f\"]//span[@class = \"b99b6ef58f df2a437b42\" and text() = \"Leaving from\"]");
    private By locatorFromLocationInput2 = By.xpath("//input[@data-ui-name = \"input_text_autocomplete\"]");
    private By locatorFromLocationDropdown2 = By.xpath("//input[@name = \"AIRPORTBER\"]");
    private By locatorToLocation = By.xpath("//button[contains(@class, 'ButtonFlip-module__flipButton')]/following-sibling::button[1]");
    private By locatorToLocationInput = By.xpath("//input[@class = 'AutoComplete-module__textInput___Qh3I- ']");
    private By locatorToLocationDropdown = By.xpath("//ul[@id = 'flights-searchbox_suggestions']/li[1]");
    private By locatorCalendarField = By.xpath("//div[contains(@class, 'SegmentHorizontal-module__date')]");
    private By locatorNumOfPassengerBtn = By.xpath("//div[contains(@class, 'SegmentHorizontal-module__line')]/button");
    private By locatorAddPassengerBtn = By.xpath("//button[@data-ui-name = \"button_occupancy_adults_plus\"]");
    private By locatorDoneBtn = By.xpath("//button[@data-ui-name=\"button_occupancy_action_bar_done\"]");
    private By locatorSearchButton = By.xpath("//span[contains(., \"Search\")]");

    @FindBy(xpath = "//h1[contains(.,'Compare and book cheap flights with ease' )]")
    WebElement locatorVerifyflightTabText;
    @FindBy(xpath = "//span[contains(@class, 'ContentWithTitle-module__content_with_title__content')]/b[contains(., 'BER')]\n")
    WebElement locatorVerifySourceLocation;
    @FindBy(xpath = "//span[contains(@class, 'ContentWithTitle-module__content_with_title__content')]/b[contains(., 'Paris')]\n")
    WebElement locatorVerifyDestLocation;
    @FindBy(xpath = "//span[contains(@class, 'ContentWithTitle-module__content_with_title__content') and contains(., '2 adults')]")
    WebElement locatorPassengerNum;
    @FindBy(xpath = "//span[contains(@class, 'Text-module__root--variant-body') and contains(.,'Cheapest')]")
    WebElement locatorCheapeastBtn;

    // Instance variables for date management
    private LocalDate currentStartDate;
    private LocalDate today;
    private DateTimeFormatter dateFormatter;
    private static final int maxRetryDays = 30; // Maximum days to retry

    // Initializing page factory / page object
    public FlightsTabPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.today = LocalDate.now();
        this.currentStartDate = today;
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        PageFactory.initElements(driver, this);
    }

    // Click on Flight tab
    public void goToFlightsTab() {
        WebElement flightTab = wait.until(ExpectedConditions.elementToBeClickable(locatorFlightTab));
        flightTab.click();
        driver.findElement(locatorDenyCookieButton).click();
    }

    public String fetchFlightTabText() {
        return locatorVerifyflightTabText.getText();
    }

    // Remove default location and enter Berlin as source location
    public void selectSourceLocation() {
        List<WebElement> autoLocation = driver.findElements(locatorFromLocation);
        if (!autoLocation.isEmpty()) {
            autoLocation.get(0).click();
            By locatorDefaultLocationCross = By.xpath("//span[contains(@class, \"Text-module__root--variant-emphasized_2\")]/following-sibling::span");
            WebElement defaultLocationCross = wait.until(ExpectedConditions.elementToBeClickable(locatorDefaultLocationCross));
            defaultLocationCross.click();
            driver.findElement(locatorFromLocationInput).sendKeys("Berlin");
            driver.findElement(locatorFromLocationDropdown).click();
        } else {
            driver.findElement(locatorFromLocation2).click();
            driver.findElement(locatorFromLocationInput2).sendKeys("Berlin");
            driver.findElement(locatorFromLocationDropdown2).click();
        }
    }

    public String fetchSourceLocationText() {
        return locatorVerifySourceLocation.getText();
    }

    // Enter Paris as destination location
    public void selectDestLocation() {
        WebElement ToLocation = wait.until(ExpectedConditions.elementToBeClickable(locatorToLocation));
        ToLocation.click();
        driver.findElement(locatorToLocationInput).sendKeys("Paris");
        WebElement ToLocationDropdown = wait.until(ExpectedConditions.elementToBeClickable(locatorToLocationDropdown));
        ToLocationDropdown.click();
    }

    public String fetchDestLocationText() {
        return locatorVerifyDestLocation.getText();
    }

    // Select round-trip dates (starting date to be (today/or the day after today) and ending date to be (the 1st date of next month))
    public void selectCalendarDate(LocalDate startDate) {
        WebElement calendarField = wait.until(ExpectedConditions.elementToBeClickable(locatorCalendarField));
        calendarField.click();

        String startDateStr = startDate.format(dateFormatter);
        String startDateXpath = "//span[@data-date= '" + startDateStr + "']";

        boolean dateFound = false;
        int startDateVisibility = driver.findElements(By.xpath(startDateXpath)).size();

        // Check if the target date(today) is visible. if not, navigate calendar
        int maxTries = 4;
        for (int i = 0; i < maxTries; i++) {
            if (startDateVisibility > 0) {
                driver.findElement(By.xpath(startDateXpath)).click();
                dateFound = true;
                System.out.println("Selected date: " + startDateStr);
                break;
            } else {
                System.out.println("Target date not visible. Clicking 'prev'...");
                WebElement prevButton = driver.findElement(By.xpath("//button[contains(@class, 'Calendar-module__control--prev')]"));
                prevButton.click();
                startDateVisibility = driver.findElements(By.xpath(startDateXpath)).size();
            }
        }
        if (!dateFound) {
            throw new NoSuchElementException("Target date " + startDateStr + " not found in calendar after trying previous months.");
        }

        // Select destination date (first clickable date in next month)
        driver.findElement(By.xpath("//button[contains(@class, 'Calendar-module__control--next')]")).click();
        WebElement toDate = driver.findElement(By.xpath("//span[@data-date and not(contains(@class,'disabled'))]"));
        toDate.click();

        // Update current start date
        this.currentStartDate = startDate;
    }

    // Overloaded method for backward compatibility
    public void selectCalendarDate() {
        selectCalendarDate(today);
    }

    // Add one more passenger
    public void selectNumOfPassenger() {
        // Add Num of Passengers
        driver.findElement(locatorNumOfPassengerBtn).click();
        driver.findElement(locatorAddPassengerBtn).click();
        driver.findElement(locatorDoneBtn).click();
    }

    public String fetchPassengerNumText() {
        return locatorPassengerNum.getText();
    }

    // Search for the flight with retry logic
    public void searchFlight() {
        int retryCount = 0;
        boolean flightsFound = false;

        while (!flightsFound && retryCount <= maxRetryDays) {
            WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(locatorSearchButton));
            searchBtn.click();
            System.out.println("Search button clicked with start date: " + currentStartDate);

            // Wait for search results to load
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector(".SearchLoadingIllustration-module__root___80xa8")));

            // Check if no flights message is displayed
            List<WebElement> noFlightsElements = driver.findElements(
                    By.xpath("//div[text() = \"We don't have any flights matching your search on our site. Try changing some details.\"]"));
            if (!noFlightsElements.isEmpty() && noFlightsElements.get(0).isDisplayed()) {
                System.out.println("No flights found for start date: " + currentStartDate + ". Retrying with next day...");
                retryCount++;

                if (retryCount <= maxRetryDays) {
                    // Increment start date by one day
                    LocalDate nextStartDate = currentStartDate.plusDays(1);

                    // Reopen calendar and select new date
                    WebElement calendarField = wait.until(ExpectedConditions.elementToBeClickable(locatorCalendarField));
                    calendarField.click();

                    selectCalendarDate(nextStartDate);
                }
            } else {
                flightsFound = true;
                System.out.println("Flights found for start date: " + currentStartDate);
            }
        }

        if (!flightsFound) {
            throw new RuntimeException("No flights found after trying " + maxRetryDays + " different start dates");
        }
    }

    public String fetchCheapestTabText() {
        // Wait for loading animation to disappear then get text from "cheapest" tab to verify flights display
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector(".SearchLoadingIllustration-module__root___80xa8")));
        return locatorCheapeastBtn.getText();
    }
}