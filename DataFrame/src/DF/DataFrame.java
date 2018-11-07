package DF;

import DF.Values.Value;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DataFrame {
    Kolumna[] kolumny;
    int ilosc_wierszy;
    public String[] lista_nazw;
    Class<? extends Value>[] lista_typow;

//-----------------Konstruktory DataFrame ------------------------------

    public DataFrame() {
    }

    ;

    public void konstruktorZwyczajny(String[] lista_nazw, Class<? extends Value>[] lista_typow) {
        kolumny = new Kolumna[lista_typow.length];
        for (int i = 0; i < lista_typow.length; i++) {
            kolumny[i] = new Kolumna(lista_nazw[i], lista_typow[i]);
        }
        ilosc_wierszy = 0; // na początku brak danych == brak wierszy
    }

    public DataFrame(String[] nazwyKolumn, Class<? extends Value>[] typyKolumn) {
        this(nazwyKolumn.length);
        for (int i = 0; i < typyKolumn.length; i++)
            kolumny[i] = new Kolumna(nazwyKolumn[i], typyKolumn[i]);
    }

//    public DataFrame(String[] lista_nazw, Class<? extends Value>[] lista_typow) {
//        Value[] typy = new Value[lista_typow.length];
//        this.lista_nazw=lista_nazw;
//        for (int i = 0; i < lista_typow.length; i++) {
//            typy[i] = Value.zwrocTypDanej(lista_typow[i]);
//        }
//        this.lista_typow=typy;
//        konstruktorZwyczajny(lista_nazw, typy);
//
//    }


    public DataFrame(Kolumna[] kolumny) {
        this.kolumny = kolumny;
        ilosc_wierszy = kolumny[0].size(); // liczba danych w dowolnej kolumnie, z zał mają mieć taką samą długość
        for (Kolumna i : kolumny) {
            if (i.size() != ilosc_wierszy) {
                throw new RuntimeException("Blad w dlugosci kolumn,kolumny nie sa rownej dlugosci!");
            }
        }
        int tmp = 0;
        lista_nazw = new String[kolumny.length];
        lista_typow = new Class[kolumny.length];
        for (Kolumna k : kolumny) {
            this.lista_nazw[tmp] = k.nazwa;
            this.lista_typow[tmp] = k.typ;
            tmp++;

        }
    }

    protected DataFrame(int ilosc_kolumn) {
        this.kolumny = new Kolumna[ilosc_kolumn];
        this.ilosc_wierszy = 0;
        lista_nazw = new String[ilosc_kolumn];
        lista_typow = new Class[ilosc_kolumn];
    }



// -----------------------dodajElement gdzie element to cały wiersz z danymi--------------------

    public void dodajElement(Value[] elementy) {
        if (elementy.length != kolumny.length) {
            throw new RuntimeException("blad");
        }
        int i = 0;
        ilosc_wierszy++;
        for (Kolumna kol : this.kolumny) {
            kol.dodaj(elementy[i++]);
        }
    }

    //------------ zwrocWiersz - zwraca wiersz o indeksie i ------- + gettery-----------------------
    public Value[] zwrocWiersz(int i) {
        Value[] wiersz = new Value[kolumny.length];
        int j = 0;
        for (Kolumna k : kolumny)
            wiersz[j++] = k.zwrocObiekt(i);

        return wiersz;
    }

    public Class<? extends Value>[] zwroc_typy(){
        Class<? extends Value>[]tablica_typow =  ( Class<? extends Value>[])(new Class[kolumny.length]);

        for (int i =0;i<this.kolumny.length;i++){
            tablica_typow[i]=kolumny[i].typ;
        }
        return tablica_typow;
    }

    public String [] zwroc_nazwy(){
        String[]tablica_nazw =  new String[kolumny.length];

        for (int i =0;i<this.kolumny.length;i++){
            tablica_nazw[i]=kolumny[i].nazwa;
        }
        return tablica_nazw;
    }

    // -------------------funkcje zadane w lab1-------------------------
    public int size() {
        return ilosc_wierszy;
    }

    public Kolumna get(String colname) {//zwraca kolumnę o podanej nazwie
        for (Kolumna i : kolumny) {
            if (i.nazwa.equals(colname)) {
                return i;
            }
        }
        throw new NoSuchElementException("Nie ma takiej kolumny" + colname + "w tej Ramce Danych");

    }

    public DataFrame iloc(int from, int to) { //  zwraca nową DataFrame z wierszami z podanego zakresu
        if (from < 0 || from >= ilosc_wierszy) {
            throw new IndexOutOfBoundsException("Nie ma wiersza o numerze" + from);
        }

        if (to < 0 || to >= ilosc_wierszy) {
            throw new IndexOutOfBoundsException("Nie ma wiersza o numerze" + to);
        }

        if (to < from) {
            throw new IndexOutOfBoundsException("Niepoprawny zakres");
        }

        Class<? extends Value>[] typy = (Class<? extends Value>[]) (new Class[kolumny.length]); //tablica na typy w nowej DF
        String[] nazwy = new String[kolumny.length]; //tablica na nazwy w nowej DF

        for (int i = 0; i < kolumny.length; i++) { // wypełnianie tablic na podstawie starej DF
            typy[i] = kolumny[i].typ;
            nazwy[i] = kolumny[i].nazwa;
        }

        DataFrame nowa_DF = new DataFrame(nazwy, typy); //nowa DF
        Value[] nowe_wiersze = new Value[kolumny.length];


        for (int i = from; i <= to; i++) {
            for (int j = 0; j < kolumny.length; j++) {   // wypełnienie nowych_wierszy danymi na podstawie wierszy ze starej DF
                nowe_wiersze[j] = this.kolumny[j].dane.get(i);
            }
            nowa_DF.dodajElement(nowe_wiersze);
        }

        return nowa_DF;
    }

    public DataFrame iloc(int i) { //zwraca wiersz o podanym indeksie (jako nową DataFrame)
        return iloc(i, i);
    }

    public DataFrame get(String[] cols, boolean copy) { // zwraca nową DataFrame z kolumnami podanymi jako parametry.W zależności od wartości parametru copy albo tworzona jest głęboka kopia, albo płytka.
        Kolumna[] nowe_kolumny = new Kolumna[cols.length];

        for (int i = 0; i < cols.length; i++) {
            if (copy) { //gleboka
                nowe_kolumny[i] = new Kolumna(get(cols[i]));
            } else {//plytka
                nowe_kolumny[i] = get(cols[i]);
            }
        }
        DataFrame nowa_DF = new DataFrame(nowe_kolumny);
        return nowa_DF;
    }
// głeboka -> towrzymy nowy obiekt z tą samą zawartości ale innym adresem w pamięci, zmiana w obj 1 nie powoduje zmiany w obj 2
//płytka -> nowy obiekt ale wskazujacy na ten sam adres w pamięci, zmiana w obj1 powoduje zmiane w obj2


//---------------czytanie z pliku-----------------------

    public void readingFromFileFunction(String path, boolean header) throws IOException {
        // Open the file
        FileInputStream fstream = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String[] strLine;


        if (header) // to znaczy że pierwsza linia pliku ma w sobie nazwy kolumn
        {
            strLine = br.readLine().split(",");
            for (int i = 0; i < kolumny.length; i++) {
                kolumny[i].nazwa = strLine[i];
            }
        }
        String tmp;
        Value[] wartosciZpliku = new Value[kolumny.length];
        while (((tmp = br.readLine()) != null)) {
            strLine = tmp.split(",");
            int m = 0;
            for (String wartoscWpostaciStringa : strLine) {
                //TmpTypDanych tmpTyp =
                try {
                    wartosciZpliku[m] = (kolumny[m].typ).newInstance().create(wartoscWpostaciStringa);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }


                //wartoscWpostaciStringa); //konwersja do konkretnego typu danych
                m++;
            }
            dodajElement(wartosciZpliku);
        }


        // else{ // trzeba zczytać typy kolumn ręcznie

        //}

//Close the input stream
        br.close();
    }

    public DataFrame(String path, Class<? extends Value>[] typy_kolumn) throws IOException {
        this(path, typy_kolumn, null); //nazwy kolumn są zawarte w 1szej linii pliku

    }

    public DataFrame(String path, Class<? extends Value>[] typy_kolumn, String[] nazwy_kolumn) throws IOException { // header==false
        this(typy_kolumn.length);
        boolean header = nazwy_kolumn == null;
        for (int i = 0; i < typy_kolumn.length; i++)
            kolumny[i] = new Kolumna(header ? "" : nazwy_kolumn[i], typy_kolumn[i]);

        readingFromFileFunction(path, header);

    }

    public void addRecord(Value... values) {
        if (values.length != kolumny.length) throw new ArrayIndexOutOfBoundsException();
        for (int i = 0; i < kolumny.length; i++)
            if (!kolumny[i].typ.isInstance(values[i])) throw new IndexOutOfBoundsException();

        int i = 0;
        ilosc_wierszy++;
        for (Kolumna k : kolumny)
            k.dodaj(values[i++]);

    }

    /// -----------group by -----------

    public TreeMap<Value, DataFrame> groupBy(String colname){
        Kolumna kol  =get(colname);
        TreeMap<Value, DataFrame> output = new TreeMap<>();

        for (int i = 0; i < ilosc_wierszy; i++) {
            Value key=kol.zwrocObiekt(i);
            Value[] row = zwrocWiersz(i);
            DataFrame ll = output.get(key);

            if(ll!= null)
                ll.addRecord(row);

            else{
                ll  =new SparseDataFrame(zwroc_nazwy(),zwroc_typy(),row);
                ll.addRecord(row);
                output.put(key,ll);
            }
        }

        return output;
    }

    public Grupator groupBy(String[] colname){
        TreeMap<Value,DataFrame> initial =  groupBy(colname[0]);

        LinkedList<DataFrame> lista;

        if(colname.length ==1)
            lista = new LinkedList<>(initial.values());
        else
            lista= new LinkedList<>();

        LinkedList<DataFrame> temp= new LinkedList<>(initial.values());

        for (int i = 1; i < colname.length; i++) {
            lista.clear();
            for(DataFrame df:temp)
                lista.addAll(df.groupBy(colname[i]).values());
            temp.clear();
            temp.addAll(lista);
        }

        return new Grupator(lista,colname);

    }




    class Grupator implements GroupBy{
        private LinkedList <DataFrame> lista;
        private String[]  kluczowe_nazwy_kolumn;
       private Kolumna[] kluczowe_kolumny;
       private String[] nazwy_kolumn_z_wartosciami;


        public Grupator (Collection<DataFrame> lista,String[]kluczowe_nazwy_kolumn){
            this.lista= new LinkedList<>(lista);
            this.kluczowe_nazwy_kolumn=kluczowe_nazwy_kolumn;
            this.kluczowe_kolumny=new Kolumna[kluczowe_nazwy_kolumn.length];
            String[] allNames = this.lista.getFirst().zwroc_nazwy();
            nazwy_kolumn_z_wartosciami= new String[allNames.length-kluczowe_nazwy_kolumn.length];

            int k=0;
            outer:
            for (String colname : allNames){
                for(String kluczowa_nazwa:kluczowe_nazwy_kolumn){
                    if(kluczowa_nazwa.equals(colname)){
                        continue outer;
                    }
                }

                nazwy_kolumn_z_wartosciami[k]=colname;
                k++;
            }

            for (int i=0;i<kluczowe_nazwy_kolumn.length;i++){
                Kolumna tmp = this.lista.getFirst().get(kluczowe_nazwy_kolumn[i]);
                kluczowe_kolumny[i]=new Kolumna(tmp.nazwa,tmp.typ);
            }

            for (DataFrame df : this.lista) {
                for (int j = 0; j < kluczowe_kolumny.length; j++) {
                       this.kluczowe_kolumny[j].dodaj(df.get(kluczowe_nazwy_kolumn[j]).zwrocObiekt(0));
                }
            }
        }


        @Override
        public DataFrame apply (Applyable funkcja){
            DataFrame output = null;
            for (int grupa = 0; grupa < this.lista.size(); grupa++) {
                DataFrame df = lista.get(grupa);
                 DataFrame tmp = funkcja.apply(df.get(nazwy_kolumn_z_wartosciami,false));


                     //inicjalizacja DF output tak żeby miał wszystkie nazwy klumn łacznie z kluczowymi i ich typy
                     if(output==null){
                         String []temp_names=tmp.zwroc_nazwy();
                         String[] output_colnames= new String[temp_names.length+kluczowe_nazwy_kolumn.length];

                         Class<? extends Value>[] temp_types = tmp.zwroc_typy();
                         Class<? extends Value>[] output_types=new Class[temp_names.length+kluczowe_nazwy_kolumn.length];

                         for (int k = 0; k < output_colnames.length ; k++) {
                             output_colnames[k] =( k<kluczowe_nazwy_kolumn.length)? kluczowe_nazwy_kolumn[k]:
                                                                    temp_names[k-kluczowe_nazwy_kolumn.length];


                             output_types[k] =( k<kluczowe_nazwy_kolumn.length)? kluczowe_kolumny[k].typ:
                                     temp_types[k-kluczowe_nazwy_kolumn.length];

                         }
                         output= new DataFrame(output_colnames,output_types);
                     }

                     //perzisanie zawarotosci z temp jesli cos tam jest
                     if(tmp.size()>0){
                         Value output_row[] = new Value[output.iloscKolumn()];
                         for (int i = 0; i <kluczowe_kolumny.length; i++) { //wpisanie identyfikatora wiersza
                             output_row[i]=kluczowe_kolumny[i].zwrocObiekt(grupa);
                         }
                         for (int j = 0; j < tmp.size();j++) {
                             Value[] tmp_row =tmp.zwrocWiersz(j);
                             for (int k = 0; k < tmp.iloscKolumn(); k++) {
                                 output_row[k + kluczowe_kolumny.length] = tmp_row[k];
                             }

                             output.dodajElement(output_row);
                         }

                     }

                     }

            return output;
             }

        }

    public int iloscKolumn() {
        return kolumny.length;
    }


    @Override
    public String toString() {
        StringBuilder s=new StringBuilder();
        String[] str;
        for(Kolumna k:kolumny){
            str=k.typ.getTypeName().split("\\.");
            s.append("|").append(k.nazwa).append(":").append(str[str.length-1]);
        }
        s.append("|\n");
        for(int i=0;i<ilosc_wierszy;i++){
            for(Kolumna k:kolumny)
                s.append("|").append(k.zwrocObiekt(i).toString());
            s.append("|\n");
        }
        return s.toString();
    }

}









