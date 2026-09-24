# Java Selenium QA Automation

โปรเจกต์ตัวอย่าง Automated Web Testing / End-to-End Testing (E2E) ด้วย Java,
Selenium WebDriver, TestNG และ Maven สำหรับทดสอบ Google, Wikipedia และ BMI
Calculator บนหลายเบราว์เซอร์และหลายขนาดหน้าจอ

## รันแบบเร็ว

สิ่งที่ต้องติดตั้ง:

- JDK 17 ขึ้นไป
- Maven 3.9 ขึ้นไป
- Chrome และ Firefox สำหรับรัน test ทั้งหมดด้วยค่าเริ่มต้น
- Internet สำหรับเข้าเว็บไซต์ที่ทดสอบและดาวน์โหลด WebDriver ครั้งแรก

ตรวจสอบว่า Java และ Maven พร้อมใช้งาน:

```bash
java -version
mvn -version
```

จากโฟลเดอร์หลักของ repository ให้รัน:

```bash
cd selenium-demo
mvn test
```

เท่านี้ก็เรียบร้อย Selenium Manager จะหาและดาวน์โหลด WebDriver ที่ตรงกับ
ระบบปฏิบัติการและเวอร์ชันเบราว์เซอร์ให้อัตโนมัติ ไม่ต้องติดตั้งหรือกำหนด path
ของ `chromedriver` และ `geckodriver` เอง

ผลการทดสอบจะอยู่ใน `selenium-demo/target/surefire-reports/`

> หากมีเพียง Chrome ให้รัน `mvn -Dbrowser=chrome test` เพื่อบังคับให้ทุก test
> ใช้ Chrome

## Test cases

| Test | Browser เริ่มต้น | สิ่งที่ตรวจสอบ |
| --- | --- | --- |
| `AppTest#testGoogleSearchTitle` | Chrome | เปิด Google และตรวจ page title |
| `AppTest#testWikipediaSearch` | Chrome | ค้นหา `Software engineering` และตรวจหัวข้อบทความ |
| `BmiCalculatorTest#testBmiCalculatorWithMetricInput` | Firefox | กรอกข้อมูลแบบ Metric แล้วตรวจค่า BMI และ category |

ข้อมูลที่ใช้ใน BMI test คือ อายุ 25 ปี, เพศชาย, ส่วนสูง 180 cm และน้ำหนัก
65 kg โดยผลที่คาดหวังคือ BMI `20.1` และ category `Normal`

## คำสั่งที่ใช้บ่อย

ให้รันคำสั่งต่อไปนี้จากโฟลเดอร์ `selenium-demo/`

| ต้องการ | คำสั่ง |
| --- | --- |
| รัน test ทั้งหมด | `mvn test` |
| รันใหม่แบบล้างผล build เดิม | `mvn clean test` |
| รันเฉพาะ `AppTest` | `mvn -Dtest=AppTest test` |
| รันเฉพาะ `BmiCalculatorTest` | `mvn -Dtest=BmiCalculatorTest test` |
| รันเฉพาะ Google test | `mvn -Dtest=AppTest#testGoogleSearchTitle test` |
| รันเฉพาะ Wikipedia test | `mvn -Dtest=AppTest#testWikipediaSearch test` |
| เปิดหน้าต่างเบราว์เซอร์ให้มองเห็น | `mvn -Dheadless=false test` |

ค่าเริ่มต้นจะรันแบบ headless จึงไม่แสดงหน้าต่างเบราว์เซอร์ หากใช้ PowerShell
แล้ว parameter `-D...` มีปัญหา ให้ครอบ parameter ด้วยเครื่องหมายคำพูด เช่น
`mvn "-Dtest=AppTest" test`

## เลือกเบราว์เซอร์

กำหนด `browser` เป็น `chrome`, `firefox`, `edge` หรือ `safari` ได้:

```bash
mvn -Dbrowser=chrome test
mvn -Dbrowser=firefox test
mvn -Dbrowser=edge test
mvn -Dbrowser=safari -Dheadless=false -Ddevice=desktop test
```

Safari ต้องรันบน macOS, เปิด **Develop > Allow Remote Automation** และรัน
`safaridriver --enable` หนึ่งครั้ง Safari ไม่รองรับ headless และ mobile
emulation ในโปรเจกต์นี้

## เลือกขนาดหน้าจอ

| Device preset | Viewport | การทำงาน |
| --- | --- | --- |
| `desktop` | 1440 x 900 | Desktop browser ปกติ |
| `tablet` | 768 x 1024 | Mobile emulation บน Chrome/Edge และ resize บน Firefox |
| `mobile` | 390 x 844 | Mobile emulation บน Chrome/Edge และ resize บน Firefox |

ตัวอย่าง:

```bash
mvn -Dbrowser=chrome -Ddevice=desktop test
mvn -Dbrowser=chrome -Ddevice=tablet test
mvn -Dbrowser=chrome -Ddevice=mobile test
```

กำหนด viewport เองได้:

```bash
mvn -Dbrowser=chrome -Ddevice=mobile -DviewportWidth=412 -DviewportHeight=915 test
```

`tablet` และ `mobile` เป็นการจำลอง viewport/touch ไม่ใช่อุปกรณ์จริง

## Selenium Grid หรือ device cloud

ส่ง test ไปยัง Remote WebDriver ได้ด้วย `remoteUrl`:

```bash
mvn -DremoteUrl=http://localhost:4444/wd/hub -Dbrowser=chrome -Ddevice=desktop test
```

ค่าที่รองรับทั้งหมด:

| Property | ค่าเริ่มต้น | ตัวอย่าง |
| --- | --- | --- |
| `browser` | Chrome สำหรับ `AppTest`, Firefox สำหรับ BMI test | `-Dbrowser=firefox` |
| `device` | `desktop` | `-Ddevice=mobile` |
| `headless` | `true` | `-Dheadless=false` |
| `viewportWidth` | ตาม device preset | `-DviewportWidth=412` |
| `viewportHeight` | ตาม device preset | `-DviewportHeight=915` |
| `remoteUrl` | Local browser | `-DremoteUrl=http://localhost:4444/wd/hub` |

## โครงสร้างโปรเจกต์

```text
selenium-demo/
├── pom.xml
└── src/
    ├── main/java/com/example/App.java
    └── test/java/com/testing/demo/
        ├── AppTest.java
        ├── BmiCalculatorTest.java
        └── DriverFactory.java
```

แต่ละ test method ใช้ TestNG lifecycle ดังนี้:

```text
@BeforeMethod -> เปิด WebDriver -> @Test -> ตรวจผล -> @AfterMethod -> ปิด WebDriver
```

## การแก้ปัญหาเบื้องต้น

- `NoSuchDriverException`: ตรวจ Internet และอัปเดตเบราว์เซอร์ แล้วลองรันใหม่
- `SessionNotCreatedException`: ตรวจว่า browser และ driver รองรับเวอร์ชันและ
  architecture ของเครื่อง
- `Unable to find an exact match for CDP version`: อัปเดต Selenium หรือใช้ browser
  stable ที่ Selenium รองรับ
- หา element ไม่พบหรือ timeout: ตรวจ Internet, consent page และดูว่าเว็บไซต์
  เปลี่ยนโครงสร้างหรือไม่
- ต้องการดูว่า test ทำอะไรบนหน้าจอ: รัน `mvn -Dheadless=false test`
- Maven หา test ไม่พบ: ตรวจว่า class อยู่ใน `src/test/java/` และชื่อลงท้ายด้วย
  `Test`

เมื่อสำเร็จ Maven จะแสดง `BUILD SUCCESS` หากไม่สำเร็จให้ดูรายละเอียดใน terminal
และไฟล์รายงานภายใต้ `target/surefire-reports/`
