package tests;

import manager.ApplicationManager;
import org.junit.jupiter.api.BeforeEach;
import java.io.File;
import org.junit.jupiter.api.AfterEach;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import java.util.Properties;
import java.util.Random;

public class TestBase {

    protected static ApplicationManager app;
    @BeforeEach
    public void setUp() throws IOException {
        if(app == null){
            var properties = new Properties();
            properties.load(new FileReader(System.getProperty("target", "local.properties")));
            app = new ApplicationManager();
            app.init(System.getProperty("browser", "firefox"), properties);
        }
    }

    public static String randomFile(String dir){
        var file = new File(dir).list();
        Random rnd = new Random();
        var index = rnd.nextInt(file.length);
        Paths.get(dir,file[index]).toString();
        return Paths.get(dir,file[index]).toString();
    }

    @AfterEach
    void checkDatabaseConsistency(){
        app.jdbc().checkConsistency();
    }
}
