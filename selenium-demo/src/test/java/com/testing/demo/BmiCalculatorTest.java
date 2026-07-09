package com.testing.demo;

import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BmiCalculatorTest {

    // Driver สำหรับควบคุม Firefox
    private WebDriver driver;

    // Explicit Wait
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        // 1. ระบุ Path ของ geckodriver.exe
        System.setProperty(
            "webdriver.gecko.driver",
            "drivers/geckodriver.exe"
        );

        // 2. เปิด Firefox
        driver = new FirefoxDriver();

        // 3. ขยาย Browser
        driver.manage().window().maximize();

        // 4. กำหนด Explicit Wait สูงสุด 10 วินาที
        wait = new WebDriverWait(
            driver,
            Duration.ofSeconds(10)
        );
    }

    @Test
    public void testBmiCalculatorWithMetricInput() {

        // =========================
        // Arrange: Test Data
        // =========================

        int age = 25;
        String gender = "m";
        int heightCm = 180;
        int weightKg = 65;

        // เปิดหน้า BMI Calculator แบบ Metric
        driver.get(
            "https://www.calculator.net/bmi-calculator.html?ctype=metric"
        );

        // =========================
        // Act 1: Input Age
        // =========================

        WebElement ageInput = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.name("cage")
            )
        );

        ageInput.clear();
        ageInput.sendKeys(
            String.valueOf(age)
        );

        System.out.println(
            "Age input = " + age
        );

        // =========================
        // Act 2: Select Gender
        // =========================

        By genderLocator = By.cssSelector(
            "input[name='csex'][value='" + gender + "']"
        );

        // Radio อาจถูกซ่อนด้วย CSS
        // จึงรอแค่ presence ไม่ใช้ elementToBeClickable
        WebElement genderRadio = wait.until(
            ExpectedConditions.presenceOfElementLocated(
                genderLocator
            )
        );

        // Debug สถานะ Element
        System.out.println(
            "Gender displayed = "
                + genderRadio.isDisplayed()
        );

        System.out.println(
            "Gender enabled = "
                + genderRadio.isEnabled()
        );

        System.out.println(
            "Gender selected before = "
                + genderRadio.isSelected()
        );

        // ถ้ายังไม่ได้เลือก Male
        if (!genderRadio.isSelected()) {

            JavascriptExecutor js =
                (JavascriptExecutor) driver;

            js.executeScript(
                "arguments[0].click();",
                genderRadio
            );
        }

        // ตรวจสอบ Gender
        Assert.assertTrue(
            genderRadio.isSelected(),
            "ไม่สามารถเลือก Gender = Male ได้!"
        );

        System.out.println(
            "Gender selected after = "
                + genderRadio.isSelected()
        );

        // =========================
        // Act 3: Input Height
        // =========================

        WebElement heightInput = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.name("cheightmeter")
            )
        );

        heightInput.clear();
        heightInput.sendKeys(
            String.valueOf(heightCm)
        );

        System.out.println(
            "Height input = "
                + heightCm
                + " cm"
        );

        // =========================
        // Act 4: Input Weight
        // =========================

        WebElement weightInput = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.name("ckg")
            )
        );

        weightInput.clear();
        weightInput.sendKeys(
            String.valueOf(weightKg)
        );

        System.out.println(
            "Weight input = "
                + weightKg
                + " kg"
        );

        // =========================
        // Act 5: Click Calculate
        // =========================

        WebElement calculateButton = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector(
                    "input[type='submit'][value='Calculate']"
                )
            )
        );

        calculateButton.click();

        System.out.println(
            "Calculate button clicked"
        );

        // =========================
        // Assert 1:
        // Calculate Expected BMI
        // =========================

        double heightMeter =
            heightCm / 100.0;

        double expectedBmi =
            weightKg
            / Math.pow(heightMeter, 2);

        String expectedBmiText =
            String.format(
                Locale.US,
                "%.1f",
                expectedBmi
            );

        System.out.println(
            "Expected BMI = "
                + expectedBmiText
        );

        // =========================
        // Assert 2:
        // Wait for Result
        // =========================

        By pageBody =
            By.tagName("body");

        wait.until(
            ExpectedConditions
                .textToBePresentInElementLocated(
                    pageBody,
                    "BMI = " + expectedBmiText
                )
        );

        String pageText =
            driver.findElement(
                pageBody
            ).getText();

        // =========================
        // Verify BMI
        // =========================

        Assert.assertTrue(
            pageText.contains(
                "BMI = " + expectedBmiText
            ),
            "BMI ไม่ตรงกับค่าที่คาดหวัง!"
        );

        System.out.println(
            "BMI result verified successfully"
        );

        // =========================
        // Verify Category
        // =========================

        Assert.assertTrue(
            pageText.contains("(Normal)"),
            "BMI Category ไม่ใช่ Normal!"
        );

        System.out.println(
            "BMI category = Normal"
        );

        System.out.println(
            "TEST PASSED"
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}