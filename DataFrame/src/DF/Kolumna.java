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


}
