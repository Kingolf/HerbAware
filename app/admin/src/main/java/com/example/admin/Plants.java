package com.example.admin;

public class Plants {

    public String plantName, botName, about, uses, risks, imageUrl;

    public Plants(){}

    public Plants(String plantName, String botName, String about, String uses, String risks, String imageUrl) {
        this.plantName = plantName;
        this.botName = botName;
        this.about = about;
        this.uses = uses;
        this.risks = risks;
        this.imageUrl = imageUrl;
    }

    public String getPlantName() {
        return plantName;
    }

    public String getBotName() {
        return botName;
    }

    public String getAbout() {
        return about;
    }

    public String getUses() {
        return uses;
    }

    public String getRisks() {
        return risks;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
