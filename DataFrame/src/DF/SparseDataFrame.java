package DF;

import java.io.IOException;
import java.util.NoSuchElementException;

public class SparseDataFrame extends DataFrame {

// ----------------COOValue - (immutable) przechowuje indeksy niezerowych elementów i ich wartość------------------
    final class COOValue {
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

//----------------SparseKolumna - specjalna kolumna dla SprsDF, przechowuje info o tym jaki obiekt ukrywać (hide) i ilość wierszy w kolumnie---------------------------------------------------------------------------

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
            if (check==false){ //jesli obiekt nie jest hide to dodajemy do kolumny
                dane.add(new COOValue(element,ilosc_wierszy));
            }
            ilosc_wierszy++;
        }
    }
    //--------------Konstruktory SparseDataFrame------------------------------------

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
                for (Kolumna kol:DF.kolumny){
                    tablica_typow[tmp++]=kol.zwrocObiekt(i);
                }
                dodajElement(tablica_typow);

            }
    }



//--------------------ToDense - zwraca DataFrame (konwertuje SparseDataFrame do DataFrame)------------------------------------------------------------

    public DataFrame toDense(SparseDataFrame SPD){
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
// ------------------------Implementacja metod z DataFrame------------------------

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

    //---czyteanie z pliku KONSTRUKTORY---------
    public SparseDataFrame(String path,String[] typy_kolumn,Object[]hide) throws IOException {
        this(path,typy_kolumn,null,hide); //nazwy kolumn są zawarte w 1szej linii pliku

    }

    public SparseDataFrame(String path,String[] typy_kolumn, String[]nazwy_kolumn,Object[]hide) throws IOException { // header==false
        super(typy_kolumn.length);
        boolean header = nazwy_kolumn==null;
        for (int i =0; i<typy_kolumn.length;i++){
            if (!header){ //jesli podano nazwy kolumn w arg, a brak ich w 1szej linii pilku
                kolumny[i]=new SparseKolumna(nazwy_kolumn[i],TmpTypDanych.zwrocTypDanej(typy_kolumn[i]),hide[i]); //to trzeba ręcznie wczytać
            }
            else{
                continue;
            }
            readingFromFileFunction(path,header);
        }

    }
    //---------------------------------------------------------------------------------------------------------------------------------------
    }


