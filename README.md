# 🧪 Java Selenium QA Automation

โปรเจกต์สำหรับฝึกและพัฒนา **Automated Web Testing / End-to-End Testing (E2E)** ด้วยภาษา Java และ Selenium WebDriver โดยใช้ Maven สำหรับจัดการ Dependencies และใช้ TestNG สำหรับจัดการ Test Case, Test Lifecycle และ Assertion

โปรเจกต์นี้รองรับการทดสอบบนหลาย Browser โดยปัจจุบันมีการใช้งาน:

- **Google Chrome** ผ่าน `chromedriver.exe`
- **Mozilla Firefox** ผ่าน `geckodriver.exe`

---

## 🛠️ Tech Stack & Tools

- **Language:** Java
- **Java Target:** JDK 17
- **Testing Framework:** TestNG
- **Automation Tool:** Selenium WebDriver
- **Build Tool:** Maven
- **Browsers:** Google Chrome, Mozilla Firefox
- **Browser Drivers:** ChromeDriver, GeckoDriver
- **IDE:** Visual Studio Code (VS Code)

---

## 📂 Project Structure

โครงสร้างโปรเจกต์อ้างอิงตามมาตรฐานของ Maven เพื่อให้ง่ายต่อการดูแลรักษาและต่อยอด:

```text
selenium-demo/
├── .github/                       # การตั้งค่า GitHub เช่น Actions / Workflows
├── drivers/
│   ├── chromedriver.exe           # Driver สำหรับ Google Chrome
│   └── geckodriver.exe            # Driver สำหรับ Mozilla Firefox
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   └── App.java           # Selenium Demo แบบ Java Main Program
│   │   └── resources/
│   └── test/
│       └── java/com/testing/demo/
│           ├── AppTest.java       # Chrome Test Module
│           └── BmiCalculatorTest.java
│                                       # Firefox BMI Test Module
├── target/                        # ผลลัพธ์จาก Maven Compile / Test
├── .env                           # Environment variables / secret values
├── .gitignore                     # กำหนดไฟล์ที่ไม่ต้องการนำขึ้น Git
├── pom.xml                        # Maven Dependencies และ Project Config
└── README.md                      # เอกสารอธิบายโปรเจกต์
```

---

# ⚙️ Prerequisites

ก่อนรันโปรเจกต์ควรติดตั้งโปรแกรมต่อไปนี้:

1. **Java JDK 17 ขึ้นไป**
2. **Apache Maven**
3. **Google Chrome**
4. **Mozilla Firefox**
5. `chromedriver.exe`
6. `geckodriver.exe`

ตรวจสอบ Java:

```bash
java -version
```

ตรวจสอบ Maven:

```bash
mvn -version
```

---

# 🚗 Browser Driver Setup

โปรเจกต์นี้ใช้ Browser Driver แบบกำหนด Path เองภายในโฟลเดอร์ `drivers/`

## ChromeDriver

วางไฟล์:

```text
drivers/chromedriver.exe
```

`AppTest.java` ใช้การตั้งค่า:

```java
System.setProperty(
    "webdriver.chrome.driver",
    "drivers/chromedriver.exe"
);
```

ตรวจสอบไฟล์บน Windows:

```bat
dir drivers\chromedriver.exe
```

---

## GeckoDriver

วางไฟล์:

```text
drivers/geckodriver.exe
```

`BmiCalculatorTest.java` ใช้การตั้งค่า:

```java
System.setProperty(
    "webdriver.gecko.driver",
    "drivers/geckodriver.exe"
);
```

ตรวจสอบ GeckoDriver:

```bat
.\drivers\geckodriver.exe --version
```

ตัวอย่างผลลัพธ์:

```text
geckodriver 0.37.0
```

---

# 📝 Test Modules

ปัจจุบันแบ่ง Automated Test ออกเป็น 2 Test Modules หลัก

---

## Module 1: `AppTest`

**File:**

```text
src/test/java/com/testing/demo/AppTest.java
```

**Browser:** Google Chrome  
**Driver:** `drivers/chromedriver.exe`

Module นี้ใช้สำหรับทดสอบ Web Application พื้นฐานด้วย ChromeDriver

### Test Case 1: `testGoogleSearchTitle`

**เป้าหมาย:** ตรวจสอบว่าหน้า Google โหลดสำเร็จและ Page Title ถูกต้อง

**Test Steps:**

1. เปิด Google Chrome
2. เข้าเว็บไซต์ `https://www.google.com`
3. อ่าน Page Title จาก Browser
4. เปรียบเทียบ Actual Result กับ Expected Result

**Expected Result:**

```text
Google
```

**Assertion:**

```java
Assert.assertEquals(
    actualTitle,
    "Google",
    "Title does not match!"
);
```

---

### Test Case 2: `testWikipediaSearch`

**เป้าหมาย:** ทดสอบ Search Functionality และจำลองพฤติกรรมผู้ใช้งานบน Wikipedia

**Test Data:**

```text
Software engineering
```

**Test Steps:**

1. เปิดเว็บไซต์ `https://www.wikipedia.org/`
2. ค้นหา Search Input ด้วย `id="searchInput"`
3. พิมพ์ `Software engineering`
4. ค้นหาปุ่ม Submit
5. คลิก Search
6. อ่านข้อความจากหัวข้อผลลัพธ์
7. เปรียบเทียบ Actual Result กับ Expected Result

**Expected Result:**

```text
Software engineering
```

---

## Module 2: `BmiCalculatorTest`

**File:**

```text
src/test/java/com/testing/demo/BmiCalculatorTest.java
```

**Browser:** Mozilla Firefox  
**Driver:** `drivers/geckodriver.exe`

**Website:**

```text
https://www.calculator.net/bmi-calculator.html
```

Module นี้ใช้ทดสอบระบบคำนวณ BMI โดยจำลองการกรอกข้อมูลผู้ใช้งานจริง ได้แก่:

- Age
- Gender
- Height
- Weight

### Test Case: `testBmiCalculatorWithMetricInput`

**เป้าหมาย:** ตรวจสอบว่าระบบรับข้อมูลแบบ Metric Units และคำนวณ BMI ได้ถูกต้อง

**Test Data:**

| Field | Value |
|---|---:|
| Age | 25 |
| Gender | Male |
| Height | 180 cm |
| Weight | 65 kg |

**Test Steps:**

1. เปิด Mozilla Firefox
2. เปิดหน้า BMI Calculator แบบ Metric Units
3. กรอก Age = `25`
4. เลือก Gender = `Male`
5. กรอก Height = `180 cm`
6. กรอก Weight = `65 kg`
7. คลิกปุ่ม `Calculate`
8. คำนวณ Expected BMI จาก Java
9. เปรียบเทียบ Expected BMI กับผลลัพธ์บนหน้าเว็บ
10. ตรวจสอบ BMI Category

**Expected Result:**

```text
BMI = 20.1 kg/m²
Category = Normal
```

สูตรที่ใช้ตรวจสอบ Expected Result:

```text
BMI = Weight / Height²

BMI = 65 / (1.80 × 1.80)
BMI = 20.0617...
BMI ≈ 20.1
```

---

# ▶️ How to Run Tests

> **สำคัญ:** ให้เปิด Terminal ที่ Project Root ซึ่งเป็นตำแหน่งเดียวกับไฟล์ `pom.xml`

ตัวอย่าง:

```text
D:\selenium-work\selenium-demo>
```

---

## 1. Run All Test Modules

รัน Test ทั้งหมดในโปรเจกต์:

```bash
mvn clean test
```

คำสั่งนี้อาจรันทั้ง:

```text
AppTest
├── testGoogleSearchTitle
└── testWikipediaSearch

BmiCalculatorTest
└── testBmiCalculatorWithMetricInput
```

ดังนั้นระหว่างการรันอาจเปิดทั้ง **Chrome** และ **Firefox** ตาม Test Module

---

# 🌐 Run Module 1: AppTest

## 2. Run ทุก Test ใน `AppTest`

### Windows CMD / PowerShell

```bash
mvn clean "-Dtest=AppTest" test
```

คำสั่งนี้รัน:

```text
AppTest
├── testGoogleSearchTitle
└── testWikipediaSearch
```

Browser ที่ใช้:

```text
Google Chrome
```

---

## 3. Run เฉพาะ Google Test

รันเฉพาะ Method:

```text
testGoogleSearchTitle
```

### Windows CMD / PowerShell

```bash
mvn clean "-Dtest=AppTest#testGoogleSearchTitle" test
```

Expected Flow:

```text
Start
  ↓
Open Chrome
  ↓
Open Google
  ↓
Read Page Title
  ↓
Assert Title = Google
  ↓
PASS / FAIL
  ↓
Close Chrome
```

---

## 4. Run เฉพาะ Wikipedia Test

รันเฉพาะ Method:

```text
testWikipediaSearch
```

### Windows CMD / PowerShell

```bash
mvn clean "-Dtest=AppTest#testWikipediaSearch" test
```

Expected Flow:

```text
Start
  ↓
Open Chrome
  ↓
Open Wikipedia
  ↓
Input Search Keyword
  ↓
Click Search
  ↓
Read Result Heading
  ↓
Assert Result
  ↓
PASS / FAIL
  ↓
Close Chrome
```

---

# 🧮 Run Module 2: BmiCalculatorTest

## 5. Run ทุก Test ใน `BmiCalculatorTest`

### Windows CMD / PowerShell

```bash
mvn clean "-Dtest=BmiCalculatorTest" test
```

Browser ที่ใช้:

```text
Mozilla Firefox
```

Driver ที่ใช้:

```text
drivers/geckodriver.exe
```

Expected Flow:

```text
Start
  ↓
Open Firefox
  ↓
Open BMI Calculator
  ↓
Input Age
  ↓
Select Gender
  ↓
Input Height
  ↓
Input Weight
  ↓
Click Calculate
  ↓
Verify BMI Result
  ↓
Verify BMI Category
  ↓
PASS / FAIL
  ↓
Close Firefox
```

---

## 6. Run เฉพาะ BMI Metric Test Method

รันเฉพาะ Method:

```text
testBmiCalculatorWithMetricInput
```

### Windows CMD / PowerShell

```bash
mvn clean "-Dtest=BmiCalculatorTest#testBmiCalculatorWithMetricInput" test
```

---

# 📌 Run Command Summary

| Target | Command |
|---|---|
| Run all tests | `mvn clean test` |
| Run all tests in AppTest | `mvn clean "-Dtest=AppTest" test` |
| Run Google test only | `mvn clean "-Dtest=AppTest#testGoogleSearchTitle" test` |
| Run Wikipedia test only | `mvn clean "-Dtest=AppTest#testWikipediaSearch" test` |
| Run all BMI tests | `mvn clean "-Dtest=BmiCalculatorTest" test` |
| Run BMI Metric test only | `mvn clean "-Dtest=BmiCalculatorTest#testBmiCalculatorWithMetricInput" test` |

---

# 🧠 TestNG Lifecycle

Test Classes ใช้ TestNG Lifecycle โดยมีโครงสร้างหลัก:

```text
@BeforeMethod
    ↓
Setup Browser Driver
    ↓
Open Browser
    ↓
@Test
    ↓
Run Test Steps
    ↓
Assertion
    ↓
@AfterMethod
    ↓
Close Browser
```

ตัวอย่าง:

```java
@BeforeMethod
public void setUp() {
    // Create WebDriver session
}

@Test
public void testExample() {
    // Run test steps
}

@AfterMethod
public void tearDown() {
    if (driver != null) {
        driver.quit();
    }
}
```

เนื่องจากใช้ `@BeforeMethod` และ `@AfterMethod` Browser จะถูกเปิดและปิดแยกสำหรับแต่ละ Test Method

---

# ✅ Test Result

เมื่อ Test ผ่าน ควรเห็นผลลัพธ์ประมาณ:

```text
Tests run: 1
Failures: 0
Errors: 0
Skipped: 0

BUILD SUCCESS
```

เมื่อ Test ไม่ผ่าน:

```text
BUILD FAILURE
```

Maven Surefire Test Reports จะอยู่ที่:

```text
target/surefire-reports/
```

บน Windows ตัวอย่าง Path:

```text
D:\selenium-work\selenium-demo\target\surefire-reports
```

---

# 🔧 Troubleshooting

## ChromeDriver ไม่พบ

ตรวจสอบ:

```bat
dir drivers\chromedriver.exe
```

---

## GeckoDriver ไม่พบ

ตรวจสอบ:

```bat
dir drivers\geckodriver.exe
```

และ:

```bat
.\drivers\geckodriver.exe --version
```

---

## Maven ไม่พบ Test

ตรวจสอบว่า Test Class อยู่ใน:

```text
src/test/java/
```

และชื่อ Class เป็นรูปแบบที่ Maven Surefire ตรวจพบ เช่น:

```text
AppTest.java
BmiCalculatorTest.java
```

---

## Element Timeout

ตัวอย่าง Error:

```text
org.openqa.selenium.TimeoutException
```

ให้ตรวจสอบ:

1. Locator เช่น `id`, `name`, `cssSelector`
2. Element มีอยู่จริงใน DOM หรือไม่
3. Element ถูกซ่อนด้วย CSS หรือไม่
4. ควรใช้ `presenceOfElementLocated`, `visibilityOfElementLocated` หรือ `elementToBeClickable` แบบใด
5. หน้าเว็บโหลดเสร็จแล้วหรือยัง

---

# 🚀 Future Improvements

แนวทางต่อยอดโปรเจกต์:

- เปลี่ยนจาก `Thread.sleep()` เป็น Explicit Wait ทั้งหมด
- แยก Driver Setup เป็น `BaseTest`
- ใช้ Page Object Model (POM)
- เพิ่ม Screenshot เมื่อ Test Fail
- เพิ่ม Test Report
- เพิ่ม Data-Driven Testing
- เพิ่ม Cross-Browser Testing
- เพิ่ม Parallel Testing
- เพิ่ม GitHub Actions / CI Pipeline

---

## 📚 Current Test Coverage Summary

```text
Chrome / AppTest
├── Google Page Title Test
└── Wikipedia Search Test

Firefox / BmiCalculatorTest
└── BMI Metric Input Test
    ├── Age
    ├── Gender
    ├── Height
    ├── Weight
    ├── BMI Result
    └── BMI Category
```

