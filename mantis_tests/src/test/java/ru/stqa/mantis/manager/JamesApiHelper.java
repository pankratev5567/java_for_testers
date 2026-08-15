package ru.stqa.mantis.manager;

import okhttp3.*;
import org.openqa.selenium.json.Json;

import java.io.IOException;
import java.net.CookieManager;

public class JamesApiHelper extends HelperBase{

    OkHttpClient client;
    public static final MediaType JSON = MediaType.get("application/json");

    public JamesApiHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient.Builder()
                .cookieJar(new JavaNetCookieJar(new CookieManager()))
                .build();
    }

    public void addUser(String email, String password) throws InterruptedException {
        var pass = String.format("{\"password\":\"%s\"}",password);
        RequestBody body = RequestBody
                .create(pass, JSON);
        Request request = new Request.Builder()
                .url(String.format("%s/users/%s",manager.getProperties("james.apiBaseUrl"),email))
                .put(body)
                .build();
        try (Response response2 = client.newCall(request).execute()) {
            if (!response2.isSuccessful()) throw new RuntimeException("Unexpected code " + response2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}