package DF;

import java.util.NoSuchElementException;

public class SparseDataFrame extends DataFrame {
    class COOValue {  // przechowuje indeksy niezerowych elementów i ich wartość
        private final Object wartosc;
        private final int indeks;

         COOValue(Object wartosc, int indeks) {
            this.wartosc = wartosc;
            this.indeks = indeks;
        }
    }

//--------------------------------------------------

    public class SparseKolumna extends Kolumna {
        Object hide;
        int ilosc_wierszy;
        public SparseKolumna(String nazwa,TmpTypDanych typ, Object hide){
            super(nazwa,typ);
            this.hide=hide;
            ilosc_wierszy=0;
        }

        public SparseKolumna(SparseKolumna sprsKol){
            super(sprsKol);
            this.hide=sprsKol.hide;
            this.ilosc_wierszy=sprsKol.ilosc_wierszy;
        }

        @Override
        public void dodaj(Object element){
            boolean check = hide.equals(element);
            if (check==false){
                super.dodaj(new COOValue(element,ilosc_wierszy));
            }
            ilosc_wierszy++;
        }
    }
    //--------------------------------------------------

    public SparseDataFrame(String[] lista_nazw, String[] lista_typow, Object []hide) {
        super(lista_nazw, lista_typow);
        for (int i=0;i<lista_typow.length;i++){
            kolumny[i]=new SparseKolumna(lista_nazw[i],TmpTypDanych.zwrocTypDanej(lista_typow[i]),hide[i]);
        }
    }

    public SparseDataFrame(DataFrame DF, Object[] hide) {
        super(DF.lista_nazw, DF.lista_typow);
        String []lista_kolumn = DF.lista_nazw;
        TmpTypDanych [] lista_typow = DF.lista_typow;
            for (int i = 0; i < DF.lista_typow.length; i++) {
                kolumny[i]=new SparseKolumna(lista_kolumn[i],lista_typow[i],hide[i]);
            }

        Object [] tablica_typow = new Object[kolumny.length];
            for (int i=0;i<DF.ilosc_wierszy;i++){
                int tmp=0;
                for (Kolumna kol:kolumny){
                    tablica_typow[tmp++]=kol.zwrocObiekt(i);
                }
                dodajElement(tablica_typow);

            }
    }

    public DataFrame toDense(SparseDataFrame SPD){ //zwraca DataFrame (konwertuje SparseDataFrame do DataFrame)
        
    }
}