package DF;

import java.util.ArrayList;

public class Kolumna {
    String nazwa;
    String typ;
    ArrayList<?> kolumna;

    public Kolumna(String name, String type){
        nazwa=name;
        typ=type;
        try{
            Class<?> nazwa_typu = Class.forName((typ));
        }catch(ClassNotFoundException e){
            System.out.println("Nie istnieje taki typ");

        }
        kolumna =  new ArrayList<Object>(10);
    }

}
