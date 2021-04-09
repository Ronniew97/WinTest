package page;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestUtils {

static WebDriver driver;
static WindowsDriver desktopDriver;

	public static WebDriver startBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.k8oms.net/links/mailto-link");
		driver.manage().window().maximize();
		return driver;
	}
	
	public static WindowsDriver switchToDesktopApp(String desktopApp) {
		try {
	        DesiredCapabilities desktopCapabilities = new DesiredCapabilities();
	        desktopCapabilities.setCapability("platformName", "Windows");
	        desktopCapabilities.setCapability("deviceName", "WindowsPC");
	        desktopCapabilities.setCapability("app", "Root");
	
	        WindowsDriver desktopSession = new WindowsDriver(new URL("http://127.0.0.1:4723/wd/hub"), desktopCapabilities);
	        desktopSession.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        WebElement findOutLookWindow = desktopSession.findElementByClassName(desktopApp);
	        String outLookWinHandleStr = findOutLookWindow.getAttribute("NativeWindowHandle");
	        int outLookWinHandleInt = Integer.parseInt(outLookWinHandleStr);
	        System.out.println(outLookWinHandleInt);
	        String outLookWinHandleHex = Integer.toHexString(outLookWinHandleInt);
	
	        DesiredCapabilities outLookCapabilities = new DesiredCapabilities();
	        outLookCapabilities.setCapability("platformName", "Windows");
	        outLookCapabilities.setCapability("deviceName", "WindowsPC");
	        outLookCapabilities.setCapability("appTopLevelWindow", outLookWinHandleHex);
	        System.out.println("OutLook Handle is: " + outLookWinHandleHex);
	        
	        desktopDriver = new WindowsDriver(new URL("http://127.0.0.1:4723/wd/hub"), outLookCapabilities);
		} catch (MalformedURLException e) {
		        e.printStackTrace();
	    }
		return desktopDriver;
	}
	
	public static void closeDesktopApp() {
        desktopDriver.close();
        desktopDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        desktopDriver.findElementByName("No").click();
	}
}
