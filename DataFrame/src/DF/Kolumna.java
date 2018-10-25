package DF;

import java.util.ArrayList;

public class Kolumna {
    String nazwa;
    Class<? extends Value> typ;
    ArrayList<Value> dane;

    public Kolumna(String nazwa,  Class<? extends Value> typ){
        this.nazwa=nazwa;
        this.typ=typ;
        dane =  new ArrayList<>(10);
    }

    public Kolumna(){};

    public Kolumna(Kolumna do_skopiowania){  // konstruktor kopiujący potrzebny do kopii głębokiej
        this.nazwa = new String(do_skopiowania.nazwa);
        typ = do_skopiowania.typ;
        dane = new ArrayList<>(do_skopiowania.dane);

    }

    public int size(){
        return dane.size();
    }
    public Value zwrocObiekt(int indeks){
        return dane.get(indeks);
    }

    public void dodaj (Value element)throws  IllegalArgumentException{
        if(typ.isInstance(element)){
            this.dane.add(element);}
            else {throw new IllegalArgumentException();}
    }


}
