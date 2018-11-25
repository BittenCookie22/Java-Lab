package DF.Values;

import DF.Exceptions.IncoherentTypeException;

public abstract class Value implements Cloneable{
    public abstract Object getValue();

    public  String toString(){
        return getValue().toString();
    }

    public abstract Value add(Value val) throws IncoherentTypeException;
    public abstract Value sub(Value val)throws IncoherentTypeException;
    public abstract Value mul(Value val)throws IncoherentTypeException;
    public abstract Value div(Value val)throws IncoherentTypeException;
    public abstract Value pow(Value val)throws IncoherentTypeException;

    public abstract boolean eq(Value val)throws IncoherentTypeException;
    public abstract boolean lte(Value val)throws IncoherentTypeException;
    public abstract boolean gte(Value val)throws IncoherentTypeException;
    public abstract boolean neq(Value val)throws IncoherentTypeException;
    public abstract boolean equals(Object other);
    public abstract int hashCode();
    public abstract Value create(String s); // tworzy konkretny obiekt ze stringa.

    public Value clone() throws CloneNotSupportedException{
        return (Value)super.clone();
    }
}
