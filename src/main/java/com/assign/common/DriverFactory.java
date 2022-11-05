package com.assign.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

public class DriverFactory {

    public static AndroidDriver driver = null;
    public static Properties prop = new Properties();
    private AppiumDriverLocalService service;
    private AppiumServiceBuilder builder;
    private DesiredCapabilities cap;

    private static ThreadLocal<WebDriver> driverContext = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return driverContext.get();
    }

    public static void setDriver(WebDriver driver) {
        driverContext.set(driver);
    }


    public void loadApplicationProperties() {
        try {
            prop.load(new FileInputStream("./config/application.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDriver() {
        String env = prop.getProperty("env");
        String platform = prop.getProperty("platform");
        if (env.equalsIgnoreCase("local")) {
            if (platform.equalsIgnoreCase("android")) {
                DesiredCapabilities capabilities = new DesiredCapabilities();

                capabilities.setCapability("platformName", prop.getProperty("platform"));
                capabilities.setCapability("platformVersion", prop.getProperty("platformVersion"));
                capabilities.setCapability("appium:deviceName", prop.getProperty("deviceName"));
                capabilities.setCapability("browserName", prop.getProperty("browserName"));
                capabilities.setCapability("appium:automationName", "UIAutomator2");
                capabilities.setCapability("appium:noReset", true);
                capabilities.setCapability("appium:unicodeKeyboard", true);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("disable-notifications");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                capabilities.setCapability("appium:adbExecTimeout", 100000);


                try {
                    driver = new AndroidDriver(new URL(prop.getProperty("url")), capabilities);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                setDriver(driver);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            } else {
                // we can write IoS driver implementation here
            }
        } else {
            //we can write sauce lab or browserstack driver implementation here
        }
    }

    public void networkOn() {
        if (System.getProperty("os.name").contains("Windows")) {
            Process p = null;
            try {
                p = Runtime.getRuntime().exec("adb shell am broadcast -a io.appium.settings.wifi --es setstatus enable");
                Process p1 = Runtime.getRuntime().exec("adb shell svc data enable");
                p.waitFor();
                p1.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("in else");
            try {
                Process p = Runtime.getRuntime().exec(new String[]{"bash", "-l", "-c", "adb shell am broadcast -a io.appium.settings.wifi --es setstatus enable"});
                Process p1 = Runtime.getRuntime().exec(new String[]{"bash", "-l", "-c", "adb shell svc data enable"});
                p.waitFor();
                p1.waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
