package org.engcia.cf.model;

public class Conclusion {
    public static final String TIPO01 = "Categoria de carros tipo 01";
    public static final String TIPO02 = "Categoria de carros tipo 02";
    public static final String TIPO03 = "Categoria de carros tipo 03";
    public static final String TIPO04 = "Categoria de carros tipo 04";
    public static final String TIPO05 = "Categoria de carros tipo 05";
    public static final String TIPO06 = "Categoria de carros tipo 06";
    public static final String TIPO07 = "Categoria de carros tipo 07";
    public static final String TIPO08 = "Categoria de carros tipo 08";
    public static final String TIPO09 = "Categoria de carros tipo 09";
    public static final String TIPO10 = "Categoria de carros tipo 10";
    public static final String TIPO11 = "Categoria de carros tipo 11";
    public static final String TIPO12 = "Categoria de carros tipo 12";
    public static final String TIPO13 = "Categoria de carros tipo 13";
    public static final String TIPO14 = "Categoria de carros tipo 14";
    public static final String TIPO15 = "Categoria de carros tipo 15";
    public static final String TIPO16 = "Categoria de carros tipo 16";
    public static final String TIPO17 = "Categoria de carros tipo 17";
    public static final String TIPO18 = "Categoria de carros tipo 18";


    private String description;

    public Conclusion(String description) {
        super();
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "Diagnosis: " + description;
    }
}