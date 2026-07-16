# Selenium Python QA Automation

โปรเจกต์ทดสอบเว็บไซต์แบบ End-to-End ด้วย Selenium WebDriver และ pytest ครอบคลุม Google, Wikipedia และ BMI Calculator โดยค่าเริ่มต้นจะเปิดหน้าต่าง browser เพื่อให้เห็นการทำงานของ test

## สิ่งที่ต้องมี

- Python 3.10 ขึ้นไป
- Chrome และ Firefox สำหรับการรัน test ทั้งหมดด้วย browser ตามที่แต่ละ test กำหนด
- Internet สำหรับเว็บไซต์ที่ทดสอบและสำหรับ Selenium Manager ดาวน์โหลด driver อัตโนมัติในครั้งแรก

ไม่ต้องดาวน์โหลด ChromeDriver หรือ GeckoDriver เอง

## เริ่มใช้งานแบบรวดเร็ว (Windows PowerShell)

เปิด PowerShell ที่โฟลเดอร์โปรเจกต์ แล้วติดตั้งเพียงครั้งแรก:

```powershell
.\setup.ps1
```

รัน test โดยมองเห็น browser ทำงาน:

```powershell
.\run-tests.ps1
```

โปรเจกต์นี้ใช้ Python ที่ติดตั้งในเครื่องโดยตรงและไม่ต้องสร้าง virtual environment

ถ้า PowerShell ไม่อนุญาตให้รัน script ให้ใช้คำสั่งนี้เฉพาะ session ปัจจุบัน:

```powershell
Set-ExecutionPolicy -Scope Process Bypass
```

## ติดตั้งและรันด้วยคำสั่ง Python

ใช้วิธีนี้ได้บน Windows, macOS และ Linux:

ติดตั้ง dependencies และรัน test ด้วย Python ที่ติดตั้งในเครื่อง:

```powershell
python -m pip install -r requirements.txt
python -m pytest
```

`python -m pytest` และ `.\run-tests.ps1` จะแสดงหน้าต่าง browser เป็นค่าเริ่มต้น

## ตัวอย่างการรัน

รันทุก test ด้วย Chrome:

```powershell
.\run-tests.ps1 -Browser chrome
# หรือ
python -m pytest --browser chrome
```

รันแบบไม่แสดง browser เหมาะสำหรับ CI:

```powershell
.\run-tests.ps1 -Headless
# หรือ
python -m pytest --headless
```

จำลองหน้าจอ mobile และรันเฉพาะไฟล์:

```powershell
.\run-tests.ps1 -Browser chrome -Device mobile tests/test_app.py
# หรือ
python -m pytest --browser chrome --device mobile tests/test_app.py
```

รัน test เดียว:

```powershell
python -m pytest tests/test_app.py::test_google_search_title
```

ดู option ทั้งหมดได้ด้วย `python -m pytest --help`

## Test cases

| Test | Browser เริ่มต้น | สิ่งที่ตรวจสอบ |
| --- | --- | --- |
| `test_google_search_title` | Chrome | เปิด Google และตรวจ page title |
| `test_wikipedia_search` | Chrome | ค้นหา `Software engineering` และตรวจหัวข้อบทความ |
| `test_bmi_calculator_with_metric_input` | Firefox | กรอกข้อมูล Metric และตรวจ BMI `20.1` กับประเภท `Normal` |

การส่ง `--browser` หรือ `-Browser` จะ override browser เริ่มต้นของทุก test

## การตั้งค่า

แนะนำให้ใช้ command-line options เพราะอ่านง่าย แต่ยังรองรับ environment variables สำหรับ CI หรือ Selenium Grid:

| Option | Environment variable | ค่าเริ่มต้น | ค่าที่รองรับ |
| --- | --- | --- | --- |
| `--browser` | `BROWSER` | ตามแต่ละ test | `chrome`, `firefox`, `edge`, `safari` |
| `--headless` | `HEADLESS=true` | แสดง browser | `true`, `false` |
| `--device` | `DEVICE` | `desktop` | `desktop`, `tablet`, `mobile` |
| - | `VIEWPORT_WIDTH` | ตาม device | จำนวนเต็มมากกว่า 0 |
| - | `VIEWPORT_HEIGHT` | ตาม device | จำนวนเต็มมากกว่า 0 |
| - | `REMOTE_URL` | local browser | URL ของ Selenium Grid/device cloud |

ตัวอย่าง Selenium Grid:

```powershell
$env:REMOTE_URL = "http://localhost:4444/wd/hub"
python -m pytest --browser chrome --headless
```

Safari ต้องรันบน macOS เปิด Allow Remote Automation และใช้ desktop แบบไม่ headless ส่วน mobile/tablet บน Chrome และ Edge ใช้ mobile emulation; Firefox จะปรับขนาดหน้าต่างตาม viewport

## โครงสร้างโปรเจกต์

```text
selenium-python/
├── setup.ps1
├── run-tests.ps1
├── pytest.ini
├── requirements.txt
├── README.md
└── tests/
    ├── conftest.py
    ├── driver_factory.py
    ├── test_app.py
    └── test_bmi_calculator.py
```

## แก้ปัญหาเบื้องต้น

- Browser เปิดไม่ได้: ตรวจว่า browser เป็นเวอร์ชันปัจจุบันและเครื่องเชื่อมต่อ Internet ได้
- การรันครั้งแรกช้า: Selenium Manager อาจกำลังดาวน์โหลด WebDriver
- Element timeout: อาจเกิดจาก network, consent page หรือโครงสร้างเว็บไซต์ภายนอกเปลี่ยน
- ไม่ต้องการเปิดหลาย browser: ระบุ browser เดียว เช่น `.\run-tests.ps1 -Browser chrome`
