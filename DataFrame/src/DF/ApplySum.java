package DF;

import DF.Values.DoubleValue;
import DF.Values.NumericValue;
import DF.Values.Value;

import java.util.ArrayList;

public class ApplySum implements Applyable {

    @Override
    public DataFrame apply(DataFrame df) {


        //logika odpowiedzialna za pracowanie w fukcji min tylko na tych kolumnach na których można wykonać daną operacje
        ArrayList<String> colnames = new ArrayList();
        ArrayList<Class<? extends Value>> types = new ArrayList<>();

        Class<? extends Value> []df_types = df.zwroc_typy();
        String[] df_names = df.zwroc_nazwy();

        for (int i = 0; i < df_types.length ; i++) {
            if (NumericValue.class.isAssignableFrom(df_types[i])){
                types.add(df_types[i]);
                colnames.add(df_names[i]);
            }
        }

        DataFrame output = new DataFrame(colnames.toArray(new String[0]),types.toArray(new Class[0]));

        int size = df.size();
        Value[] sum = new Value[output.iloscKolumn()];

        if (size > 0) {
            String[] lista_nazw = output.zwroc_nazwy();
            for (int i = 0; i < output.iloscKolumn(); i++) {
                Kolumna kol = df.get(lista_nazw[i]);
                    Value suma = new DoubleValue(0.0);
                    for (int j = 0; j < kol.size(); j++) {
                        suma = suma.add(kol.zwrocObiekt(j));
                    }
                    sum[i]=suma;
            }
            output.addRecord(sum);
        }
        return output;
    }
}
