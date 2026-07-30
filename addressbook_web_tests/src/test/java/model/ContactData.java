package model;

public record ContactData(String id, String Firstname, String Middlename, String Lastname, String Nickname,
                          String Photo, String Title, String Company, String Address,
                          String Home, String Mobile, String Work, String EmailOne, String EmailTwo, String EmailThree, String Homepage) {

    public ContactData(){
        this("", "","","","", "", "","",
                "","","","","","","","");
    }

//    public ContactData withFull(String Firstname, String Middlename, String Lastname, String Nickname, String Title, String Company, String Address,
//                                String Home, String Mobile, String Work, String EmailOne, String EmailTwo, String EmailThree, String Homepage){
//        return new ContactData(Firstname, Middlename, Lastname, Nickname, Title, Company, Address,
//                Home, Mobile, Work, EmailOne, EmailTwo, EmailThree, Homepage);
//    }

    public ContactData withFIO(String Firstname, String Middlename, String Lastname){
        return new ContactData("", Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.Home, this.Mobile,
                this.Work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage);
    }

    public ContactData withId(String Id){
        return new ContactData(Id, Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.Home, this.Mobile,
                this.Work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage);
    }

    public ContactData withNames(String Firstname, String Lastname) {
        return new ContactData(this.id, Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.Home, this.Mobile,
                this.Work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage);
    }

    public ContactData withFirstname(String Firstname) {
        return new ContactData(this.id, Firstname, Middlename, this.Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.Home, this.Mobile,
                this.Work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage);
    }
    public ContactData withLastname( String Lastname) {
        return new ContactData(this.id, this.Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.Home, this.Mobile,
                this.Work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage);
    }

    public ContactData withPhoto(String photo){
        return new ContactData(this.id, this.Firstname, Middlename, Lastname, this.Nickname, photo, this.Title, this.Company, this.Address, this.Home, this.Mobile,
                this.Work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage);
    }

    public ContactData withIdName(String Id, String Firstname, String Lastname){
        return new ContactData(Id, Firstname, this.Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.Home, this.Mobile,
                this.Work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage);
    }
}