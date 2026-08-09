package ru.stqa.mantis.manager;


import okhttp3.OkHttpClient;

public class HttpSessionHelper extends HelperBase {

    OkHttpClient client;


    public HttpSessionHelper(ApplicationManager manager) {
        super(manager);
        client = new OkHttpClient();
    }

    public void login(String username, String password) {
    }

    public boolean isLoggedIn() {
        return false;
    }
}