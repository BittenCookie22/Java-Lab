package DF;

import DF.Values.Value;

import java.util.ArrayList;

public class Kolumna {
    String nazwa;
    Class<? extends Value> typ;
    ArrayList<Value> dane;

    public Kolumna(String nazwa,  Class<? extends Value> typ){
        this.nazwa=nazwa;
        this.typ=typ;
        dane =  new ArrayList<Value>();
    }

    public Kolumna(){};

    @Override
    public String toString() {

        StringBuilder s=new StringBuilder();
        s.append(nazwa).append(" : ").append(typ.toGenericString()).append('\n');
        for(Value o:dane)
            s.append(o.toString()).append('\n');
        return s.toString();
    }

    public Kolumna(Kolumna do_skopiowania){  // konstruktor kopiujący potrzebny do kopii głębokiej
        dane = new ArrayList<Value>(do_skopiowania.dane);
        this.nazwa = new String(do_skopiowania.nazwa);
        typ = do_skopiowania.typ;


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
