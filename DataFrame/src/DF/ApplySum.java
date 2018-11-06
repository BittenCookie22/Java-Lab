package DF;

import DF.Values.Value;

import java.util.ArrayList;

public class ApplySum implements Applyable {

    @Override
    public DataFrame apply(DataFrame df) {


        //logika odpowiedzialna za pracowanie w fukcji min tylko na tych kolumnach na których można wykonać daną operacje
        ArrayList<String> colnames = new ArrayList();
        ArrayList<Class<? extends Value>> types = new ArrayList<>();

        Class<? extends Value> []df_types = df.zwroc_typy();
        String[] df_names = df.lista_nazw;

        for (int i = 0; i < df_types.length ; i++) {
            if (NumericValue.class.isAssignableFrom(df_types[i])){
                types.add(df_types[i]);
                colnames.add(df_names[i]);
            }
        }

        DataFrame output = new DataFrame((String [] )colnames.toArray(), (Class<? extends Value>[])types.toArray());

        int size = df.size();
        Value[] sum = df.zwrocWiersz(0);
        String[]output_colnames=(String [] )colnames.toArray();

        if (size > 0) {
            for (int i = 0; i < output_colnames.length; i++) {
                Kolumna kol = output.get(output_colnames[i]);
                    Value suma = kol.zwrocObiekt(0);
                    for (int j = 1; j < kol.dane.size(); j++) {
                        suma = suma.add(kol.zwrocObiekt(j));
                    }
                    sum[i]=suma;
            }
            output.addRecord(sum);
        }
        return output;
    }
}
