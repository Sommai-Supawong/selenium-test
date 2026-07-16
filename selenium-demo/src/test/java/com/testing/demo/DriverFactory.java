package com.testing.demo;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

/**
 * Creates a local or remote WebDriver from Maven system properties.
 * Selenium Manager resolves the platform-appropriate driver automatically.
 */
final class DriverFactory {

    private DriverFactory() {
    }

    static WebDriver create(String defaultBrowser) {
        String browser = property("browser", defaultBrowser).toLowerCase(Locale.ROOT);
        DeviceProfile device = DeviceProfile.from(property("device", "desktop"));
        boolean headless = Boolean.parseBoolean(property("headless", "true"));
        String remoteUrl = System.getProperty("remoteUrl", "").trim();

        Capabilities capabilities = createCapabilities(browser, device, headless);
        WebDriver driver = remoteUrl.isEmpty()
            ? createLocalDriver(browser, capabilities)
            : new RemoteWebDriver(toUrl(remoteUrl), capabilities);

        // Chrome/Edge mobile emulation controls its own viewport.
        if (!(device.mobile() && (browser.equals("chrome") || browser.equals("edge")))) {
            driver.manage().window().setSize(new Dimension(device.width(), device.height()));
        }

        return driver;
    }

    private static URL toUrl(String remoteUrl) {
        try {
            return URI.create(remoteUrl).toURL();
        } catch (IllegalArgumentException | MalformedURLException exception) {
            throw new IllegalArgumentException("Invalid remoteUrl: " + remoteUrl, exception);
        }
    }

    private static Capabilities createCapabilities(
        String browser,
        DeviceProfile device,
        boolean headless
    ) {
        return switch (browser) {
            case "chrome" -> chromeOptions(device, headless);
            case "firefox" -> firefoxOptions(headless);
            case "edge" -> edgeOptions(device, headless);
            case "safari" -> safariOptions(device, headless);
            default -> throw new IllegalArgumentException(
                "Unsupported browser: " + browser + ". Use chrome, firefox, edge, or safari."
            );
        };
    }

    private static WebDriver createLocalDriver(String browser, Capabilities capabilities) {
        return switch (browser) {
            case "chrome" -> new ChromeDriver((ChromeOptions) capabilities);
            case "firefox" -> new FirefoxDriver((FirefoxOptions) capabilities);
            case "edge" -> new EdgeDriver((EdgeOptions) capabilities);
            case "safari" -> new SafariDriver((SafariOptions) capabilities);
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        };
    }

    private static ChromeOptions chromeOptions(DeviceProfile device, boolean headless) {
        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=" + device.width() + "," + device.height());
        if (device.mobile()) {
            options.setExperimentalOption("mobileEmulation", mobileEmulation(device));
        }
        return options;
    }

    private static EdgeOptions edgeOptions(DeviceProfile device, boolean headless) {
        EdgeOptions options = new EdgeOptions();
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=" + device.width() + "," + device.height());
        if (device.mobile()) {
            options.setExperimentalOption("mobileEmulation", mobileEmulation(device));
        }
        return options;
    }

    private static FirefoxOptions firefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
        }
        return options;
    }

    private static SafariOptions safariOptions(DeviceProfile device, boolean headless) {
        if (headless) {
            throw new IllegalArgumentException(
                "Safari does not support headless mode. Run with -Dheadless=false."
            );
        }
        if (device.mobile()) {
            throw new IllegalArgumentException(
                "Safari WebDriver cannot emulate a mobile device. Use Chrome/Edge emulation or Appium."
            );
        }
        return new SafariOptions();
    }

    private static Map<String, Object> mobileEmulation(DeviceProfile device) {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("width", device.width());
        metrics.put("height", device.height());
        metrics.put("pixelRatio", device.pixelRatio());
        metrics.put("mobile", true);
        metrics.put("touch", true);

        Map<String, Object> emulation = new HashMap<>();
        emulation.put("deviceMetrics", metrics);
        return emulation;
    }

    private static String property(String name, String defaultValue) {
        String value = System.getProperty(name);
        return value == null || value.isBlank() ? defaultValue : value.trim();
    }

    private record DeviceProfile(int width, int height, double pixelRatio, boolean mobile) {

        private static DeviceProfile from(String deviceName) {
            String normalized = deviceName.toLowerCase(Locale.ROOT);
            DeviceProfile preset = switch (normalized) {
                case "desktop" -> new DeviceProfile(1440, 900, 1.0, false);
                case "tablet" -> new DeviceProfile(768, 1024, 2.0, true);
                case "mobile" -> new DeviceProfile(390, 844, 3.0, true);
                default -> throw new IllegalArgumentException(
                    "Unsupported device: " + deviceName + ". Use desktop, tablet, or mobile."
                );
            };

            int width = positiveIntegerProperty("viewportWidth", preset.width());
            int height = positiveIntegerProperty("viewportHeight", preset.height());
            return new DeviceProfile(width, height, preset.pixelRatio(), preset.mobile());
        }

        private static int positiveIntegerProperty(String name, int defaultValue) {
            String value = System.getProperty(name);

            if (value == null || value.isBlank()) {
                return defaultValue;
            }

            int parsed = Integer.parseInt(value);
            if (parsed <= 0) {
                throw new IllegalArgumentException(name + " must be greater than 0.");
            }
            return parsed;
        }
    }
}
