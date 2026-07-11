package tests.Contact;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactFIO() {
        var emptyContact = new ContactData();
        var contactFIO = emptyContact.withFIO("Майкл", "Джозеф", "Джексон");
        app.contactHelper().createContact(contactFIO);
    }

    @Test
    public void canCreateFull() {
        app.contactHelper().createContact(new ContactData("Майкл", "Джозеф", "Джексон", "Michael", "King of Pop", "Sony Music",
                "Neverland Ranch, 5225 Figueroa Mountain Road, Los Olivos, CA 93441", "USA", "555-1234", "work", "michael@jackson.com",
                "mj@neverland.com", "info@michaeljackson.com", "home"));
    }
}