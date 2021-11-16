package org.engcia.meiaChallenge1Team4.model;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private final String brand;
    private final String model;
    private final String year;
    private final String link;

    public Car(String brand, String model, String year, String link) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.link = link;
    }

    public static ArrayList<Car> getCarsForConclusion(String conclusion) {
        ArrayList<Car> conclusion1Cars = new ArrayList<Car>();

        switch (conclusion){
            case Conclusion.TIPO01:
                conclusion1Cars.add(new Car("dsadsa", "dasasdsa", "dsadsa", "dsads"));
                conclusion1Cars.add(new Car("dsadsa", "dasasdsa", "dsadsa", "dsads"));
                conclusion1Cars.add(new Car("dsadsa", "dasasdsa", "dsadsa", "dsads"));
                return conclusion1Cars;
        }

        return null;
    }
}
