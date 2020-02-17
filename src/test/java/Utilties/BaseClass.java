package Utilties;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.swing.text.Document;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseClass
{
	public static ExtentHtmlReporter Extent;
	public static ExtentReports Report;
    public static ExtentTest test;
   public static JavascriptExecutor js;
   
  	public static WebDriver driver;
  	
	public static WebElement Xpath(String filename, String Xpath)	
	{
		WebElement xpath= driver.findElement(By.xpath(ReadConfig.ReadFile(filename, Xpath)));	
		return xpath;	
	}
	public static WebElement ID(String filename, String ID)	
	{
		WebElement id= driver.findElement(By.id(ReadConfig.ReadFile(filename,ID)));	
		return id;	
	}
	public static WebElement linkText(String link)	
	{
		WebElement links= driver.findElement(By.linkText(link));	
		return links;	
	}
	public static String Paths(String filename,String path)
	{
		String pat=ReadConfig.ReadFile(filename,path);		
		return pat;		
	}
	public static void openURL(String filename,String url)	
	{
		driver.get(ReadConfig.ReadFile(filename,url));				
	}

//***********************************Method is to enter the text in the TextBox Elements*******************************
	public static void enterTextboxValue(String filename,String webelement,String fname,String sheet,int row,int column) 
	{
		explicitWait(3000, filename, webelement);
		try {
		/*	scrollToElement(filename, webelement);
			if(Xpath(filename,webelement).isDisplayed() && Xpath(filename,webelement).isEnabled()) {
				WebElement complexelement=Xpath(filename, webelement);		
				Actions act=new Actions(driver);
				act.moveToElement(complexelement);
				act.click();
				act.sendKeys(ReadExcel.readData(fname, sheet, row, column)).build().perform();
				{
				if (Xpath(filename,webelement).getAttribute("value").isEmpty() && Xpath(filename,webelement).getText().isEmpty()) {
					Xpath(filename,webelement).sendKeys(ReadExcel.readData(fname, sheet, row, column));
				}
			
			}*/
				
				do {
					WebElement complexelement1=Xpath(filename, webelement);		
					Actions act1=new Actions(driver);
					act1.moveToElement(complexelement1);
					act1.click();
					act1.sendKeys(ReadExcel.readData(fname, sheet, row, column)).build().perform();
					
				} while (Xpath(filename,webelement).getText().contains(""));
				
				Xpath(filename,webelement).sendKeys(ReadExcel.readData(fname, sheet, row, column));
			
				
				{
					
				}
			/*}*/
		/*
		else {
			System.out.println("Provided text is already entered in the text field");
				
			}	*/
		} 
			
		catch ( NoSuchElementException exception) {
			scrollToElement(filename, webelement);
			System.out.println("Catch Block");
			if (driver.getPageSource().contains("Error Code")) {
				
				Enter(KeyEvent.VK_F5);
			}
			else if(Xpath(filename,webelement).isDisplayed() && Xpath(filename,webelement).isEnabled()) {
				WebElement complexelement=Xpath(filename, webelement);		
				Actions act=new Actions(driver);
				act.moveToElement(complexelement);
				act.click();
				act.sendKeys(ReadExcel.readData(fname, sheet, row, column)).build().perform();		
				}
	
			else if(Xpath(filename,webelement).isDisplayed() && Xpath(filename,webelement).isEnabled()) {
				Xpath(filename,webelement).sendKeys(ReadExcel.readData(fname, sheet, row, column));
			}
			
			else {
				System.out.println("Provided text is not entered in the text field");
				
			}
			
		}
	}
		
		
	public static void selectDropdownValue(String filename,String webelement,String excelfname,String sheet,int row,int column) 
	{
		Select select=new Select(Xpath(filename,webelement));
		select.selectByValue(ReadExcel.readData(excelfname, sheet, row, column));	      		
	}
	public static void selectDropdownByVisibleText(String filename,String webelement,String excelfname,String sheet,int row,int column) 
	{
		Select select=new Select(Xpath(filename,webelement));
		select.selectByVisibleText(ReadExcel.readData(excelfname, sheet, row, column));	      		
	}
	public static void selectDropdownByIndex(String filename,String webelement,int indexNumber) 
	{
		Select select=new Select(Xpath(filename,webelement));
		select.selectByIndex(indexNumber);      		
	}
	public static void clickWebelement(String filename,String webelement)
	{
		Xpath(filename,webelement).click();	
	}
	public static void clearWebelement(String filename,String webelement)
	{
		Xpath(filename,webelement).clear();
	}
	public static void doubleClickWebelement(String filename,String webelement)
	{
		Actions action=new Actions(driver);
		action.moveToElement(Xpath(filename,webelement)).doubleClick().build().perform();	
	}
	public static void setProperty(String filename, String Browser, String browserPath)
	{		
	 System.setProperty("webdriver."+Browser+".driver",Paths(filename,browserPath)); 	 
	}
	public static void openChrome()
	{		
	 driver=new ChromeDriver();	
	}
	public static void openFirefox()
	{		
	 driver=new FirefoxDriver();	
	}
	public static void openInternetExploer()
	{		
	 driver=new InternetExplorerDriver();	
	}
	public static void maximizeWindow()
	{
		 driver.manage().window().maximize();		
	} 
	public static void implicitlywait(int time)
	{
		driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);	
	}	 		
	public static void movetoElement(String filename,String webelement)
	{		 
		Actions action=new Actions(driver);	
		action.moveToElement(Xpath(filename,webelement)).build().perform();	
	}
	public static void scrollToElement(String filename,String webelement)
	{			
	  JavascriptExecutor js = (JavascriptExecutor) driver;
	  WebElement Element=Xpath(filename, webelement);
	  js.executeScript("arguments[0].scrollIntoView();",Element);
	 }
	
	public static void clickKeyboardValue(String filename,String webelement,Keys value) 
	{
		Xpath(filename,webelement).sendKeys(value);		
	}
	public static void handlePopup()
	{	
        Alert alert = driver.switchTo().alert();
        alert.accept();	
	}
	
	public static void explicitWait(int time,String filename,String webelement)
	{
		new WebDriverWait(driver, time).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ReadConfig.ReadFile(filename, webelement))));	
	}
	public static void explicitWaitForClickable(int time,String filename,String weblement)
	{
		new WebDriverWait(driver, time).until(ExpectedConditions.elementToBeClickable(By.xpath(ReadConfig.ReadFile(filename, weblement))));	
	}

	public static void passValue(String filename,String webelement,CharSequence[] value)
	{		        
        Xpath(filename, webelement).sendKeys(value);
	}
	
	//Method is to Handle Multiple Windows
	public static void handleMultipleWindow(String filename,String webelement)
	{	
		driver.getWindowHandle();
		clickWebelement(filename, webelement);
	}
	public static void ClickByActionClass(String filename,String webelement,String fname,String sheet,int row,int column)
	{				
		WebElement complexelement=Xpath(filename, webelement);		
		Actions act=new Actions(driver);
		act.moveToElement(complexelement).build().perform();
		driver.findElement(By.linkText(ReadExcel.readData(fname, sheet, row, column))).click();	
	}
	public static void ClickWebelementByActionClass(String filename,String webelement) {
		WebElement complexelement=Xpath(filename, webelement);
		Actions act=new Actions(driver);
		//act.moveToElement(complexelement).build().perform();
		act.click(complexelement).build().perform();
	}
	
	public static void Close() {
	
	driver.close();
}
public static void SwitchToFrame(String FileName, String xpath ) {
	
	WebElement frm0 = Xpath(FileName, xpath);
	driver.switchTo().frame(frm0);
}
public static void Enter(int KeyEventVKKey)  {
	
	Robot Keys;
	try {
		Keys = new Robot();
		Keys.keyPress(KeyEventVKKey);
	} catch (AWTException e) {
		System.out.println("Keyword value was not entered");
	}
}
	 public static void FileDownloaded(String downloadPath, String fileName) {
		  File dir = new File(downloadPath);
		  File[] dirContents = dir.listFiles();

		      if (dirContents.equals(fileName)) {
		    	  
		    	  System.out.println("Verified that file is downloaded");
		          //return true;
		      }
		      else {
		    	  System.out.println("Verified that file is not downloaded");
		      }
		          }
	public static void SwitchTab(String filename, String Xpath) {
		
		String parentWindowHandle = driver.getWindowHandle();
		System.out.println("parentWindowHandle"+parentWindowHandle);
		clickWebelement(filename, Xpath);
		threadWait(2500);
		System.out.println(driver.getTitle());
		for(String childTab:driver.getWindowHandles()) {
		 driver.switchTo().window(childTab);
		}
		System.out.println(driver.getTitle());
	}
public static void Clear(String Xpath, String FileName) {
	
	driver.findElement(By.xpath(ReadConfig.ReadFile(Xpath, FileName))).clear();
}
	public static void threadWait(int time)
	{
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
	}

public static void enterLogs(String message)
{
	Logger logger= Logger.getLogger("BaseClass");
    PropertyConfigurator.configure("log4j.properties");   
     logger.info(message);
}	
public static void Navigate(String Filename,String ProvidedURL)
{
	driver.navigate().to(ProvidedURL);
}	

public static void captureScreen(WebDriver driver, String tname) 
{
TakesScreenshot ts = (TakesScreenshot) driver;
File source = ts.getScreenshotAs(OutputType.FILE);
File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
try {
	FileUtils.copyFile(source, target);
} catch (IOException e) {
	e.printStackTrace();
}
System.out.println("Screenshot is taken");
}

public static void validationWithWebelement(String filename,String webelement) 
{
	String element=Xpath(filename, webelement).getText();
	if( element!= null)
	{
		test.log(LogStatus.PASS, "Validated Successfully for the element = "+ element);
		System.out.println("Validated Successfully for the element-->   "+element);
	}
	else
	{				
		//captureScreen(driver,test); 	
		System.out.println("Does not find Referenced Object");			 
	}
}

public static void Validation(String Text) {
if(driver.getPageSource().contains(Text))
{
    System.out.println("Validation is passed:" + Text);
}

else
{
	System.out.println("Validation is Failed:" + Text);
}
}


public static void UploadFile(String Filename,String Path) throws AWTException {
	
	StringSelection s = new StringSelection(ReadConfig.ReadFile(Filename, Path));
    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(s, null);
    Robot robot = new Robot();
    threadWait(2500);
    robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
    threadWait(2500);
    robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);
    threadWait(2500);
    robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
    threadWait(2500);
    robot.keyPress(java.awt.event.KeyEvent.VK_V);
    threadWait(2500);
    robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
    threadWait(2500);
    robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
}
public static void SwitchToChild() {
	for(String childTab:driver.getWindowHandles()) {
		 driver.switchTo().window(childTab);
		}
		System.out.println(driver.getTitle());
}

	public static void DeleteFile() {
		
		  File file = new File("C:\\Users\\Admin\\Downloads\\Canvassing List.xlsx"); 
		   if(file.delete())
		   System.out.println("file deleted");
	}


}