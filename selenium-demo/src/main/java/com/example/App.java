package com.example; // เปลี่ยนให้ตรงกับ Group Id ของคุณ

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        // Selenium 4 จะจัดการ ChromeDriver ให้เราอัตโนมัติ
        
        // 1. สร้าง Instance ของ WebDriver (เปิดเบราว์เซอร์ Chrome)
        WebDriver driver = new ChromeDriver();
        
        try {
            // ตั้งค่า Timeout ป้องกันการโหลดนานเกินไป
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            // ขยายหน้าต่างเบราว์เซอร์ให้เต็มจอ
            driver.manage().window().maximize();
            
            // 2. สั่งให้เบราว์เซอร์เปิดเว็บ Google
            driver.get("https://www.google.com");
            
            // 3. ดึงชื่อ Title ของหน้าเว็บมาแสดงผลใน Console
            String pageTitle = driver.getTitle();
            System.out.println("เปิดหน้าเว็บสำเร็จ! ชื่อหน้าเว็บคือ: " + pageTitle);
            
            // หยุดรอ 3 วินาทีเพื่อให้คุณมองเห็นหน้าต่างเบราว์เซอร์ (ในการทำงานจริงไม่แนะนำให้ใช้ Thread.sleep)
            Thread.sleep(3000);
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 4. ปิดเบราว์เซอร์เมื่อทำงานเสร็จ
            driver.quit();
            System.out.println("ปิดเบราว์เซอร์เรียบร้อยแล้ว");
        }
    }
}