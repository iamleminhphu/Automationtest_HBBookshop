package interfaces;

public class CustomerPage {
    //SCREEN CUSTOMER
    public static final String BUTTON_FUNCTION_CUSTOMER ="//a[@ng-reflect-router-link='/khach-hang']";
    //ADD CUSTOMER
    public static final String BUTTON_ADD_CUSTOMER ="//a//button[@class='btn btn-primary']";
    public static final String TEXTBOX_CUS_NAME ="//input[@ng-reflect-name='name']";
    public static final String TEXTBOX_CUS_TEL ="//input[@ng-reflect-name='tel']";
    public static final String TEXTBOX_CUS_ADDRESS ="//input[@ng-reflect-name='address']";
    public static final String TEXTBOX_CUS_BIRTHDAY ="//input[@ng-reflect-name='birthday']";
    public static final String BUTTON_CREATE_NEW_SUCCESS ="//button[@ng-reflect-disabled='false']";
    public static final String MESSAGE_SUCCESS_ADDCUS ="//p[@class='notifier__notification-message ng-star-inserted']";
    public static final String MESSAGE_ALREADYEXIST_ADDCUS ="//notifier-notification[@class='notifier__notification notifier__notification--warning notifier__notification--material']";
    public static final String MESSAGE_ERROR_ADDCUS ="//div[@class='ng-star-inserted']";
    public static final String BUTTON_CREATE_NEW_FAIL ="//button[@class='btn btn-primary disabled']";
    //DELETE CUSTOMER
    public static final String ICON_DEL_CUSTOMER ="//tbody/tr[1]/td[6]//*[name()='svg']//*[name()='path' and contains(@d,'M135.2 17.')]";
    public static final String AUTHENTICATION_MESSAGE ="//c-modal-body";
    public static final String BUTTON_YES ="//c-modal-footer//button[@class='btn btn-primary']";
    public static final String SUCCESS_MESSAGE_DELCUS ="//notifier-container//notifier-notification[@ng-reflect-notification='[object Object]']";
    //SEARCH CUSTOMER
    public static final String SEARCH_CUS_BAR="//input[@class='form-control']";
    public static final String SEARCH_CUS_ACTUAL="//tbody/tr[1]/td[1]";
    public static final String ALL_NAME_CUSTOMER="//table//tbody/tr/td[1]";
}