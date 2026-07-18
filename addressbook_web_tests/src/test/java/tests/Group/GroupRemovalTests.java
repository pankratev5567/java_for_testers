package tests.Group;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup()  {
        if(app.groupHelper().getCount() == 0){
            app.groupHelper().createGroup(new GroupData("", "group name", "header", "footer"));
        }
        var oldGroups = app.groupHelper().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groupHelper().removeGroup(oldGroups.get(index));
        var newGroups = app.groupHelper().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups,expectedList);
    }

    @Test
    void canRemoveAllGroupAtOnce(){
        if(app.groupHelper().getCount() == 0){
            app.groupHelper().createGroup(new GroupData("", "group name", "header", "footer"));
        }
        app.groupHelper().removeAllGroups();

        Assertions.assertEquals(0,app.groupHelper().getCount());
    }
}