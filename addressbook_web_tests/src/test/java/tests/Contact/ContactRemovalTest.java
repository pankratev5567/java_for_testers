package tests.Contact;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactRemovalTest extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contactHelper().isContactPresent()) {
            var emptyContact = new ContactData();
            var contactFIO = emptyContact.withFIO("Майкл", "Джозеф", "Джексон");
            app.contactHelper().createContact(contactFIO);
        }
        app.contactHelper().removalContact();
        app.getDriver().switchTo().alert().accept();
    }
}
