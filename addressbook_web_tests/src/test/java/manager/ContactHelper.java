package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;


public class ContactHelper extends HelperBase{

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }
    public void createContact(ContactData conactD){
        openContactPage();
        setContact(conactD);
        setBirthday();
        submitContactCreation();
        returnToHomePage();
    }
    public void modifContact(ContactData conactD, ContactData modif){
        openHomePage();
        selectContactToEdit(conactD);
        fillContactForm(modif);
        submitContactUpdate();
        returnToHomePage();
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"),contact.Firstname());
        type(By.name("middlename"),contact.Middlename());
        type(By.name("lastname"),contact.Lastname());
    }

    private void submitContactUpdate() {
        click(By.name("update"));
    }

    private void selectContactToEdit(ContactData conactD) {
        click(By.cssSelector(String.format("a[href*='edit.php?id=%s']", conactD.id())));    }
    private void setBirthday() {
        typeS(By.name("bday"),"24");
        typeS(By.name("bmonth"),"May");
        typeS(By.name("byear"),"2000");
    }
    public void removalContact(ContactData conactD){
        openHomePage();
        selectContact(conactD);
        click(By.name("delete"));
        returnToHomePage();
    }
    public void removeAllContacts(){
        openHomePage();
        selectAllContact();
        returnToHomePage();
    }
    private void selectContact(ContactData conactD) {
        click(By.cssSelector(String.format("input[value='%s']", conactD.id())));    }

    private void selectAllContact() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
        click(By.name("delete"));;
    }
    private void openContactPage() {
        if (!manager.isElementPresent(By.linkText("add address book entry"))) {
            click(By.linkText("add new"));
        }
    }
    private void openHomePage() {
        if (!manager.isElementPresent(By.linkText("Select all"))) {
            click(By.linkText("home"));
        }
    }
    private void setContact(ContactData contact) {
        type(By.name("firstname"),contact.Firstname());
        type(By.name("middlename"), contact.Middlename());
        type(By.name("lastname"),contact.Lastname());
        type(By.name("nickname"),contact.Nickname());
        attach(By.name("photo"),contact.Photo());
        type(By.name("title"),contact.Title());
        type(By.name("company"),contact.Company());
        type(By.name("address"),contact.Address());
        type(By.name("home"),contact.Home());
        type(By.name("mobile"),contact.Mobile());
        type(By.name("work"),contact.Work());
        type(By.name("email"), contact.EmailOne());
        type(By.name("email2"),contact.EmailTwo());
        type(By.name("email3"),contact.EmailThree());
        type(By.name("homepage"),contact.Homepage());
    }
    private void submitContactCreation() {
        click(By.name("submit"));
    }
    private void returnToHomePage() {
        click(By.linkText("home page"));
    }
    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }
    public int getContactCount(){
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList(){
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : trs){
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            var lastName = tr.findElement(By.cssSelector("td:nth-child(2)")).getText();;
            var firstName  = tr.findElement(By.cssSelector("td:nth-child(3)")).getText();
            contacts.add(new ContactData().withId(id).withNames(firstName, lastName));
        }
        return contacts;
    }

}