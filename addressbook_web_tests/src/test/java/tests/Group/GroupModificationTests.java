package tests.Group;

import model.GroupData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup(){
        if(!app.groupHelper().isGroupPresent()){
            app.groupHelper().createGroup(new GroupData("group name", "header", "footer"));
        }
        app.groupHelper().modifyGroup(new GroupData().withName("mod name"));
    }
}