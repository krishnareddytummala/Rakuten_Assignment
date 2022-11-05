package com.assign.common;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class AppiumUtil {

    private static AppiumDriverLocalService service;

    public static void startServer() throws Exception {
        HashMap<String, String> appiumPaths = getOSSpecificPaths(System.getProperty("os.name").toLowerCase());
        service = new AppiumServiceBuilder().withAppiumJS(new File(appiumPaths.get("appiumPath"))).withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/").withIPAddress("127.0.0.1").usingPort(4723).build();
        service.start();
    }

    public static void stopServer() {
        service.stop();
    }

    static HashMap<String, String> getOSSpecificPaths(String osName) throws IOException, InterruptedException {
        HashMap<String, String> osPaths = new HashMap<>();
        if (osName.contains("win")) {
            String appiumPath = getJSPath();
            String nodePath = getNodePath();
            osPaths.put("nodePath", nodePath);
            osPaths.put("appiumPath", appiumPath);
        } else if (osName.contains("linux")) {
            osPaths.put("nodePath", "/opt/node/bin/node");
            osPaths.put("appiumPath", "/opt/node/lib/node_modules/appium/build/lib/main.js");
        }
        System.out.println(osName);
        return osPaths;
    }


    private static String getNodePath() throws IOException, InterruptedException {
        String jsPaths;
        String nodePath = null;
        Process p;
        BufferedReader reader;
        String operatingSystem = System.getProperty("os.name");
        if (operatingSystem.contains("Win")) {
            String whereNode = "where" + " " + "node";
            p = Runtime.getRuntime().exec(whereNode);
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((jsPaths = reader.readLine()) != null) {
                nodePath = jsPaths;
                break;
            }
            p.waitFor();
            p.destroy();
            if (nodePath == null) {
                System.exit(0);
            }
        } else {
            String command = "which node";
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                nodePath = line;
                break;
            }
            p.destroy();
            if (nodePath == null) {
                System.exit(0);
            }
        }
        return nodePath;
    }


    private static String getJSPath() throws InterruptedException, IOException {
        String jsPaths;
        String actualJSPath = null;

        String operatingSystem = System.getProperty("os.name");
        if (operatingSystem.contains("Win")) {
            String whereAppium = "where" + " " + "appium";
            Process p = Runtime.getRuntime().exec(whereAppium);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((jsPaths = stdInput.readLine()) != null) {
                actualJSPath = jsPaths.replace("appium", "node_modules\\appium\\build\\lib\\main.js");
                break;
            }
            p.waitFor();
            p.destroy();
            if (actualJSPath == null) {
                System.exit(0);
            }
        } else {
            actualJSPath = "//usr//local//lib//node_modules//appium//build//lib//main.js";
        }
        return actualJSPath;
    }

}
