package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.mantis.tests.IssueCreationTests;

import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;
    private String browser;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSession;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private UiHelper uiHelper;
    private JamesApiHelper jamesApiHelper;
    private RestApiHelper rest;
    private SoapApiHelper soapApiHelper;
    private boolean loggedIn = false; // флаг, чтобы логин выполнялся только один раз

    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;
    }

    public WebDriver driver(){
        if (driver == null) {
            if ("firefox".equals(browser)) {
                driver = new FirefoxDriver();
            } else if ("chrome".equals(browser)) {
                driver = new ChromeDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %s", browser));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseURL"));
            driver.manage().window().setSize(new Dimension(1936, 1048));
            if (!loggedIn) {
                performLogin();
                loggedIn = true;
            }
        }
        return driver;
    }

    private void performLogin() {
        String user = properties.getProperty("web.adminUser", "administrator");
        String pass = properties.getProperty("web.adminPassword", "secret");
        driver.findElement(By.name("user")).sendKeys(user);
        driver.findElement(By.name("pass")).sendKeys(pass);
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public SessionHelper session(){
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpSession == null) {
            httpSession = new HttpSessionHelper(this);
        }
        return httpSession;
    }

    public JamesCliHelper jamesCli() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public UiHelper uiHelper() {
        if (uiHelper == null) {
            uiHelper = new UiHelper(this);
        }
        return uiHelper;
    }

    public JamesApiHelper jamesApiHelper() {
        if (jamesApiHelper == null) {
            jamesApiHelper = new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }

    public RestApiHelper rest() {
        if (rest == null) {
            rest = new RestApiHelper(this);
        }
        return rest;
    }

    public SoapApiHelper soap() {
        if (soapApiHelper == null) {
            soapApiHelper = new SoapApiHelper(this);
        }
        return soapApiHelper;
    }

    public String property(String name) {
        return properties.getProperty(name);
    }

    public String getProperties(String name) {
        return properties.getProperty(name);
    }
}