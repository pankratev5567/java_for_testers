package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.DoubleSummaryStatistics;
import java.util.Properties;

public class ApplicationManager {

    protected WebDriver driver;
    private LoginHelper session;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;
    private Properties properties;
    private JdbcHelper jdbc;
    private HibernateHelper hbm;
    public LoginHelper session() {
        if (session == null) {
            session = new LoginHelper(this);
        }
        return session ;
    }
    public GroupHelper groupHelper(){
        if (groupHelper == null){
            groupHelper = new GroupHelper(this);
        }
        return groupHelper;
    }
    public ContactHelper contactHelper(){
        if (contactHelper == null){
            contactHelper = new ContactHelper(this);
        }
        return contactHelper;
    }
    public JdbcHelper jdbc(){
        if (jdbc == null){
            jdbc = new JdbcHelper(this);
        }
        return jdbc;
    }
    public HibernateHelper hbm(){
        if (hbm == null){
            hbm = new HibernateHelper(this);
        }
        return hbm;
    }


    public void init(String browser, Properties properties) {
        this.properties = properties;
        if (driver == null) {
            if ("firefox".equals(browser)){
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            }
            else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseURL"));
            driver.manage().window().setSize(new Dimension(1936, 1048));
            session().login(properties.getProperty("web.userName"), properties.getProperty("web.password"));
        }
    }
    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public WebDriver getDriver() {
        return driver;
    }

    public DoubleSummaryStatistics groups() {
        return null;
    }

}