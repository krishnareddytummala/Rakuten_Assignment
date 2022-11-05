package com.assign.stepdefs;

import com.assign.common.AppiumUtil;
import com.assign.pages.BasePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks extends BasePage {

    @Before
    public void before() {
        loadApplicationProperties();
        try {
            AppiumUtil.startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDriverSession();
        networkOn();
    }

    @After
    public void after() {
        driver.quit();
        AppiumUtil.stopServer();
    }

}
