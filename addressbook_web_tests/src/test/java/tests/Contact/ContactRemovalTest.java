package tests.Contact;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;


public class ContactRemovalTest extends TestBase {

    @Test
    public void CanRemoveAllContacts() {
        if (!app.contactHelper().isContactPresent()){
            var emptyContact = new ContactData();
            var contactFIO = emptyContact.withFIO("Майкл", "Джозеф", "Джексон");
            app.contactHelper().createContact(contactFIO);
        }
        app.contactHelper().removeAllContacts();
    }
    @Test
    public void canRemoveContact(){
        if (!app.contactHelper().isContactPresent()){
            var emptyContact = new ContactData();
            var contactFIO = emptyContact.withFIO("Майкл", "Джозеф", "Джексон");
            app.contactHelper().createContact(contactFIO);
        }
        var oldContact = app.contactHelper().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        app.contactHelper().removalContact(oldContact.get(index));
        var newContact = app.contactHelper().getList();
        var expectedList = new ArrayList<>(oldContact);
        expectedList.remove(index);
        Assertions.assertEquals(newContact,expectedList);
    }
}
