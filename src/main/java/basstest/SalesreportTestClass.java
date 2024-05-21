package basstest;

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import interfaces.*;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class SalesreportTestClass {
    ChromeDriver chromeDriver;


    @BeforeMethod
    public void Setup(){
        WebDriverManager.chromedriver().setup();
        if(chromeDriver == null) {
            chromeDriver = new ChromeDriver();
            chromeDriver.get("http://localhost:4200/#/login");
            chromeDriver.manage().window().maximize();
            chromeDriver.findElement(By.xpath(LoginPage.TEXTBOX_ACCOUNT)).sendKeys("0372653551");
            chromeDriver.findElement(By.xpath(LoginPage.TEXTBOX_PASSWORD)).sendKeys("abc123");
            chromeDriver.findElement(By.xpath(LoginPage.BUTTON_LOGIN)).click();
            sleep(1000);
            //Click function sales report
            chromeDriver.findElement(By.xpath(SalesreportPage.BUTTON_FUNCTION_REPORT)).click();
            sleep(100);
            chromeDriver.findElement(By.xpath(SalesreportPage.BUTTON_FUNCTION_SALES)).click();
        }
    }

    @Test(priority = 1)
    public void ViewRevenueSuccessful(){
        System.out.println("1. Click Button Choose Date");
        sleep(1000);
        chromeDriver.findElement(By.xpath(SalesreportPage.BUTTON_CHOOSE_DATE)).click();

        System.out.println("2. Enter Start Date and End Date");
        sleep(200);
        chromeDriver.findElement(By.xpath(SalesreportPage.TEXTBOX_START_DATE)).sendKeys("01/05/2024");
        sleep(200);
        chromeDriver.findElement(By.xpath(SalesreportPage.TEXTBOX_END_DATE)).sendKeys("31/05/2024");
        sleep(200);

        System.out.println("3. Click Button Filter");
        chromeDriver.findElement(By.xpath(SalesreportPage.BUTTON_FILTER)).click();
        sleep(1000);

        System.out.println("3. Verify the system displays the revenue report for the selected period");
        WebElement ActualRevenue = chromeDriver.findElement(By.xpath(SalesreportPage.REVENUE_ACTUAL));
        String GetActualRevenue = gettext(ActualRevenue);

        sleep(200);
        chromeDriver.findElement(By.xpath(SalesreportPage.BUTTON_DASHBOARD)).click();
        WebElement ExpectedRevenue = chromeDriver.findElement(By.xpath(SalesreportPage.REVENUE_EXPECTED));
        String GetExpectedRevenue = gettext(ExpectedRevenue);

        Assert.assertEquals(GetActualRevenue,GetExpectedRevenue);
        if (GetActualRevenue.equals(GetExpectedRevenue)) {
            System.out.println("Total monthly revenue: " + GetExpectedRevenue);
            System.out.println("Filter revenue by exact month: " + GetActualRevenue);
        } else {
            System.out.println("Total monthly revenue: " + GetExpectedRevenue);
            System.out.println("Filtering revenue by month is incorrect:" + GetActualRevenue);
        }
    }

    @Test (priority = 5)
    public void ViewRevenueFailInvalid() {
        System.out.println("1. Click Button Choose Date");
        sleep(1000);
        chromeDriver.findElement(By.xpath(SalesreportPage.BUTTON_CHOOSE_DATE)).click();

        System.out.println("2. Enter Start Date and End Date");
        sleep(200);
        chromeDriver.findElement(By.xpath(SalesreportPage.TEXTBOX_START_DATE)).sendKeys("@");
        sleep(200);
        chromeDriver.findElement(By.xpath(SalesreportPage.TEXTBOX_END_DATE)).sendKeys("@");
        sleep(200);

        System.out.println("3. Click Button Filter");
        WebElement ButtonFilter = chromeDriver.findElement(By.xpath(SalesreportPage.BUTTON_FILTER));
        ButtonFilter.click();
        sleep(100);

        try {
            Assert.assertFalse(ButtonFilter.isEnabled(), "Button Save is clickable");
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 5)
    public void ViewRevenueFailBlank(){
        System.out.println("1. Click Button Choose Date");
        sleep(1000);
        chromeDriver.findElement(By.xpath(SalesreportPage.BUTTON_CHOOSE_DATE)).click();

        System.out.println("2. Enter Start Date and End Date");
        sleep(200);
        chromeDriver.findElement(By.xpath(SalesreportPage.TEXTBOX_START_DATE)).click();
        sleep(200);
        chromeDriver.findElement(By.xpath(SalesreportPage.TEXTBOX_END_DATE)).click();
        sleep(200);

        System.out.println("3. Click Button Filter");
        WebElement ButtonFilter = chromeDriver.findElement(By.xpath(SalesreportPage.BUTTON_FILTER));
        ButtonFilter.click();
        sleep(100);

        try {
            Assert.assertFalse(ButtonFilter.isEnabled(), "Button Save is clickable");
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
    }

    @AfterMethod
    public void CleanUp(){
        if(chromeDriver != null) {
            System.out.println("Test execution completed");
            sleep(100);
            chromeDriver.quit();
            chromeDriver = null;
        }
    }

    public void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (Exception ex){
            System.out.printf(ex.getMessage());
        }
    }

    public String gettext(WebElement element){
        return element.getText();
    }
}

