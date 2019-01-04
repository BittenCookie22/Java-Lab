package DF;

import DF.Exceptions.DifferentAmountOfColumns;
import DF.Exceptions.IncoherentTypeException;
import DF.Exceptions.ZeroLengthException;
import DF.Values.Value;

public interface GroupBy  {

    default DataFrame max() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplyMax());}
    default DataFrame min() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplyMin());}
    default DataFrame mean() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplyMean());}
    default DataFrame std() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplyStd());}
    default DataFrame sum() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplySum());}
    default DataFrame var() throws IncoherentTypeException, ZeroLengthException { return apply(new ApplyVar());}

    DataFrame apply(Applyable funkcja) throws IncoherentTypeException, ZeroLengthException;

    static DataFrame getOutputDataFrame(Class<? extends Value>[] keyTypes, String[] keyNames, Class<? extends Value>[] dfTypes, String[] dfNames) {
        Class<? extends Value>[] fullTypes = new Class[keyTypes.length + dfTypes.length];

        System.arraycopy(keyTypes, 0, fullTypes, 0, keyTypes.length);
        System.arraycopy(dfTypes, 0, fullTypes, keyTypes.length, dfTypes.length);

        String[] fullNames = new String[keyNames.length + dfNames.length];

        System.arraycopy(keyNames, 0, fullNames, 0, keyNames.length);
        System.arraycopy(dfNames, 0, fullNames, keyNames.length, dfNames.length);

        return new DataFrame(fullNames, fullTypes);

    }

    static void addGroup(DataFrame output, Value[] keyValues, DataFrame group) throws  DifferentAmountOfColumns, IncoherentTypeException {

        Value[] rowValues = new Value[group.iloscKolumn() + keyValues.length];
        System.arraycopy(keyValues, 0, rowValues, 0, keyValues.length);

        for (int j = 0; j < group.size(); j++) {
            Value[] groupValues = group.zwrocWiersz(j);

            System.arraycopy(groupValues, 0, rowValues, keyValues.length, groupValues.length);
            output.dodajElement(rowValues);

        }
    }


}
