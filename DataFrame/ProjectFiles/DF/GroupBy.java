package DF;

import DF.Exceptions.IncoherentTypeException;
import DF.Exceptions.ZeroLengthException;

public interface GroupBy  {

    default DataFrame max() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplyMax());}
    default DataFrame min() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplyMin());}
    default DataFrame mean() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplyMean());}
    default DataFrame std() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplyStd());}
    default DataFrame sum() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplySum());}
    default DataFrame var() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplyVar());}

    DataFrame apply(Applyable funkcja) throws IncoherentTypeException, ZeroLengthException;
}
