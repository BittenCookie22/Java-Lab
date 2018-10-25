package DF;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

public class DataFrame {
    Kolumna[] kolumny;
    int ilosc_wierszy;
    String[] lista_nazw;
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
            if (ilosc_wierszy != ilosc_wierszy) {
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

    // public DataFrame(String path,String [] tablica_typow, boolean header){//jesli header==true oznacz to że pierwsza linia w pliku to nagłówek. Jeśli header==false to znaczy że należy podać kolejny argument z nazwami kolumn. Domyślnie header==true

    //}

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

    //------------ zwrocWiersz - zwraca wiersz o indeksie i ------------------------------
    public Value[] zwrocWiersz(int i) {
        Value[] wiersz = new Value[kolumny.length];
        int j = 0;
        for (Kolumna k : kolumny)
            wiersz[j++] = k.zwrocObiekt(i);

        return wiersz;
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

    public DataFrame(String path, Class<? extends Value>[] typy_kolumn) throws IOException{
        this(path,typy_kolumn,null); //nazwy kolumn są zawarte w 1szej linii pliku

    }
    public DataFrame(String path, Class<? extends Value>[] typy_kolumn, String[]nazwy_kolumn) throws IOException { // header==false
        this(typy_kolumn.length);
        boolean header = nazwy_kolumn==null;
        for(int i=0;i<typy_kolumn.length;i++)
            kolumny[i]=new Kolumna(header? "" : nazwy_kolumn[i],typy_kolumn[i]);

        readingFromFileFunction(path,header);

    }

    public void addRecord(Value... values) {
        if(values.length!=kolumny.length)
            throw new ArrayIndexOutOfBoundsException();
        for(int i=0;i<kolumny.length;i++)
            if(!kolumny[i].typ.isInstance(values[i]))
                throw new IndexOutOfBoundsException();

        int i=0;
        ilosc_wierszy++;
        for(Kolumna k:kolumny)
            k.dodaj(values[i++]);

    }

    }








