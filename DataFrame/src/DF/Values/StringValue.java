package DF.Values;

public class StringValue  extends Value implements Comparable<StringValue>{
    private String value;

    public StringValue(String value){
        this.value=value;
    }


    public StringValue(){};


    @Override
    public String getValue() {
        return value;
    }

//    @Override
//    public String toString() {
//        return this.value;
//    }

    @Override
    public StringValue add(Value value){
        if ( value instanceof  StringValue){
            return new StringValue(this.value+((StringValue)value).value);
        }
        else{throw new UnsupportedOperationException();}
    }

    @Override
    public StringValue sub(Value val){
        throw new UnsupportedOperationException();
    }

    @Override
    public StringValue mul(Value val){
        throw new UnsupportedOperationException();
    }

    @Override
    public StringValue div(Value val){
        throw new UnsupportedOperationException();
    }

    @Override
    public StringValue pow(Value val){
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean eq(Value value){
        if( value instanceof  StringValue){
            if (((StringValue)value).value.equals(this.value)){
                return true; }
        }
        return false;
    }

    @Override
    public boolean neq(Value value){
        if( value instanceof  StringValue){
            if (!(((StringValue)value).value.equals(this.value))){
                return true; }
        }
        return false;
    }

  //  public String[] sortowanieAlfabetyczne(String str1, String str2){
    //    String [] wyrazy = {str1,str2};
      //  Arrays.sort(wyrazy, Collator.getInstance(Locale.ENGLISH));
       // return wyrazy;
    //}

    @Override
    public boolean lte(Value val) {// sprawdzany porządek alfabetyczny czy this.value jest wczesniej niz val
        if(val instanceof StringValue){
            if((this.value.compareTo(((StringValue)val).value))>=0){
                return true;
            } else{return false;}
        }
         return false;
    }
    @Override
    public boolean gte(Value val) {// sprawdzany porządek alfabetyczny czy this.value jest później niz val
        if(val instanceof StringValue){
            if((this.value.compareTo(((StringValue)val).value))<=0){
                return true;
            } else{return false;}
        }
        return false;
    }

    @Override
    public  boolean equals(Object other){
        if (other==null){return false;}
        if (other instanceof StringValue){
            StringValue otherString = (StringValue)other;
            return this.value.equals(otherString.value);
        }
        return false;
    }

    @Override
    public int hashCode() {

        return getValue().hashCode()*37;
    }

    @Override
    public StringValue create(String s){
        return new StringValue(s);
    }

    @Override
    public int compareTo(StringValue obj) {
        return value.compareTo(obj.value);
    }
}
