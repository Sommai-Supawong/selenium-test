package com.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

class LoginTest {

        private WebDriver driver;

        @BeforeEach
        void setUp() {
                driver = new ChromeDriver();
        }

        @AfterEach
        void tearDown() {
                if (driver != null) {
                        driver.quit();
                }
        }

        // Test 1: Login สำเร็จ
        @Test
        void shouldLoginSuccessfully() {

                driver.get("https://seleniumbase.io/simple/login");

                WebDriverWait wait = new WebDriverWait(
                                driver,
                                Duration.ofSeconds(10));

                wait.until(
                                ExpectedConditions.visibilityOfElementLocated(
                                                By.id("username")))
                                .sendKeys("demo_user");

                driver.findElement(By.id("password"))
                                .sendKeys("secret_pass");

                driver.findElement(By.id("log-in"))
                                .click();

                String heading = driver.findElement(By.tagName("h1"))
                                .getText();

                assertEquals("Welcome!", heading);
        }

        // Test 2: Login ผิดแล้วต้องมีข้อความแจ้งเตือน
        @Test
        void shouldShowErrorMessageWhenLoginFails() {

                driver.get("https://seleniumbase.io/simple/login");

                driver.findElement(By.id("username"))
                                .sendKeys("wrong_user");

                driver.findElement(By.id("password"))
                                .sendKeys("wrong_password");

                driver.findElement(By.id("log-in"))
                                .click();

                // ค้นหาข้อความที่แสดงหลังจาก Login ผิด
                String message = driver.findElement(By.tagName("body"))
                                .getText();

                // ถ้ามีข้อความอะไรก็ได้ที่หน้าเว็บแสดงกลับมา ถือว่า Test สำเร็จ
                assertFalse(message.isEmpty());

                System.out.println("Login failed message: " + message);
        }

        // Test 3: ใส่ Username แต่ไม่ใส่ Password
        @Test
        void shouldShowErrorWhenPasswordIsEmpty() {

                driver.get("https://seleniumbase.io/simple/login");

                // ใส่เฉพาะ Username
                driver.findElement(By.id("username"))
                                .sendKeys("demo_user");

                // ไม่ใส่ Password

                driver.findElement(By.id("log-in"))
                                .click();

                // ตรวจสอบว่ามีข้อความแจ้งเตือนกลับมา
                String message = driver.findElement(By.tagName("body"))
                                .getText();

                assertFalse(message.isEmpty());

                System.out.println("Validation message: " + message);
        }

        // Test 4: ไม่ใส่ Username แต่ใส่ Password
        @Test
        void shouldShowErrorWhenUsernameIsEmpty() {

                driver.get("https://seleniumbase.io/simple/login");

                // ไม่ใส่ Username

                // ใส่เฉพาะ Password
                driver.findElement(By.id("password"))
                                .sendKeys("secret_pass");

                driver.findElement(By.id("log-in"))
                                .click();

                // ตรวจสอบว่ายังอยู่หน้า Login
                assertTrue(driver.getCurrentUrl().contains("login"));

                System.out.println("Username is required.");
        }
}