"""WebDriver factory configured through environment variables."""

from __future__ import annotations

import os
from dataclasses import dataclass

from selenium import webdriver


@dataclass(frozen=True)
class DeviceProfile:
    width: int
    height: int
    pixel_ratio: float
    mobile: bool

    @classmethod
    def from_environment(cls, device_name: str | None = None) -> "DeviceProfile":
        presets = {
            "desktop": cls(1440, 900, 1.0, False),
            "tablet": cls(768, 1024, 2.0, True),
            "mobile": cls(390, 844, 3.0, True),
        }
        name = (device_name or _environment("DEVICE", "desktop")).lower()
        if name not in presets:
            raise ValueError(
                f"Unsupported device: {name}. Use desktop, tablet, or mobile."
            )

        preset = presets[name]
        return cls(
            _positive_integer("VIEWPORT_WIDTH", preset.width),
            _positive_integer("VIEWPORT_HEIGHT", preset.height),
            preset.pixel_ratio,
            preset.mobile,
        )


def create_driver(
    default_browser: str,
    browser: str | None = None,
    headless: bool | None = None,
    device_name: str | None = None,
):
    browser = (browser or _environment("BROWSER", default_browser)).lower()
    device = DeviceProfile.from_environment(device_name)
    if headless is None:
        headless = _environment("HEADLESS", "false").lower() == "true"
    remote_url = os.getenv("REMOTE_URL", "").strip()
    options = _create_options(browser, device, headless)

    if remote_url:
        driver = webdriver.Remote(command_executor=remote_url, options=options)
    else:
        constructors = {
            "chrome": webdriver.Chrome,
            "firefox": webdriver.Firefox,
            "edge": webdriver.Edge,
            "safari": webdriver.Safari,
        }
        driver = constructors[browser](options=options)

    if not (device.mobile and browser in {"chrome", "edge"}):
        driver.set_window_size(device.width, device.height)
    return driver


def _create_options(browser: str, device: DeviceProfile, headless: bool):
    if browser == "chrome":
        options = webdriver.ChromeOptions()
        if headless:
            options.add_argument("--headless=new")
        options.add_argument(f"--window-size={device.width},{device.height}")
        if device.mobile:
            options.add_experimental_option("mobileEmulation", _mobile_emulation(device))
        return options

    if browser == "edge":
        options = webdriver.EdgeOptions()
        if headless:
            options.add_argument("--headless=new")
        options.add_argument(f"--window-size={device.width},{device.height}")
        if device.mobile:
            options.add_experimental_option("mobileEmulation", _mobile_emulation(device))
        return options

    if browser == "firefox":
        options = webdriver.FirefoxOptions()
        if headless:
            options.add_argument("-headless")
        return options

    if browser == "safari":
        if headless:
            raise ValueError("Safari does not support headless mode. Set HEADLESS=false.")
        if device.mobile:
            raise ValueError("Safari WebDriver cannot emulate a mobile device.")
        return webdriver.SafariOptions()

    raise ValueError(
        f"Unsupported browser: {browser}. Use chrome, firefox, edge, or safari."
    )


def _mobile_emulation(device: DeviceProfile) -> dict:
    return {
        "deviceMetrics": {
            "width": device.width,
            "height": device.height,
            "pixelRatio": device.pixel_ratio,
            "mobile": True,
            "touch": True,
        }
    }


def _environment(name: str, default: str) -> str:
    return os.getenv(name, default).strip() or default


def _positive_integer(name: str, default: int) -> int:
    raw_value = os.getenv(name, "").strip()
    if not raw_value:
        return default
    value = int(raw_value)
    if value <= 0:
        raise ValueError(f"{name} must be greater than 0.")
    return value
