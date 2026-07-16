package tests.Group;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup()  {
        if(app.groupHelper().getCount() == 0) {
            app.groupHelper().createGroup(new GroupData("group name", "header", "footer"));
        }
        int groupCountStart = app.groupHelper().getCount();
        app.groupHelper().removeGroup();
        int groupCountEnd = app.groupHelper().getCount();
        Assertions.assertEquals(groupCountStart-1,groupCountEnd);
    }
    @Test
    void canRemoveAllGroupAtOnce(){
        if(app.groupHelper().getCount() == 0){
            app.groupHelper().createGroup(new GroupData("group name", "header", "footer"));
        }
        app.groupHelper().removeAllGroups();

        Assertions.assertEquals(0,app.groupHelper().getCount());
    }
}