package DF;

import java.util.ArrayList;

public class DataFrame {
    int liczba_nazw;
    int liczba_typow;
    String  [] tablica_nazw = new String[liczba_nazw];
    String  [] tabica_typow = new String[liczba_typow];

    ArrayList <Kolumna> kolumny;

    public void DataFrame (String[] lista_nazw, String[]lista_typow){
        liczba_nazw=lista_nazw.length;
        liczba_typow=lista_typow.length;
        tablica_nazw=lista_nazw;
        tabica_typow=lista_typow;

        // towrzy i dodaje kolejne kolumny do df
        for (int i=0;i<tablica_nazw.length;i++){
            Kolumna kol = new Kolumna(tablica_nazw[i],tabica_typow[i]);
            kolumny.add(kol);
    }

   // public void zrobDF(){ // towrzy i dodaje kolejne kolumny do df
     //   for (int i=0;i<tablica_nazw.length;i++){
       //     Kolumna kol = new Kolumna(tablica_nazw[i],tabica_typow[i]);
        //    kolumny.add(kol);
        //}
    }

    public int size(){
        Kolumna tmp = kolumny.get(0);
        return tmp.kolumna.size();
    }

    public ArrayList get(String colname){//zwracającą kolumnę o podanej nazwie
        int indeks=0;
        int licznik = 0;
        for (Kolumna x: kolumny){
            if (x.nazwa == colname){
                indeks = licznik;
            }
            else{licznik++;}
        }
        Kolumna tmp  = kolumny.get(indeks);
        return tmp.kolumna;
    }

    public  DataFrame iloc(int i){ //zwracającą wiersz o podanym indeksie (jako nową DataFrame)
        int licznik =0;
        DataFrame nowa =  new DataFrame();
        for (Kolumna j : kolumny){
           Object tmp = j.kolumna.get(i);
           nowa.tablica_nazw[licznik]=tmp.toString();
           licznik++;
        }
        for (int m=0;m<nowa.tablica_nazw.length;m++){
            Kolumna kol = new Kolumna(nowa.tablica_nazw[m],tabica_typow[m]);
            nowa.kolumny.add(kol);
        }
        return nowa;

    }

    public DataFrame iloc(int from,int to){ //zwracającą nową DataFrame z wierszami z podanego zakresu
        DataFrame nowa =  new DataFrame();
        for (int i=from;i<=to;i++){
           DataFrame tmp = iloc(i);
           nowa.kolumny.addAll(tmp.kolumny);
        }
        return nowa;
    }
}




