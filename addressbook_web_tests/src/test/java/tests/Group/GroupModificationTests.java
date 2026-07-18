package tests.Group;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup(){
        if(app.groupHelper().getCount()==0){
            app.groupHelper().createGroup(new GroupData("", "group name", "header", "footer"));
        }
        var oldGroups = app.groupHelper().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new GroupData().withName("mod name");
        app.groupHelper().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.groupHelper().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index,testData.withId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups,expectedList);
    }
}