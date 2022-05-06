package com.example.herbaware;

public class Plant {
    String plant_name, botanical_name, localName,about,benefits,prepare;
    int imageId;

    public Plant(String plant_name, String botanical_name, String localName, String about,
                 String benefits, String prepare) {
        this.plant_name = plant_name;
        this.botanical_name = botanical_name;
        this.localName = localName;
        this.about = about;
        this.benefits = benefits;
        this.prepare = prepare;
    }


}
