import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Makemytrip {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().deleteAllCookies();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,30);
		
		//2) Click Hotels
		
		driver.findElementByXPath("//div[contains(@class,'loginModal')]").click();
		driver.findElementByXPath("//div[@class='chHeaderContainer']//li[@class='menu_Hotels']/a").click();
		//driver.findElementByXPath("//a[@class='active makeFlex hrtlCenter column']//span[text()='Hotels']").click();
				
		//3) Enter city as Goa, and choose Goa, India
		Thread.sleep(2000);
		WebElement cityDD = driver.findElementById("city");
		cityDD.click();
		
		Thread.sleep(2000);
		cityDD.sendKeys("Goa");
		cityDD.sendKeys(Keys.ARROW_DOWN,Keys.TAB);
		System.out.println("selected city as Goa");
		
		
		Thread.sleep(2000);
	    //4) Enter Check in date as Next month 15th (July 15) and Check out as start date+4
		driver.findElementByXPath("(//div[text()='15'])[1]").click();
		driver.findElementByXPath("(//div[text()='19'])[1]").click();
		System.out.println("Selected the checkin and check out dates");
		
		//5) Click on ROOMS & GUESTS and click 2 Adults and one Children(age 12). Click Apply Button.
		Thread.sleep(2000);
		driver.findElementById("guest").click();
		Thread.sleep(2000);
		
		driver.findElementByXPath("//li[@data-cy='children-1']").click();
		
		WebElement childAgeDD = driver.findElementByXPath("//select[@class='ageSelectBox']");
		Select childAge = new Select(childAgeDD);
		childAge.selectByVisibleText("12");
		
		
		driver.findElementByXPath("//button[text()='APPLY']").click();
		System.out.println("Selected the room");
		
		//6) Click Search button
		driver.findElementById("hsw_search_button").click();
		System.out.println("search button clicked");
		
		//7) Select locality as Baga
		// to solve access denied error
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		Thread.sleep(4000);
		
		// to click on the black screen
		driver.findElementByXPath("//div[@class='mmBackdrop wholeBlack']").click();
		//finding the textbox element
		WebElement location = driver.findElementByXPath("//input[@placeholder='Enter location or property name']");
		wait.until(ExpectedConditions.visibilityOf(location));
		
		location.click();
		driver.findElementByXPath("//label[@for='mmLocality_checkbox_35']").click();
		//location.sendKeys("Baga");
		//location.sendKeys(Keys.DOWN);
		Thread.sleep(3000);
		
		//8) 8) Select user rating as 3&above(good) under Select Filters
		driver.findElementByXPath("//label[text()='3 & above (Good)']").click();
		
		//9) Select Sort By: Price-Low to High
		driver.findElementByXPath("//div[@id='hlistpg_sortby_option']").click();
		Thread.sleep(3000);
		driver.findElementByXPath("//li[text()='Price - Low to High']").click();
		
		//10) Click on the first resulting hotel and go to the new window
		Thread.sleep(3000);
		driver.findElementById("hlistpg_hotel_name").click();
		
		//11) Print the Hotel Name
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winHandle = new ArrayList<String>(windowHandles);
		driver.switchTo().window(winHandle.get(1));
		driver.manage().deleteAllCookies();
		driver.navigate().refresh();
		
		//12) Click VIEW THIS COMBO button under Recommended Combo
		String hotelName = driver.findElementById("detpg_hotel_name").getText();
		System.out.println("Hotel name is " + hotelName);
		
		//13) Click on BOOK THIS COMBO button
		//Verify if the element is present here
		
		//14) Print the Total Payable amount
		//15) Close the browser 
		driver.close();
		driver.switchTo().window(winHandle.get(0));
		driver.close();
	}

}
