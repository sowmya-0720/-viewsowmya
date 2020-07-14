import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Day7_BigBasket {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver,30);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		Actions action = new Actions(driver);
		driver.get("https://www.bigbasket.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//2) mouse over on  Shop by Category
		action.moveToElement(driver.findElementByXPath("//a[@qa='categoryDD']")).perform();
				
		
		//3) Go to Beverages and Fruit juices & Drinks
		Thread.sleep(1000);
		action.moveToElement(driver.findElementByXPath("(//a[text()='Beverages'])[2]")).perform();
		Thread.sleep(1000);
		action.moveToElement(driver.findElementByXPath("(//a[@href='/pc/beverages/fruit-juices-drinks/?nc=nb'])[2]")).perform();
		
		//4) Click on JUICES
		Thread.sleep(1000);
		
		action.moveToElement(driver.findElementByXPath("(//a[@qa='catL3'])[5]")).click().perform();
		Thread.sleep(2000);
		
		
		//5) click Tropicana and Real under Brand
		WebElement brandSearchBox = driver.findElementByXPath("//input[@placeholder='Search by Brand']");
		//4548795469856
		brandSearchBox.clear();
		brandSearchBox.sendKeys("Real");
		Thread.sleep(2000);
		driver.findElementByXPath("(//span[text()='Real'])[1]").click();
		
		brandSearchBox.clear();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Search by Brand']")));
		driver.findElementByXPath("//input[@placeholder='Search by Brand']").sendKeys("Tropicana");
		Thread.sleep(2000);
		driver.findElementByXPath("(//span[@class='cr']//i)[1]").click();
		Thread.sleep(2000);
		
		//6) Check count of the products from each Brands and total count
		List<WebElement> brands = driver.findElementsByXPath("//h6[@ng-bind='vm.selectedProduct.p_brand']");
		System.out.println("total count of products " + brands.size());
		int realJuiceCnt=0;
		int tropJuiceCnt=0;
		
		for (WebElement each : brands)
		{
			System.out.println(each.getText());
			if(each.getText().equalsIgnoreCase("Tropicana"))
			{	
				tropJuiceCnt++;
			} else {
				realJuiceCnt++;
				
			}
			
		}
		System.out.println("Tropicana juice count is " + tropJuiceCnt );
		System.out.println("Real juice count is " + realJuiceCnt);
				
		//7) Check whether the products is available with Add button.
		List<WebElement> addButton = driver.findElementsByXPath("//button[@qa='add']");
		System.out.println("Number of products having add button" + addButton.size());
		driver.findElementByXPath("(//button[@qa='add'])[1]").click();
			
		
		//8) click on Change Address
		Thread.sleep(8000);

		/*wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//a[@qa='areaDD']")));
		je.executeScript("arguments[0].click()", driver.findElementByXPath("//a[@qa='areaDD']"));
		//driver.findElementByXPath("//a[@qa='areaDD']").click();
		Thread.sleep(1000);
		*/
		je.executeScript("arguments[0].click()", driver.findElementByXPath("//a[text()='Change Location']"));
		System.out.println("Clicked change location");
		Thread.sleep(2000);
		
		//9) Select CHennai as City, Alandur-600016, as Area  and click Continue
		driver.findElementById("areaselect").sendKeys("Alandur");
		Thread.sleep(2000);
		driver.findElementById("areaselect").sendKeys(Keys.TAB);
		driver.findElementByName("continue").click();
		Thread.sleep(2000);
		
		//10) Mouse over on My Basket print the product name. count and price.
		action.moveToElement(driver.findElementByXPath("//a[@qa='myBasket']")).perform();
		Thread.sleep(2000);
		System.out.println((driver.findElementByXPath("//a[@qa='prodNameMB']")).getText());
		System.out.println((driver.findElementByXPath("//button[@qa='decQtyMB']/following-sibling::input")).getText());
		System.out.println((driver.findElementByXPath("//span[@qa='priceMB']")).getText());
		
		
		//11) Click View Basket and Checkout
		driver.findElementByXPath("//button[@qa='viewBasketMB']").click();
		Thread.sleep(1000);
		
		//12) Click the close button and close the browser
		driver.findElementByXPath("(//button[@class='close'])[1]").click();
		
		
		
	}

}

