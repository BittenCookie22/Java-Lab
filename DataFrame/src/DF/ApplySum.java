package DF;

import DF.Values.Value;

public class ApplySum implements Applyable {

    @Override
    public DataFrame apply(DataFrame df) {
        DataFrame output = new DataFrame(df.lista_nazw, df.zwroc_typy());

        int size = df.size();
        Value[] sum = df.zwrocWiersz(0);

        if (size > 0) {
            for (int i = 0; i < df.kolumny.length; i++) {
                Kolumna kol = df.get(df.lista_nazw[i]);
                if (NumericValue.class.isAssignableFrom(kol.typ)) {
                    Value suma = kol.dane.get(i);
                    for (int j = 1; j < kol.dane.size(); j++) {
                        suma = suma.add(kol.dane.get(j));
                    }
                    sum[i]=suma;
                }

            }
            output.addRecord(sum);
        }
        return output;
    }
}
