package DF;

import DF.Values.Value;

public abstract class NumericValue extends Value {
    @Override
    abstract public Number getValue();

    public NumericValue(){};

    @Override
    public boolean neq (Value val){
        if(!eq(val)){return true;}
        else{return false;}
    }

}
