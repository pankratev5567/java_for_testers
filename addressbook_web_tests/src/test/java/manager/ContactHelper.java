package manager;

import model.ContactData;
import org.openqa.selenium.By;

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
    private void setBirthday() {
        typeS(By.name("bday"),"24");
        typeS(By.name("bmonth"),"May");
        typeS(By.name("byear"),"2000");
    }
    public void removalContact(){
        selectContact();
        click(By.name("delete"));
        returnToHomePage();
    }
    private void selectContact() {
        click(By.name("selected[]"));
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
}