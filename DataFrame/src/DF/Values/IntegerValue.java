package DF.Values;

public class IntegerValue extends NumericValue {
    private int value;


    /**
     * klasa dziedzicząca po Value implementuje Integera
     * @param value argument konstruktora parametrycznego
     */
    public IntegerValue(int value){
        this.value=value;
    }

    public IntegerValue(){};

//    @Override
//    public String toString() {
//        return "IntegerValue{" + "value=" + value + '}';
//    }

    @Override
    public Integer getValue() {
        return this.value;
    }


    /**
     * dodaje do siebie this.wartosc oraz val jesli jest to wartosc numeryczna, ale castuje ja do inta
     * @param val dodawana wartosc ktora musi byc numeric
     * @return IntegerValue nowy obiekt utowrzony na podstawie obliczonej sumy
     */
    @Override
    public IntegerValue add(Value val) throws UnsupportedOperationException {
        if(val instanceof NumericValue){
            return new IntegerValue(((NumericValue)val).getValue().intValue()+this.value);
        }
       else{
            throw new UnsupportedOperationException();
        }
    }


    /**
     * odejmje od siebie this.wartosc oraz val jesli jest to wartosc numeryczna, ale castuje ja do inta
     * @param val odejmowana wartosc ktora musi byc numeric
     * @return IntegerValue nowy obiekt utowrzony na podstawie obliczonej różnicy
     */
    @Override
    public IntegerValue sub(Value val) throws UnsupportedOperationException {
        if(val instanceof NumericValue){
            return new IntegerValue(this.value-((NumericValue)val).getValue().intValue());
        }
        else{
            throw new UnsupportedOperationException();

        }
    }

    /**
     * sprawdza w pierwszej kolejnosi czy val w ogóle jest numeric, a potem czy jest zgodna co do warotosci z this
     * @param val sprawdzana wartosc
     * @return true jesli warunki spełnione, false jeśli nie
     */
    @Override
    public  boolean eq(Value val){
        if ( val instanceof  NumericValue && (((NumericValue)val).getValue().equals(this.value))){
                return true;
        }
        else {
            return false;
        }
    }
    @Override
    public  IntegerValue mul(Value val){
        if(val instanceof NumericValue){
            return new IntegerValue(this.value*((NumericValue)val).getValue().intValue());
        }
        else{
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public  IntegerValue div(Value val){
        if(val instanceof NumericValue ){
                    return new IntegerValue(this.value/((NumericValue)val).getValue().intValue());}

        else{
            throw new UnsupportedOperationException();
        }
    }

    /**
     * podnosi this do potegi val,najpierw sprawdza czy val instanceof Numeric
     * @param val wykladnik potegi
     * @return IntegerValue this podniesione do potegi ktora jest reprezentowana jako Double w celu zachowania jak najwiekszej dokladnosci
     */
    @Override
    public  IntegerValue pow(Value val){
        IntegerValue tmp = new IntegerValue(0);
        if (val instanceof NumericValue){
            tmp= new IntegerValue ((int)(Math.pow(this.value,((NumericValue)val).getValue().doubleValue())));
        }
        return tmp;
    }

    @Override
    public  boolean lte(Value val) {
        if (val instanceof NumericValue) {
            if (this.value <= (((NumericValue) val).getValue().doubleValue())) {
                return true;
            } else {
                return false;
            }
        }return false;
    }


    /**
     * sprawdza czy val jest Numeric, potem czy this  od val,przy czym val reperezentowana jako Double  w celu zachowania jak najwiekszej dokladnosci
     * @param val porownywana wartosc
     * @return true jesli this  val, false jesli nie lub val nie jest Numeric
     */
    @Override
    public  boolean gte(Value val) {
        if (val instanceof NumericValue) {
            if ( this.value >=(((NumericValue) val).getValue().doubleValue())) {
                return true;
            } else {
                return false;
            }
        }return false;
    }




    @Override
    public  boolean equals(Object other){
        if (other==null){return false;}
        if (other instanceof IntegerValue){
            IntegerValue otherInt = (IntegerValue)other;
            return this.value==otherInt.value;
        }
        return false;
    }



    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    @Override
    public IntegerValue clone() throws CloneNotSupportedException {
        return new IntegerValue(value);
    }

    @Override
    public IntegerValue create(String s){
        return new IntegerValue(Integer.parseInt(s));
    }

}
