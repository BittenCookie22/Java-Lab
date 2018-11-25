package DF.Exceptions;

public class DifferentSizeOfColumnsExcepiton extends Exception{
    int kol1size;
    int kol2size;

    public DifferentSizeOfColumnsExcepiton(int kol1size,int kol2size){
        super("Rozne dlugosci kolumn - nie mozna wykonac operacji. Dlugosc kolumny 1: "+kol1size+" "+" dlugosc kolumny 2: "+kol2size);
        this.kol1size=kol1size;
        this.kol2size=kol2size;
    }


}
