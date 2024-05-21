package interfaces;

public class BookwarehousePage {
    //SCREEN BOOK
    public static final String BUTTON_FUNCTION_WAREHOUSE="//a[normalize-space()='Kho']";
    public static final String BUTTON_FUNCTION_BOOK="//a[@ng-reflect-router-link='/kho/sach']";
    //ADD BOOK
    public static final String BUTTON_ADD_BOOK="//a//button[@class='btn btn-primary']";
    public static final String TEXTBOX_BOOK_NAME="//input[@id='bookName']";
    public static final String TEXTBOX_BOOK_AUTHOR="//input[@id='authorName']";
    public static final String TEXTBOX_BOOK_PRICE="//input[@id='price']";
    public static final String TEXTBOX_BOOK_QUANTITY="//input[@id='quantity']";
    public static final String BUTTON_SAVE_BOOK="//button[@class='btn btn-primary']";
    public static final String MESSAGE_SUCCES_ADDBOOK="//p[@class='notifier__notification-message ng-star-inserted']";
    public static final String DIV_NAME="//div[@class='col-md-6']/*[self::div]";
    public static final String DIV_AUTHOR="//body//app-root//div[2]/*[self::div]";
    public static final String DIV_PRICE="//body//app-root//div[3]";
    public static final String DIV_QUANTITY="//body//app-root//div[4]/*[self::div]";
    //UPDATE BOOK
    public static final String BOOKNAME_UPDATE="//tr//td[@class='h6']";
    public static final String ICON_EDIT_BOOK="//label[@class='form-label']";
    public static final String MESSAGE_UPDATE_SUCCESS="//li[@class='notifier__container-list-item ng-star-inserted']";
    //DELETE BOOK
    public static final String ICON_DELETE_BOOK="//tbody[5]/tr[1]/td[5]//*[name()='svg']//*[name()='path' and contains(@d,'M135.2 17.')]";
    public static final String AUTHENTICATION_MESSAGE ="//c-modal-body";
    public static final String BUTTON_YES ="//c-modal-footer//button[@class='btn btn-primary']";
    public static final String SUCCESS_MESSAGE_DELBOOK ="//notifier-container//notifier-notification[@ng-reflect-notification='[object Object]']";
    //SEARCH BOOK
    public static final String SEARCH_BOOK_BAR ="//input[@id='inputPassword2']";
    public static final String SEARCH_BOOK_ACTUAL ="//tbody[1]/tr[1]/td[1]";
    public static final String ALL_ROW_BOOK="//table//tbody//tr";
    public static final String TABLE_BOOK="//table";
    public static final String BOOK_NAME_COLUMN="./td[";
}
