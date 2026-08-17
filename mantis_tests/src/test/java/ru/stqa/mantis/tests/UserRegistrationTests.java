package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.stqa.mantis.common.CommonFunctions;
import java.time.Duration;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() throws InterruptedException {
        String user = CommonFunctions.randomString(9);
        String email = String.format("%s@localhost", CommonFunctions.randomString(7));
        String pass = app.getProperties("pass");

        app.jamesCli().addUser(email, pass);
        app.mail().drain(email, pass);
        app.uiHelper().startRegistration(user, email);
        var messages = app.mail().receive(email, pass, Duration.ofSeconds(30));
        Assertions.assertFalse(messages.isEmpty(), "Письмо не пришло");
        String url = app.mail().getUrl(messages);
        app.uiHelper().finishRegistration(url, user, pass);
        app.http().login(user, pass);
        Assertions.assertTrue(app.http().isLoggedIn(), "Не удалось войти после регистрации");
    }
    @Test
    void canRegisterUserApiAlternative () {
        var user = CommonFunctions.randomString(9);
        var email = (String.format("%s@localhost", CommonFunctions.randomString(7)));
        var pass = app.getProperties("pass");

        try {
            app.jamesApiHelper().addUser(email, pass);
            app.rest().userRegistration(user, email);

            var message = app.mail().receive(email, pass, Duration.ofSeconds(25));
            Assertions.assertFalse(message.isEmpty(), "Письмо не пришло");
            System.out.println(message);

            var url = app.mail().getUrl(message);
            System.out.println(url);

            app.uiHelper().finishRegistration(url, user, pass);

            app.http().login(user, pass);
            Assertions.assertTrue(app.http().isLoggedIn(), "Не удалось войти после регистрации");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
//    @Test
//    void canRegisterUser(){
//var email = String.format("%s@localhost", username);
//          Создать пользователя (адрес) на почтовом сервере (JamesHelper)
//          Открыть браузер и заполнить форму создания и отправляем (браузер)
//          ждём почту  (MailHelper)
//          извлекаем ссылку из письма
//          проходим по ссылке и завершаем регистрацию (браузер)
//          проверяем что пользователь может залогиниться (HttpSessionHelper)
