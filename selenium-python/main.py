from selenium import webdriver

 
driver = webdriver.Chrome()

 
driver.get("https://pgm.npru.ac.th/se/")

 
print(driver.title)

 
# driver.quit()