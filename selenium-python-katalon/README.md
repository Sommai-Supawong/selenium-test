# 🧪 Selenium Python Automated Testing

โปรเจกต์สำหรับทดสอบระบบอัตโนมัติแบบ **Automated UI Testing** บนเว็บไซต์ [Calculator.net – BMI Calculator](https://www.calculator.net/bmi-calculator.html) โดยใช้ **Python**, **Selenium 4** และ **unittest**

---

## 📌 เกี่ยวกับโปรเจกต์

โปรเจกต์นี้ใช้ Selenium เพื่อจำลองการใช้งานหน้าเว็บคำนวณค่า BMI เช่น

* เปิดเว็บไซต์ BMI Calculator
* กรอกอายุ ส่วนสูง และน้ำหนัก
* เลือกเพศ
* กดปุ่มคำนวณ
* ตรวจสอบผลลัพธ์ที่แสดงบนหน้าเว็บไซต์

---

## 🛠️ เทคโนโลยีที่ใช้

* Python 3
* Selenium 4
* unittest
* Google Chrome
* Selenium Manager สำหรับจัดการ ChromeDriver อัตโนมัติ

---

## 📁 โครงสร้างโปรเจกต์

```text
selenium-test/
├── selenium-python/
│   └── .venv/                         # Python Virtual Environment
│
└── selenium-python-katalon/
    └── test_bmi.py                    # Selenium Test Script
```

> ไฟล์ `.venv` เป็นสภาพแวดล้อมจำลองสำหรับติดตั้งไลบรารีของโปรเจกต์โดยไม่กระทบกับ Python หลักในเครื่อง

---

## ✅ ความต้องการของระบบ

ก่อนเริ่มใช้งาน ควรติดตั้งโปรแกรมต่อไปนี้ให้เรียบร้อย

* [Python 3.x](https://www.python.org/downloads/)
* [Google Chrome](https://www.google.com/chrome/)
* `pip` สำหรับติดตั้ง Python Package

ตรวจสอบเวอร์ชัน Python ด้วยคำสั่ง

```bash
python --version
```

หรือบน macOS

```bash
python3 --version
```

ตรวจสอบเวอร์ชัน `pip`

```bash
pip --version
```

---

## 🚀 การติดตั้งและรันโปรเจกต์

เปิด Terminal หรือ Command Prompt แล้วเข้าไปยังโฟลเดอร์หลักของโปรเจกต์

```bash
cd selenium-test
```

---

## 🍏 สำหรับ macOS

### 1. เปิดใช้งาน Virtual Environment

```bash
source selenium-python/.venv/bin/activate
```

เมื่อเปิดใช้งานสำเร็จ จะเห็นคำว่า `(.venv)` อยู่ด้านหน้าบรรทัดคำสั่ง เช่น

```text
(.venv) user@MacBook selenium-test %
```

### 2. ติดตั้ง Selenium

ทำขั้นตอนนี้เฉพาะครั้งแรก หรือเมื่อยังไม่ได้ติดตั้ง Selenium ใน Virtual Environment

```bash
pip install selenium
```

ตรวจสอบว่า Selenium ถูกติดตั้งแล้ว

```bash
pip show selenium
```

### 3. เข้าไปยังโฟลเดอร์ไฟล์ทดสอบ

```bash
cd selenium-python-katalon
```

### 4. รัน Selenium Test

```bash
python3 test_bmi.py
```

หรือรันผ่าน `unittest`

```bash
python3 -m unittest test_bmi.py
```

---

## 🪟 สำหรับ Windows

### 1. เปิดใช้งาน Virtual Environment

สำหรับ Command Prompt:

```bat
selenium-python\.venv\Scripts\activate.bat
```

สำหรับ PowerShell:

```powershell
selenium-python\.venv\Scripts\Activate.ps1
```

เมื่อเปิดใช้งานสำเร็จ จะเห็นคำว่า `(.venv)` อยู่ด้านหน้าบรรทัดคำสั่ง เช่น

```text
(.venv) C:\selenium-test>
```

> หาก PowerShell ไม่อนุญาตให้รันสคริปต์ สามารถเปิดสิทธิ์สำหรับผู้ใช้ปัจจุบันด้วยคำสั่งต่อไปนี้

```powershell
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```

### 2. ติดตั้ง Selenium

ทำขั้นตอนนี้เฉพาะครั้งแรก หรือเมื่อยังไม่ได้ติดตั้ง Selenium ใน Virtual Environment

```bash
pip install selenium
```

ตรวจสอบว่า Selenium ถูกติดตั้งแล้ว

```bash
pip show selenium
```

### 3. เข้าไปยังโฟลเดอร์ไฟล์ทดสอบ

```bash
cd selenium-python-katalon
```

### 4. รัน Selenium Test

```bash
python test_bmi.py
```

หรือรันผ่าน `unittest`

```bash
python -m unittest test_bmi.py
```

---

## 🧪 ตัวอย่างผลลัพธ์การทดสอบ

เมื่อการทดสอบผ่าน ระบบจะแสดงผลลัพธ์คล้ายกับตัวอย่างต่อไปนี้

```text
.
----------------------------------------------------------------------
Ran 1 test in 8.432s

OK
```

ความหมายของผลลัพธ์:

* `.` หมายถึง Test Case ผ่าน
* `Ran 1 test` หมายถึงรันทั้งหมด 1 Test Case
* `OK` หมายถึงการทดสอบทั้งหมดสำเร็จ

หากการทดสอบไม่ผ่าน อาจแสดงข้อความ เช่น

```text
FAIL
```

หรือ

```text
ERROR
```

พร้อมรายละเอียดตำแหน่งและสาเหตุของปัญหา

---

## 🛑 การออกจาก Virtual Environment

เมื่อทดสอบเสร็จแล้ว สามารถออกจาก Virtual Environment ได้ด้วยคำสั่ง

```bash
deactivate
```

เมื่อออกสำเร็จ คำว่า `(.venv)` ที่อยู่ด้านหน้าบรรทัดคำสั่งจะหายไป

---

## 🔧 การแก้ไขปัญหาเบื้องต้น

### ไม่พบคำสั่ง `python`

ลองใช้คำสั่งต่อไปนี้แทน

```bash
python3 --version
```

และรันไฟล์ด้วย

```bash
python3 test_bmi.py
```

### ไม่พบ Selenium

ติดตั้ง Selenium ใหม่ด้วยคำสั่ง

```bash
pip install selenium
```

หรืออัปเกรดเป็นเวอร์ชันล่าสุด

```bash
pip install --upgrade selenium
```

### Google Chrome ไม่เปิด

ตรวจสอบว่าได้ติดตั้ง Google Chrome ไว้ในเครื่องแล้ว และลองอัปเดต Selenium

```bash
pip install --upgrade selenium
```

### ไม่พบไฟล์ `test_bmi.py`

ตรวจสอบว่า Terminal อยู่ในโฟลเดอร์ `selenium-python-katalon`

```bash
cd selenium-python-katalon
```

ตรวจสอบไฟล์ภายในโฟลเดอร์

macOS หรือ Linux:

```bash
ls
```

Windows:

```bat
dir
```

---

## 📦 บันทึก Dependencies

สามารถบันทึกรายการไลบรารีที่ติดตั้งไว้ในไฟล์ `requirements.txt` ด้วยคำสั่ง

```bash
pip freeze > requirements.txt
```

เมื่อต้องการติดตั้งไลบรารีทั้งหมดในเครื่องอื่น ให้ใช้คำสั่ง

```bash
pip install -r requirements.txt
```

---

## 📝 หมายเหตุ

* ควรเปิดใช้งาน Virtual Environment ก่อนติดตั้งไลบรารีและรัน Test ทุกครั้ง
* Selenium 4 สามารถใช้ Selenium Manager เพื่อดาวน์โหลดและจัดการ WebDriver ได้อัตโนมัติ
* ควรเชื่อมต่ออินเทอร์เน็ตระหว่างการรันทดสอบ เนื่องจากเว็บไซต์ที่ใช้ทดสอบเป็นเว็บไซต์ออนไลน์
* หากหน้าเว็บไซต์มีการเปลี่ยนแปลง อาจต้องปรับ Locator ภายในไฟล์ `test_bmi.py`

---

## 🌐 เว็บไซต์ที่ใช้ทดสอบ

[Calculator.net – BMI Calculator](https://www.calculator.net/bmi-calculator.html)

---

## 📄 License

โปรเจกต์นี้จัดทำขึ้นเพื่อการศึกษาและการฝึกใช้งาน Automated Testing ด้วย Selenium
