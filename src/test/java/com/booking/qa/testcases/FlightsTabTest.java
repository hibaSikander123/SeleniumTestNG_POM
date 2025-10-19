package com.booking.qa.testcases;

import com.booking.qa.pages.FlightsTabPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FlightsTabTest extends BaseTest {
    private FlightsTabPage flightsTabPage;

    @BeforeMethod
    public void settingUp() {
        flightsTabPage = new FlightsTabPage(driver, wait);
    }

    @Test(dependsOnMethods = "com.booking.qa.testcases.VerificationCodeTest.feedVCodeTest")
    public void goToFlightsTabTest()
    {
        flightsTabPage.goToFlightsTab();
        String verifyFlightTabText = flightsTabPage.fetchFlightTabText();
        Assert.assertEquals(verifyFlightTabText, "Compare and book cheap flights with ease", "Unable to reach to the flight tab");
    }

    @Test(dependsOnMethods = "goToFlightsTabTest")
    public void selectSourceLocationTest()
    {
        flightsTabPage.selectSourceLocation();
        String verifySourceLocation = flightsTabPage.fetchSourceLocationText();
        Assert.assertEquals(verifySourceLocation, "BER", "Unable to select source location");
    }

    @Test(dependsOnMethods = "selectSourceLocationTest")
    public void selectDestLocationTest()
    {
        flightsTabPage.selectDestLocation();
        String verifyDestLocation = flightsTabPage.fetchDestLocationText();
        Assert.assertEquals(verifyDestLocation, "Paris", "Unable to select Destination location");
    }

    @Test(dependsOnMethods = "selectDestLocationTest")
    public void selectCalendarDateTest()
    {
        flightsTabPage.selectCalendarDate();
    }

    @Test(dependsOnMethods = "selectCalendarDateTest")
    public void selectNumOfPassengerTest()
    {
        flightsTabPage.selectNumOfPassenger();
        String verifyPassengerNum = flightsTabPage.fetchPassengerNumText();
        Assert.assertEquals(verifyPassengerNum, "2 adults", "Unable to add passenger");
    }

    @Test(dependsOnMethods = "selectNumOfPassengerTest")
    public void searchFlightTest()
    {
        flightsTabPage.searchFlight();
        String verifyFilteredFlightsDisplay = flightsTabPage.fetchCheapestTabText();
        Assert.assertEquals(verifyFilteredFlightsDisplay, "Cheapest", "Unable to display filtered (by cheapest) flight list succesfully");
    }
}

