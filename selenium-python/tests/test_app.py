import pytest
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as conditions
from selenium.webdriver.support.ui import WebDriverWait


@pytest.mark.parametrize("driver", ["chrome"], indirect=True)
def test_google_search_title(driver):
    driver.get("https://www.google.com")
    WebDriverWait(driver, 15).until(conditions.title_is("Google"))
    assert driver.title == "Google"


@pytest.mark.parametrize("driver", ["chrome"], indirect=True)
def test_wikipedia_search(driver):
    driver.get("https://www.wikipedia.org/")
    wait = WebDriverWait(driver, 15)

    search_box = wait.until(conditions.element_to_be_clickable((By.ID, "searchInput")))
    search_box.send_keys("Software engineering")
    wait.until(
        conditions.element_to_be_clickable((By.CSS_SELECTOR, "button[type='submit']"))
    ).click()

    heading = wait.until(conditions.visibility_of_element_located((By.ID, "firstHeading")))
    assert heading.text == "Software engineering"

