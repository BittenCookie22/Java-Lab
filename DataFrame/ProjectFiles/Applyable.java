package DF;

import DF.Exceptions.IncoherentTypeException;
import DF.Exceptions.ZeroLengthException;

public interface Applyable {

    DataFrame apply(DataFrame df) throws ZeroLengthException,IncoherentTypeException;
}
