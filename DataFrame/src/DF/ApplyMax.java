package DF;

import DF.Values.Value;

import java.util.HashSet;

public class ApplyMax implements Applyable {


    @Override
    public DataFrame apply(DataFrame df) {
        DataFrame output = new DataFrame(df.lista_nazw,df.zwroc_typy());
        int size = df.size();
        HashSet<Integer> bannedColumns = new HashSet<>();


         if(size>0) {
             Value[] max = df.zwrocWiersz(0);
             for (int i = 0; i < size; i++) {
                 Value[] row = df.zwrocWiersz(i);
                 for (int kolumna = 0; kolumna < row.length; kolumna++) {

                     if (bannedColumns.contains(kolumna)) {continue;}

                     try {
                         if (max[kolumna].lte(row[kolumna])) {
                             max[kolumna] = row[kolumna];
                         }

                     } catch (UnsupportedOperationException ignored) {
                         bannedColumns.add(kolumna);
                     }
                 }

             }
             output.dodajElement(max);
         }

             String []  output_colnames = output.lista_nazw;
             String [] colnames = new String [output_colnames.length-bannedColumns.size()];

             if(bannedColumns.size() == output_colnames.length ){throw new RuntimeException("nope");}

             for (int i = 0; i < output_colnames.length; i++) {
                 if(!bannedColumns.contains(i)){
                     colnames[i]=output_colnames[i];
                 }
             }

        return output.get(colnames,false);
    }




}
