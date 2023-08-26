public class Homework24 extends BaseTest{

import com.fasterxml.jackson.databind.ser.Serializers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.UUID;

    public Object[][] getDataFromDataProviders() {

        @BeforeSuite
        static void setupClass() {
            WebDriverManager.chromedriver().setup();
//        WebDriverManager.chromedriver().setup();
        }

        public static WebDriver pickBrowser(String browserName) throws MalformedURLException {
            DesiredCapabilities caps = new DesiredCapabilities();
            String gridURL = "http://192.168.1.160:4444";

            switch (browserName){
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    return driver = new FirefoxDriver();
                case "MSEdge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.addArguments("--remote-allow-origins=*");
                    return driver = new EdgeDriver(edgeOptions);
                case "grid-edge":
                    caps.setCapability("browserName", "MicrosoftEdge");
                    return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
                case "grid-firefox":
                    caps.setCapability("browserName", "firefox");
                    return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
                case "grid-chrome":
                    caps.setCapability("browserName", "chrome");
                    return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    return driver = new ChromeDriver(options);
            }
        }

        @BeforeMethod
        @Parameters({"BaseURL"})
        public void launchBrowser(String BaseURL) {
            public void launchBrowser(String BaseURL) throws MalformedURLException {
                //      Added ChromeOptions argument below to fix websocket error
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-allow-origins=*");

                driver = new ChromeDriver(options);
                driver = pickBrowser(System.getProperty("browser"));
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                driver.manage().window().maximize();
}
