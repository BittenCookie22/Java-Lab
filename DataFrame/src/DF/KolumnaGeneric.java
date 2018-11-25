//
//package DF;
//
//import DF.Values.IValue;
//import DF.Values.Value;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Deprecated
//public class KolumnaGeneric<T extends Value> {
//    String nazwa;
//    List<T> dane;
//
//    public KolumnaGeneric(String nazwa){
//        this.nazwa=nazwa;
//        dane =  new ArrayList<T>();
//    }
//
//    public KolumnaGeneric(){};
//
//    @Override
//    public String toString() {
//
//        StringBuilder s=new StringBuilder();
//        s.append(nazwa).append('\n');
//        for(Value o:dane)
//            s.append(o.toString()).append('\n');
//        return s.toString();
//    }
//
//    public KolumnaGeneric(KolumnaGeneric<T> do_skopiowania){  // konstruktor kopiujący potrzebny do kopii głębokiej
//        dane = new ArrayList<T>(do_skopiowania.dane);
//        this.nazwa = do_skopiowania.nazwa;
//
//
//    }
//
//    public int size(){
//        return dane.size();
//    }
//
//    public Value zwrocObiekt(int indeks){
//        return dane.get(indeks);
//    }
//
//    public void dodaj (T element){
//
//            this.dane.add(element);
//    }
//
//
//    public KolumnaGeneric add(T val){
//
//            KolumnaGeneric<Value> output = new KolumnaGeneric<>(nazwa);
//            for(T i :dane) {
//                output.dodaj(i.add(val));
//            }
//            return output;
//
//
//    }
//
////    public KolumnaGeneric sub(Value val){
////        if(val instanceof NumericValue && zwrocObiekt(0) instanceof NumericValue){
////            KolumnaGeneric output = new KolumnaGeneric(nazwa,typ);
////            for(Value i :dane) {
////                output.dodaj(i.sub(val));
////            }
////            return output;
////        }
////        else {throw new IllegalArgumentException("niemozliwa operacja do wykonania, błedne typy!");}
////
////    }
////
////    public KolumnaGeneric mul(Value val){
////        if(val instanceof NumericValue && zwrocObiekt(0) instanceof NumericValue){
////            KolumnaGeneric output = new KolumnaGeneric(nazwa,typ);
////            for(Value i :dane) {
////                output.dodaj(i.mul(val));
////            }
////            return output;
////        }
////        else {throw new IllegalArgumentException("niemozliwa operacja do wykonania, błedne typy!");}
////
////    }
////
////    public KolumnaGeneric div(Value val){
////        if(val instanceof NumericValue && zwrocObiekt(0) instanceof NumericValue){
////            KolumnaGeneric output = new KolumnaGeneric(nazwa,DoubleValue.class);
////            for(Value i :dane) {
////                output.dodaj  (new DoubleValue(((NumericValue)i).getValue().doubleValue()).div(new DoubleValue(((NumericValue)val).getValue().doubleValue())));
////            }
////            return output;
////        }
////        else {throw new IllegalArgumentException("niemozliwa operacja do wykonania, błedne typy!");}
////
////    }
////
////    public KolumnaGeneric pow(Value val){
////        if(val instanceof NumericValue && zwrocObiekt(0) instanceof NumericValue){
////            KolumnaGeneric output = new KolumnaGeneric(nazwa,DoubleValue.class);
////            for(Value i :dane) {
////                output.dodaj  (new DoubleValue(((NumericValue)i).getValue().doubleValue()).pow(new DoubleValue(((NumericValue)val).getValue().doubleValue())));
////            }
////            return output;
////        }
////        else {throw new IllegalArgumentException("niemozliwa operacja do wykonania, błedne typy!");}
////
////    }
////
////    public KolumnaGeneric addKol(KolumnaGeneric kol){
////        if(this.dane.size()==kol.dane.size()){
////            if(kol.zwrocObiekt(0) instanceof NumericValue && zwrocObiekt(0) instanceof NumericValue) {
////                KolumnaGeneric output = new KolumnaGeneric(nazwa, DoubleValue.class);
////                for (int i = 0; i < dane.size(); i++) {
////                    output.dane.get(i).add(kol.dane.get(i));
////                }
////
////
////                return output;
////            }
////        }
////        throw new UnsupportedOperationException("bład w rozmiare");
////    }
//
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
//
//
//
//
//}
