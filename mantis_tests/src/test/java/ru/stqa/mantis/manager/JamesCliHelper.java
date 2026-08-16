package ru.stqa.mantis.manager;


import org.openqa.selenium.os.ExternalProcess;

import java.time.Duration;

public class JamesCliHelper extends HelperBase {
    public JamesCliHelper(ApplicationManager manager) {
        super(manager);
    }

    public void addUser(String email, String password) throws InterruptedException {
        String workingDir = manager.getProperties("james.workingDir");
        String classpath = workingDir + "/james-server-jpa-app.jar:" + workingDir + "/james-server-jpa-app.lib/*";

        ExternalProcess.builder()
                .command("java", "-cp", classpath,
                        "org.apache.james.cli.ServerCmd", "AddUser", email, password)
                .directory(workingDir)
                .copyOutputTo(System.err)
                .start()
                .waitFor(Duration.ofHours(1));
    }
}