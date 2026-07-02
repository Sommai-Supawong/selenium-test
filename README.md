# 🧪 Java Selenium QA Automation

โปรเจกต์เริ่มต้นสำหรับการทำ Automated Testing ด้วยภาษา Java และ Selenium WebDriver โดยใช้ Maven ในการจัดการ Dependencies โครงสร้างโปรเจกต์ถูกออกแบบมาให้สะอาด เรียบง่าย และพร้อมสำหรับการต่อยอดในการทดสอบ Web Application แบบ E2E (End-to-End Testing)

## 🛠️ Tech Stack & Tools
* **Language:** Java (JDK 17+)
* **Testing Framework:** TestNG
* **Automation Tool:** Selenium WebDriver
* **Build Tool:** Maven
* **IDE:** Visual Studio Code (VS Code)

## 📂 Project Structure
โครงสร้างโปรเจกต์อ้างอิงตามมาตรฐานของ Maven เพื่อให้ง่ายต่อการดูแลรักษา:

```text
selenium-demo/
├── .github/               # การตั้งค่าสำหรับระบบของ GitHub (เช่น Actions/Workflows)
├── drivers/               # โฟลเดอร์สำหรับเก็บ WebDriver (เช่น chromedriver.exe)
├── src/
│   ├── main/              # โฟลเดอร์สำหรับ Source Code หลักของแอปพลิเคชัน
│   │   ├── java/com/example/
│   │   │   └── App.java
│   │   └── resources/     # ไฟล์การตั้งค่าหรือ Resource ต่างๆ ของแอปพลิเคชัน
│   └── test/              # 🌟 โฟลเดอร์หลักสำหรับเขียนสคริปต์ Automated Test
│       └── java/com/testing/demo/
│           └── AppTest.java
├── target/                # โฟลเดอร์เก็บไฟล์ที่ผ่านการ Compile และ Build จาก Maven (ไม่นำขึ้น GitHub)
├── .env                   # ไฟล์เก็บข้อมูลความลับ เช่น รหัสผ่าน หรือ URL (ซ่อนไว้ผ่าน .gitignore)
├── .gitignore             # ระบุไฟล์ที่ไม่อัปโหลดขึ้น GitHub (เช่น .env, target/, drivers/)
├── pom.xml                # ไฟล์จัดการ Dependencies ของ Maven
└── README.md              # ไฟล์เอกสารอธิบายรายละเอียดของโปรเจกต์

## 📝 Test Cases (เคสทดสอบในระบบ)

ปัจจุบันโปรเจกต์นี้มีสคริปต์ตัวอย่างสำหรับการทดสอบ E2E (End-to-End) พื้นฐาน เพื่อใช้เป็นแนวทางในการเขียนเคสอื่นๆ ต่อไป:

*   **`testGoogleSearchTitle`**
    *   **เป้าหมาย:** ตรวจสอบความถูกต้องของการโหลดหน้าเว็บและการแสดงผล Title
    *   **ขั้นตอน:** 
        1. เปิดเว็บเบราว์เซอร์และเข้าไปที่ `https://www.google.com`
        2. ดึงค่า Page Title ที่แสดงบนแท็บของเบราว์เซอร์
        3. ตรวจสอบ (Assert) ว่าค่าที่ได้ตรงกับคำว่า "Google" อย่างถูกต้อง

*   **`testWikipediaSearch`**
    *   **เป้าหมาย:** ทดสอบการทำงานของระบบค้นหา (Search Functionality) และการจำลองพฤติกรรมผู้ใช้งาน
    *   **ขั้นตอน:** 
        1. เข้าไปที่หน้าเว็บ `https://www.wikipedia.org/`
        2. ค้นหาช่องป้อนข้อมูล (Input) และจำลองการพิมพ์ข้อความคำว่า "Software engineering"
        3. ค้นหาปุ่มค้นหา (Submit Button) และจำลองการคลิกเมาส์
        4. รอให้หน้าเว็บโหลดและดึงค่าข้อความหัวข้อหลัก (H1) ของหน้าผลลัพธ์
        5. ตรวจสอบ (Assert) ว่าหัวข้อหลักที่แสดงผลออกมา ตรงกับคำที่ใช้ค้นหา