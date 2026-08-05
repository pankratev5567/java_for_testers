package tests;

import ru.stqa.common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactinfoTests extends TestBase {

    @Test
    void testPhones(){
        if (!app.contactHelper().isContactPresent()){
            var emptyContact = new ContactData();
            var contactFIO = emptyContact
                    .withFIO("Майкл", "Джозеф", "Джексон")
                    .withhome(CommonFunctions.randomString(10))
                    .withmobile(CommonFunctions.randomString(10))
                    .withwork(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10))
                    .withEmailOne(CommonFunctions.randomString(10))
                    .withEmailTwo(CommonFunctions.randomString(10))
                    .withEmailThree(CommonFunctions.randomString(10));
            app.contactHelper().createContact(contactFIO);
        }
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact->
                Stream.of(contact.home(),contact.mobile(),contact.work())
                        .filter(s-> s!=null &&!"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));
        var phones = app.contactHelper().getPhones();
        Assertions.assertEquals(expected,phones);
    }
    @Test
    void testAddress() {
        if (!app.contactHelper().isContactPresent()) {
            var emptyContact = new ContactData();
            var contactFIO = emptyContact
                    .withFIO("Майкл", "Джозеф", "Джексон")
                    .withhome(CommonFunctions.randomString(10))
                    .withmobile(CommonFunctions.randomString(10))
                    .withwork(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10))
                    .withEmailOne(CommonFunctions.randomString(10))
                    .withEmailTwo(CommonFunctions.randomString(10))
                    .withEmailThree(CommonFunctions.randomString(10));
            app.contactHelper().createContact(contactFIO);
        }

        var contacts = app.hbm().getContactList();
        Random rnd = new Random();
        var number = rnd.nextInt(contacts.size()/*-1*/);
        var contact = contacts.get(number);
        var address = app.contactHelper().getAddress(contact);
        var expected = Stream.of(contact.Address())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, address);
    }

    @Test
    void testEmail() {
        if (!app.contactHelper().isContactPresent()) {
            var emptyContact = new ContactData();
            var contactFIO = emptyContact
                    .withFIO("Майкл", "Джозеф", "Джексон")
                    .withhome(CommonFunctions.randomString(10))
                    .withmobile(CommonFunctions.randomString(10))
                    .withwork(CommonFunctions.randomString(10))
                    .withAddress(CommonFunctions.randomString(10))
                    .withEmailOne(CommonFunctions.randomString(10))
                    .withEmailTwo(CommonFunctions.randomString(10))
                    .withEmailThree(CommonFunctions.randomString(10));
            app.contactHelper().createContact(contactFIO);
        }

        var contacts = app.hbm().getContactList();
        Random rnd = new Random();
        var number = rnd.nextInt(contacts.size()/*-1*/);
        var contact = contacts.get(number);
        var email = app.contactHelper().getEmails(contact);
        var expected = Stream.of(contact.EmailOne(), contact.EmailTwo(), contact.EmailThree())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, email);
    }
}
