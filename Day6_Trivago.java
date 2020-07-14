import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day6_Trivago {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,30);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		driver.get("https://www.trivago.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2) Type Agra in Destination and select Agra, Uttar Pradesh.
		driver.findElementById("querytext").sendKeys("Agra",Keys.DOWN,Keys.TAB);
		
		
		//3) Choose July 20 as check in and July 24 as check out
		
		WebElement checkIn = driver.findElementByXPath("//time[@datetime='2020-07-20']");
		je.executeScript("arguments[0].click()", checkIn);
		
		Thread.sleep(2000);
		WebElement checkOut= driver.findElementByXPath("//time[@datetime='2020-07-24']");
		je.executeScript("arguments[0].click()", checkOut);
		
		//4) Select Room as Family Room
		/*Thread.sleep(2000);
		driver.findElementByXPath("(//span[@class='flex'])[3]").click();
		Thread.sleep(2000);
		System.out.println("Select room");
		
		
		//5) Choose Number of Adults 2, Childern 1 and set Child's Age as 4
		driver.findElementByXPath("(//button[@class='circle-btn circle-btn--plus'])[2]").click();
		
		Thread.sleep(2000);
		Select childAge = new Select(driver.findElementByName("mf-select-children-age-0"));
		childAge.selectByValue("4");
			*/
		
		
		//6) Click Confirm button and click Search
		Thread.sleep(2000);
		driver.findElementByXPath("//button[text()='Apply']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//button[@data-qa='search-button']").click();
		Thread.sleep(3000);
				
		//7) Select Accommodation type as Hotels only and choose 3 stars
		//action.moveToElement(driver.findElementByXPath("(//div[@class='filter-toolbar']//li)[2]")).build();
		action.moveToElement(driver.findElementByXPath("//span[text()='All types']")).build().perform();
		System.out.println("Mouse over accomodation");
		Thread.sleep(4000);
		driver.findElementByXPath("//input[@value='Hotel']").click();
		driver.findElementByXPath("//button[@title='3-star hotels']").click();
		Thread.sleep(2000);
		
		//8) Select Guest rating as Very Good
		action.moveToElement(driver.findElementByXPath("//li[@data-qa='rating-filter']//button//span[1]")).build().perform();
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='Very good']").click();
		Thread.sleep(2000);
		
		//9) Set Hotel Location as Agra Fort and click Done
		action.moveToElement(driver.findElementByXPath("//strong[text()='Hotel location']")).build().perform();
		Thread.sleep(2000);
		Select locationDD = new Select(driver.findElementById("pois"));
		locationDD.selectByVisibleText("Agra Fort");
		driver.findElementById("filter-popover-done-button").click();
		
		//10) In more Filters, select Air conditioning, Restaurant and WiFi and click Done
		action.moveToElement(driver.findElementByXPath("//button[@title='Select']")).build().perform();
		Thread.sleep(2000);
		driver.findElementByXPath("(//input[@data-qa='filters-list-checkbox'])[3]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("(//input[@data-qa='filters-list-checkbox'])[4]").click();
		Thread.sleep(1000);
		driver.findElementByXPath("(//input[@data-qa='filters-list-checkbox'])[7]").click();
		Thread.sleep(2000);
		driver.findElementById("filter-popover-done-button").click();
		Thread.sleep(3000);
		
		//11) Sort the result as Our recommendations
		Select sortDD = new Select(driver.findElementById("mf-select-sortby"));
		sortDD.selectByValue("1");
		Thread.sleep(2000);
		
		//12) Print the Hotel name, Rating, Number of Reviews and Click View Deal
		List<WebElement> hotelNames = driver.findElementsByXPath("//span[@data-qa='item-name']");
		int sizeofLst = hotelNames.size();
		System.out.println("List size is" + sizeofLst);
		System.out.println("Hotel names are ::");
		for (WebElement each : hotelNames)
		{
			System.out.println(each.getText());
			
		}	
		
		
	}

}
