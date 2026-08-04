package tests.Group;

import ru.stqa.common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;


public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup(){
        if(app.groupHelper().getCount()==0){
            app.groupHelper().createGroup(new GroupData("", "group name", "header", "footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new GroupData().withName(CommonFunctions.randomString(10));
        app.groupHelper().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index,testData.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newGroups),Set.copyOf(expectedList));
    }
}