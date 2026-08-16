package ru.stqa.mantis.manager;


import org.openqa.selenium.By;

    public class UiHelper extends HelperBase {

        public UiHelper(ApplicationManager manager) {
            super(manager);
        }

        public void startRegistration(String username, String email) {
            manager.driver().get(manager.getProperties("web.baseURL"));
            click(By.linkText("Signup for a new account"));
            type(By.name("username"), username);
            type(By.name("email"), email);
            click(By.xpath("//input[@value='Signup']"));
            click(By.xpath("//a[normalize-space(text()) = 'Proceed']"));
        }

        public void finishRegistration(String url, String realname, String password) {
            manager.driver().get(url);
            type(By.id("realname"), realname);
            type(By.id("password"), password);
            type(By.id("password-confirm"), password);
            click(By.cssSelector("button.btn-success"));
        }
    }

