package DF;

import DF.Values.Value;

public class ApplyMin implements Applyable {


    @Override
    public DataFrame apply(DataFrame df) {
        DataFrame output = new DataFrame(df.lista_nazw,df.zwroc_typy());
        int size = df.size();

        if(size>0){
            Value[] min = df.zwrocWiersz(0);
            for (int i=0; i<size;i++){
                Value[] row = df.zwrocWiersz(i);
                for (int kolumna=0; kolumna<row.length;kolumna++){
                    try{
                        if(min[kolumna].gte(row[kolumna])){
                            min[kolumna]=row[kolumna];
                        }

                    }
                    catch (UnsupportedOperationException ignored){};
                }

            }
            output.dodajElement(min);

        }
        return output;
    }
}