package tests.Contact;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTest extends TestBase {

    @Test
    public void canModifyContact(){
        if (!app.contactHelper().isContactPresent()){
            var emptyContact = new ContactData();
            var contactFIO = emptyContact.withFIO("Майкл", "Джозеф", "Джексон");
            app.contactHelper().createContact(contactFIO);
        }
        var oldContact = app.contactHelper().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        var testData = new ContactData().withNames("Майкл","Джексон");
        app.contactHelper().modifContact(oldContact.get(index),testData);
        var newContact = app.contactHelper().getList();
        var expectedList = new ArrayList<>(oldContact);
        expectedList.set(index,testData.withId(oldContact.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact, expectedList);
    }

}