package com.example.herbadmin;

public class Plant {
    private String plantName,botName,dop,prep,bnr,plantImage;

    public Plant(){

    }

    public Plant(String f_name, String b_name, String dop, String prep, String bnr, String plantImage) {
        this.plantName = f_name;
        this.botName = b_name;
        this.dop = dop;
        this.prep = prep;
        this.bnr = bnr;


    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String lastName) {
        this.botName = botName;
    }

    public String getDop() {
        return dop;
    }

    public void setDop(String dob) {
        this.dop = dop;
    }

    public String getPrep() {
        return prep;
    }

    public void setPrep(String prep) {
        this.prep = prep;
    }

    public String getBnr() {
        return bnr;
    }

    public void setBnr(String desc) {
        this.bnr = bnr;
    }


    public String getPlantImage() { return plantImage; }

    public void setPlantImage(String plantImage) {
        this.plantImage = plantImage;
    }
}
