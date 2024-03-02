package data.gender;

public enum Gender {
    MALE("m"),
    FEMALE("f");

    private String name;

    Gender(String name){
        this.name = name;
    }

    public String getName(){
        return  this.name;
    }
}
