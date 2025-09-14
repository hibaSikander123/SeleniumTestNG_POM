package com.booking.qa.testcases;

import com.booking.qa.base.TestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends TestBase {

    @BeforeMethod
    public void setUp() {
        initialization();
    }

    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}