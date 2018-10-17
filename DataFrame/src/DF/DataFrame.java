package DF;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DataFrame {
    Kolumna[] kolumny;
    int ilosc_wierszy;
    String [] lista_nazw;
    TmpTypDanych [] lista_typow;

//-----------------Konstruktory DataFrame -----------------------------

    public void konstruktorZwyczajny(String[] lista_nazw, TmpTypDanych[] lista_typow) {
        kolumny = new Kolumna[lista_typow.length];
        for (int i = 0; i < lista_typow.length; i++) {
            kolumny[i] = new Kolumna(lista_nazw[i], lista_typow[i]);
        }
        ilosc_wierszy = 0; // na początku brak danych == brak wierszy
    }

    public  DataFrame(String[] lista_nazw, TmpTypDanych[] lista_typow){
        this.lista_typow=lista_typow;
        this.lista_nazw=lista_nazw;

        konstruktorZwyczajny(lista_nazw,lista_typow);
    }

    public DataFrame(String[] lista_nazw, String[] lista_typow){
        TmpTypDanych[] typy = new TmpTypDanych[lista_typow.length];
        for(int i=0;i<lista_typow.length;i++){
            typy[i]=TmpTypDanych.zwrocTypDanej(lista_typow[i]);
        }
        konstruktorZwyczajny(lista_nazw,typy);

    }


    public DataFrame(Kolumna[] kolumny) {
        this.kolumny = kolumny;
        ilosc_wierszy = kolumny[0].dane.size(); // liczba danych w dowolnej kolumnie, z zał mają mieć taką samą długość
        for (Kolumna i : kolumny) {
            if (ilosc_wierszy != i.dane.size()) {
                throw new RuntimeException("Blad w dlugosci kolumn,kolumny nie sa rownej dlugosci!");
            }
        }
    }

// -----------------------dodajElement gdzie element to cały wiersz z danymi--------------------

    public void dodajElement(Object[] elementy){
        if(elementy.length!=kolumny.length){
            throw new RuntimeException("blad");
        }
        int i=0;
        ilosc_wierszy++;
        for (Kolumna kol:this.kolumny){
            kol.dodaj(elementy[i++]);
        }
    }

//------------ zwrocWiersz - zwraca wiersz o indeksie i ------------------------------
    public Object[] zwrocWiersz(int i){
        Object[] wiersz=new Object[kolumny.length];
        int j=0;
        for(Kolumna k:kolumny)
            wiersz[j++]=k.zwrocObiekt(i);

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

    public DataFrame iloc(int from,int to){ //  zwraca nową DataFrame z wierszami z podanego zakresu
        if (from<0 || from >= ilosc_wierszy){
            throw new IndexOutOfBoundsException("Nie ma wiersza o numerze" + from);
        }

        if(to<0 || to>=ilosc_wierszy){
            throw new IndexOutOfBoundsException("Nie ma wiersza o numerze" + to);
        }

        if(to<from){
            throw new IndexOutOfBoundsException("Niepoprawny zakres");
        }

        String[] typy = new String [kolumny.length]; //tablica na typy w nowej DF
        String[] nazwy = new String [kolumny.length]; //tablica na nazwy w nowej DF

        for (int i=0;i<kolumny.length;i++){ // wypełnianie tablic na podstawie starej DF
            typy[i]= kolumny[i].typ.nazwa_typu;
            nazwy[i]=new String (kolumny[i].nazwa);
        }

        DataFrame nowa_DF = new DataFrame(nazwy,typy); //nowa DF
        Object[] nowe_wiersze = new Object[kolumny.length];

        for (int i=from;i<=to;i++){
            for (Kolumna k:this.kolumny){   // wypełnienie nowych_wierszy danymi na podstawie wierszy ze starej DF
                nowe_wiersze[i]=k.dane.get(i);
            }
            nowa_DF.dodajElement(nowe_wiersze);
            }

        return nowa_DF;
    }

    public DataFrame iloc(int i){ //zwraca wiersz o podanym indeksie (jako nową DataFrame)
        return iloc(i,i);
    }

    public DataFrame get(String[] cols,boolean copy){ // zwraca nową DataFrame z kolumnami podanymi jako parametry.W zależności od wartości parametru copy albo tworzona jest głęboka kopia, albo płytka.
        Kolumna[] nowe_kolumny = new Kolumna[cols.length];

        for(int i=0;i<cols.length;i++) {
            if (copy){ //gleboka
                nowe_kolumny[i] = new Kolumna(get(cols[i]));}
            else {//plytka
                nowe_kolumny[i] = get(cols[i]);}
        }
        DataFrame nowa_DF = new DataFrame(nowe_kolumny);
        return nowa_DF;
    }
}
// głeboka -> towrzymy nowy obiekt z tą samą zawartości ale innym adresem w pamięci, zmiana w obj 1 nie powoduje zmiany w obj 2
//płytka -> nowy obiekt ale wskazujacy na ten sam adres w pamięci, zmiana w obj1 powoduje zmiane w obj2




