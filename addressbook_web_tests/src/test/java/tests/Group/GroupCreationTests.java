package tests.Group;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        int groupCount = app.groupHelper().getCount();
        app.groupHelper().createGroup(new GroupData("name", "group header", "group footer"));
        int newGroupCount = app.groupHelper().getCount();
        Assertions.assertEquals(groupCount+1,newGroupCount);
    }


    @Test
    public void canCreateGroupWithEmptyName() {
        app.groupHelper().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        var emptyGroup =new GroupData();
        var groupWithName = emptyGroup.withName("some name");
        app.groupHelper().createGroup(groupWithName);
    }

    @Test
    public void canCreateGroupWithHeaderOnly() {
        app.groupHelper().createGroup(new GroupData().withHeader("some header"));
    }

    @Test
    public void canCreateGroupWithFooterOnly() {
        app.groupHelper().createGroup(new GroupData().withFooter("some footer"));
    }

}