package DF.Exceptions;

public class IncoherentTypeException extends Exception{


    String colName;
    int rowNumber;

    public String getColName() {
        return colName;
    }

    public int getRowNumber() {
        return rowNumber;
    }


    public IncoherentTypeException(int rowNumber,String name,String mes){
        super("Niezgodne typy! Blad w kolumnie o nazwie "+name+"w wierszu numer: "+rowNumber+mes);
        colName=name;
        this.rowNumber=rowNumber;
    }
    public IncoherentTypeException(int rowNumber,String name){
        super("Niezgodne typy! Blad w kolumnie o nazwie "+name+"w wierszu numer: "+rowNumber);
        colName=name;
        this.rowNumber=rowNumber;
    }



    public IncoherentTypeException(String mes){
        super(mes);
    }



}
