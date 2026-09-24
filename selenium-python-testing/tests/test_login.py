from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import WebDriverWait


USERNAME = "demo_user"
PASSWORD = "secret_pass"


def open_login_page(driver, base_url):
    driver.get(base_url)
    return WebDriverWait(driver, 10)


def test_login_and_logout(driver, base_url):
    wait = open_login_page(driver, base_url)

    wait.until(
        EC.visibility_of_element_located(
            (By.ID, "username")
        )
    ).send_keys(USERNAME)

    driver.find_element(
        By.ID,
        "password",
    ).send_keys(PASSWORD)

    wait.until(
        EC.element_to_be_clickable(
            (By.LINK_TEXT, "Sign in")
        )
    ).click()

    heading = wait.until(
        EC.visibility_of_element_located(
            (By.TAG_NAME, "h1")
        )
    )

    assert heading.text == "Welcome!"

    wait.until(
        EC.element_to_be_clickable(
            (By.LINK_TEXT, "Sign out")
        )
    ).click()

    message = wait.until(
        EC.visibility_of_element_located(
            (By.ID, "top_message")
        )
    )

    assert "signed out" in message.text.lower()


def test_login_with_correct_user_and_blank_password(driver, base_url):
    wait = open_login_page(driver, base_url)

    wait.until(
        EC.visibility_of_element_located(
            (By.ID, "username")
        )
    ).send_keys(USERNAME)

    wait.until(
        EC.element_to_be_clickable(
            (By.LINK_TEXT, "Sign in")
        )
    ).click()

    heading = wait.until(
        EC.visibility_of_element_located(
            (By.TAG_NAME, "h6")
        )
    )

    assert heading.text == "The Password is Required!"


def test_login_with_correct_user_and_incorrect_password(driver, base_url):
    wait = open_login_page(driver, base_url)

    wait.until(
        EC.visibility_of_element_located(
            (By.ID, "username")
        )
    ).send_keys(USERNAME)

    driver.find_element(
        By.ID,
        "password",
    ).send_keys("secret")

    wait.until(
        EC.element_to_be_clickable(
            (By.LINK_TEXT, "Sign in")
        )
    ).click()

    heading = wait.until(
        EC.visibility_of_element_located(
            (By.TAG_NAME, "h6")
        )
    )

    assert heading.text == "Invalid Password!"


def test_login_with_blank_username_and_correct_password(driver, base_url):
    wait = open_login_page(driver, base_url)

    driver.find_element(
        By.ID,
        "password",
    ).send_keys(PASSWORD)

    wait.until(
        EC.element_to_be_clickable(
            (By.LINK_TEXT, "Sign in")
        )
    ).click()

    heading = wait.until(
        EC.visibility_of_element_located(
            (By.TAG_NAME, "h6")
        )
    )

    assert heading.text == "The Username is Required!"


def test_login_with_incorrect_username_and_correct_password(driver, base_url):
    wait = open_login_page(driver, base_url)

    wait.until(
        EC.visibility_of_element_located(
            (By.ID, "username")
        )
    ).send_keys("invalid_user")

    driver.find_element(
        By.ID,
        "password",
    ).send_keys(PASSWORD)

    wait.until(
        EC.element_to_be_clickable(
            (By.LINK_TEXT, "Sign in")
        )
    ).click()

    heading = wait.until(
        EC.visibility_of_element_located(
            (By.TAG_NAME, "h6")
        )
    )

    assert heading.text == "Invalid Username!"


def test_login_with_blank_username_and_password(driver, base_url):
    wait = open_login_page(driver, base_url)

    wait.until(
        EC.element_to_be_clickable(
            (By.LINK_TEXT, "Sign in")
        )
    ).click()

    heading = wait.until(
        EC.visibility_of_element_located(
            (By.TAG_NAME, "h6")
        )
    )

    assert heading.text == "The Username is Required!"
