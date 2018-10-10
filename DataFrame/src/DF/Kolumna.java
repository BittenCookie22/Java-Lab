package DF;

import java.io.OptionalDataException;
import java.util.ArrayList;

public class Kolumna {
    String nazwa;
    String typ;
    ArrayList dane;

    public Kolumna(String nazwa, String typ){
        this.nazwa=nazwa;
        this.typ=typ;
        dane =  new ArrayList(10);
    }

    public Kolumna(Kolumna do_skopiowania){  // konstruktor kopiujący porzebny do kopii głębokiej
        nazwa = new String(do_skopiowania.nazwa);
        typ = new String(do_skopiowania.typ);
        dane = new ArrayList<>(do_skopiowania.dane);
    }

    public void addElement(Object element){ // --> przenieść do DF
        if (element.getClass().toString() == typ){
            dane.add(element);
        }
        else {
            System.out.println("zły typ danych, nie psauje do tej kolmumny");}

    }

}
