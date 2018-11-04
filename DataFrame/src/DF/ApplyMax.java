package DF;

import DF.Values.Value;

public class ApplyMax implements Applyable {


    @Override
    public DataFrame apply(DataFrame df) {
        DataFrame output = new DataFrame(df.lista_nazw,df.zwroc_typy());
         int size = df.size();

         if(size>0){
             Value[] max = df.zwrocWiersz(0);
             for (int i=0; i<size;i++){
                 Value[] row = df.zwrocWiersz(i);
                 for (int kolumna=0; kolumna<row.length;kolumna++){
                     try{
                         if(max[kolumna].lte(row[kolumna])){
                            max[kolumna]=row[kolumna];
                         }

                     }
                     catch (UnsupportedOperationException ignored){};
                 }

             }
            output.dodajElement(max);

         }
        return output;
    }
}
