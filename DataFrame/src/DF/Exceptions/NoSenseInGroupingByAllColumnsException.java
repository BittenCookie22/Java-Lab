package DF.Exceptions;

public class NoSenseInGroupingByAllColumnsException extends Exception{
    int dfColumns;
    int idColumns;

    public NoSenseInGroupingByAllColumnsException(int dfColumns,int idColumns){
        super("Nie można wykonać operacji statystycznych na wszystkich kolumnach w Data Frame jednocześnie!");
        this.dfColumns=dfColumns;
        this.idColumns=idColumns;
    }

    public NoSenseInGroupingByAllColumnsException(){
        super("Nie można wykonać operacji statystycznych na wszystkich kolumnach w Data Frame jednocześnie!");
    }
}
