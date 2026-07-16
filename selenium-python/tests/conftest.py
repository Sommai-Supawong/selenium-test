import pytest

from tests.driver_factory import create_driver


def pytest_addoption(parser):
    group = parser.getgroup("selenium", "Selenium browser options")
    group.addoption(
        "--browser",
        choices=("chrome", "firefox", "edge", "safari"),
        help="Override the browser selected by each test.",
    )
    group.addoption(
        "--headless",
        action="store_true",
        default=None,
        help="Run without displaying the browser window.",
    )
    group.addoption(
        "--device",
        choices=("desktop", "tablet", "mobile"),
        help="Device/viewport profile (default: desktop).",
    )


@pytest.fixture
def driver(request):
    default_browser = getattr(request, "param", "chrome")
    web_driver = create_driver(
        default_browser,
        browser=request.config.getoption("--browser"),
        headless=request.config.getoption("--headless"),
        device_name=request.config.getoption("--device"),
    )
    try:
        yield web_driver
    finally:
        web_driver.quit()
