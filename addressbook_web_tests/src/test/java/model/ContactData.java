package model;

public record ContactData(String id,
                          String Firstname,
                          String Middlename,
                          String Lastname,
                          String Nickname,
                          String Photo,
                          String Title,
                          String Company,
                          String Address,
                          String home,
                          String mobile,
                          String work,
                          String EmailOne,
                          String EmailTwo,
                          String EmailThree,
                          String Homepage,
                          String secondary) {

    public ContactData(){
        this("", "","","","", "", "","",
                "","","","","","","","", "");
    }

    public ContactData withFIO(String Firstname, String Middlename, String Lastname){
        return new ContactData(this.id, Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.home, this.mobile,
                this.work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage, this.secondary);
    }

    public ContactData withId(String Id){
        return new ContactData(Id, Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.home, this.mobile,
                this.work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage,this.secondary);
    }

    public ContactData withNames(String Firstname, String Lastname) {
        return new ContactData(this.id, Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.home, this.mobile,
                this.work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage,this.secondary);
    }

    public ContactData withFirstname(String Firstname) {
        return new ContactData(this.id, Firstname, Middlename, this.Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.home, this.mobile,
                this.work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage,this.secondary);
    }
    public ContactData withLastname( String Lastname) {
        return new ContactData(this.id, this.Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.home, this.mobile,
                this.work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage,this.secondary);
    }

    public ContactData withPhoto(String photo){
        return new ContactData(this.id, this.Firstname, Middlename, Lastname, this.Nickname, photo, this.Title, this.Company, this.Address, this.home, this.mobile,
                this.work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage,this.secondary);
    }

    public ContactData withIdName(String Id, String Firstname, String Lastname){
        return new ContactData(Id, Firstname, this.Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.home, this.mobile,
                this.work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage,this.secondary);
    }

    public ContactData withhome(String Home) {
        return new ContactData(this.id, this.Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, Home, this.mobile,
                this.work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage,this.secondary);
    }

    public ContactData withmobile(String Mobile) {
        return new ContactData(this.id, this.Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.home, Mobile,
                this.work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage,this.secondary);
    }

    public ContactData withwork(String work) {
        return new ContactData(this.id, this.Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.home, this.mobile,
                work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage,this.secondary);
    }
    public ContactData withsecondary(String secondary) {
        return new ContactData(this.id, this.Firstname, Middlename, Lastname, this.Nickname, this.Photo, this.Title, this.Company, this.Address, this.home, this.mobile,
                this.work, this.EmailOne, this.EmailTwo,
                this.EmailThree, this.Homepage,secondary);
    }
}