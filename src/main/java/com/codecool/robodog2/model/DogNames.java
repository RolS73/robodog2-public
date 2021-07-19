package com.codecool.robodog2.model;

import java.util.Arrays;
import java.util.List;


public class DogNames {

    private static DogNames instance;
    private List<String> dogNames;

    public static DogNames getInstance() {
        if (instance == null) {
            instance = new DogNames();
        }
        return instance;
    }

    public DogNames() {
        this.dogNames = Arrays.asList(
                "Foltos",
                "Morcos",
                "Buksi",
                "Jancsi",
                "Kanoan",
                "Jeremia",
                "Jawbone",
                "Fido"
                );
    }

    public List<String> getDogNames() {
        return dogNames;
    }
}
