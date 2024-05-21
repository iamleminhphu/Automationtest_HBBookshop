package basstest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import interfaces.*;
import org.testng.asserts.SoftAssert;
import java.util.List;
import java.util.NoSuchElementException;

public class StaffTestClass {
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
            chromeDriver.findElement(By.xpath(StaffPage.BUTTON_FUNCTION_STAFF)).click();
        }
    }

    @Test(priority = 1)
    public void AddStaffSuccessful(){
        System.out.println("1. Click Button Add Staff");
        sleep(500);
        chromeDriver.findElement(By.xpath(StaffPage.BUTTON_ADD_STAFF)).click();

        System.out.println("2. Enter Staff Information Valid");
        sleep(100);
        chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_NAME)).sendKeys("Le Minh Phu");
        chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_TEL)).sendKeys("1234567891");
        chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_BIRTHDAY)).sendKeys("25/08/2002");
        chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_EMAIL)).sendKeys("leminhphudue@gmail.com");
        WebElement dropdownElement = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_ROLE));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(1);

        System.out.println("3. Click Button Save");
        chromeDriver.findElement(By.xpath(StaffPage.BUTTON_SAVE_STAFF_SUCCESS)).click();
        sleep(100);

        System.out.println("4. Check display and Verify screen displays a success message");
        try {
            // Tìm element
            WebElement element = chromeDriver.findElement(By.xpath(StaffPage.MESSAGE_SUCCESS_ADDSTAFF));
            // Nếu tìm thấy element, in thông báo
            System.out.println("The screen displays a success message: " + element.getText());
        } catch (Exception e) {
            // Nếu không tìm thấy element, in thông báo tương ứng
            Assert.fail("The screen does not display the success message");
        }
    }

    @Test (priority = 5)
    public void AddStaffNameExits(){
        System.out.println("1. Click Button Add Staff");
        sleep(500);
        chromeDriver.findElement(By.xpath(StaffPage.BUTTON_ADD_STAFF)).click();

        System.out.println("2. Enter Staff Information Valid");
        sleep(100);
        chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_NAME)).sendKeys("Le Minh Phu");
        chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_TEL)).sendKeys("0372653551");
        chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_BIRTHDAY)).sendKeys("25/08/2002");
        chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_EMAIL)).sendKeys("leminhphudue@gmail.com");
        WebElement dropdownElement = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_ROLE));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByIndex(1);

        System.out.println("3. Click Button Save");
        WebElement SaveButton =  chromeDriver.findElement(By.xpath(StaffPage.BUTTON_SAVE_STAFF_SUCCESS));
        SaveButton.click();
        sleep(100);

        System.out.println("4. Check display and Verify screen displays a success message");
        WebElement MessageSuccess = chromeDriver.findElement(By.xpath(StaffPage.MESSAGE_ALREADYEXIST_ADDSTAFF));
        Assert.assertTrue(MessageSuccess.isDisplayed());
        sleep(100);
        System.out.println(MessageSuccess);
    }

    @Test(priority = 5)
    public void AddFailStaffInvalid(){
        System.out.println("1. Click Button Add Staff");
        sleep(1000);
        chromeDriver.findElement(By.xpath(StaffPage.BUTTON_ADD_STAFF)).click();

        System.out.println("2. Enter Staff Information Invalid");
        sleep(100);
        WebElement txtStaffname = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_NAME));
        txtStaffname.sendKeys("@");
        WebElement txtTel = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_TEL));
        txtTel.sendKeys("@");
        WebElement txtBirthday = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_BIRTHDAY));
        txtBirthday.sendKeys("@");
        WebElement txtEmail = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_EMAIL));
        txtEmail.sendKeys("@");

        System.out.println("3. Check button Save");
        WebElement SaveButton = chromeDriver.findElement(By.xpath(StaffPage.BUTTON_SAVE_STAFF_FAIL));
        Actions actions = new Actions(chromeDriver);
        actions.click(SaveButton).perform();
        sleep(100);
        if (SaveButton.isEnabled()) {
            System.out.println("Button Save is clickable");
        } else {
            System.out.println("Button Save is not clickable");
        }

        System.out.println("4. Check display and Verify screen displays a error message");
        SoftAssert softAssert = new SoftAssert();
        try {
            String currentClassCusname = txtStaffname.getAttribute("class");
            boolean isErrorClassCusname = currentClassCusname.contains("invalid");
            softAssert.assertTrue(isErrorClassCusname, ("Textbox with ID "+ txtStaffname.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassTel = txtTel.getAttribute("class");
            boolean isErrorClassTel = currentClassTel.contains("invalid");
            softAssert.assertTrue(isErrorClassTel, ("Textbox with ID "+ txtTel.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassAddress = txtBirthday.getAttribute("class");
            boolean isErrorClassAddress = currentClassAddress.contains("invalid");
            softAssert.assertTrue(isErrorClassAddress, ("Textbox with ID "+ txtBirthday.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassBirthday = txtEmail.getAttribute("class");
            boolean isErrorClassBirthday = currentClassBirthday.contains("invalid");
            softAssert.assertTrue(isErrorClassBirthday, ("Textbox with ID "+ txtEmail.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void AddFailStaffBlank(){
        System.out.println("1. Click Button Add Staff");
        sleep(1000);
        chromeDriver.findElement(By.xpath(StaffPage.BUTTON_ADD_STAFF)).click();

        System.out.println("2. Enter Staff Information Invalid");
        sleep(100);
        WebElement txtStaffname = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_NAME));
        txtStaffname.click();
        WebElement txtTel = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_TEL));
        txtTel.click();
        WebElement txtBirthday = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_BIRTHDAY));
        txtBirthday.click();
        WebElement txtEmail = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_EMAIL));
        txtEmail.click();

        System.out.println("3. Check button Save");
        WebElement SaveButton = chromeDriver.findElement(By.xpath(StaffPage.BUTTON_SAVE_STAFF_FAIL));
        Actions actions = new Actions(chromeDriver);
        actions.click(SaveButton).perform();
        sleep(100);
        if (SaveButton.isEnabled()) {
            System.out.println("Button Save is clickable");
        } else {
            System.out.println("Button Save is not clickable");
        }

        System.out.println("4. Check display and Verify screen displays a error message");
        SoftAssert softAssert = new SoftAssert();
        try {
            String currentClassCusname = txtStaffname.getAttribute("class");
            boolean isErrorClassCusname = currentClassCusname.contains("invalid");
            softAssert.assertTrue(isErrorClassCusname, ("Textbox with ID "+ txtStaffname.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassTel = txtTel.getAttribute("class");
            boolean isErrorClassTel = currentClassTel.contains("invalid");
            softAssert.assertTrue(isErrorClassTel, ("Textbox with ID "+ txtTel.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassAddress = txtBirthday.getAttribute("class");
            boolean isErrorClassAddress = currentClassAddress.contains("invalid");
            softAssert.assertTrue(isErrorClassAddress, ("Textbox with ID "+ txtBirthday.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassBirthday = txtEmail.getAttribute("class");
            boolean isErrorClassBirthday = currentClassBirthday.contains("invalid");
            softAssert.assertTrue(isErrorClassBirthday, ("Textbox with ID "+ txtEmail.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void CheckDeleteStaffSuccess(){
        System.out.println("1. Click Icon Delete");
        chromeDriver.findElement(By.xpath(StaffPage.ICON_DEL_STAFF)).click();
        sleep(500);

        System.out.println("2. Check the screen displays the authentication message");
        String GiveMessageConfirm = chromeDriver.findElement(By.xpath(StaffPage.AUTHENTICATION_MESSAGE)).getText();
        System.out.println(GiveMessageConfirm);
        Assert.assertNotNull(GiveMessageConfirm);
        sleep(100);

        System.out.println("3. Click button Yes");
        chromeDriver.findElement(By.xpath(StaffPage.BUTTON_YES)).click();
        sleep(500);

        System.out.println("4. Verify Delete Staff Successful");
        String GiveMessageSuccess = chromeDriver.findElement(By.xpath(StaffPage.SUCCESS_MESSAGE_DELSTAFF)).getText();
        System.out.println(GiveMessageSuccess);
        Assert.assertFalse(GiveMessageSuccess.isEmpty());
    }

    @Test(priority = 2)
    public void UpdateStaffSuccess(){
        System.out.println("1. Click Staff Name");
        chromeDriver.findElement(By.xpath(StaffPage.STAFF_NAME_COLUMN)).click();
        sleep(500);

        System.out.println("2. Click Icon Edit");
        chromeDriver.findElement(By.xpath(StaffPage.ICON_DEL_STAFF)).click();

        System.out.println("3. Clear Text and Enter Text Update");
        WebElement txtName = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_NAME));
        txtName.clear();
        txtName.sendKeys("Le Minh Phu");
        WebElement txtTel = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_TEL));
        txtTel.clear();
        txtTel.sendKeys("0372653551");
        WebElement txtBirthday = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_BIRTHDAY));
        txtBirthday.clear();
        txtBirthday.sendKeys("25/08/2002");
        WebElement txtEmail = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_EMAIL));
        txtEmail.clear();
        txtEmail.sendKeys("leminhphu2k2@gmail.com");

        System.out.println("4. Click Button Save");
        chromeDriver.findElement(By.xpath(StaffPage.BUTTON_SAVE_STAFF_SUCCESS)).click();
        System.out.println("5. Check display and Verify screen displays a success message");
        WebElement MessageSuccess = chromeDriver.findElement(By.xpath(StaffPage.MESSAGE_SUCCESS_ADDSTAFF));
        Assert.assertTrue(MessageSuccess.isDisplayed());
        sleep(400);
        String actual = gettext(MessageSuccess);
        System.out.println(actual);
    }

    @Test(priority = 5)
    public void UpdateStaffFail(){
        System.out.println("1. Click Staff Name");
        chromeDriver.findElement(By.xpath(StaffPage.STAFF_NAME_COLUMN)).click();
        sleep(500);

        System.out.println("2. Click Icon Edit");
        chromeDriver.findElement(By.xpath(CustomerPage.BUTTON_CREATE_NEW_FAIL)).click();

        System.out.println("3. Clear Text and Enter Text Invalid Update");
        WebElement txtName = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_NAME));
        txtName.clear();
        txtName.sendKeys("@");
        WebElement txtTel = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_TEL));
        txtTel.clear();
        txtTel.sendKeys("@");
        WebElement txtBirthday = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_BIRTHDAY));
        txtBirthday.clear();
        txtBirthday.sendKeys("@");
        WebElement txtEmail = chromeDriver.findElement(By.xpath(StaffPage.TEXTBOX_STAFF_EMAIL));
        txtEmail.clear();
        txtEmail.sendKeys("@");

        WebElement SaveButton = chromeDriver.findElement(By.xpath(StaffPage.BUTTON_SAVE_STAFF_FAIL));
        SaveButton.click();
        if (SaveButton.isEnabled()) {
            System.out.println("Button Save is clickable");
        } else {
            System.out.println("Button Save is not clickable");
        }

        WebElement MsgError = chromeDriver.findElement(By.xpath(StaffPage.MESSAGE_SUCCESS_ADDSTAFF));
        sleep(100);
        String MsgActual = gettext(MsgError);
        System.out.println(MsgActual);
        Assert.assertFalse(MsgError.isDisplayed(),"No error message is displayed");
    }

    @Test(priority = 3)
    public void SearchForStaffByFullName(){
        WebElement SearchBookBar = chromeDriver.findElement(By.xpath(StaffPage.SEARCH_STAFF_BAR));
        sleep(100);
        SearchBookBar.click();
        SearchBookBar.sendKeys("Le Minh Phu");
        sleep(100);

        WebElement SearchActual = chromeDriver.findElement(By.xpath(StaffPage.SEARCH_STAFF_ACTUAL));
        sleep(500);
        String Actual = gettext(SearchActual);
        Assert.assertEquals(Actual,"Le Minh Phu");
    }

    @Test(priority = 3)
    public void SearchForStaffByLetter(){
        WebElement SearchBookBar = chromeDriver.findElement(By.xpath(StaffPage.SEARCH_STAFF_BAR));
        sleep(100);
        SearchBookBar.click();
        SearchBookBar.sendKeys("L");
        sleep(100);

        int columnNameIndex = 1;

        //First letter value to search
        String searchFirstLetter = "L";

        //Get all rows in the table
        List<WebElement> rows = chromeDriver.findElements(By.xpath(StaffPage.ALL_ROW_STAFF));

        //Variable to evaluate the final result
        boolean allNamesContainFirstLetter = true;

        //Browse through each row in the table
        for (WebElement row : rows) {
            //Get information from the cell in the book name column
            sleep(100);
            WebElement nameCell = row.findElement(By.xpath(StaffPage.STAFF_NAME_COLUMN + columnNameIndex + "]"));
            String customerName = nameCell.getText();
            System.out.println("Staff name: " + customerName);

            //Check if the letter T of the book name matches
            if (!customerName.toUpperCase().contains(searchFirstLetter.toUpperCase())) {
                allNamesContainFirstLetter = false; //If the letter T is not found, mark failure
                break; //Stop checking as soon as an inappropriate name is found
            }
        }
        //Check the final result
        if (allNamesContainFirstLetter) {
            System.out.println("All staff contain the '" + searchFirstLetter + "' entered letter");
            Assert.assertTrue(true); //Pass when all book names contain the letter T
        } else {
            System.out.println("There is a staff name that does not contain the '" + searchFirstLetter + "' entered letter");
            Assert.fail("There is a staff name that does not contain the '" + searchFirstLetter + "' entered letter"); //Fail when the name does not match
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
