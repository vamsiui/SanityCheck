package com.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ShoppingCart {
	public WebDriver driver;
	private static ExtentReports extent;
	private static ExtentTest test;

	
	
	@BeforeMethod
	public void ShoppingCart() throws MalformedURLException, InterruptedException {
		File directory = new File(System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/Reports");
		File[] files = directory.listFiles();
		for (File file : files) {
			if (!file.delete()) {
				System.out.println("");
			}
		}

		extent = new ExtentReports(
				System.getProperty("user.dir") + "/src/test/java/com/shoppingcart/Reports/ShoppingCartDemo.html", true);
		extent.loadConfig(new File("./extent-config.xml"));
		extent.addSystemInfo("Selenium", "3.12").addSystemInfo("Environment", "Production");
		System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&*************************");
		driver.get("http://14.99.175.107:17670/SpringMVCAnnotationShoppingCart/");
		
	}


	@Test(priority=1, enabled=true)
	public void ShoppingCartCheckTabs()
			throws  IOException, InterruptedException, AWTException {
		test = extent.startTest(Thread.currentThread().getStackTrace()[1].getMethodName(),
				"Online Shopping - Navigate Tabs.");

			test.log(LogStatus.INFO, "Login into application");
			// Login into application
			driver.findElement(By.xpath("//*[text()='Login']")).click();
			driver.findElement(By.xpath("//*[@name='userName']")).sendKeys("admin");
			driver.findElement(By.xpath("//*[@name='password']")).sendKeys("123");
			driver.findElement(By.xpath("//*[@value='Login']")).click();
			Thread.sleep(2000);

			String fileName = "Login.png";
			takeScreenshotImage(fileName);

			// Validate login
			if (driver.findElement(By.xpath("//*[contains(text(),'Hello')]")).isDisplayed()) {
				System.out.println(
						"Entered username, password and clicked login button.");
				test.log(LogStatus.PASS,
						"Entered username, password and clicked login button.."
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName).getPath()));

			} else {
				System.out.println(
						"Entered username, password and clicked login button. User Failed to login.");
				test.log(LogStatus.FAIL,
						"Entered username, password and clicked login button. User User Failed to login"
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName).getPath()));
		
			}
			
			
			test.log(LogStatus.INFO, "Navigate to Product List");

			Thread.sleep(2000);
			String fileName1 = "ProductList.png";
			takeScreenshotImage(fileName1);
			driver.findElement(By.xpath("//a[normalize-space() = 'Product List']")).click();
			
			int pcount = driver.findElements(By.xpath("//a[normalize-space() = 'Buy Now']")).size();
			if (pcount!=0) {
				System.out.println(
						"Navigated to Product List successfully.");
				test.log(LogStatus.PASS,
						"Navigate to Product List successfully "
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName1).getPath()));
			

			} else {
				System.out.println("User Failed to Navigate to the Product List");
				test.log(LogStatus.FAIL, "User Failed to Navigate to the Product List ." + test.addScreenCapture(new File(System.getProperty("user.dir") +
						"/src/test/java/com/shoppingcart/screenshots/" + fileName1)
								.getPath()));
				
			}
			Thread.sleep(2000);
			
			test.log(LogStatus.INFO, "Navigate to MYCART");

			String fileName2 = "MyCart.png";
			takeScreenshotImage(fileName2);
			
			driver.findElement(By.xpath("//a[normalize-space() = 'My Cart']")).click();
			
			int mycartcnt = driver.findElements(By.xpath("//div[text()='My Cart']")).size();
			
			
			
			// Validate the Create product
			if (mycartcnt!=0) {
				System.out.println(
						"User Navigated to the MY Cart page successfully.");
				test.log(LogStatus.PASS,
						"User Navigated to the MY Cart page Successfully"
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName2).getPath()));
			

			} else {
				System.out.println("User failed to Navigate to the MY Cart page");
				test.log(LogStatus.FAIL, "User failed to Navigate to the MY Cart page" + test.addScreenCapture(new File(System.getProperty("user.dir") +
						"/src/test/java/com/shoppingcart/screenshots/" + fileName2)
								.getPath()));
				
			}
			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Navigate to OrderList");

			String fileName3 = "OrdersList.png";
			takeScreenshotImage(fileName3);
			
			driver.findElement(By.xpath("//a[normalize-space() = 'Order List']")).click();
			
			int myOrderListCnt = driver.findElements(By.xpath("//div[text()='Order List']")).size();
			
			if (myOrderListCnt!=0) {
				System.out.println("User Navigated to the OrderList successfully");
				test.log(LogStatus.PASS,
						"User Navigated to the OrderList successfully" + test.addScreenCapture(
								new File(System.getProperty("user.dir") +"/src/test/java/com/shoppingcart/screenshots/"
										+ fileName3).getPath()));
				
			} else {
				System.out.println("User failed to Navigate to the OrderList page");
				test.log(LogStatus.FAIL,
						"User failed to Navigate to the OrderList page" + test.addScreenCapture(
								new File(System.getProperty("user.dir") +"/src/test/java/com/shoppingcart/screenshots/"
										+ fileName3).getPath()));
				
			}
			
			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Navigate to CraeteProduct");

			String fileName4 = "CreateProduct.png";
			takeScreenshotImage(fileName4);
			
			driver.findElement(By.xpath("//a[normalize-space() = 'Create Product']")).click();
			
			int createProdCnt = driver.findElements(By.xpath("//table//td[text()='Upload Image']")).size();	

			// Validate the Buy a product page navigation
			if (createProdCnt!=0) {
				System.out.println("User Navigated to the CraeteProduct page successfully");
				test.log(LogStatus.PASS,
						"User Navigated to the CraeteProduct page successfully" + test.addScreenCapture(
								new File(System.getProperty("user.dir") +"/src/test/java/com/shoppingcart/screenshots/"
										+ fileName4).getPath()));
				
			} else {
				System.out.println("Clicked 'User faield to Navigate  CraeteProduct page");
				test.log(LogStatus.FAIL, "User faield to Navigate CraeteProduct page"
						+ test.addScreenCapture(
								new File(System.getProperty("user.dir") +"/src/test/java/com/shoppingcart/screenshots/"
										+ fileName4).getPath()));
				
			}
			Thread.sleep(2000);
			test.log(LogStatus.INFO, "Logout");

			String fileName5 = "Logout.png";
			takeScreenshotImage(fileName5);
			
			driver.findElement(By.xpath("//a[text()='Logout']")).click();
			
			// Validate the Create product
			if (driver.findElement(By.xpath("//div[text()='Shopping Cart Demo']")).isDisplayed()) {
				System.out.println("Users Logged Out successfully");
				test.log(LogStatus.PASS,
						"Users Logged Out successfully"
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName5).getPath()));
				
			} else {
				System.out.println(
						"User is failed to logout");
				test.log(LogStatus.FAIL,
						"User is failed to logout"
								+ test.addScreenCapture(new File(System.getProperty("user.dir") +
										"/src/test/java/com/shoppingcart/screenshots/"
												+ fileName5).getPath()));
				
			}
		extent.endTest(test);
		extent.flush();
		extent.close();

		Thread.sleep(2000);
		driver.close();

	}

	public void takeScreenshotImage(String fileNameWithExtention) throws IOException {
		WebDriver driver1 = new Augmenter().augment(driver);
		File actualFile = ((TakesScreenshot) driver1).getScreenshotAs(OutputType.FILE);
		try {
			BufferedImage fullImg = ImageIO.read(actualFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			FileUtils.copyFile(actualFile,
					new File(System.getProperty("user.dir") +"/src/test/java/com/shoppingcart/screenshots/"
							+ fileNameWithExtention));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
