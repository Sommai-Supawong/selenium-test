# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.support.ui import Select
from selenium.common.exceptions import NoSuchElementException
from selenium.common.exceptions import NoAlertPresentException
import unittest, time, re

class UntitledTestCase(unittest.TestCase):
    def setUp(self):
        # ลบ executable_path ออก เพราะ Selenium 4 จัดการให้เอง
        self.driver = webdriver.Chrome()
        self.driver.implicitly_wait(10) # ปรับลดเวลาลงมา
        self.base_url = "https://www.google.com/"
        self.verificationErrors = []
        self.accept_next_alert = True
    
    def test_untitled_test_case(self):
        driver = self.driver
        driver.get("https://www.calculator.net/bmi-calculator.html")
        
        # อัปเดตไวยากรณ์หา Element เป็นเวอร์ชันใหม่ทั้งหมด
        driver.find_element(By.ID, "cage").click()
        driver.find_element(By.ID, "cage").clear()
        driver.find_element(By.ID, "cage").send_keys("20")
        
        driver.find_element(By.ID, "cheightmeter").click()
        driver.find_element(By.XPATH, "//table[@id='metricheightweight']/tbody/tr").click()
        driver.find_element(By.ID, "cheightmeter").clear()
        driver.find_element(By.ID, "cheightmeter").send_keys("165")
        
        driver.find_element(By.ID, "ckg").click()
        driver.find_element(By.ID, "metricweight").click()
        driver.find_element(By.ID, "ckg").clear()
        driver.find_element(By.ID, "ckg").send_keys("50")
        
        driver.find_element(By.NAME, "x").click()
        
        # หน่วงเวลาเล็กน้อยเพื่อให้มองเห็นผลลัพธ์การคำนวณ (ถ้าไม่ต้องการ ลบบรรทัดนี้ได้ครับ)
        time.sleep(3)
        
        driver.get("https://www.calculator.net/bmi-calculator.html?cage=20&csex=m&cheightfeet=5&cheightinch=10&cpound=160&cheightmeter=165&ckg=50&printit=0&ctype=metric&x=Calculate")
        
        # ปิด Tab นี้
        driver.close()
    
    def is_element_present(self, how, what):
        try: self.driver.find_element(by=how, value=what)
        except NoSuchElementException as e: return False
        return True
    
    def is_alert_present(self):
        # อัปเดตไวยากรณ์ Alert
        try: self.driver.switch_to.alert
        except NoAlertPresentException as e: return False
        return True
    
    def close_alert_and_get_its_text(self):
        try:
            # อัปเดตไวยากรณ์ Alert
            alert = self.driver.switch_to.alert
            alert_text = alert.text
            if self.accept_next_alert:
                alert.accept()
            else:
                alert.dismiss()
            return alert_text
        finally: self.accept_next_alert = True
    
    def tearDown(self):
        self.driver.quit()
        self.assertEqual([], self.verificationErrors)

if __name__ == "__main__":
    unittest.main()