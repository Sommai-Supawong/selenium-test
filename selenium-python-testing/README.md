# Selenium Python Login Testing

โปรเจกต์ตัวอย่างสำหรับทดสอบหน้า Login ด้วย Selenium WebDriver และ pytest โดยครอบคลุมทั้งกรณีเข้าสู่ระบบสำเร็จและกรณีข้อมูลไม่ถูกต้อง

## สิ่งที่ต้องมี

- Python 3.12 ขึ้นไป
- [uv](https://docs.astral.sh/uv/) สำหรับติดตั้ง dependency และรันคำสั่ง
- Google Chrome (ค่าเริ่มต้น) หรือ Microsoft Edge
- อินเทอร์เน็ตสำหรับเปิดหน้าเว็บทดสอบ และสำหรับ Selenium Manager ดาวน์โหลด driver ในครั้งแรกหากเครื่องยังไม่มี driver ที่ตรงกับ browser

ไม่จำเป็นต้องติดตั้ง ChromeDriver หรือ EdgeDriver ด้วยตนเอง เพราะ Selenium Manager จะจัดการให้โดยอัตโนมัติ

## ติดตั้ง

```powershell
uv sync
```

คำสั่งนี้จะสร้าง virtual environment ที่ `.venv` และติดตั้งเวอร์ชัน dependency ตาม `uv.lock`

หากไม่ได้ใช้ uv สามารถติดตั้งด้วย pip ได้:

```powershell
python -m venv .venv
.\.venv\Scripts\Activate.ps1
python -m pip install pytest selenium
```

## รัน Test

รัน test ทั้งหมดด้วย Chrome โดยจะแสดงหน้าต่าง browser ระหว่างทดสอบ:

```powershell
uv run pytest
```

รันเฉพาะไฟล์ login และแสดงรายละเอียดแต่ละ test:

```powershell
uv run pytest tests/test_login.py -v
```

รันแบบ headless โดยไม่แสดงหน้าต่าง browser เหมาะสำหรับ CI:

```powershell
uv run pytest --headless
```

ใช้ Microsoft Edge:

```powershell
uv run pytest --browser edge
```

ใช้ URL ของ environment อื่น:

```powershell
uv run pytest --base-url "https://example.com/login"
```

สามารถใช้ option ร่วมกันได้ เช่น:

```powershell
uv run pytest -v --browser edge --headless
```

## Test Cases

| Test | รายละเอียด | ผลลัพธ์ที่คาดหวัง |
| --- | --- | --- |
| `test_login_and_logout` | Login ด้วย username/password ที่ถูกต้อง แล้ว Logout | พบข้อความ `Welcome!` และข้อความยืนยันว่าออกจากระบบแล้ว |
| `test_login_with_correct_user_and_blank_password` | Login โดยไม่กรอก password | แสดง `The Password is Required!` |
| `test_login_with_correct_user_and_incorrect_password` | Login ด้วย password ที่ไม่ถูกต้อง | แสดง `Invalid Password!` |
| `test_login_with_blank_username_and_correct_password` | Login โดยไม่กรอก username | แสดง `The Username is Required!` |
| `test_login_with_incorrect_username_and_correct_password` | Login ด้วย username ที่ไม่ถูกต้อง | แสดง `Invalid Username!` |
| `test_login_with_blank_username_and_password` | Login โดยไม่กรอกทั้ง username และ password | แสดง `The Username is Required!` |

ข้อมูลทดสอบของเว็บตัวอย่าง:

- Username: `demo_user`
- Password: `secret_pass`
- URL: `https://seleniumbase.io/simple/login`

## โครงสร้างโปรเจกต์

```text
.
|-- tests/
|   |-- conftest.py      # WebDriver fixture และ command-line options
|   `-- test_login.py    # Login test cases
|-- main.py              # ตัวอย่าง Selenium script แบบ standalone
|-- pyproject.toml       # Metadata, dependencies และ pytest config
|-- uv.lock              # ล็อกเวอร์ชัน dependency
`-- README.md
```

## Configuration

| Option | ค่าเริ่มต้น | ความหมาย |
| --- | --- | --- |
| `--browser` | `chrome` | เลือก `chrome` หรือ `edge` |
| `--headless` | ปิด | ซ่อนหน้าต่าง browser; หากไม่ระบุจะแสดง browser ระหว่างทดสอบ |
| `--base-url` | SeleniumBase demo | กำหนด URL ของหน้า Login |

ดู option ทั้งหมดได้ด้วย `uv run pytest --help`

## ผลการตรวจสอบล่าสุด

ตรวจสอบเมื่อ 24 กันยายน 2026 ด้วย Python 3.13.14, pytest 9.1.1, Selenium 4.49.0 และ Google Chrome แบบแสดงหน้าต่าง:

```text
collected 6 items
6 passed in 23.15s
```

## การแก้ปัญหาเบื้องต้น

- หาก browser เปิดไม่ได้ ให้ตรวจสอบว่าได้ติดตั้ง Chrome/Edge และ browser เป็นเวอร์ชันล่าสุด
- หากดาวน์โหลด driver ไม่สำเร็จ ให้ตรวจสอบ internet, proxy และ firewall จากนั้นลองรันใหม่
- หาก test timeout ให้ตรวจสอบว่าสามารถเข้า URL ทดสอบจาก browser ได้ โดยการรันปกติจะแสดง browser ให้ดูพฤติกรรมบนหน้าจอ
- หาก PowerShell ไม่อนุญาตให้ activate virtual environment สามารถใช้ `uv run pytest` ได้โดยไม่ต้อง activate
