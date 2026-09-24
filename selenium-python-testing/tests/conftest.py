import pytest
from selenium import webdriver


def pytest_addoption(parser):
    """Expose the browser settings used by the UI tests."""
    parser.addoption(
        "--browser",
        action="store",
        default="chrome",
        choices=("chrome", "edge"),
        help="Browser to use: chrome (default) or edge",
    )
    parser.addoption(
        "--headless",
        action="store_true",
        help="Run without showing the browser window",
    )
    parser.addoption(
        "--base-url",
        action="store",
        default="https://seleniumbase.io/simple/login",
        help="Login page URL",
    )


@pytest.fixture(scope="session")
def base_url(pytestconfig):
    return pytestconfig.getoption("--base-url")


@pytest.fixture
def driver(pytestconfig):
    browser = pytestconfig.getoption("--browser")
    headless = pytestconfig.getoption("--headless")

    if browser == "edge":
        options = webdriver.EdgeOptions()
        if headless:
            options.add_argument("--headless=new")
        browser_driver = webdriver.Edge(options=options)
    else:
        options = webdriver.ChromeOptions()
        if headless:
            options.add_argument("--headless=new")
        browser_driver = webdriver.Chrome(options=options)

    browser_driver.set_window_size(1440, 1000)

    yield browser_driver

    browser_driver.quit()
