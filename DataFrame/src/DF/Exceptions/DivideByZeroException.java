package DF.Exceptions;

import DF.Values.Value;

public class DivideByZeroException extends Exception{ // komunikuje w ktorej kolumnie i ktorym wierszu jest 0 przez ktore mialo byc dzielenie
    String colNamee;                                   // lub przy dzieleniu przez value ze value ma wartosc 0
    int rowNumber;

    public DivideByZeroException(String colNamee,int rowNumber){
        super("Dzielenie przez 0 jest niedozwolone!Proba podzielenia przez 0 ktore znajduje sie w kolumnie o nazwie "+colNamee+" "+" w wierszu numer "+rowNumber);
    }

    public DivideByZeroException(Value val){
        super("proba dzielenie przez 0! Zerem jest "+val.getValue()+" "+" klasa zera: "+val.getClass());
    }
}
