package DF;

import DF.Exceptions.DifferentSizeOfColumnsExcepiton;
import DF.Exceptions.DivideByZeroException;
import DF.Exceptions.IncoherentTypeException;
import DF.Values.DoubleValue;
import DF.Values.NumericValue;
import DF.Values.Value;

import java.util.ArrayList;
import java.util.Scanner;

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

    public String getNazwa(){
        return this.nazwa;
    }

    public Class<? extends Value>  getType(){return this.typ;}

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

    public void dodaj (Value element)throws  IncoherentTypeException{
       // Class<? extends Value> tmpTyp = element.getClass();
        if(typ.isInstance(element)){
            this.dane.add(element);}
            else {throw new IncoherentTypeException(size(),nazwa,"klasa elementu dodawanego: "+element.getClass()+" niezgodna z typem w kolumnie " +
                "do ktorej mial byc dodany "+this.typ + " - operacja dodania elementu "+ element+ " niemozliwa" );}
    }



    @Deprecated
    public Kolumna doMathByUser(Value val) throws IncoherentTypeException {  //pozwala wybraz uzytkownikowi dzialanie do wykonania na kolumnie i value
        Kolumna output = new Kolumna(nazwa,NumericValue.class);

            String dzialanie;
            Scanner odczyt = new Scanner(System.in); //obiekt do odebrania danych od użytkownika
            dzialanie = odczyt.nextLine();
            switch (dzialanie){
                case "add":
                    for(Value i :dane) {
                            output.dodaj(i.add(val));
                    }
                    break;
                case "sub":
                    for(Value i :dane) {

                            output.dodaj(i.sub(val));

                    }
                    break;
                case "mul":
                    for(Value i :dane) {

                            output.dodaj(i.mul(val));

                    }
                    break;
                case "div":
                    for(Value i :dane) {

                            output.dodaj  (new DoubleValue(((NumericValue)i).getValue().doubleValue()).div(new DoubleValue(((NumericValue)val).getValue().doubleValue())));

                    }
                case "pow":
                    for(Value i :dane) {

                            output.dodaj  (new DoubleValue(((NumericValue)i).getValue().doubleValue()).pow(new DoubleValue(((NumericValue)val).getValue().doubleValue())));

                    }
                    break;


            }
            return output;
    }


    public Kolumna doMathOperationWithValue(Value val,String operation) throws IncoherentTypeException, DivideByZeroException {
        Kolumna output = new Kolumna(nazwa,NumericValue.class);
        switch (operation){
            case "add": for(Value i :dane) {output.dodaj(i.add(val));} return output;
            case "mul": for(Value i :dane) {output.dodaj(i.mul(val));} return output;
            case "sub": for(Value i :dane) {output.dodaj(i.sub(val));} return output;
            case "pow": for(Value i :dane) {output.dodaj(new DoubleValue(((NumericValue)i).getValue().doubleValue()).pow(new DoubleValue(((NumericValue)val).getValue().doubleValue()))); } return output;
            case "div": for(Value i :dane) {
                if(((NumericValue) val).getValue().doubleValue()!=0){
                    output.dodaj(new DoubleValue(((NumericValue) i).getValue().doubleValue()).div(new DoubleValue(((NumericValue) val).getValue().doubleValue())));}
                else {throw new DivideByZeroException(val);}
                }
                return output;
            default:
                return this;
        }
    }


    public Kolumna doMathOperationWithOtherColumn(Kolumna kol,String operation) throws DifferentSizeOfColumnsExcepiton, IncoherentTypeException {
        if(this.dane.size()==kol.dane.size()){
            Kolumna output = new Kolumna(nazwa, DoubleValue.class);
            switch (operation){
                case "add":for (int i = 0; i < dane.size(); i++) {output.dodaj(new DoubleValue(((NumericValue) this.dane.get(i).add(kol.dane.get(i))).getValue().doubleValue())); }
                return output;
                case "sub":for (int i = 0; i < dane.size(); i++) { output.dodaj(new DoubleValue (((NumericValue)this.dane.get(i).sub(kol.dane.get(i))).getValue().doubleValue())); }
                    return output;
                case "mul":for (int j = 0; j < dane.size(); j++) { output.dodaj(new DoubleValue (((NumericValue)this.dane.get(j).mul(kol.dane.get(j))).getValue().doubleValue())); }
                    return output;
                case "div":for (int i = 0; i < dane.size(); i++) { output.dodaj(new DoubleValue (((NumericValue)this.dane.get(i).div(kol.dane.get(i))).getValue().doubleValue())); }
                    return output;
                case "pow":for (int i = 0; i < dane.size(); i++) { output.dodaj(new DoubleValue (((NumericValue)this.dane.get(i).pow(kol.dane.get(i))).getValue().doubleValue())); }
                    return output;
                default:
                    return this;
            }
        }
        throw new DifferentSizeOfColumnsExcepiton(this.size(),kol.size());
    }


    //-------------------------prev version -----------------

    @Deprecated
    public Kolumna addKol(Kolumna kol) throws IncoherentTypeException, DifferentSizeOfColumnsExcepiton {
        if (this.dane.size() == kol.dane.size()) {
            Kolumna output = new Kolumna(nazwa, DoubleValue.class);
            for (int i = 0; i < dane.size(); i++) {
                output.dodaj(new DoubleValue(((NumericValue) this.dane.get(i).add(kol.dane.get(i))).getValue().doubleValue()));
            }
            return output;
        }
        throw new DifferentSizeOfColumnsExcepiton(this.size(), kol.size());
    }

    @Deprecated
    public Kolumna subKol(Kolumna kol) throws IncoherentTypeException, DifferentSizeOfColumnsExcepiton {
        if(this.dane.size()==kol.dane.size()){
            Kolumna output = new Kolumna(nazwa, DoubleValue.class);
            for (int i = 0; i < dane.size(); i++) {
                output.dodaj(new DoubleValue (((NumericValue)this.dane.get(i).sub(kol.dane.get(i))).getValue().doubleValue()));
            }
            return output;
        }
        throw new DifferentSizeOfColumnsExcepiton(this.size(),kol.size());
    }

    @Deprecated
    public Kolumna mulKol(Kolumna kol) throws IncoherentTypeException, DifferentSizeOfColumnsExcepiton {
        if(this.dane.size()==kol.dane.size()){
            Kolumna output = new Kolumna(nazwa, DoubleValue.class);
            for (int j = 0; j < dane.size(); j++) {
                output.dodaj(new DoubleValue (((NumericValue)this.dane.get(j).mul(kol.dane.get(j))).getValue().doubleValue()));
            }
            return output;
        }
        throw new DifferentSizeOfColumnsExcepiton(this.size(),kol.size());
    }

    @Deprecated
    public Kolumna divKol(Kolumna kol) throws IncoherentTypeException,DifferentSizeOfColumnsExcepiton {
        if(this.dane.size()==kol.dane.size()){
            Kolumna output = new Kolumna(nazwa, DoubleValue.class);
            for (int i = 0; i < dane.size(); i++) {
                output.dodaj(new DoubleValue (((NumericValue)this.dane.get(i).div(kol.dane.get(i))).getValue().doubleValue()));
            }
            return output;
        }
        throw new DifferentSizeOfColumnsExcepiton(this.size(),kol.size());
    }

    @Deprecated
    public Kolumna powKol(Kolumna kol) throws IncoherentTypeException, DifferentSizeOfColumnsExcepiton {
        if(this.dane.size()==kol.dane.size()){
            Kolumna output = new Kolumna(nazwa, DoubleValue.class);
            for (int i = 0; i < dane.size(); i++) {
                output.dodaj(new DoubleValue (((NumericValue)this.dane.get(i).pow(kol.dane.get(i))).getValue().doubleValue()));
            }
            return output;

        }
        throw new DifferentSizeOfColumnsExcepiton(this.size(),kol.size());
    }

    @Deprecated
    public Kolumna add(Value val) throws IncoherentTypeException {
        Kolumna output = new Kolumna(nazwa,NumericValue.class);
        for(Value i :dane) {
            output.dodaj(i.add(val));
        }
        return output;
    }

    @Deprecated
    public Kolumna sub(Value val) throws IncoherentTypeException {
        Kolumna output = new Kolumna(nazwa,typ);
        for(Value i :dane) {
            output.dodaj(i.sub(val));
        }
        return output;
    }

    @Deprecated
    public Kolumna mul(Value val) throws IncoherentTypeException {
        Kolumna output = new Kolumna(nazwa, typ);
        for (Value i : dane) {
            output.dodaj(i.mul(val));
        }
        return output;
    }

    @Deprecated
    public Kolumna div(Value val) throws IncoherentTypeException, DivideByZeroException {
        Kolumna output = new Kolumna(nazwa, DoubleValue.class);
        for (Value i : dane) {
            if(((NumericValue) val).getValue().doubleValue()!=0){
                output.dodaj(new DoubleValue(((NumericValue) i).getValue().doubleValue()).div(new DoubleValue(((NumericValue) val).getValue().doubleValue())));}
            else {throw new DivideByZeroException(val);
            }
        }
        return output;
    }

    @Deprecated
    public Kolumna pow(Value val) throws IncoherentTypeException {
        Kolumna output = new Kolumna(nazwa,DoubleValue.class);
        for(Value i :dane) {

            output.dodaj  (new DoubleValue(((NumericValue)i).getValue().doubleValue()).pow(new DoubleValue(((NumericValue)val).getValue().doubleValue())));

        }
        return output;
    }
//    @Override
//    public String toString() {
//
//        StringBuilder s=new StringBuilder();
//        String[] str=typ.getTypeName().split("\\.");
//        s.append(String.format("|%-14.14s:%15.15s",nazwa ,str[str.length-1]));
//        s.append("|\n");
//        for(int i=0;i<dane.size();i++){
//            s.append(String.format("|%30.30s|\n",zwrocObiekt(i).toString()));
//        }
//        return s.toString();
//    }




}
