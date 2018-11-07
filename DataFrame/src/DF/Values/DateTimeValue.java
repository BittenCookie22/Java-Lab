package DF.Values;

import java.time.LocalDateTime;

public class DateTimeValue extends Value {
    private LocalDateTime data;  //rok,miesiąc,dzień,godzina,minuta

    /**
     * klasa DateTimeValue odpowiada za przechowywanie daty w formacie 2007-12-03T10:15:30
     * dziedziczy po Value,
     * @param data argument konstruktora parametrycznego
     */
    public DateTimeValue(LocalDateTime data){
        this.data=data;
    }

    public DateTimeValue(){};

    @Override
    public LocalDateTime getValue(){
        return this.data;
    }


    /**
     * funkcja toString() korzysta z funkcji o tej samej nazwie dla pakietu java.time.LocalDateTime
     * @return tmp zwraca datę w postaci stringa 2007-12-03T10:15:30.
     */
    @Override
    public String toString() {
        String tmp = this.data.toString(); //Outputs this date-time as a String, such as 2007-12-03T10:15:30.
        return tmp;
    }

    /**
     * dodawanie dat nie jest sensowne
     * @return tmp zwraca datę w postaci stringa 2007-12-03T10:15:30
     * @throws UnsupportedOperationException wyrzuca błąd niedopuszczalnej operacji
     */
    @Override
    public DateTimeValue add(Value val){ //dodawanie daty nie ma sensu
        throw new UnsupportedOperationException();
   }

    /**
     * odejmowanie dat nie jest sensowne
     * @return tmp zwraca datę w postaci stringa 2007-12-03T10:15:30
     * @throws UnsupportedOperationException wyrzuca błąd niedopuszczalnej operacji
     */
   @Override
    public DateTimeValue sub(Value val){ //odejmowanie też
        throw new UnsupportedOperationException();
   }


    /**
     * mnożenie dat nie jest sensowne
     * @return tmp zwraca datę w postaci stringa 2007-12-03T10:15:30
     * @throws UnsupportedOperationException wyrzuca błąd niedopuszczalnej operacji
     */
    @Override
    public DateTimeValue mul(Value val)throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }


    /**
     * dzielenie dat nie jest sensowne
     * @return tmp zwraca datę w postaci stringa 2007-12-03T10:15:30
     * @throws UnsupportedOperationException wyrzuca błąd niedopuszczalnej operacji
     */
    @Override
    public DateTimeValue div(Value val){
        throw new UnsupportedOperationException();
    }


    /**
     * podnoszenie dat do potęgi nie jest sensowne
     * @return tmp zwraca datę w postaci stringa 2007-12-03T10:15:30
     * @throws UnsupportedOperationException wyrzuca błąd niedopuszczalnej operacji
     */
    @Override
    public DateTimeValue pow(Value val){
        throw new UnsupportedOperationException();
    }


    /**
     * sprawdza czy dwie daty są takie same
     * @return true jeśli są takie same,false jeśli są różne lub val nie jest instanceof DateTimeValue
     */
    @Override
    public boolean eq(Value val){
        if (val instanceof DateTimeValue){
            if (this.data.equals(((DateTimeValue)val).data)){
                return true; }
        }
        return false;
    }


    /**
     * sprawdza czy dwie daty nie są takie same
     * @return true jeśli nie są takie same,false jeśli są te same lub val nie jest instanceof DateTimeValue
     */
    @Override
    public boolean neq(Value val){
        if (val instanceof DateTimeValue){
            if (!(this.data.equals(((DateTimeValue)val).data))){
                return true; }
        }
        return false;
    }

    /**
     * sprawdza czy val jest wczesniej niz this.data
     * @return true jeśli jest wcześniej,false jeśli nie jest lub val nie jest instanceof DateTimeValue
     */
    @Override
    public boolean lte(Value val){  //isAfter- Checks if this date-time is after the specified date-time.
        if (val instanceof DateTimeValue){
            if(this.data.isAfter(((DateTimeValue)val).data)){
                return true;
            }else{return false;}
        }
        return false;
    }


    /**
     * sprawdza czy val jest pozniej niz this.data
     * @return true jeśli jest pozniej,false jeśli nie jest lub val nie jest instanceof DateTimeValue
     */
    @Override
    public boolean gte(Value val){
        if (val instanceof DateTimeValue){
            if((this.data.isBefore(((DateTimeValue)val).data))){
                return true;
            }else{return false;}
        }
       return false;
    }

    @Override
    public  boolean equals(Object other){
        if (other==null){return false;}
        if (other instanceof DateTimeValue){
            DateTimeValue otherDateTime = (DateTimeValue) other;
            return this.data.equals(otherDateTime.data);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 31*this.data.hashCode();
    }



    @Override
    public DateTimeValue create(String s){
        if(s.contains("T")) {
            return new DateTimeValue(LocalDateTime.parse(s));
        }
        String output = s+"T00:00:00";
        return new DateTimeValue(LocalDateTime.parse(output));
    }
}




