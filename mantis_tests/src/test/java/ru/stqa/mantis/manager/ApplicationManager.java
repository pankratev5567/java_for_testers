package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


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
    public void init(String browser, Properties properties) {
        this.browser = browser;
        this.properties = properties;
    }
    public WebDriver driver(){
        if (driver==null){
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
        }
        return driver;
    }
    public SessionHelper session(){
        if (sessionHelper==null){
            sessionHelper=new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpSession==null){
            httpSession=new HttpSessionHelper(this);
        }
        return httpSession;
    }
    public JamesCliHelper jamesCli() {
        if (jamesCliHelper==null){
            jamesCliHelper=new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }
    public MailHelper mail() {
        if (mailHelper==null){
            mailHelper=new MailHelper(this);
        }
        return mailHelper;
    }
    public UiHelper uiHelper() {
        if (uiHelper==null){
            uiHelper=new UiHelper(this);
        }
        return uiHelper;
    }
    public JamesApiHelper jamesApiHelper() {
        if (jamesApiHelper==null){
            jamesApiHelper=new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }
    public String getProperties(String name) {
        return properties.getProperty(name);
    }
}
