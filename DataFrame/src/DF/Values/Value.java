package DF.Values;

public abstract class Value implements Cloneable{
    public abstract Object getValue();
    public abstract String toString();
    public abstract Value add(Value val);
    public abstract Value sub(Value val);
    public abstract Value mul(Value val);
    public abstract Value div(Value val);
    public abstract Value pow(Value val);
    public abstract boolean eq(Value val);
    public abstract boolean lte(Value val);
    public abstract boolean gte(Value val);
    public abstract boolean neq(Value val);
    public abstract boolean equals(Object other);
    public abstract int hashCode();
    public abstract Value create(String s); // tworzy konkretny obiekt ze stringa.

    public Value clone() throws CloneNotSupportedException{
        return (Value)super.clone();
    }
}
