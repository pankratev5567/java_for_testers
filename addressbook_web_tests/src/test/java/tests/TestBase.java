package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;

import java.util.Random;

public class TestBase {

    protected static ApplicationManager app;
    @BeforeEach
    public void setUp() {
        if(app == null){
            app = new ApplicationManager();
        }
        app.init(System.getProperty("browser","firefox"));
    }
    public static String randomString(int n){
        var r = new Random();
        var result = "";
        for (int i =0;i<n;i++){
            result = result+(char)('a'+r.nextInt(26/*33*/));
        }
        return result;
    }
}
