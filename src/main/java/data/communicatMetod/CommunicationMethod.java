package data.communicatMetod;

public enum CommunicationMethod {
    TELEGRAM("Тelegram"),
    WHATSAPP("Whatsapp");
    private String name;

    CommunicationMethod(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
