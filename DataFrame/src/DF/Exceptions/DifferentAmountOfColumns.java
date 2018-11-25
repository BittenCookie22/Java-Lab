package DF.Exceptions;

public class DifferentAmountOfColumns extends Exception {
//    int DFkol1size;
//    int DFkol2size;

    public DifferentAmountOfColumns(int DFkol1size,int DFkol2size){
        super("Rozne ilosci kolumn  - nie mozna wykonac operacji. Ilosc kolumn w DF: "+DFkol1size+" "+" ilosc kolumn dodawanych: "+DFkol2size);
//        this.DFkol1size=DFkol1size;
//        this.DFkol2size=DFkol2size;
    }
}
