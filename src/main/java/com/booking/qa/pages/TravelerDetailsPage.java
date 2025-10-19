package com.booking.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class TravelerDetailsPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By locatorAddCarryOnBag = By.xpath("//div[@data-testid = \"grouped_inputs_traveller_1\"]//div[@data-testid = \"cabin\"]//span[contains(@class, \"Icon-module__root InputCheckbox-module__checkbox-icon Icon-module__root--size-small\")]");
    private By locatorAddTraveler1DetailsBtn = By.xpath("//button[@aria-label='Add this traveler’s details Adult 1']//span[contains(@class, 'Button-module__text') and contains(., 'Add this traveler’s details')]");
    private By firstNameInput1 = By.xpath("//div[contains(@class, \"SheetContainer-module__body\")]/div/div[1]/div/div/div/input");
    private By lastNameInput1 = By.xpath("//div[contains(@class, \"SheetContainer-module__body\")]/div/div[2]/div/div/div/input");
    private By genderInput1 = By.xpath("//div[contains(@class, \"SheetContainer-module__body\")]/div/div[3]/div/div/select/option[@data-key = \"female\"]");
    private By birthDateInput1 = By.xpath("//input[@data-testid= \"traveller_data_field_passenger_0_dd\"]\n");
    private By birthYearInput1 = By.xpath("//input[@data-testid= \"traveller_data_field_passenger_0_yyyy\"]\n");
    private By doneDetailsBtn1 = By.xpath("//span[text()= \"Done\"]");
    private By locatorAddTraveler2DetailsBtn = By.xpath("//button[@aria-label='Add this traveler’s details Adult 2']//span[contains(@class, 'Button-module__text') and contains(., 'Add this traveler’s details')]");
    private By firstNameInput2 = By.xpath("//div[contains(@class, \"SheetContainer-module__body\")]/div/div[1]/div/div/div/input");
    private By lastNameInput2 = By.xpath("//div[contains(@class, \"SheetContainer-module__body\")]/div/div[2]/div/div/div/input");
    private By genderInput2 = By.xpath("//div[contains(@class, \"SheetContainer-module__body\")]/div/div[3]/div/div/select/option[@data-key = \"male\"]");
    private By birthDateInput2 = By.xpath("//input[@data-testid= \"traveller_data_field_passenger_1_dd\"]\n");
    private By birthYearInput2 = By.xpath("//input[@data-testid= \"traveller_data_field_passenger_1_yyyy\"]\n");
    private By doneDetailsBtn2 = By.xpath("//span[text()= \"Done\"]");
    private By locatorPhoneCodeInput = By.xpath("//div[@data-testid = \"checkout_form_phone\"]//select/option[@data-key = \"de\"]");
    private By phoneInput = By.xpath("//div[@data-testid = \"checkout_form_phone\"]//input[contains(@class, \"InputText-module__control\")]");
    private By locatorNextBtn1 = By.xpath("//span[contains(@class, 'Button-module__text') and contains(., 'Next')]");

    @FindBy(xpath = "//span[contains(@class, 'Button-module__text') and contains(., \"Edit this traveler's details\")]")
    WebElement travelerDetailsEditBTn;
    @FindBy(xpath = "//div[contains(@class, 'HiddenVisually-module__root')]/parent::span/parent::span/following-sibling::strong[contains(., 'Your details')]")
    WebElement yourDetailsStepDone;
    @FindBy(xpath = "//div[@data-testid='upb_title-extra-cabinbaggagepertraveller']")
    WebElement cabinBagCheckedIn;

    // Initializing page factory / page object
    public TravelerDetailsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // Enter details for the first Traveller (details are conditioned accordingly if they are asked or not)
    public void feedTraveler1Details(String firstName1, String lastName1, String birthMonth1, String nationality1, String birthDate1, String birthYear1) {
        WebElement addTraveler1DetailsBtn = wait.until(ExpectedConditions.elementToBeClickable(locatorAddTraveler1DetailsBtn));
        addTraveler1DetailsBtn.click();
        driver.findElement(firstNameInput1).sendKeys(firstName1);
        driver.findElement(lastNameInput1).sendKeys(lastName1);
        driver.findElement(genderInput1).click();

        List<WebElement> birthMonthDropdown1 = driver.findElements(By.xpath("//div[contains(@class, \"SheetContainer-module__body\")]/div/div[4]/fieldset/div/div/div/div/select/option[@data-key = " + birthMonth1 + "]"));
        List<WebElement> nationalityInput1 = driver.findElements(By.xpath("//select[@name = 'passengers.0.nationality']/option[@data-key = " + nationality1 + "]"));

        //Check if DOB is asked in traveler_1's detail, if yes then enter
        if (!birthMonthDropdown1.isEmpty()) {
            birthMonthDropdown1.get(0).click();
            driver.findElement(birthDateInput1).sendKeys(birthDate1);
            driver.findElement(birthYearInput1).sendKeys(birthYear1);
        } else {
            System.out.println("DOB wasn't asked for passenger 1");
        }
        //Check if nationality is asked in traveler_1's detail, if yes then enter
        if (!nationalityInput1.isEmpty()) {
            nationalityInput1.get(0).click();
        } else {
            System.out.println("nationality wasn't asked for passenger 1");
        }
        driver.findElement(doneDetailsBtn1).click();
    }

    public String fetchTraveler1EditBtnText() {
        return travelerDetailsEditBTn.getText();
     }

    // Enter details for the second Traveller (details are conditioned accordingly if they are asked or not)
    public void feedTraveler2Details(String firstName2, String lastName2, String birthMonth2, String nationality2, String birthDate2, String birthYear2) {
        WebElement addTraveler2DetailsBtn = wait.until(ExpectedConditions.elementToBeClickable(locatorAddTraveler2DetailsBtn));
        addTraveler2DetailsBtn.click();
        driver.findElement(firstNameInput2).sendKeys(firstName2);
        driver.findElement(lastNameInput2).sendKeys(lastName2);
        driver.findElement(genderInput2).click();

        List<WebElement> birthMonthDropdown2 = driver.findElements(By.xpath("//div[contains(@class, 'SheetContainer-module__body')]/div/div[4]/fieldset/div/div/div/div/select/option[@data-key = " + birthMonth2 + "]"));
        List<WebElement> nationalityInput2 = driver.findElements(By.xpath("//select[@name = 'passengers.1.nationality']/option[@data-key =" + nationality2 + "]"));

        //Check if DOB is asked in traveler_2's detail, if yes then enter
        if (!birthMonthDropdown2.isEmpty()) {
            birthMonthDropdown2.get(0).click();
            driver.findElement(birthDateInput2).sendKeys(birthDate2);
            driver.findElement(birthYearInput2).sendKeys(birthYear2);
        } else {
            System.out.println("DOB wasn't asked for passenger 2");
        }
        //Check if nationality is asked in traveler_2's detail, if yes then enter
        if (!nationalityInput2.isEmpty()) {
            nationalityInput2.get(0).click();
        } else {
            System.out.println("nationality wasn't asked for passenger 2");
        }
        driver.findElement(doneDetailsBtn2).click();
    }

    public String fetchTraveler2EditBtnText() {
        return travelerDetailsEditBTn.getText();
    }

    //Check if cabin bag can be added, if yes then add for traveler_1
    public String addCabinBag() {
        List<WebElement> addCarryOnBag = driver.findElements(locatorAddCarryOnBag);

        if(!addCarryOnBag.isEmpty()){
            addCarryOnBag.get(0).click();
            return "cabinBagAdded";
        } else {
            return "cabinBagNotAdded";
        }
    }

    public String fetchcabinBagSelectionText() {
        return cabinBagCheckedIn.getText();
    }

    // Enter phone num
    public void enterPhoneNum(String phoneNum){
        WebElement phoneCodeInput = wait.until(ExpectedConditions.elementToBeClickable(locatorPhoneCodeInput));
        phoneCodeInput.click();
        driver.findElement(phoneInput).sendKeys(phoneNum);
    }

    // Click the next button to go to the next step
    public void goToExtrasPage(){
        WebElement nextBtn1 = wait.until(ExpectedConditions.elementToBeClickable(locatorNextBtn1));
        nextBtn1.click();
    }

    public String fetchDetailsStepDoneMark() {
        return yourDetailsStepDone.getText();
    }
}
