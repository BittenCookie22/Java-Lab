package DF;

import DF.Values.DoubleValue;
import DF.Values.NumericValue;
import DF.Values.Value;

public class ApplyVar  implements Applyable{

    @Override
    public DataFrame apply(DataFrame df){


        Applyable a = new ApplyMean();
        DataFrame mean = a.apply(df);

        Value[] srednie = mean.zwrocWiersz(0);

        DataFrame output = new DataFrame(mean.zwroc_nazwy(), mean.zwroc_typy());

        Value[] var = new Value[output.iloscKolumn()];
        int size = mean.size();


        if(df.ilosc_wierszy!=1) {

            if (size > 0) {
                String[] lista_nazw = mean.zwroc_nazwy();
                for (int i = 0; i < mean.iloscKolumn(); i++) {
                    Kolumna kol = df.get(lista_nazw[i]);
                    Value roznica;
                    Value suma = new DoubleValue(0.0);
                    for (int j = 0; j < kol.size(); j++) {
                        roznica = (new DoubleValue(((NumericValue) kol.zwrocObiekt(j)).getValue().doubleValue())).sub(srednie[i]).pow(new DoubleValue(2.0));
                        suma = suma.add(roznica);
                    }
                    var[i] = suma.div(new DoubleValue((df.size()) - 1));

                }

                output.dodajElement(var);
            }

        }
        else{
            for (int i = 0; i < var.length; i++) {
                var[i]=new DoubleValue(0.0);

            }
            output.dodajElement(var);
        }
        return output;
    }


}
