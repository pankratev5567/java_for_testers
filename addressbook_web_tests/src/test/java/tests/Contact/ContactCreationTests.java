package tests.Contact;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider(){
        var result = new ArrayList<ContactData>();
        for (var Firstname :List.of("","Майкл")) {
                for (var Lastname : List.of("", "Джексон")) {
                    result.add(new ContactData().withNames(Firstname,Lastname));
                }
            }
        for (int i=0;i<5;i++) {
            result.add(new ContactData().withFIO(randomString(i*10),randomString(i*10),randomString(i*10)));
        }
        return result;
    }

    @Test
    public void canCreateContactFIO() {
        var emptyContact = new ContactData()
                .withFirstname("Майкл")
                .withLastname("Джексон")
                .withPhoto("src/test/resources/images/avatar.png");
        app.contactHelper().createContact(emptyContact);
    }

    @Test
    public void canCreateFull() {
        app.contactHelper().createContact(new ContactData("","Майкл", "Джозеф", "Джексон", "Michael","", "King of Pop", "Sony Music",
                "Neverland Ranch, 5225 Figueroa Mountain Road, Los Olivos, CA 93441", "USA", "555-1234", "work", "michael@jackson.com",
                "mj@neverland.com", "info@michaeljackson.com", "home"));
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContactFIO(ContactData contact){
        var oldContact = app.contactHelper().getList();
        app.contactHelper().createContact(contact);
        var newContact = app.contactHelper().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        var expectedList = new ArrayList<>(oldContact);
        expectedList.add(contact.withId(newContact.get(newContact.size()-1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact, expectedList);
    }
}