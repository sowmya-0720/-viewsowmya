import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day2_Ajio {
	
	public static JavascriptExecutor js;
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		
		//Disable notifications
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(ops);
		
		//Go to https://www.ajio.com/shop/sale
		driver.get("https://www.ajio.com/shop/sale");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		WebDriverWait wait = new WebDriverWait(driver,30);
		
			
		//Enter Bags in the Search field and Select Bags in Women Handbags
		
		driver.findElementByXPath("//input[@placeholder='Search AJIO']").sendKeys("Bags");
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//ul[@class = 'rilrtl-list ']/li[3]")));
		driver.findElementByXPath("//ul[@class = 'rilrtl-list ']/li[3]").click();
		Thread.sleep(3000);
		
		
		//3) Click on five grid and Select SORT BY as "What's New"
		driver.findElementByXPath("//div[@class='five-grid-container ']").click();
		Thread.sleep(2000);
		WebElement sortBy = driver.findElementByXPath("//div[@class='filter-dropdown']/select");
		Select sortDD = new Select(sortBy);
		sortDD.selectByValue("newn");
		Thread.sleep(3000);
		
		//4) Enter Price Range Min as 2500 and Max as 5000
		
		driver.findElementByXPath("(//div[@class='facet-head '])[1]").click();
		driver.findElementById("minPrice").sendKeys("2500");
		driver.findElementById("maxPrice").sendKeys("5000");
		driver.findElementByXPath("//input[@id='maxPrice']/following-sibling::button[1]").click();
		Thread.sleep(3000);
		
		//5) Click on the product "TOMMY HILFIGER Sling Bag with Chain Strap"
		WebElement handBag = driver.findElementByXPath("//div[text()='Sling Bag with Chain Strap']");
		js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(false);",handBag);
		handBag.click();
		Thread.sleep(2000);
		
		//6)Verify the Coupon code for the price above 2890 is applicable for your product, 
		//if applicable then get the Coupon Code and the discount price for the coupon
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowLst = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windowLst.get(1));
		
		String price = driver.findElementByXPath("//div[@class='prod-sp']").getText();
		price = price.replaceAll("[^0-9]", "").trim();
		System.out.println("Price of the bag is " + price);
		
		Integer bagPr = Integer.parseInt(price);
		boolean discount = false;
		String discountPrice="";
		String couponCode="";
		
		if(bagPr >2890)
		{
			discount= true;
		}	
		
		if (discount)
		{
			couponCode = driver.findElementByXPath("//div[@class='promo-title']").getText();
			couponCode =couponCode.substring(9, 14);
			System.out.println("Coupon code is " + couponCode);
			Thread.sleep(3000);
			discountPrice = driver.findElementByXPath("//div[@class='promo-discounted-price']//span").getText();
			discountPrice = discountPrice.replaceAll("[^0-9]", "").trim();
			System.out.println("Discounted price is " + discountPrice);
			
		}
		Thread.sleep(2000);
		
		//7) Check the availability of the product for pincode 560043, print the expected delivery date if it is available
		driver.findElementByXPath("(//div[@class='edd-pincode-msg-container']//span)[2]").click();
		Thread.sleep(3000);
		driver.findElementByName("pincode").sendKeys("560043", Keys.ENTER);
		Thread.sleep(3000);
		String delDate = driver.findElementByXPath("//ul[@class='edd-message-success-details']//li[1]/span").getText();
		System.out.println("Delivery date is "+ delDate);
		Thread.sleep(2000);
		
		//8) Click on other Informations under Product Details and Print the Customer Care address, 
		//phone and email
		driver.findElementByXPath("//div[@class='other-info-toggle']").click();
		Thread.sleep(2000);
		String address = driver.findElementByXPath("(//span[@class='other-info'])[7]").getText();
		System.out.println("Address is " + address);
		Thread.sleep(2000);
		
		//9) Click on ADD TO BAG and then GO TO BAG
		driver.findElementByXPath("//span[text()='ADD TO BAG']").click();
		Thread.sleep(2000);
		driver.findElementByXPath("//span[text()='GO TO BAG']").click();
		
		
		//10) Check the Order Total before apply coupon
		Thread.sleep(2000);
		String totalPrice = driver.findElementByXPath("//div[@class='net-price best-price-strip']").getText();
		totalPrice = totalPrice.replaceAll("[^0-9]", "");
		System.out.println("Total price is " + totalPrice);
		
		if(bagPr == (Integer.parseInt(totalPrice)))	
		{
			System.out.println("Prices are the same");
		}
		
		//11) Enter Coupon Code and Click Apply
		
		
		Thread.sleep(2000);
		driver.findElementById("couponCodeInput").sendKeys(couponCode);
		Thread.sleep(2000);

	
		driver.findElementByXPath("//input[@id='couponCodeInput']//following-sibling::button").click();
		Thread.sleep(2000);
		
		//12) Verify the discount price matches with the product price
		
		String netPrice = driver.findElementByXPath("//div[@class='net-price best-price-strip']").getText();
		netPrice=netPrice.replaceAll("[^0-9]", "").trim();
		if((Integer.parseInt(netPrice)) == (Integer.parseInt(discountPrice)))
		{
			System.out.println("Disount and net price are the same");
			
		} else {
			System.out.println("Discount and net price are not the same");
		}
					
		//13) Click on Delete and Delete the item from Bag
		Thread.sleep(3000);
		driver.findElementByXPath("//div[text()='Delete']").click();
		Thread.sleep(1000);
		driver.findElementByXPath("//div[text()='DELETE']").click();
		
		//14) Close all the browsers
		driver.close();
		driver.switchTo().window(windowLst.get(0));
		driver.close();
		
	}

}
