package test;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import io.appium.java_client.windows.WindowsDriver;
import page.TestUtils;

public class OutLookTest {

	WebDriver driver;
	WindowsDriver desktopDriver;
	
	@Test
	public void setUp() throws MalformedURLException {
		driver = TestUtils.startBrowser();
		driver.findElements(By.partialLinkText("Mailto with")).get(0).click();
		desktopDriver = TestUtils.switchToDesktopApp("rctrl_renwnd32");

        desktopDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        String emailAddress = desktopDriver.findElementByClassName("RichEdit20WPT").getText();
        System.out.println(emailAddress);
        TestUtils.closeDesktopApp();
	   
		driver.close();
	}

}
