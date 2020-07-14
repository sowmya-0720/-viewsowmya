import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day5_Zalando {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,30);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		driver.get("https://www.zalando.com/");

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2) Get the Alert text and print it 
		Thread.sleep(1000);
		String alertTxt = driver.switchTo().alert().getText();
		System.out.println("Alert text is "+ alertTxt);
		driver.switchTo().alert().accept();
		driver.manage().window().maximize();
		
		//3) Close the Alert box and click on Zalando.uk
		driver.findElementByXPath("//a[text()='Zalando.uk']").click();
		
		//close the tailor experience pop up
		Thread.sleep(4000);
		driver.findElementByXPath("//button[@id='uc-btn-accept-banner']").click();
		
		//4) Click Women--> Clothing and click Coats 
		Thread.sleep(2000);
		action.moveToElement(driver.findElementByXPath("(//span[text()='Women'])[1]")).click().perform();
		action.moveToElement(driver.findElementByXPath("//span[text()='Clothing']")).perform();
		action.moveToElement(driver.findElementByXPath("//span[text()='Coats']")).click().perform();
		
		
		//5) Choose Material as cotton (100%) and Length as thigh-length
		Thread.sleep(4000);
		
		WebElement material = driver.findElementByXPath("//span[text()='Material']");
		je.executeScript("arguments[0].click()", material);
		Thread.sleep(1000);
		driver.findElementByXPath("//span[text()='cotton (100%)']").click();
		driver.findElementByXPath("//button[text()='Save']").click();
		Thread.sleep(2000);
		//choosing length and thigh length
		WebElement length = driver.findElementByXPath("//span[text()='Length']");
		je.executeScript("arguments[0].click()", length);
		Thread.sleep(1000);
		driver.findElementByXPath("//span[text()='thigh-length']").click();
		driver.findElementByXPath("//button[text()='Save']").click();
		Thread.sleep(2000);
				
		//6) Click on 
		
		WebElement coat = driver.findElementByXPath("//div[text()='CLASSIC TRENCH - Trenchcoat - black']");
		je.executeScript("arguments[0].scrollIntoView(true);", coat);
		coat.click();
		Thread.sleep(3000);
				
		//7) Click Colour as Black and Size as 'M' Under Manufacture Sizes
		driver.findElementByXPath("//span[text()='Choose your size']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//li[@id='ES421U05S-Q110038000']").click();
		
		//8) Add to bag only if Standard Delivery is free
		String delivery = driver.findElementByXPath("//button[@aria-label='Free']//span").getText();
		System.out.println("Delivery text is --" + delivery);
		if(delivery.equalsIgnoreCase("Free"))
		{
			driver.findElementByXPath("//span[text()='Add to bag']").click();
			
		}
		
		//9) Mouse over on Your Bag and Click on "Go to Bag"
		WebElement bag = driver.findElementByXPath("//span[text()='Your bag']");
		je.executeScript("arguments[0].scrollIntoView(true);",bag);
		action.moveToElement(bag).build();
		Thread.sleep(2000);
		driver.findElementByXPath("//div[text()='Go to bag']").click();
		
		//10) Read the Estimated Delivery Date and print
		String deliveryDate = driver.findElementByXPath("//div[@data-id='delivery-estimation']//span").getText();
		System.out.println("delivery date is" + deliveryDate);
		
		//11) Click on 'Go To Checkout'
		Thread.sleep(2000);
		driver.findElementByXPath("//div[text()='Go to checkout']").click();
		
		//12) Enter your email
		Thread.sleep(2000);
		driver.findElementById("login.email").sendKeys("sowmya@gmail.com");
		
		//13) Click on Login button
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Login']").click();
		
		//14) Print the error message
		Thread.sleep(4000);
		String errorText = driver.findElementByXPath("//span[text()='error']/following-sibling::span").getText();
		System.out.println("Error text is " + errorText);
		
		driver.close();
	}

}
