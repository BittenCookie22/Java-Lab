package DF;

import DF.Values.DoubleValue;
import DF.Values.Value;

public class ApplyVar  implements Applyable{

    @Override
    public DataFrame apply(DataFrame df){
        DataFrame output = new DataFrame(df.lista_nazw,df.zwroc_typy());

        int size = df.size();
        Value[] var = df.zwrocWiersz(0);
        Applyable a =new ApplyMean();


        DataFrame mean = a.apply(df);
        //-----wyliczenie średniej--------
        Value[] srednie = mean.zwrocWiersz(0);

        if (size>0) {
            for (int i = 0; i < size; i++) {
                Kolumna kol = df.get(df.lista_nazw[i]);
                if (NumericValue.class.isAssignableFrom(kol.typ)) {
                    Value suma = srednie[i];
                    for (int j = 1; j < kol.size(); j++) {
                        suma = suma.add(kol.dane.get(j));
                    }
                    srednie[i] = suma.div(new DoubleValue(size));
                }
            }
        }

        if(size>0){
            for (int i =0; i<df.kolumny.length;i++){
                Kolumna kol =  df.get(df.lista_nazw[i]);
                if (NumericValue.class.isAssignableFrom(kol.typ)){
                    Value roznica;
                    Value suma= new DoubleValue(0.0); //???
                    for(int j=0;j<kol.size();j++){
                        roznica = kol.zwrocObiekt(j).sub(srednie[i]).mul(kol.zwrocObiekt(j).sub(mean[i]));
                        suma = suma.add(roznica);
                    }
                    var[i] = suma.div(new DoubleValue((size)));
                }
            }

            output.dodajElement(var);
        }
        return output;
    }


}
