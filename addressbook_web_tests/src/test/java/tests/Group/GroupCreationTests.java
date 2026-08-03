package tests.Group;

import ru.stqa.common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
//        for (var name :List.of("","group name")){
//            for (var header:List.of("","group header")){
//                for (var footer:List.of("","group footer")){
//                    result.add(new GroupData()
//                            .withName(name)
//                            .withHeader(header)
//                            .withFooter(footer));
//                }
//            }
//        }
        var json = "";
        try (var reader = new FileReader("groups.json");
             var breader = new BufferedReader(reader)
        ) {
            var line =  breader.readLine();
            while (line!= null){
                json=json+line;
                line=breader.readLine();
            }
        }
//        var json = Files.readString(Paths.get("groups.json"));
        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(json,  new TypeReference<List<GroupData>>(){});
        result.addAll(value);
        return result;
    }

    public static List<GroupData> negativeGroupProvider() {
        var result = new ArrayList<GroupData>(List.of(
                new GroupData("", "group name ' ","group header", "group footer")));
        return result;
    }

//    @Test
//    public void canCreateGroup() {
//        app.groupHelper().createGroup(new GroupData("group name", "group header", "group footer"));
//    }


//    @ParameterizedTest
//    @ValueSource(strings = {"group name","group name'"})
//    public void canCreateGroupWithEmptyName(String name) {
//        int groupCount = app.groupHelper().getCount();
//        app.groupHelper().createGroup(new GroupData(name, "group header", "group footer"));
//        int newGroupCount = app.groupHelper().getCount();
//        Assertions.assertEquals(groupCount+1,newGroupCount);
//    }

//    @Test
//    public void canCreateGroupWithNameOnly() {
//        var emptyGroup =new GroupData();
//        var groupWithName = emptyGroup.withName("some name");
//        app.groupHelper().createGroup(groupWithName);
//    }

//    @Test
//    public void canCreateGroupWithHeaderOnly() {
//        app.groupHelper().createGroup(new GroupData().withHeader("some header"));
//    }
//
//    @Test
//    public void canCreateGroupWithFooterOnly() {
//        app.groupHelper().createGroup(new GroupData().withFooter("some footer"));
//    }

    public static Stream<GroupData> singleRandomGroup() throws IOException{
        Supplier<GroupData> randomGroup = () -> new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10));
        return Stream.generate(randomGroup).limit(3);
    }

    @ParameterizedTest
    @MethodSource("singleRandomGroup")
    public void canCreateSingleGroups(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groupHelper().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newGroups.sort(compareById);
        var maxId =newGroups.get(newGroups.size()-1).id();

        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups,expectedList);

        // var mewUiGroups = app.groupHelper().getList();

    }


    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        var oldGroups = app.hbm().getGroupList();
        app.groupHelper().createGroup(group);
        var newGroups = app.hbm().getGroupList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size()-1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups,expectedList);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData name) {
        var oldGroups = app.hbm().getGroupList();
        app.groupHelper().createGroup(name);
        var newGroups = app.hbm().getGroupList();
        Assertions.assertEquals(newGroups,oldGroups);
    }

}