package DF;

import DF.Values.DoubleValue;
import DF.Values.Value;

import java.util.ArrayList;

public class ApplyMean implements Applyable  {

    @Override
    public DataFrame apply(DataFrame df) {

        //logika odpowiedzialna za pracowanie w fukcji mean tylko na tych kolumnach na których można wykonać daną operacje
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

        int size = output.iloscKolumn();
        Value[] srednie = df.zwrocWiersz(0);

        if (size>0) {
            for (int i = 0; i < size; i++) {
                Kolumna kol = df.get(df.lista_nazw[i]);
                    Value suma = srednie[i];
                    for (int j = 1 ;j < kol.size(); j++) {
                        suma = suma.add(kol.zwrocObiekt(j));
                    }
                    srednie[i] = suma.div(new DoubleValue(output.size()));
            }
            output.dodajElement(srednie);
        }

        return output;
    }

}
