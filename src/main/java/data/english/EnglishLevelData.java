package data.english;

public enum EnglishLevelData {
    BEGINNER("Начальный уровень (Beginner)"),
    ELEMENTARY("Элементарный уровень (Elementary)"),
    PREINTERMEDIATE("Ниже среднего (Pre-Intermediate)");


    private String name;

    EnglishLevelData(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
