package interfaces;

public class SalesreportPage {
    //SCREEN REPORT
    public static final String BUTTON_FUNCTION_REPORT="//c-sidebar-nav-group[1]";
    public static final String BUTTON_FUNCTION_SALES="//a[@ng-reflect-router-link='/bao-cao/bao-cao-doanh-thu']";
    public static final String BUTTON_CHOOSE_DATE="//button[@class='btn btn-primary']";
    public static final String TEXTBOX_START_DATE="//input[@ng-reflect-name='fromDate']";
    public static final String TEXTBOX_END_DATE="//input[@ng-reflect-name='toDate']";
    public static final String BUTTON_FILTER="//c-modal//button[@class='btn btn-primary']";
    public static final String BUTTON_DASHBOARD="//a[@ng-reflect-router-link='/dashboard']";
    public static final String REVENUE_ACTUAL="//table//tbody//td[4]";
    public static final String REVENUE_EXPECTED="//body[1]/app-root[1]/app-dashboard[1]/div[1]/div[1]/c-container[1]/ng-component[1]/app-widgets-dropdown[1]/c-row[1]/c-col[3]/c-widget-stat-a[1]/c-card-body[1]/div[1]/div[1]";
}

