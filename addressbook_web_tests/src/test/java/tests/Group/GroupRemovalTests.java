package tests.Group;

import io.qameta.allure.Allure;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class GroupRemovalTests extends TestBase {

    @Test
    public void canRemoveGroup() {
        if (app.groupHelper().getCount() == 0) {
            app.groupHelper().createGroup(new GroupData("", "group name", "header", "footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groupHelper().removeGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    void canRemoveAllGroupAtOnce() {
        if (app.hbm().getGroupList().isEmpty()) {
            app.groupHelper().createGroup(new GroupData("", "group name", "header", "footer"));
        }
        app.groupHelper().removeAllGroups();
        var newGroups = app.hbm().getGroupList();
        Assertions.assertEquals(0, app.groupHelper().getCount());
    }

    @Test
    public void canRemoveGroupAllure() {
        Allure.step("Checking precondition", () -> {
            if (app.hbm().getGroupCount() == 0) {
                app.hbm().createGroup(new GroupData("", "group name", "header", "footer"));
            }
        });
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groupHelper().removeGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);

        Allure.step("Validation results", () -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }
}