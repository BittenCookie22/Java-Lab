package DF.Values;

import DF.Exceptions.IncoherentTypeException;

public abstract class NumericValue extends Value {
    @Override
    abstract public Number getValue();

    public NumericValue() {
    }

    ;

    @Override
    public boolean neq(Value val) throws IncoherentTypeException {
        if (!eq(val)) {
            return true;
        } else {
            return false;
        }
    }
}


