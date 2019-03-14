package DF;

import DF.Values.DoubleValue;
import DF.Values.FloatValue;
import DF.Values.IntegerValue;
import DF.Values.Value;

public  class isNumeric {
     Value wartosc;

    public isNumeric(Value val){
        this.wartosc=val;
    }

    public static boolean isThisValueNumeric(Value val){
        if(val instanceof IntegerValue || val instanceof DoubleValue || val instanceof FloatValue){
            return true;
        }else{return false;}
    }
}
