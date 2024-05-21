package interfaces;

public class StaffPage {
    //SCREEN STAFF
    public static final String BUTTON_FUNCTION_STAFF ="//a[@ng-reflect-router-link='/nhan-vien']";
    //ADD STAFF
    public static final String BUTTON_ADD_STAFF ="//a//button[@class='btn btn-primary']";
    public static final String TEXTBOX_STAFF_NAME ="//input[@ng-reflect-name='name']";
    public static final String TEXTBOX_STAFF_TEL ="//input[@ng-reflect-name='tel']";
    public static final String TEXTBOX_STAFF_EMAIL ="//input[@ng-reflect-name='email']";
    public static final String TEXTBOX_STAFF_BIRTHDAY ="//input[@ng-reflect-name='birthday']";
    public static final String TEXTBOX_STAFF_ROLE ="//select[@ng-reflect-name='roleName']";
    public static final String BUTTON_SAVE_STAFF_SUCCESS="//button[@class='btn btn-primary']";
    public static final String BUTTON_SAVE_STAFF_FAIL="//button[@class='btn btn-primary disabled']";
    public static final String MESSAGE_SUCCESS_ADDSTAFF ="//p[@class='notifier__notification-message ng-star-inserted']";
    public static final String MESSAGE_ALREADYEXIST_ADDSTAFF ="//li//p[@class='notifier__notification-message ng-star-inserted']";
    //DELETE STAFF
    public static final String ICON_DEL_STAFF ="//tbody/tr[1]/td[6]//*[name()='svg']//*[name()='path' and contains(@d,'M135.2 17.')]";
    public static final String AUTHENTICATION_MESSAGE ="//c-modal-body";
    public static final String BUTTON_YES ="//c-modal-footer//button[@class='btn btn-primary']";
    public static final String SUCCESS_MESSAGE_DELSTAFF ="//notifier-container//notifier-notification[@ng-reflect-notification='[object Object]']";
    //SEARCH STAFF
    public static final String SEARCH_STAFF_BAR="//input[@class='form-control']";
    public static final String SEARCH_STAFF_ACTUAL="//tbody/tr[1]/td[1]";
    public static final String ALL_ROW_STAFF="//table//tbody//tr";
    public static final String STAFF_NAME_COLUMN="./td[";
}