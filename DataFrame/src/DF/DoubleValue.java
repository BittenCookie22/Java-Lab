package DF;

import java.util.Objects;

public class DoubleValue extends Value{
    private double value;

    public DoubleValue(double value){
        this.value=value;
    }

    public DoubleValue(){};

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DoubleValue{" + "value=" + value + '}';
    }

    @Override
    public DoubleValue add(Value val) throws UnsupportedOperationException {
        if(val instanceof NumericValue){
            return new DoubleValue(((NumericValue)val).getValue().doubleValue()+this.value);
        }
        else{
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public DoubleValue sub(Value val) throws UnsupportedOperationException {
        if(val instanceof NumericValue){
            return new DoubleValue(this.value-((NumericValue)val).getValue().doubleValue());
        }
        else{
            throw new UnsupportedOperationException();

        }
    }

    @Override
    public  boolean eq(Value val){
        if ((Math.abs(this.value-((NumericValue)val).getValue().doubleValue())< 0.000001)){ // porównywanie liczb zmiennoprzecinkowych
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public  DoubleValue mul(Value val){
        if(val instanceof DoubleValue){
            return new DoubleValue(this.value*((NumericValue)val).getValue().doubleValue());
        }
        else{
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public  DoubleValue div(Value val){
        if(val instanceof DoubleValue ){
            //  if( ((IntegerValue)val).value)!=0){
            return new DoubleValue(this.value/((NumericValue)val).getValue().doubleValue());}

        else{
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public  DoubleValue pow(Value val){
        DoubleValue tmp=new DoubleValue(0.0);
        if (val instanceof NumericValue){
           tmp= new DoubleValue ((Math.pow(this.value,((NumericValue)val).getValue().doubleValue())));
        }
        return tmp;
    }

    /**
     * sprawdza czy val jest Numeric, potem czy this <= od val,przy czym val reperezentowana jako Double  w celu zachowania jak najwiekszej dokladnosci
     * @param val porownywana wartosc
     * @return true jesli this <= val, false jesli nie lub val nie jest Numeric
     */
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

    @Override
    public  boolean gte(Value val) {
        if (val instanceof NumericValue) {
            if (this.value >= (((DoubleValue) val).value)) {
                return true;
            } else {
                return false;
            }
        }return false;
    }

    @Override
    public  boolean neq(Value val){
        if (!(Math.abs(this.value-((NumericValue)val).getValue().doubleValue())< 0.000001)){ // porównywanie liczb zmiennoprzecinkowych
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public  boolean equals(Object other){
        if (other==null){return false;}
        if (other instanceof DoubleValue){
            DoubleValue otherDouble = (DoubleValue)other;
            return this.value==otherDouble.value;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return Objects.hash(getValue())*7;
    }

    @Override
    public DoubleValue create(String s){
        return new DoubleValue(Double.parseDouble(s));
    }
}
