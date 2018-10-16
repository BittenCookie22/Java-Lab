package DF;

import java.util.NoSuchElementException;

public class SparseDataFrame extends DataFrame {
    final class COOValue {  // przechowuje indeksy niezerowych elementów i ich wartość
        private final Object wartosc;
        private final int indeks;

         COOValue(Object wartosc, int indeks) {
            this.wartosc = wartosc;
            this.indeks = indeks;
        }

        public int zwrocIndeks() {
            return indeks;
        }

        public Object zwrocWartosc(){
            return wartosc;
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

    public SparseDataFrame(String[] lista_nazw, TmpTypDanych[] lista_typow, Object []hide) {
        super(lista_nazw, lista_typow);
        for (int i=0;i<lista_typow.length;i++){
            kolumny[i]=new SparseKolumna(lista_nazw[i],TmpTypDanych.zwrocTypDanej(lista_typow[i].nazwa_typu),hide[i]);
        }
    }
    public SparseDataFrame(SparseKolumna[] kolumny) {
        super(kolumny);
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
        String []nazwy = new String[SPD.kolumny.length];
        TmpTypDanych [] typy = new TmpTypDanych[SPD.kolumny.length];

        nazwy = SPD.lista_nazw;
        typy = SPD.lista_typow;

        DataFrame df =  new DataFrame(nazwy,typy);

        for(int i=0;i<size();i++)
        {
            df.dodajElement(zwrocWiersz(i));
        }
        return df;
    }
// -------------------------Implementacja metod z DataFrame------------------------

    @Override
    public int size() {
        return ilosc_wierszy;
    }

    @Override
    public SparseKolumna get(String colname) {//zwracającą kolumnę o podanej nazwie
        for (Kolumna i : this.kolumny) {
            if (i.nazwa.equals(colname)) {
                return (SparseKolumna)i;
            }
        }
        throw new NoSuchElementException("Nie ma takiej kolumny" + colname + "w tej Ramce Danych");

    }

    @Override
    public SparseDataFrame get(String[] cols, boolean copy) {
        SparseKolumna[] kolumny = new SparseKolumna[cols.length];

        for(int i=0;i<cols.length;i++){
            if(copy){
                Kolumna tmp = new Kolumna(get(cols[i]));
                kolumny[i]=(SparseKolumna)tmp;}
            else{
                kolumny[i]= get(cols[i]);}
        }
        return new SparseDataFrame(kolumny);
    }
    @Override
    public SparseDataFrame iloc(int i) {
        return iloc(i,i);
    }

    @Override
    public SparseDataFrame iloc(int from, int to) {
        if (from<0 || from >= ilosc_wierszy){
            throw new IndexOutOfBoundsException("Nie ma wiersza o numerze" + from);
        }

        if(to<0 || to>=ilosc_wierszy){
            throw new IndexOutOfBoundsException("Nie ma wiersza o numerze" + to);
        }

        if(to<from){
            throw new IndexOutOfBoundsException("Niepoprawny zakres");
        }
        String[] nazwy = new String[kolumny.length];
        TmpTypDanych[] typy = new TmpTypDanych[kolumny.length];
        Object[] hide= new Object[kolumny.length];

        nazwy = this.lista_nazw;
        typy = this.lista_typow;

        for(int i=0;i<kolumny.length;i++) {
            hide[i] = ((SparseKolumna)kolumny[i]).hide;
        }

        SparseDataFrame df=new SparseDataFrame(nazwy,typy,hide);
        for(int i=from;i<=to;i++){
            df.dodajElement(zwrocWiersz(i));
        }

        return df;
    }
    //---------------------------------------------------------------------------------------------------------------------------------------
    }


