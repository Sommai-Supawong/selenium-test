import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as conditions
from selenium.webdriver.support.ui import WebDriverWait


@pytest.mark.parametrize("driver", ["firefox"], indirect=True)
def test_bmi_calculator_with_metric_input(driver):
    age, gender, height_cm, weight_kg = 25, "m", 180, 65
    driver.get("https://www.calculator.net/bmi-calculator.html?ctype=metric")
    wait = WebDriverWait(driver, 15)

    age_input = wait.until(conditions.element_to_be_clickable((By.NAME, "cage")))
    age_input.clear()
    age_input.send_keys(str(age))

    gender_radio = wait.until(
        conditions.presence_of_element_located(
            (By.CSS_SELECTOR, f"input[name='csex'][value='{gender}']")
        )
    )
    if not gender_radio.is_selected():
        driver.execute_script("arguments[0].click();", gender_radio)
    assert gender_radio.is_selected(), "Cannot select male gender"

    height_input = wait.until(
        conditions.element_to_be_clickable((By.NAME, "cheightmeter"))
    )
    height_input.clear()
    height_input.send_keys(str(height_cm))

    weight_input = wait.until(conditions.element_to_be_clickable((By.NAME, "ckg")))
    weight_input.clear()
    weight_input.send_keys(str(weight_kg))

    wait.until(
        conditions.element_to_be_clickable(
            (By.CSS_SELECTOR, "input[type='submit'][value='Calculate']")
        )
    ).click()

    expected_bmi = f"{weight_kg / (height_cm / 100) ** 2:.1f}"
    body = (By.TAG_NAME, "body")
    wait.until(conditions.text_to_be_present_in_element_located(body, f"BMI = {expected_bmi}"))
    page_text = driver.find_element(*body).text

    assert f"BMI = {expected_bmi}" in page_text
    assert "(Normal)" in page_text

