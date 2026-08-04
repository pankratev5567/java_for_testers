package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactinfoTests extends TestBase {

    @Test
    void testPhones(){
        if (!app.contactHelper().isContactPresent()){
            var emptyContact = new ContactData();
            var contactFIO = emptyContact
                    .withFIO("Майкл", "Джозеф", "Джексон")
                    .withhome("123")
                    .withmobile("456")
                    .withwork("789");
            app.contactHelper().createContact(contactFIO);
        }
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var phones = app.contactHelper().getPhones(contact);
        var expected = Stream.of(contact.home(),contact.mobile(),contact.work())
                .filter(s-> s!=null &&!"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected,phones);
    }

}