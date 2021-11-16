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
                conclusion1Cars.add(new Car("Ford", "Fiesta 1.25", "2004", "https://volantesic.pt/ford/fiesta/2004/usado/comprar/?ID=333783&PP=true&ML=41000&VG=3"));
                conclusion1Cars.add(new Car("Renault", "Clio 1.2", "2007", "https://volantesic.pt/renault/clio/2007/usado/comprar/?ID=397063&PP=true&ML=50953&VG=3"));
                conclusion1Cars.add(new Car("Peugeot", "206 1.1", "2001", "https://volantesic.pt/peugeot/206/2001/usado/comprar/?ID=382297&PP=true&ML=49000&VG=3"));
                return conclusion1Cars;
            case Conclusion.TIPO02:
                conclusion1Cars.add(new Car("Mercedes-Benz", "CLASSE S", "2001", "https://volantesic.pt/mercedes-benz/classe-s/2001/usado/comprar/?ID=356385&PP=true&ML=1140000&VG=3"));
                conclusion1Cars.add(new Car("BMW", "SÉRIE 3", "2001", "https://volantesic.pt/bmw/serie-3/2001/usado/comprar/?ID=478313&PP=true&ML=127000&VG=3"));
                conclusion1Cars.add(new Car("JAGUAR ", "S-TYPE", "2001", "https://volantesic.pt/jaguar/s-type/2001/usado/comprar/?ID=350137&PP=true&ML=73000&VG=3"));
                return conclusion1Cars;
            case Conclusion.TIPO03:
                conclusion1Cars.add(new Car("BMW", "SÉRIE 1", "2013", "https://volantesic.pt/bmw/serie-1/2013/usado/comprar/?ID=477551&PP=true&ML=239000&VG=3"));
                conclusion1Cars.add(new Car("AUDI", "A3", "2010", "https://volantesic.pt/audi/a3/2010/usado/comprar/?ID=301919&PP=true&ML=262000&VG=3"));
                conclusion1Cars.add(new Car("MERCEDES-BENZ", "CLASSE B", "2008", "https://volantesic.pt/mercedes-benz/classe-b/2008/usado/comprar/?ID=345732&PP=true&ML=215000&VG=3"));
                return conclusion1Cars;
            case Conclusion.TIPO04:
                conclusion1Cars.add(new Car("BMW", "SÉRIE 3", "2007", "https://volantesic.pt/bmw/serie-3/2007/usado/comprar/?ID=484353&PP=true&ML=300000&VG=3"));
                conclusion1Cars.add(new Car("AUDI", "A6 ALLROAD", "2016", "https://volantesic.pt/audi/a6-allroad/2006/usado/comprar/?ID=314331&PP=true&ML=500000&VG=2"));
                conclusion1Cars.add(new Car("Mercedes-Benz", "CLASSE C", "2007", "https://volantesic.pt/mercedes-benz/classe-c/2007/usado/comprar/?ID=354296&PP=true&ML=800000&VG=2"));
                return conclusion1Cars;
            case Conclusion.TIPO05:
                conclusion1Cars.add(new Car("FIAT", "500", "2014", "https://volantesic.pt/fiat/500/2014/usado/comprar/?ID=331111&PP=true&ML=90000&VG=3"));
                conclusion1Cars.add(new Car("PEUGEOT", "208", "2016", "https://volantesic.pt/peugeot/208/2016/usado/comprar/?ID=382602&PP=true&ML=143000&VG=3"));
                conclusion1Cars.add(new Car("TOYOTA", "YARIS", "2017", "https://volantesic.pt/toyota/yaris/2017/usado/comprar/?ID=450423&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO06:
                conclusion1Cars.add(new Car("FIAT", "500X", "2021", "https://volantesic.pt/fiat/500x/2021/usado/comprar/?ID=494129&PP=true"));
                conclusion1Cars.add(new Car("BMW", "SÉRIE 2", "2018", "https://volantesic.pt/bmw/serie-2/2018/usado/comprar/?ID=454562&PP=true"));
                conclusion1Cars.add(new Car("JAGUAR", "XE", "2015", "https://volantesic.pt/jaguar/xe/2015/usado/comprar/?ID=342471&PP=true&ML=170000&VG=3"));
                return conclusion1Cars;
            case Conclusion.TIPO07:
                conclusion1Cars.add(new Car("BMW", "SÉRIE 5", "2009", "https://volantesic.pt/bmw/serie-5/2009/usado/comprar/?ID=467834&PP=true"));
                conclusion1Cars.add(new Car("JAGUAR", "XF", "2013", "https://volantesic.pt/jaguar/xf/2013/usado/comprar/?ID=350326&PP=true"));
                conclusion1Cars.add(new Car("MERCEDES-BENZ", "CLASSE CL", "2009", "https://volantesic.pt/mercedes-benz/classe-cl/2009/usado/comprar/?ID=366981&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO08:
                conclusion1Cars.add(new Car("SEAT", "LEON", "2019", "https://volantesic.pt/seat/leon/2019/usado/comprar/?ID=458224&PP=true"));
                conclusion1Cars.add(new Car("FORD", "PUMA", "2021", "https://volantesic.pt/ford/puma/2021/novo/comprar/?ID=489593"));
                conclusion1Cars.add(new Car("MERCEDES-BENZ", "CLASSE A", "2018", "https://volantesic.pt/mercedes-benz/classe-a/2018/usado/comprar/?ID=478877&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO09:
                conclusion1Cars.add(new Car("NISSAN", "LEAF", "2017", "https://volantesic.pt/nissan/leaf/2017/usado/comprar/?ID=457581&PP=true"));
                conclusion1Cars.add(new Car("RENAULT", "ZOE", "2019", "https://volantesic.pt/renault/zoe/2018/usado/comprar/?ID=403069&PP=true"));
                conclusion1Cars.add(new Car("HYUNDAI", "IONIQ", "2018", "https://volantesic.pt/hyundai/ioniq/2018/usado/comprar/?ID=466778&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO10:
                conclusion1Cars.add(new Car("TESLA", "MODEL 3", "2019", "https://volantesic.pt/tesla/model-3/2019/usado/comprar/?ID=474384&PP=true"));
                conclusion1Cars.add(new Car("TESLA", "MODEL S", "2016", "https://volantesic.pt/tesla/model-s/2016/usado/comprar/?ID=447061&PP=true"));
                conclusion1Cars.add(new Car("MERCEDES-BENZ", "EQC", "2019", "https://volantesic.pt/mercedes-benz/eqc/2019/usado/comprar/?ID=461117&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO11:
                conclusion1Cars.add(new Car("JAGUAR", "I-PACE", "2019", "https://volantesic.pt/electrico/jaguar/i-pace/2019/usado/comprar/?ID=453897&PP=true"));
                conclusion1Cars.add(new Car("KIA", "NIRO", "2020", "https://volantesic.pt/electrico/kia/niro/2020/usado/comprar/?ID=460407&PP=true"));
                conclusion1Cars.add(new Car("HYUNDAI", "KAUAI", "2019", "https://volantesic.pt/electrico/hyundai/kauai/2019/usado/comprar/?ID=454343&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO12:
                conclusion1Cars.add(new Car("AUDI", "Q5", "2018", "https://volantesic.pt/audi/q5/2018/usado/comprar/?ID=466983&PP=true"));
                conclusion1Cars.add(new Car("BMW", "X5", "2018", "https://volantesic.pt/bmw/x5/2018/usado/comprar/?ID=458515&PP=true"));
                conclusion1Cars.add(new Car("PORSCHE", "CAYENNE", "2017", "https://volantesic.pt/porsche/cayenne/2017/usado/comprar/?ID=483937&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO13:
                conclusion1Cars.add(new Car("MITSUBISHI", "PAJERO", "2007", "https://volantesic.pt/mitsubishi/pajero/2007/usado/comprar/?ID=372460&PP=true"));
                conclusion1Cars.add(new Car("JEEP", "WRANGLER", "2010", "https://volantesic.pt/jeep/wrangler/2010/usado/comprar/?ID=344016&PP=true"));
                conclusion1Cars.add(new Car("LAND ROVER", "DEFENDER", "2015", "https://volantesic.pt/land-rover/defender/2015/usado/comprar/?ID=351152&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO14:
                conclusion1Cars.add(new Car("AUDI", "A5 CABRIO", "2019", "https://volantesic.pt/audi/a5/2019/usado/comprar/?ID=467235&PP=true"));
                conclusion1Cars.add(new Car("BMW", "SÉRIE 6", "2018", "https://volantesic.pt/bmw/serie-6/2018/usado/comprar/?ID=481288&PP=true"));
                conclusion1Cars.add(new Car("MERCEDES-BENZ", "CLASSE SL", "2017", "https://volantesic.pt/mercedes-benz/classe-sl/2017/usado/comprar/?ID=369498&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO15:
                conclusion1Cars.add(new Car("BMW", "M2", "2018", "https://volantesic.pt/bmw/m2/2018/usado/comprar/?ID=466458&PP=true"));
                conclusion1Cars.add(new Car("PORSCHE", "911", "2013", "https://volantesic.pt/porsche/911/2013/usado/comprar/?ID=483934&PP=true"));
                conclusion1Cars.add(new Car("AUDI", "R8", "2016", "https://volantesic.pt/audi/r8/2016/usado/comprar/?ID=321874&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO16:
                conclusion1Cars.add(new Car("AUDI", "A6", "2016", "https://volantesic.pt/audi/a6/2016/usado/comprar/?ID=315136&PP=true"));
                conclusion1Cars.add(new Car("BMW", "M5", "2017", "https://volantesic.pt/bmw/m5/2017/usado/comprar/?ID=453190&PP=true"));
                conclusion1Cars.add(new Car("PORSCHE", "PANAMERA", "2015", "https://volantesic.pt/porsche/panamera/2015/usado/comprar/?ID=400739&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO17:
                conclusion1Cars.add(new Car("CITROEN", "C4 G. PICASSO", "2017", "https://volantesic.pt/citroen/c4-g--picasso/2017/usado/comprar/?ID=447386&PP=true"));
                conclusion1Cars.add(new Car("FORD", "S-MAX", "2019", "https://volantesic.pt/ford/s-max/2019/usado/comprar/?ID=455014&PP=true"));
                conclusion1Cars.add(new Car("PEUGEOT", "5008", "2017", "https://volantesic.pt/peugeot/5008/2017/usado/comprar/?ID=457588&PP=true"));
                return conclusion1Cars;
            case Conclusion.TIPO18:
                conclusion1Cars.add(new Car("BMW", "SÉRIE 5 GRAN TURISMO", "2014", "https://volantesic.pt/bmw/serie-5-gran-turismo/2014/usado/comprar/?ID=484043&PP=true"));
                conclusion1Cars.add(new Car("AUDI", "A6 ALLROAD", "2016", "https://volantesic.pt/carrinha/audi/a6-allroad/2016/usado/comprar/?ID=473119&PP=true"));
                conclusion1Cars.add(new Car("VOLKSWAGEN", "PASSAT", "2018", "https://volantesic.pt/volkswagen/passat/2018/usado/comprar/?ID=492714&PP=true"));
                return conclusion1Cars;

        }

        return null;
    }

    @Override
    public String toString() {
        return  "Marca: '" + brand + '\'' +
                ", Modelo: '" + model + '\'' +
                ", Ano: '" + year + '\'' +
                ", Link: '" + link + '\''
                ;
    }
}
