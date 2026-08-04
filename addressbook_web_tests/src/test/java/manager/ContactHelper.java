package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData conactD) {
        openContactPage();
        setContact(conactD);
        setBirthday();
        submitContactCreation();
        returnToHomePage();
    }

    public void addToGroupContact(ContactData s, GroupData group) {
        openHomePage();
        selectOnlyNonGroup();
        selectContact(s);
        selectAddGroup(group);
        addToGroup();
        openHomePage();
    }

    private void selectCheckbox() {
        click(By.xpath("//input[@type='checkbox' and @id!='MassCB']"));
    }

    public void create(ContactData conactD, GroupData group) {
        openContactPage();
        setContact(conactD);
        setBirthday();
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    public void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void a() {
        openHomePage();
        selectOnlyNonGroup();
    }

    public List<WebElement> checkboxes() {
        return manager.driver.findElements(By.xpath("//input[@type='checkbox' and @id!='MassCB']"));
    }

    public void selectOnlyNonGroup() {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue("[none]");
    }

    private void setBirthday() {
        typeS(By.name("bday"), "13");
        typeS(By.name("bmonth"), "April");
        typeS(By.name("byear"), "1999");
    }

    public void removalContact(ContactData conactD) {
        openHomePage();
        selectContact(conactD);
        click(By.name("delete"));
        returnToHomePage();
    }

    public void removeAllContacts() {
        openHomePage();
        selectAllContact();
        returnToHomePage();
    }

    public void modifContact(ContactData conactD, ContactData modif) {
        openHomePage();
        selectContactToEdit(conactD);
        fillContactForm(modif);
        submitContactUpdate();
        returnToHomePage();
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.Firstname());
        type(By.name("middlename"), contact.Middlename());
        type(By.name("lastname"), contact.Lastname());
    }

    private void submitContactUpdate() {
        click(By.name("update"));
    }

    private void selectContactToEdit(ContactData conactD) {
        click(By.cssSelector(String.format("a[href*='edit.php?id=%s']", conactD.id())));
    }

    private void selectContact(ContactData conactD) {
        click(By.cssSelector(String.format("input[value='%s']", conactD.id())));
    }

    private void selectContactStr(String conact) {
        click(By.cssSelector(String.format("input[value='%s']", Integer.parseInt(conact))));
    }

    private void selectAllContact() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
        click(By.name("delete"));
        ;
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
        type(By.name("firstname"), contact.Firstname());
        type(By.name("middlename"), contact.Middlename());
        type(By.name("lastname"), contact.Lastname());
        type(By.name("nickname"), contact.Nickname());
        // attach(By.name("photo"),contact.Photo());
        type(By.name("title"), contact.Title());
        type(By.name("company"), contact.Company());
        type(By.name("address"), contact.Address());
        type(By.name("home"), contact.home());
        type(By.name("mobile"), contact.mobile());
        type(By.name("work"), contact.work());
        type(By.name("email"), contact.EmailOne());
        type(By.name("email2"), contact.EmailTwo());
        type(By.name("email3"), contact.EmailThree());
        type(By.name("homepage"), contact.Homepage());
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    // Возврат на домашнюю страницу
    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    //Есть ли
    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    private void selectAddGroup(GroupData group) {
        click(By.cssSelector(String.format("select[name='to_group'] option[value='%s']", group.id())));
    }

    private void addToGroup() {
        click(By.name("add"));
    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var tr : trs) {
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            var lastName = tr.findElement(By.cssSelector("td:nth-child(2)")).getText();
            ;
            var firstName = tr.findElement(By.cssSelector("td:nth-child(3)")).getText();
            contacts.add(new ContactData().withId(id).withNames(firstName, lastName));
        }
        return contacts;
    }

    public List<String> getNonGroupList() {
        openHomePage();
        selectOnlyNonGroup();
        List<WebElement> elements = manager.driver.findElements(By.xpath("//input[@type='checkbox' and @id!='MassCB']"));
        return elements.stream()
                .map(el -> el.getAttribute("id"))
                .filter(id -> id != null && !id.isEmpty())
                .collect(Collectors.toList());
    }

    public void RemoveGroupFromContact(ContactData contact, GroupData group) {
        openHomePage();
        selectGroupFilter(group);
        selectContact(contact);
        removeFromGroup();
        openHomePage();
    }

    private void selectGroupFilter(GroupData group) {
        click(By.cssSelector(String.format("select[name='group'] option[value='%s']", group.id())));
    }

    private void removeFromGroup() {
        click(By.name("remove"));
    }

    public Object getPhones(ContactData contact) {
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String,String> getPhones() {
        var result = new HashMap<String,String>();
        List<WebElement> rows =  manager.driver.findElements(By.name("entry"));
        for (var row:rows){
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id,phones);
        }
        return result;
    }
}