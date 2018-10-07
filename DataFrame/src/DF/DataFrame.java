package DF;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class DataFrame {
    Kolumna[] kolumny;
    int ilosc_wierszy;

    public DataFrame(String[] lista_nazw, String[] lista_typow) {
        kolumny = new Kolumna[lista_typow.length];
        for (int i = 0; i < lista_typow.length; i++) {
            kolumny[i] = new Kolumna(lista_nazw[i], lista_typow[i]);
        }
        ilosc_wierszy = 0; // na początku brak danych == brak wierszy
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


    public int size() {
        //Kolumna tmp = kolumny[0];
        //return tmp.dane.size();
        return ilosc_wierszy; // --> szybszy sposób
    }

    public Kolumna get(String colname) {//zwracającą kolumnę o podanej nazwie
        //int indeks=0;
        //int licznik = 0;
        //for (Kolumna x: kolumny){
        //   if (x.nazwa == colname){
        //      indeks = licznik;
        // }
        // else{licznik++;}
        // }
        //Kolumna tmp  = kolumny[indeks];
        //return tmp;
        for (Kolumna i : kolumny) {
            if (colname == i.nazwa) {  // --> szybszy sposób
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
            typy[i]= kolumny[i].typ;  // new String?
            nazwy[i]=kolumny[i].nazwa;
        }

        DataFrame nowa_DF = new DataFrame(nazwy,typy); //nowa DF
        Object[] nowe_wiersze = new Object[kolumny.length*(to-from+1)];  // inny zakres niz kolumny.lenght

        for (int i=from;i<to;i++){
            for (Kolumna k:kolumny){   // wypełnienie nowych_wierszy danymi na podstawie wierszy ze starej DF
                nowe_wiersze[i]=k.dane.get(i);
            }
        }

        for (int j=0;j<nowe_wiersze.length;j++){
            for (Kolumna m:nowa_DF.kolumny){ //wstawanienie danych z nowych wierszy do pola Kolumny w nowej DF
                m.dane.add(nowe_wiersze[j]);
            }
        }
        return nowa_DF;
    }

    public DataFrame iloc(int i){ //zwraca wiersz o podanym indeksie (jako nową DataFrame)
        return iloc(i,i);
    }

}




