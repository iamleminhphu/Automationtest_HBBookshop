package basstest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;
import interfaces.*;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomerTestClass extends CustomerPage{
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
            chromeDriver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            //Click on the function to test
            chromeDriver.findElement(By.xpath(BUTTON_FUNCTION_CUSTOMER)).click();
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

    @Test(priority = 1)
    public void VerifyAddCustomersSuccessful(){
        System.out.println("1. Click Button Add Customer");
        sleep(100);
        chromeDriver.findElement(By.xpath(BUTTON_ADD_CUSTOMER)).click();

        System.out.println("2. Enter Customer Information Valid");
        chromeDriver.findElement(By.xpath(TEXTBOX_CUS_NAME)).sendKeys("Le Minh Phu");
        chromeDriver.findElement(By.xpath(TEXTBOX_CUS_TEL)).sendKeys("0372653551");
        chromeDriver.findElement(By.xpath(TEXTBOX_CUS_ADDRESS)).sendKeys("Da Nang");
        chromeDriver.findElement(By.xpath(TEXTBOX_CUS_BIRTHDAY)).sendKeys("25/08/2002");

        System.out.println("3. Click Button Create New");
        chromeDriver.findElement(By.xpath(BUTTON_CREATE_NEW_SUCCESS)).click();
        sleep(100);

        System.out.println("4. Check display and Verify screen displays a success message");
        sleep(100);
        WebElement MessageSuccess = chromeDriver.findElement(By.xpath(MESSAGE_SUCCESS_ADDCUS));
        String GetTextMsgSuccess = gettext(MessageSuccess);
        Assert.assertTrue(MessageSuccess.isDisplayed());
        System.out.println(GetTextMsgSuccess);
    }

    @Test (priority = 2)
    public void VerifyAddFailCustomerByPhonenumberExits(){
        System.out.println("1. Click Button Add Customer");
        sleep(500);
        chromeDriver.findElement(By.xpath(BUTTON_ADD_CUSTOMER)).click();

        System.out.println("2. Enter Customer_Tel already exist");
        chromeDriver.findElement(By.xpath(TEXTBOX_CUS_NAME)).sendKeys("Ho Thanh Tuan");
        chromeDriver.findElement(By.xpath(TEXTBOX_CUS_TEL)).sendKeys("0372653552");
        chromeDriver.findElement(By.xpath(TEXTBOX_CUS_ADDRESS)).sendKeys("Da Nang");
        chromeDriver.findElement(By.xpath(TEXTBOX_CUS_BIRTHDAY)).sendKeys("24/02/2002");

        System.out.println("3. Click Button Create New");
        chromeDriver.findElement(By.xpath(BUTTON_CREATE_NEW_SUCCESS)).click();
        sleep(100);

        System.out.println("4. Check display and Verify screen displays a error message");
        sleep(200);
        WebElement MessageErrorFail = chromeDriver.findElement(By.xpath(MESSAGE_ALREADYEXIST_ADDCUS));
        String actual = gettext(MessageErrorFail);
        Assert.assertTrue(MessageErrorFail.isDisplayed());
        sleep(100);
        System.out.println(actual);
    }

    @Test(priority = 5)
    public void VerifyAddFailCustomerByTextInvalid(){
        System.out.println("1. Click Button Add Customer");
        sleep(500);
        chromeDriver.findElement(By.xpath(BUTTON_ADD_CUSTOMER)).click();

        System.out.println("2. Enter Customer Information Invalid");
        WebElement txtCusname = chromeDriver.findElement(By.xpath(TEXTBOX_CUS_NAME));
        txtCusname.sendKeys("Ho Thanh Tuan@");
        WebElement txtTel = chromeDriver.findElement(By.xpath(TEXTBOX_CUS_TEL));
        txtTel.sendKeys("0372653552@");
        WebElement txtAddress = chromeDriver.findElement(By.xpath(TEXTBOX_CUS_ADDRESS));
        txtAddress.sendKeys("Da Nang@");
        WebElement txtBirthday = chromeDriver.findElement(By.xpath(TEXTBOX_CUS_BIRTHDAY));
        txtBirthday.sendKeys("24/02/2002@");

        System.out.println("3. Check button Create new");
        WebElement SaveButton = chromeDriver.findElement(By.xpath(BUTTON_CREATE_NEW_FAIL));
        Actions actions = new Actions(chromeDriver);
        actions.click(SaveButton).perform();
        sleep(100);
        if (SaveButton.isEnabled()) {
            System.out.println("Button Create New is clickable");
        } else {
            System.out.println("Button Create New is not clickable");
        }

        System.out.println("4. Check display and Verify screen displays a error message");
        SoftAssert softAssert = new SoftAssert();
        try {
            String currentClassCusname = txtCusname.getAttribute("class");
            boolean isErrorClassCusname = currentClassCusname.contains("invalid");
            softAssert.assertTrue(isErrorClassCusname, ("Textbox with ID "+ txtCusname.getAttribute("ng-reflect-name") + " No error message is displayed"));
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
            String currentClassAddress = txtAddress.getAttribute("class");
            boolean isErrorClassAddress = currentClassAddress.contains("invalid");
            softAssert.assertTrue(isErrorClassAddress, ("Textbox with ID "+ txtAddress.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassBirthday = txtBirthday.getAttribute("class");
            boolean isErrorClassBirthday = currentClassBirthday.contains("invalid");
            softAssert.assertTrue(isErrorClassBirthday, ("Textbox with ID "+ txtBirthday.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void VerifyAddFailCustomerByTextBlank(){
        System.out.println("1. Click Button Add Customer");
        sleep(500);
        chromeDriver.findElement(By.xpath(BUTTON_ADD_CUSTOMER)).click();

        System.out.println("2. Click on the fields and leave them blank");
        WebElement txtCusname = chromeDriver.findElement(By.xpath(TEXTBOX_CUS_NAME));
        txtCusname.click();
        WebElement txtTel = chromeDriver.findElement(By.xpath(TEXTBOX_CUS_TEL));
        txtTel.click();
        WebElement txtAddress = chromeDriver.findElement(By.xpath(TEXTBOX_CUS_ADDRESS));
        txtAddress.click();
        WebElement txtBirthday = chromeDriver.findElement(By.xpath(TEXTBOX_CUS_BIRTHDAY));
        txtBirthday.click();

        System.out.println("3. Check button Create new");
        WebElement SaveButton = chromeDriver.findElement(By.xpath(BUTTON_CREATE_NEW_FAIL));
        Actions actions = new Actions(chromeDriver);
        actions.click(SaveButton).perform();
        sleep(100);
        if (SaveButton.isEnabled()) {
            System.out.println("Button Create New is clickable");
        } else {
            System.out.println("Button Create New is not clickable");
        }

        System.out.println("4. Check display and Verify screen displays a error message");
        // Xác định các xpath của các textbox và thông báo lỗi tương ứng (ví dụ)
        String[] textBoxXpaths = {TEXTBOX_CUS_NAME, TEXTBOX_CUS_TEL, TEXTBOX_CUS_ADDRESS, TEXTBOX_CUS_BIRTHDAY};

        // Duyệt qua từng textbox để kiểm tra có thông báo lỗi không
        for (String textBoxXpath : textBoxXpaths) {
            WebElement textBox = chromeDriver.findElement(By.xpath(textBoxXpath));

            // Kiểm tra xem textbox có thông báo lỗi không
            List<WebElement> errorMessages = textBox.findElements(By.xpath(MESSAGE_ERROR_ADDCUS));
            boolean hasError = !errorMessages.isEmpty(); // Kiểm tra có tồn tại thông báo lỗi hay không

            // In ra thông báo tương ứng dựa trên việc có thông báo lỗi hay không
            if (hasError) {
                System.out.println("Textbox with ID " + textBox.getAttribute("ng-reflect-name") + " has error message");
            } else {
                System.out.println("Textbox with ID " + textBox.getAttribute("ng-reflect-name") + " has no error message");
            }
        }
    }

    @Test(priority = 4)
    public void CheckDeleteCustomerSuccess(){
        System.out.println("1. Click Icon Delete");
        chromeDriver.findElement(By.xpath(ICON_DEL_CUSTOMER)).click();
        sleep(500);

        System.out.println("2. Check the screen displays the authentication message");
        String GiveMessageConfirm = chromeDriver.findElement(By.xpath(AUTHENTICATION_MESSAGE)).getText();
        Assert.assertNotNull(GiveMessageConfirm);
        System.out.println(GiveMessageConfirm);
        sleep(100);

        System.out.println("3. Click button Yes");
        sleep(100);
        chromeDriver.findElement(By.xpath(BUTTON_YES)).click();
        sleep(100);

        System.out.println("4. Verify Delete Customer Successful");
        String GiveMessageSuccess = chromeDriver.findElement(By.xpath(SUCCESS_MESSAGE_DELCUS)).getText();
        Assert.assertFalse(GiveMessageSuccess.isEmpty());
        System.out.println(GiveMessageSuccess);
    }

    @Test(priority = 5)
    public void TestSearchForCustomerByFullName(){
        System.out.println("1. Click on the customer search bar");
        sleep(500);
        WebElement SearchCustomer = chromeDriver.findElement(By.xpath(SEARCH_CUS_BAR));
        SearchCustomer.click();

        System.out.println("2. Enter customer name Le Minh Phu");
        SearchCustomer.sendKeys("Le Minh Phu");

        System.out.println("3. Verify search success");
        //Get all rows in the table
        List<WebElement> rows = chromeDriver.findElements(By.xpath(ALL_NAME_CUSTOMER));
        try {
            // Nếu không có kết quả nào thì fail
            Assert.assertFalse(rows.isEmpty(), "No search results found.");

            // Kiểm tra từng dòng kết quả tìm kiếm
            boolean allResultsContainName = true;
            for (WebElement result : rows) {
                String resultText = result.getText();
                if (!resultText.contains("Le Minh Phu")) {
                    allResultsContainName = false;
                    System.out.println("Unexpected result found: " + resultText);
                }
            }
            // Assert rằng tất cả các kết quả đều chứa tên "Le Minh Phu"
            if (allResultsContainName) {
                System.out.println("All search results contain the name 'Le Minh Phu'");
                Assert.assertTrue(true, "All search results contain the name 'Le Minh Phu'");
            } else {
                Assert.fail("Some search results do not contain 'Le Minh Phu'");
            }
        } catch (AssertionError e) {
            System.out.println("ErrorMessage: " + e.getMessage());
            throw e;
        }
    }

    @Test(priority = 5)
    public void TestSearchForCustomerByLetter(){
        System.out.println("1. Click on the customer search bar");
        sleep(500);
        WebElement SearchCustomer = chromeDriver.findElement(By.xpath(SEARCH_CUS_BAR));
        SearchCustomer.click();

        System.out.println("2. Enter L");
        SearchCustomer.sendKeys("L");

        System.out.println("3. Verify search success");
        int columnNameIndex = 1;

        //First letter value to search
        String searchLetter = "L";

        //Get all rows in the table
        List<WebElement> rows = chromeDriver.findElements(By.xpath(ALL_CUSTOMER));

        //Variable to evaluate the final result
        boolean allNamesContainLetter = true;
        List<String> addlistCustomerNames = new ArrayList<>();

        // Browse through each row in the table
        for (WebElement row : rows) {
            // Get information from the cell in the customer name column
            sleep(100);
            WebElement nameCell = row.findElement(By.xpath(CUSTOMER_NAME_COLUMN + columnNameIndex + "]"));
            String customerName = nameCell.getText();
            System.out.println("Customer name: " + customerName);

            // Check if the letter L of the customer name matches
            if (!customerName.toUpperCase().contains(searchLetter.toUpperCase())) {
                allNamesContainLetter = false; // If the letter L is not found, mark failure
                addlistCustomerNames.add(customerName); // Add to list of list names
            }
        }

        // Check the final result
        if (allNamesContainLetter) {
            System.out.println("All customers contain the '" + searchLetter + "' entered letter");
            Assert.assertTrue(true); // Pass when all customer names contain the letter L
        } else {
            for (String getCustomerName : addlistCustomerNames) {
                System.out.println("There is a customer name '" + getCustomerName + "' that does not contain the '" + searchLetter + "' entered letter");
            }
            Assert.fail("Search results have customer names that do not contain the '" + searchLetter + "' entered letter"); // Fail when the name does not match
        }
    }

    @Test(priority = 5)
    public void TestSearchForCustomerByNameNotExist(){
        System.out.println("1. Click on the customer search bar");
        sleep(500);
        WebElement SearchCustomer = chromeDriver.findElement(By.xpath(SEARCH_CUS_BAR));
        SearchCustomer.click();

        System.out.println("2. Enter customer name does not exist");
        SearchCustomer.sendKeys("Mai Thanh Cong");

        System.out.println("3. Verify screen not display");
        try {
            WebElement SearchActual = chromeDriver.findElement(By.xpath(SEARCH_CUS_ACTUAL));
            Assert.assertFalse(SearchActual.isDisplayed());
        } catch (AssertionError e){
            System.out.println("ErrorMessage: " + e.getMessage());
            System.out.println("The expected is empty but the actual are still displayed");
            throw e;
        }
    }

    @Test(priority = 5)
    public void TestSearchForCustomerByNameInvalid(){
        System.out.println("1. Click on the customer search bar");
        sleep(500);
        WebElement SearchCustomer = chromeDriver.findElement(By.xpath(SEARCH_CUS_BAR));
        SearchCustomer.click();

        System.out.println("2. Enter customer name invalid");
        SearchCustomer.sendKeys("@@@");

        System.out.println("3. Verify screen not display");
        try {
            WebElement SearchActual = chromeDriver.findElement(By.xpath(SEARCH_CUS_ACTUAL));
            Assert.assertFalse(SearchActual.isDisplayed());
        } catch (AssertionError e){
            System.out.println("ErrorMessage: " + e.getMessage());
            System.out.println("The expected is empty but the actual are still displayed");
            throw e;
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