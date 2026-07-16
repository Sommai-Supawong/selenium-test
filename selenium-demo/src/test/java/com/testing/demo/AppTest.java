package com.testing.demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AppTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.create("chrome");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void testGoogleSearchTitle() {
        driver.get("https://www.google.com");

        wait.until(ExpectedConditions.titleIs("Google"));
        String actualTitle = driver.getTitle();
        System.out.println("Page Title is: " + actualTitle);

        Assert.assertEquals(actualTitle, "Google", "Title does not match!");
    }

    @Test
    public void testWikipediaSearch() {
        driver.get("https://www.wikipedia.org/");

        WebElement searchBox = wait.until(
            ExpectedConditions.elementToBeClickable(By.id("searchInput"))
        );
        searchBox.sendKeys("Software engineering");

        WebElement searchButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']"))
        );
        searchButton.click();

        WebElement heading = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.id("firstHeading"))
        );
        String actualText = heading.getText();

        Assert.assertEquals(actualText, "Software engineering", "หัวข้อไม่ตรงกับข้อมูลที่ค้นหา!");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
