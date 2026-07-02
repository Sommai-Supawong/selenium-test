package com.testing.demo;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AppTest {

    // ประกาศตัวแปร driver ให้อยู่ระดับ Class เพื่อให้เรียกใช้ได้ทุก Method
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // คอมเมนต์หรือลบบรรทัด WebDriverManager ทิ้งไป
        // WebDriverManager.chromedriver().setup();
        
        // 1. ระบุที่อยู่ (Path) ของไฟล์ chromedriver.exe ที่เราโหลดมา
        // ใช้ "drivers/chromedriver.exe" ได้เลย เพราะอ้างอิงจากโฟลเดอร์โปรเจกต์
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        
        // 2. สร้าง Instance ของ ChromeDriver เพื่อเปิดเบราว์เซอร์
        driver = new ChromeDriver();
        
        // ขยายหน้าจอให้เต็ม
        driver.manage().window().maximize();
    }

    @Test
    public void testGoogleSearchTitle() {
        // 3. สั่งให้ Driver ไปที่ URL ที่กำหนด
        driver.get("https://www.google.com");
        
        // ดึงค่า Title ของหน้าเว็บเพจมาเก็บไว้ในตัวแปร
        String actualTitle = driver.getTitle();
        System.out.println("Page Title is: " + actualTitle);
        
        // 4. ตรวจสอบผลลัพธ์ (Assertion) ว่า Title ตรงกับที่คาดหวังหรือไม่
        Assert.assertEquals(actualTitle, "Google", "Title does not match!");
    }

    @AfterMethod
    public void tearDown() {
        // 5. ปิดเบราว์เซอร์และคืนค่าหน่วยความจำหลังเทสเสร็จ
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void testWikipediaSearch() throws InterruptedException {
        // 1. สั่งให้ Driver ไปที่หน้าเว็บ Wikipedia
        driver.get("https://www.wikipedia.org/");
        
        // 2. ค้นหา "ช่องใส่ข้อความ" (Input Box) ด้วย ID และสั่งให้พิมพ์ข้อความ
        WebElement searchBox = driver.findElement(By.id("searchInput"));
        searchBox.sendKeys("Software engineering");
        
        // 3. ค้นหา "ปุ่มค้นหา" (Button) และสั่งให้คลิก
        WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
        searchButton.click();
        
        // หน่วงเวลา 2 วินาทีเพื่อให้คุณมองเห็นหน้าจอทัน (ในการทำงานจริงเราจะใช้ Wait แบบอื่นแทนครับ)
        Thread.sleep(2000); 
        
        // 4. ตรวจสอบผลลัพธ์ โดยการดึงข้อความจากหัวข้อใหญ่ (H1) ของหน้าเว็บที่โหลดขึ้นมาใหม่
        WebElement heading = driver.findElement(By.id("firstHeading"));
        String actualText = heading.getText();
        
        // 5. เทียบว่าข้อความ H1 ตรงกับคำที่เราค้นหาไปหรือไม่
        Assert.assertEquals(actualText, "Software engineering", "หัวข้อไม่ตรงกับข้อมูลที่ค้นหา!");
    }
}