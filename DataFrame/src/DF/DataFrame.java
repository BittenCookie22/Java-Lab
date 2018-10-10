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
            if (i.nazwa.equals(colname)) {  // --> szybszy sposób
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
            typy[i]= new String (kolumny[i].typ);
            nazwy[i]=new String (kolumny[i].nazwa);
        }

        DataFrame nowa_DF = new DataFrame(nazwy,typy); //nowa DF
        Object[] nowe_wiersze = new Object[kolumny.length];

        for (int i=from;i<=to;i++){
            for (Kolumna k:this.kolumny){   // wypełnienie nowych_wierszy danymi na podstawie wierszy ze starej DF
                nowe_wiersze[i]=k.dane.get(i);
            }
            for (int j=0;j<nowe_wiersze.length;j++){  //wstawanienie danych z nowych wierszy do pola Kolumny w nowej DF
                    nowa_DF.kolumny[j].dane.add(nowe_wiersze[j]);
            }
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




