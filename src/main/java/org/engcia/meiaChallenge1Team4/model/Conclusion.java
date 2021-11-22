package org.engcia.meiaChallenge1Team4.model;

import java.util.ArrayList;

public class Conclusion extends FactCF{
    public static final String TIPO01 = "Tipo 01 -10K +Antigo -Imposto";
    public static final String TIPO02 = "Tipo 02 -10K +Antigo +Imposto";
    public static final String TIPO03 = "Tipo 03 -10K +KM +Estado";
    public static final String TIPO04 = "Tipo 04 -10K +KM -Estado";
    public static final String TIPO05 = "Tipo 05 Gasoleo -Emissoes";
    public static final String TIPO06 = "Tipo 06 Gasoleo +Emissoes";
    public static final String TIPO07 = "Tipo 07 Gasolina +Performance";
    public static final String TIPO08 = "Tipo 08 Gasolina -Performance";
    public static final String TIPO09 = "Tipo 09 Eletrico -200KM dia";
    public static final String TIPO10 = "Tipo 10 Eletrico +200KM Piloto Automatico";
    public static final String TIPO11 = "Tipo 11 Eletrico +200KM s/Piloto Automatico";
    public static final String TIPO12 = "Tipo 12 SUV On-Road";
    public static final String TIPO13 = "Tipo 13 SUV Off-Road";
    public static final String TIPO14 = "Tipo 14 Desportivo 2 Portas Cabrio";
    public static final String TIPO15 = "Tipo 15 Desportivo 2 Portas";
    public static final String TIPO16 = "Tipo 16 Desportivo 4-5 Portas";
    public static final String TIPO17 = "Tipo 17 Familiar 7 lugares";
    public static final String TIPO18 = "Tipo 18 Familiar 4-5 lugares";

    private ArrayList<Car> listCars;

    public Conclusion(double cf, String description, ArrayList<Car> listCars) {
        super(cf,description,"true");
        this.listCars = listCars;
    }

    public String toString() {
/*        for (int i = 0; i < listCars.size(); i++) {
            System.out.println(listCars.get(i).toString());
        }*/
        return super.toString();
    }

}