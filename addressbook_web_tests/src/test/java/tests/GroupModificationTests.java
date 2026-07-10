package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup(){
        if(!app.groupHelper().isGroupPresent()){
            app.groupHelper().createGroup(new GroupData("group name", "header", "footer"));
        }
        app.groupHelper().modifyGroup(new GroupData().withName("mod name"));
    }
}