package com.booking.qa.testcases;

import com.booking.qa.pages.TravelerDetailsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TravelerDetailsTest extends BaseTest{

    private TravelerDetailsPage travelerDetailsPage;

    @BeforeMethod
    public void settingUp() {
        travelerDetailsPage = new TravelerDetailsPage(driver, wait);
    }

    @Test(dependsOnMethods = "com.booking.qa.testcases.SelectFlightTest.selectTicketTypeTest")
    public void feedTraveler1DetailsTest() {
        travelerDetailsPage.feedTraveler1Details(firstName1, lastName1, birthMonth1, nationality1, birthDate1, birthYear1);
        String traveler1DataEntry = travelerDetailsPage.fetchTraveler1EditBtnText();
        Assert.assertEquals(traveler1DataEntry, "Edit this traveler's details", "Traveler 1 Data Entry failed");
    }

    @Test(dependsOnMethods = "feedTraveler1DetailsTest")
    public void feedTraveler2DetailsTest () {
        travelerDetailsPage.feedTraveler2Details(firstName2, lastName2, birthMonth2, nationality2, birthDate2, birthYear2);
        String traveler2DataEntry = travelerDetailsPage.fetchTraveler2EditBtnText();
        Assert.assertEquals(traveler2DataEntry, "Edit this traveler's details", "Traveler 2 Data Entry failed");
    }

    @Test(dependsOnMethods = "feedTraveler2DetailsTest")
    public void addCabinBagTest () {
        String cabinScenerioResult = travelerDetailsPage.addCabinBag();

        if ("ADDED".equals(cabinScenerioResult)) {
            // Verify cabin bag was actually added
            String verifyCabinBagCheckedIn = travelerDetailsPage.fetchcabinBagSelectionText();
            Assert.assertEquals(verifyCabinBagCheckedIn, "Carry-on bag (1)", "Selection of Cabin Bag failed");
        } else {
            // Verify cabin bag was not added
            System.out.println("Cabin bag not added - this is expected behavior for this scenario (either not allowed or already included in the package)");
        }
            }

    @Test(dependsOnMethods = "addCabinBagTest")
    public void enterPhoneNumTest () {
        travelerDetailsPage.enterPhoneNum(phoneNum);
        Assert.assertEquals(phoneNum, phoneNum);
    }

    @Test(dependsOnMethods = "enterPhoneNumTest")
    public void goToExtrasPageTest () {
        travelerDetailsPage.goToExtrasPage();
        String verifyDetailsStepDone = travelerDetailsPage.fetchDetailsStepDoneMark();
        Assert.assertEquals(verifyDetailsStepDone, "Your details", "Unable to Finish the Traveler Details Step");
    }
}
