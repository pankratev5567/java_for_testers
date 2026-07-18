package model;

public record GroupData(String id, String name, String header, String footer) {

    public GroupData(){
        this ("", "","","");
    }

    public GroupData withName(String Name) {
        return new GroupData(this.id, Name,this.header,this.footer);
    }

    public GroupData withId(String id) {
        return new GroupData(id, this.name,this.header,this.footer);
    }

    public GroupData withHeader(String Header) {
        return new GroupData(this.id, this.name,Header,this.footer);
    }

    public GroupData withFooter(String Footer) {
        return new GroupData(this.id, this.name,this.header,Footer);
    }
}