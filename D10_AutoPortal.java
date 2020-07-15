import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class D10_AutoPortal {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stubdriver.findElementById("ac_user_city").sendKeys(
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);

		ChromeDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver,30);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		driver.get("https://autoportal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//driver.findElementByXPath("//div[@class='maincity-in field']").click();
		//Thread.sleep(2000);
		driver.findElementByXPath("//span[@data-trackevent-label='changeCity']").click();
		Thread.sleep(2000);
		driver.findElementById("ac_user_city").sendKeys("Chennai");
		Thread.sleep(2000);
		driver.findElementById("ac_user_city").sendKeys(Keys.DOWN,Keys.ENTER);
		Thread.sleep(3000);
		driver.findElementByXPath("//span[@data-trackevent-action='ConfirmCity']").click();
		
		//Select Car Brand as Renault
		Thread.sleep(3000);
		Select brandDD= new Select(driver.findElementByName("brand"));
		brandDD.selectByVisibleText("Renault");
		Thread.sleep(2000);
		
		//Select Model as Arkana
		Select modelDD = new Select(driver.findElementByName("model"));
		modelDD.selectByVisibleText("Arkana");
		Thread.sleep(2000);
		
		//Search for the car
		driver.findElementByXPath("//input[@value='Find new cars']").click();
		
		//Print the expected price
		String cost = driver.findElementByXPath("//div[@class='nm_price-launch-date']//div[1]").getText();
		System.out.println(cost);
		
		
		
		
		
		
	}

}
