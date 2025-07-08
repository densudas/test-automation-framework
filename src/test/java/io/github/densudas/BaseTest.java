package io.github.densudas;

import io.github.densudas.selenium.utils.DriverFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    @BeforeSuite
    public void setUp() {
        Runtime.getRuntime()
                .addShutdownHook(new Thread(DriverFactory::closeAllDrivers, "Shutdown-thread"));
    }

    @AfterSuite
    public void tearDown() {
        DriverFactory.closeAllDrivers();
    }
}
