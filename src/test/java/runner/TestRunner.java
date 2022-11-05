package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "(@GiftCardLandScape)", glue = {"com.assign.stepdefs"}, plugin = {"html:target/cucumber-reports.html"}, features = {"src/test/resources/features"})
public class TestRunner {

    @BeforeClass
    public static void before() {
        System.out.println("Before - " + System.currentTimeMillis());
    }

    @AfterClass
    public static void after() {
        System.out.println("After - " + System.currentTimeMillis());
    }

}
