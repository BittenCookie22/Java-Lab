package DF.Exceptions;

public class ValueParseException extends Exception{
    String mes;
    int row;
    String colName;

    public  ValueParseException(int row,String colName, String mes){
        super(mes);
        this.row=row;
        this.colName=colName;
    }
    public ValueParseException(int row,String colName){
        super("Błąd parsowania w wierszu "+row+" w kolumnie o nazwie: "+colName);
        this.row=row;
        this.colName=colName;
    }


}
