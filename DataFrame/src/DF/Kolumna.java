package DF;

import java.io.OptionalDataException;
import java.util.ArrayList;

public class Kolumna {
    String nazwa;
    TmpTypDanych typ;
    ArrayList dane;

    public Kolumna(String nazwa, TmpTypDanych typ){
        this.nazwa=nazwa;
        this.typ=typ;
        dane =  new ArrayList(10);
    }

    public Kolumna(Kolumna do_skopiowania){  // konstruktor kopiujący potrzebny do kopii głębokiej
        this.nazwa = new String(do_skopiowania.nazwa);
        typ = do_skopiowania.typ;
        dane = new ArrayList<>(do_skopiowania.dane);

    }

    public Object zwrocObiekt(int indeks){
        return dane.get(indeks);
    }

    public void dodaj (Object element){
        this.dane.add(element);
    }


}
