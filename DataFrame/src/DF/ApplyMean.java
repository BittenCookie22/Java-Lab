package DF;

import DF.Values.DoubleValue;
import DF.Values.Value;

public class ApplyMean implements Applyable  {


    @Override
    public DataFrame apply(DataFrame df) {
        DataFrame output = new DataFrame(df.lista_nazw,df.zwroc_typy());

        int size = df.size();
        Value[] srednie = df.zwrocWiersz(0);

        if (size>0) {
            for (int i = 0; i < size; i++) {
                Kolumna kol = df.get(df.lista_nazw[i]);
                if (NumericValue.class.isAssignableFrom(kol.typ)) {
                    Value suma = srednie[i];
                    for (int j = 0; j < kol.size(); j++) {
                        suma = suma.add(kol.dane.get(j));
                    }
                    srednie[i] = suma.div(new DoubleValue(size));

                }

            }
            output.dodajElement(srednie);
        }

        return output;
    }

}
