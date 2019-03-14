package DF.Exceptions;

public class ZeroLengthException extends Exception {

    public ZeroLengthException(){
        super("Przetwarzany element jest w jakims sensie zerowy, a tak nie moze byc");
    }

    public ZeroLengthException(String mes){
        super(mes);
    }
}
