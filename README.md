# 🚀 Java Selenium WebDriver - Automation Testing Demo

โปรเจกต์ทดสอบระบบอัตโนมัติ (Automated Web Testing) สำหรับแอปพลิเคชันบนเว็บ พัฒนาด้วยภาษา **Java** และ **Selenium WebDriver** โดยใช้โครงสร้างโปรเจกต์มาตรฐานของ **Apache Maven** ร่วมกับ **TestNG Framework**

---

## 🛠️ Tech Stack (เครื่องมือที่ใช้พัฒนา)

* **Language:** Java (JDK 17+)
* **Automation Tool:** Selenium WebDriver 4.20+
* **Testing Framework:** TestNG 7.9+
* **Build Tool:** Apache Maven
* **IDE:** Visual Studio Code

---

## 📂 Project Structure (โครงสร้างไฟล์)

```text
selenium-demo/
│
├── drivers/
│   └── chromedriver.exe       # เบราว์เซอร์ไดรเวอร์สำหรับ Chrome (จำลองการทำงานจริง)
├── src/
│   └── test/
│       └── java/
│           └── com/testing/demo/
│               └── AppTest.java   # ไฟล์หลักสำหรับเขียน Test Cases และ Step การทดสอบ
├── .gitignore                 # ระบุไฟล์ที่ห้ามอัปโหลดขึ้น Git (เช่น .env, โฟลเดอร์ .vscode)
├── pom.xml                    # ไฟล์จัดการ Library และ Dependencies ของ Maven
└── README.md                  # เอกสารสรุปรายละเอียดโปรเจกต์

---

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