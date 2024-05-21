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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BookwarehouseTestClass extends BookwarehousePage{
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
            //Click function book warehouse
            chromeDriver.findElement(By.xpath(BUTTON_FUNCTION_WAREHOUSE)).click();
            sleep(200);
            chromeDriver.findElement(By.xpath(BUTTON_FUNCTION_BOOK)).click();
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
    public void AddBookSuccessful(){
        System.out.println("1. Click Button Add Book");
        sleep(1000);
        chromeDriver.findElement(By.xpath(BUTTON_ADD_BOOK)).click();

        System.out.println("2. Enter Book Information Valid");
        sleep(100);
        chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_NAME)).sendKeys("The Old Man and the Sea");
        chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_AUTHOR)).sendKeys("Ernest Hemingway");
        chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_PRICE)).sendKeys("300000");
        chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_QUANTITY)).sendKeys("80");

        System.out.println("3. Click Button Save");
        chromeDriver.findElement(By.xpath(BUTTON_SAVE_BOOK)).click();
        sleep(100);

        System.out.println("4. Check display and Verify screen displays a success message");
        sleep(100);
        WebElement MessageSuccess = chromeDriver.findElement(By.xpath(MESSAGE_SUCCES_ADDBOOK));
        String actual = gettext(MessageSuccess);
        Assert.assertTrue(MessageSuccess.isDisplayed());
        System.out.println(actual);
    }

    @Test (priority = 5)
    public void AddFailBookNameExits(){
        System.out.println("1. Click Button Add Book");
        sleep(1000);
        chromeDriver.findElement(By.xpath(BUTTON_ADD_BOOK)).click();

        System.out.println("2. Enter Book Information Exits");
        sleep(100);
        chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_NAME)).sendKeys("The Lord of the Rings");
        chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_AUTHOR)).sendKeys("J.R.R. Tolkien");
        chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_PRICE)).sendKeys("400000");
        chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_QUANTITY)).sendKeys("100");

        System.out.println("3. Click Button Save");
        chromeDriver.findElement(By.xpath(BUTTON_SAVE_BOOK)).click();
        sleep(100);

        System.out.println("4. Check display and Verify screen displays a error message");
        sleep(100);
        WebElement MessageSuccess = chromeDriver.findElement(By.xpath(MESSAGE_SUCCES_ADDBOOK));
        sleep(100);
        String actual = gettext(MessageSuccess);
        Assert.assertEquals(actual, "Tên sách đã tồn tại");
    }

    @Test(priority = 5)
    public void AddFailBookInvalid(){
        System.out.println("1. Click Button Add Book");
        sleep(1000);
        chromeDriver.findElement(By.xpath(BUTTON_ADD_BOOK)).click();

        System.out.println("2. Enter Book Information Invalid");
        sleep(100);
        WebElement txtBookname = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_NAME));
        txtBookname.sendKeys("The Lord of the Rings@");
        WebElement txtAuthorname = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_AUTHOR));
        txtAuthorname.sendKeys("J.R.R. Tolkien@");
        WebElement txtPrice = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_PRICE));
        txtPrice.sendKeys("400000@");
        WebElement txtQuantity = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_QUANTITY));
        txtQuantity.sendKeys("100@");

        System.out.println("3. Check button Save");
        WebElement SaveButton = chromeDriver.findElement(By.xpath(BUTTON_SAVE_BOOK));
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
            String currentClassCusname = txtBookname.getAttribute("class");
            boolean isErrorClassCusname = currentClassCusname.contains("invalid");
            softAssert.assertTrue(isErrorClassCusname, ("Textbox with ID "+ txtBookname.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassTel = txtAuthorname.getAttribute("class");
            boolean isErrorClassTel = currentClassTel.contains("invalid");
            softAssert.assertTrue(isErrorClassTel, ("Textbox with ID "+ txtAuthorname.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassAddress = txtPrice.getAttribute("class");
            boolean isErrorClassAddress = currentClassAddress.contains("invalid");
            softAssert.assertTrue(isErrorClassAddress, ("Textbox with ID "+ txtPrice.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassBirthday = txtQuantity.getAttribute("class");
            boolean isErrorClassBirthday = currentClassBirthday.contains("invalid");
            softAssert.assertTrue(isErrorClassBirthday, ("Textbox with ID "+ txtQuantity.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void AddFailBookBlank(){
        System.out.println("1. Click Button Add Book");
        sleep(1000);
        chromeDriver.findElement(By.xpath(BUTTON_ADD_BOOK)).click();

        System.out.println("2. Click on the fields and leave them blank");
        sleep(100);
        WebElement txtBookname = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_NAME));
        txtBookname.click();
        WebElement txtAuthorname = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_AUTHOR));
        txtAuthorname.click();
        WebElement txtPrice = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_PRICE));
        txtPrice.click();
        WebElement txtQuantity = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_QUANTITY));
        txtQuantity.click();

        System.out.println("3. Check button Save");
        WebElement SaveButton = chromeDriver.findElement(By.xpath(BUTTON_SAVE_BOOK));
        Actions actions = new Actions(chromeDriver);
        actions.click(SaveButton).perform();
        sleep(100);
        if (SaveButton.isEnabled()) {
            System.out.println("Button Save is clickable");
        } else {
            System.out.println("Button Save is not clickable");
        }

        System.out.println("4. Check display and Verify screen displays a error message");
        WebElement divName = chromeDriver.findElement(By.xpath(DIV_NAME));
        WebElement divAuthor = chromeDriver.findElement(By.xpath(DIV_AUTHOR));
        WebElement divPrice = chromeDriver.findElement(By.xpath(DIV_PRICE));
        WebElement divQuantity = chromeDriver.findElement(By.xpath(DIV_QUANTITY));
        SoftAssert softAssert = new SoftAssert();
        try {
            String currentClassCusname = divName.getAttribute("class");
            boolean isErrorClassCusname = currentClassCusname.contains("text-danger ng-star-inserted");
            softAssert.assertTrue(isErrorClassCusname, ("Textbox with ID "+ txtBookname.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassCusname = divAuthor.getAttribute("class");
            boolean isErrorClassCusname = currentClassCusname.contains("text-danger ng-star-inserted");
            softAssert.assertTrue(isErrorClassCusname, ("Textbox with ID "+ txtAuthorname.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassCusname = divPrice.getAttribute("class");
            boolean isErrorClassCusname = currentClassCusname.contains("text-danger ng-star-inserted");
            softAssert.assertTrue(isErrorClassCusname, ("Textbox with ID "+ txtPrice.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            String currentClassCusname = divQuantity.getAttribute("class");
            boolean isErrorClassCusname = currentClassCusname.contains("text-danger ng-star-inserted");
            softAssert.assertTrue(isErrorClassCusname, ("Textbox with ID "+ txtQuantity.getAttribute("ng-reflect-name") + " No error message is displayed"));
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void CheckDeleteCustomerSuccess(){
        System.out.println("1. Click Icon Delete");
        chromeDriver.findElement(By.xpath(ICON_DELETE_BOOK)).click();
        sleep(500);

        System.out.println("2. Check the screen displays the authentication message");
        String GiveMessageConfirm = chromeDriver.findElement(By.xpath(AUTHENTICATION_MESSAGE)).getText();
        System.out.println(GiveMessageConfirm);
        Assert.assertNotNull(GiveMessageConfirm);
        sleep(100);

        System.out.println("3. Click button Yes");
        chromeDriver.findElement(By.xpath(BUTTON_YES)).click();
        sleep(500);

        System.out.println("4. Verify Delete Book Successful");
        String GiveMessageSuccess = chromeDriver.findElement(By.xpath(SUCCESS_MESSAGE_DELBOOK)).getText();
        System.out.println(GiveMessageSuccess);
        Assert.assertFalse(GiveMessageSuccess.isEmpty());
    }

    @Test(priority = 5)
    public void UpdateBookSuccess(){
        System.out.println("1. Click Book Name");
        sleep(200);
        chromeDriver.findElement(By.xpath(BOOKNAME_UPDATE)).click();
        sleep(500);

        System.out.println("2. Click Icon Edit");
        chromeDriver.findElement(By.xpath(ICON_EDIT_BOOK)).click();

        System.out.println("3. Enter Text Update");
        WebElement txtBookname = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_NAME));
        txtBookname.clear();
        txtBookname.sendKeys("The Lord of the Rings");
        WebElement txtAuthorname = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_AUTHOR));
        txtAuthorname.clear();
        txtAuthorname.sendKeys("J.R.R. Tolkien");
        WebElement txtPrice = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_PRICE));
        txtPrice.clear();
        txtPrice.sendKeys("400000");
        WebElement txtQuantity = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_QUANTITY));
        txtQuantity.clear();
        txtQuantity.sendKeys("100");

        System.out.println("4. Click Button Save");
        chromeDriver.findElement(By.xpath(BUTTON_SAVE_BOOK)).click();
        System.out.println("5. Check display and Verify for notifications and changes");
        sleep(100);
        WebElement MessageSuccess = chromeDriver.findElement(By.xpath(MESSAGE_UPDATE_SUCCESS));
        Assert.assertTrue(MessageSuccess.isDisplayed());
        sleep(400);
        String actual = gettext(MessageSuccess);
        System.out.println(actual);

        sleep(100);
        chromeDriver.findElement(By.xpath("//button[@class='btn btn-secondary']")).click();

        SoftAssert softAssert = new SoftAssert();
        WebElement actualBookName = chromeDriver.findElement(By.xpath("//tbody//td[1]"));
        String gettxtBookName = gettext(actualBookName);
        WebElement actualAuthorName = chromeDriver.findElement(By.xpath("//tbody//td[2]"));
        String gettxtAuthorName = gettext(actualAuthorName);
        WebElement actualQuantity = chromeDriver.findElement(By.xpath("//tbody//td[3]"));
        String gettxtQuantity = gettext(actualQuantity);
        WebElement actualPrice = chromeDriver.findElement(By.xpath("//tbody//td[4]"));
        String gettxtPrice = gettext(actualPrice);

        try {
            softAssert.assertEquals(gettxtBookName, "The Lord of the Rings");
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            softAssert.assertEquals(gettxtAuthorName, "J.R.R. Tolkien");
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            softAssert.assertEquals(gettxtQuantity, "100");
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        try {
            softAssert.assertFalse(gettxtPrice.isEmpty());
        } catch (AssertionError e) {
            Assert.fail(e.getMessage());
        }
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void UpdateBookByEnterBookInfInvalid(){
        System.out.println("1. Click Book Name");
        chromeDriver.findElement(By.xpath(BOOKNAME_UPDATE)).click();
        sleep(500);

        System.out.println("2. Click Icon Edit");
        chromeDriver.findElement(By.xpath(ICON_EDIT_BOOK)).click();

        System.out.println("3. Enter Text Update");
        WebElement txtBookname = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_NAME));
        txtBookname.clear();
        txtBookname.sendKeys("@");
        WebElement txtAuthorname = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_AUTHOR));
        txtAuthorname.clear();
        txtAuthorname.sendKeys("@");
        WebElement txtPrice = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_PRICE));
        txtPrice.clear();
        txtPrice.sendKeys("@");
        WebElement txtQuantity = chromeDriver.findElement(By.xpath(TEXTBOX_BOOK_QUANTITY));
        txtQuantity.clear();
        txtQuantity.sendKeys("@");

        System.out.println("4. Verify save fail and screen display error message");
        WebElement SaveButton = chromeDriver.findElement(By.xpath(BUTTON_SAVE_BOOK));
        SaveButton.click();
        if (SaveButton.isEnabled()) {
            System.out.println("Button Save is clickable");
        } else {
            System.out.println("Button Save is not clickable");
        }
        sleep(100);
        WebElement MsgError = chromeDriver.findElement(By.xpath(MESSAGE_UPDATE_SUCCESS));
        sleep(100);
        String MsgActual = gettext(MsgError);
        System.out.println(MsgActual);
        Assert.assertFalse(MsgError.isDisplayed(),"No error message is displayed");
    }

    @Test(priority = 2)
    public void SearchForBookByFullName(){
        System.out.println("1. Click on the search bar");
        WebElement SearchBookBar = chromeDriver.findElement(By.xpath(SEARCH_BOOK_BAR));
        sleep(100);
        SearchBookBar.click();

        System.out.println("2. Enter the name of the book you want to search for");
        SearchBookBar.sendKeys("The Lord of the Rings");
        sleep(100);

        System.out.println("3. Verify search results display");
        WebElement SearchActual = chromeDriver.findElement(By.xpath(SEARCH_BOOK_ACTUAL));
        sleep(500);
        String Actual = gettext(SearchActual);
        Assert.assertEquals(Actual,"The Lord of the Rings");
    }

    @Test(priority = 2)
    public void SearchForBookByLetter(){
        System.out.println("1. Click on the search bar");
        WebElement SearchBookBar = chromeDriver.findElement(By.xpath(SEARCH_BOOK_BAR));
        sleep(100);
        SearchBookBar.click();

        System.out.println("2. Enter search bar the letter T");
        SearchBookBar.sendKeys("T");
        sleep(100);

        System.out.println("3. Verify search results display");
        int columnNameIndex = 1;

        //First letter value to search
        String searchLetter = "T";

        //Get all rows in the table
        List<WebElement> rows = chromeDriver.findElements(By.xpath(ALL_ROW_BOOK));

        //Variable to evaluate the final result
        boolean allNamesContainLetter = true;

        //Browse through each row in the table
        for (WebElement row : rows) {
            //Get information from the cell in the book name column
            sleep(100);
            WebElement nameCell = row.findElement(By.xpath(BOOK_NAME_COLUMN + columnNameIndex + "]"));
            String bookName = nameCell.getText();
            System.out.println("Book name: " + bookName);

            //Check if the letter T of the book name matches
            if (!bookName.toUpperCase().contains(searchLetter.toUpperCase())) {
                allNamesContainLetter = false; //If the letter T is not found, mark failure
                break; //Stop checking as soon as an inappropriate name is found
            }
        }
        //Check the final result
        if (allNamesContainLetter) {
            System.out.println("All book contain the '" + searchLetter + "' entered letter");
            Assert.assertTrue(true); //Pass when all customer names contain the letter L
        } else {
            System.out.println("There is a book name that does not contain the '" + searchLetter + "' entered letter");
            Assert.fail("There is a book name that does not contain the '" + searchLetter + "' entered letter"); //Fail when the name does not match
        }
    }

    @Test(priority = 2)
    public void SearchForBookByBookNameNotExist() {
        System.out.println("1. Click on the search bar");
        WebElement SearchBookBar = chromeDriver.findElement(By.xpath(SEARCH_BOOK_BAR));
        sleep(100);
        SearchBookBar.click();

        System.out.println("2. Enter search bar the book name not exist");
        SearchBookBar.sendKeys("The Little Prince");
        sleep(100);
        String txtDataSearch = "The Little Prince";

        System.out.println("3. Verify search results display");
        // Xác định vị trí của bảng
        WebElement table = chromeDriver.findElement(By.xpath(TABLE_BOOK));

        // Lấy tất cả các hàng trong bảng
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        // Biến kiểm tra giá trị tồn tại
        boolean valueExists = false;

        // Duyệt qua từng hàng để kiểm tra giá trị
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(txtDataSearch)) {
                    valueExists = true;
                    break;
                }
            }
            if (valueExists) {
                break;
            }
        }

        // Kiểm tra kết quả cuối cùng
        if (valueExists) {
            System.out.println("Valuable '" + txtDataSearch + "' in table");
            Assert.fail("Valuable '" + txtDataSearch + "' in table"); // Fail nếu giá trị tồn tại
        } else {
            System.out.println("Not valuable '" + txtDataSearch + "' in table");
            Assert.assertTrue(true); // Pass nếu giá trị không tồn tại
        }
    }

    @Test(priority = 2)
    public void SearchForBookByBookNameInvalid() {
        System.out.println("1. Click on the search bar");
        WebElement SearchBookBar = chromeDriver.findElement(By.xpath(SEARCH_BOOK_BAR));
        sleep(100);
        SearchBookBar.click();

        System.out.println("2. Enter search bar the book name invalid");
        SearchBookBar.sendKeys("@@@");
        sleep(100);
        String txtDataSearch = "@@@";

        System.out.println("3. Verify search results display");
        // Xác định vị trí của bảng
        WebElement table = chromeDriver.findElement(By.xpath("//table"));

        // Lấy tất cả các hàng trong bảng
        List<WebElement> rows = table.findElements(By.tagName("tr"));

        // Biến kiểm tra giá trị tồn tại
        boolean valueExists = false;

        // Duyệt qua từng hàng để kiểm tra giá trị
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().equals(txtDataSearch)) {
                    valueExists = true;
                    break;
                }
            }
            if (valueExists) {
                break;
            }
        }

        // Kiểm tra kết quả cuối cùng
        if (valueExists) {
            System.out.println("Valuable '" + txtDataSearch + "' in table");
            Assert.fail("Valuable '" + txtDataSearch + "' in table"); // Fail nếu giá trị tồn tại
        } else {
            System.out.println("Not valuable in table");
            Assert.assertTrue(true); // Pass nếu giá trị không tồn tại
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
