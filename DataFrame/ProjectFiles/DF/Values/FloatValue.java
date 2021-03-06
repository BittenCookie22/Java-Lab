package DF.Values;

public class FloatValue extends NumericValue  implements Comparable<FloatValue>{
    private float value;

    public FloatValue(float value){
        this.value=value;
    }

    public FloatValue(){};

    @Override
    public Float getValue() {
        return value;
    }

//    @Override
//    public String toString() {
//        return "FloatValue{" + "value=" + value + '}';
//    }

    @Override
    public FloatValue add(Value val) throws UnsupportedOperationException {
        if(val instanceof NumericValue){
            return new FloatValue((float)((NumericValue)val).getValue().floatValue()+this.value);
        }
        else{
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public FloatValue sub(Value val) throws UnsupportedOperationException {
        if(val instanceof NumericValue){
            return new FloatValue((float)(this.value-((NumericValue)val).getValue().floatValue()));
        }
        else{
            throw new UnsupportedOperationException();

        }
    }

    @Override
    public  boolean eq(Value val){
        if ((Math.abs(this.value-((NumericValue)val).getValue().floatValue())< 0.000001)){ // porównywanie liczb zmiennoprzecinkowych
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public  FloatValue mul(Value val){
        if(val instanceof NumericValue){
            return new FloatValue((float)(this.value*((NumericValue)val).getValue().floatValue()));
        }
        else{
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public  FloatValue div(Value val){
        if(val instanceof NumericValue ){
            //  if( ((IntegerValue)val).value)!=0){
            return new FloatValue((float)(this.value/((NumericValue)val).getValue().floatValue()));}

        else{
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public  FloatValue pow(Value val){
        FloatValue tmp = new FloatValue(0);
        if (val instanceof NumericValue){
            tmp=new FloatValue ((float)(Math.pow(this.value,((NumericValue)val).getValue().floatValue())));
        }
        return tmp;
    }


    @Override
    public  boolean lte(Value val){
        if (this.value <= (((NumericValue)val).getValue().floatValue())){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public  boolean gte(Value val){
        if (this.value >= (((NumericValue)val).getValue().floatValue())){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public  boolean neq(Value val){
        if (!(Math.abs(this.value-((NumericValue)val).getValue().floatValue())< 0.000001)){ // porównywanie liczb zmiennoprzecinkowych
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public  boolean equals(Object other){
        if (other==null){return false;}
        if (other instanceof FloatValue){
            FloatValue otherFloat = (FloatValue)other;
            return this.value==otherFloat.value;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return getValue().hashCode()*21;
    }

    @Override
    public FloatValue create(String s){
        return new FloatValue(Float.parseFloat(s));
    }


    @Override
    public int compareTo(FloatValue obj){
        return Float.compare(value,obj.value);
    }
}
