package model;

public record ContactData(String id, String Firstname, String Middlename, String Lastname, String Nickname, String Title, String Company, String Address,
                         String Home, String Mobile, String Work, String EmailOne, String EmailTwo, String EmailThree, String Homepage) {
    public ContactData(){
        this("", "","","","","","",
                "","","","","","","","");
    }
    public ContactData withFIO(String Firstname, String Middlename, String Lastname){
        return new ContactData("", Firstname, Middlename, Lastname, this.Nickname, this.Title, this.Company, this.Address, this.Home, this.Mobile,
                this.Work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage);
    }
    public ContactData withId(String Id){
        return new ContactData(Id, Firstname, Middlename, Lastname, this.Nickname, this.Title, this.Company, this.Address, this.Home, this.Mobile,
                this.Work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage);
    }
    public ContactData withNames(String Firstname, String Lastname) {
        return new ContactData(this.id, Firstname, Middlename, Lastname, this.Nickname, this.Title, this.Company, this.Address, this.Home, this.Mobile,
                this.Work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage);
    }
}